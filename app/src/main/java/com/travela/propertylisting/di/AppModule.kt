package com.travela.propertylisting.di

import android.content.Context
import com.travela.propertylisting.data.repository.PropertyRepositoryImpl
import com.travela.propertylisting.datamodel.repository.PropertyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(@ApplicationContext application: Context): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideNotificationQueueRepository(propertyRepoImpl: PropertyRepositoryImpl): PropertyRepository =
        propertyRepoImpl

}