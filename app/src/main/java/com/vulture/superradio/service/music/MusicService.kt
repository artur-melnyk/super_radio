package com.vulture.superradio.service.music

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.ui.PlayerNotificationManager.NotificationListener
import com.vulture.superradio.Constants
import com.vulture.superradio.R
import com.vulture.superradio.data.repository.station.StationRepository
import com.vulture.superradio.ui.models.Station
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MusicService() : Service() {

    @Inject
    lateinit var player: ExoPlayer

    @Inject
    lateinit var musicServiceConnection: MusicServiceConnection


    private lateinit var notificationManager: PlayerNotificationManager

    private val scope = CoroutineScope(Dispatchers.Main)

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            scope.launch {
                musicServiceConnection.emitEvent(MusicIsPlaying(isPlaying))
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        observeMusicServiceEvents()
        player.addListener(playerListener)
    }

    override fun onDestroy() {
        scope.cancel()
        notificationManager.setPlayer(null)
        player.release()

        super.onDestroy()
    }

    private fun observeMusicServiceEvents() {
        scope.launch {
            musicServiceConnection.events.collect { event ->
                when (event) {
                    is MusicPlay -> play(event.station)
                    is MusicStop -> {
                        player.stop()
                    }
                    else -> {
                        //nothing
                    }
                }
            }
        }
    }

    private fun play(station: Station) {
        player.stop()
        prepareNotification(station)

        val mediaItem: MediaItem = MediaItem.fromUri(station.audioSourceUrl)
        player.setMediaItem(mediaItem)
        player.playWhenReady = true
        player.prepare()
    }


    private fun prepareNotification(station: Station) {
        val mediaDescriptionAdapter = object : PlayerNotificationManager.MediaDescriptionAdapter {
            override fun getCurrentContentTitle(player: Player): CharSequence {
                return station.name
            }

            override fun createCurrentContentIntent(player: Player): PendingIntent? {
                /**
                 * Build intent for activity you wan to open on notification tap
                 */
                return null
            }

            override fun getCurrentContentText(player: Player): CharSequence {
                return station.genre
            }

            override fun getCurrentLargeIcon(
                player: Player, callback: PlayerNotificationManager.BitmapCallback
            ): Bitmap? {
                /** Load image using Coil and trigger callback with loaded bitmap
                 * if wanna show custom image on notification
                 */
                return null
            }
        }

        val notificationListener = object : NotificationListener {
            override fun onNotificationPosted(
                notificationId: Int, notification: Notification, ongoing: Boolean
            ) {
                startForeground(notificationId, notification)
            }
        }

        notificationManager = PlayerNotificationManager.Builder(
            this@MusicService,
            Constants.MUSIC_SERVICE_FOREGROUND_ID,
            Constants.NOTIFICATIONS_CHANNEL_ID
        ).setMediaDescriptionAdapter(mediaDescriptionAdapter)
            .setNotificationListener(notificationListener).build()

        notificationManager.setPlayer(player)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            Constants.NOTIFICATIONS_CHANNEL_ID,
            getString(R.string.app_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
    }
}