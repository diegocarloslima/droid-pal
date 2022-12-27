package com.diegocarloslima.droidpal.info.ui.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegocarloslima.droidpal.base.ui.inject.ViewModelKey
import com.diegocarloslima.droidpal.info.ui.viewmodel.InfoViewModelFactory
import com.diegocarloslima.droidpal.info.ui.viewmodel.SystemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class InfoViewModelModule {

    @Binds
    abstract fun bindInfoViewModelFactory(factory: InfoViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SystemViewModel::class)
    internal abstract fun systemViewModel(viewModel: SystemViewModel): ViewModel
}