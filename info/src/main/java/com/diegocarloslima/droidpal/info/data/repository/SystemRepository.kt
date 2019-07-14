package com.diegocarloslima.droidpal.info.data.repository

import androidx.lifecycle.LiveData
import com.diegocarloslima.droidpal.info.data.model.AndroidMainInfo
import com.diegocarloslima.droidpal.info.data.model.InfoItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SystemRepository @Inject constructor(localDataSource: SystemLocalDataSource) {

    val androidMainInfo: LiveData<AndroidMainInfo> = localDataSource.androidMainInfo

    val androidItems: LiveData<List<InfoItem>> = localDataSource.androidItems

    val buildItems: LiveData<List<InfoItem>> = localDataSource.buildItems

    val systemItems: LiveData<List<InfoItem>> = localDataSource.systemItems

    val kernelItems: LiveData<List<InfoItem>> = localDataSource.kernelItems
}