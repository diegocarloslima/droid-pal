package com.diegocarloslima.droidpal.info.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.diegocarloslima.droidpal.info.data.repository.SystemRepository
import javax.inject.Inject

internal class SystemViewModel @Inject constructor(private val systemRepository: SystemRepository): ViewModel() {

    val test = "Test"

}