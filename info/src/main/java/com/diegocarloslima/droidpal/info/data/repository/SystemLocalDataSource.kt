package com.diegocarloslima.droidpal.info.data.repository

import android.content.Context
import com.diegocarloslima.droidpal.info.data.model.AndroidVersion
import com.diegocarloslima.droidpal.info.data.model.InfoItem
import com.diegocarloslima.droidpal.info.data.util.androidApi
import com.diegocarloslima.droidpal.info.data.util.apiReleaseDate
import com.diegocarloslima.droidpal.info.data.util.apiVersionName
import javax.inject.Inject

class SystemLocalDataSource @Inject constructor(context: Context) {

    val androidVersion: AndroidVersion = AndroidVersion(androidApi, apiVersionName, apiReleaseDate)
    val infoItems: List<InfoItem> = loadInfoItems(context)

    init {

    }

    private fun loadInfoItems(context: Context): List<InfoItem> {
        val infoItems = mutableListOf<InfoItem>()



        // TODO: Q -> Build.Partition

        return infoItems
    }
}