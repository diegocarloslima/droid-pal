package com.diegocarloslima.droidpal.inject

import com.diegocarloslima.droidpal.DroidPalApplication
import com.diegocarloslima.droidpal.info.ui.inject.InfoViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        InfoViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<DroidPalApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<DroidPalApplication>
}