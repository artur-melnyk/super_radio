package com.vulture.superradio.di

import com.vulture.superradio.service.music.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventsModule {

    @Provides
    @Singleton
    fun provideMusicServiceConnection(): MusicServiceConnection = MusicServiceConnection()

}