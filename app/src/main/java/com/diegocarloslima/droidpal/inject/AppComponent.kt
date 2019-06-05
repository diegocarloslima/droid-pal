package com.diegocarloslima.droidpal.inject

import com.diegocarloslima.droidpal.DroidPalApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<DroidPalApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DroidPalApplication>()
}