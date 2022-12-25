package com.vulture.superradio.di

import com.vulture.superradio.data.api.StationsApi
import com.vulture.superradio.data.repository.player.PlayerRepository
import com.vulture.superradio.data.repository.player.PlayerRepositoryImpl
import com.vulture.superradio.data.repository.station.StationRepository
import com.vulture.superradio.data.repository.station.StationRepositoryImpl
import com.vulture.superradio.service.music.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideStationsRepository(
        api: StationsApi,
    ): StationRepository {
        return StationRepositoryImpl(api)
    }

    @Provides
    fun providePlayerRepository(musicServiceConnection: MusicServiceConnection): PlayerRepository {
        return PlayerRepositoryImpl(musicServiceConnection)
    }

}