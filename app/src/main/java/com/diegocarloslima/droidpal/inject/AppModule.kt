package com.diegocarloslima.droidpal.inject

import android.app.Application
import com.diegocarloslima.droidpal.DroidPalApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApplication(application: DroidPalApplication): Application = application
}