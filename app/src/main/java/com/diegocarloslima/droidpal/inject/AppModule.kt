package com.diegocarloslima.droidpal.inject

import android.content.Context
import com.diegocarloslima.droidpal.DroidPalApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: DroidPalApplication): Context = application.applicationContext
}