package com.diegocarloslima.droidpal.info.data.repository

import android.content.Context
import android.os.Build
import com.diegocarloslima.droidpal.base.util.runOnApiAtLeast
import com.diegocarloslima.droidpal.info.R
import com.diegocarloslima.droidpal.info.data.model.AndroidMainInfo
import com.diegocarloslima.droidpal.info.data.model.InfoItem
import com.diegocarloslima.droidpal.info.data.util.Constants
import com.diegocarloslima.droidpal.info.data.util.addInfoItem
import com.diegocarloslima.droidpal.info.data.util.releaseDateForApi
import com.diegocarloslima.droidpal.info.data.util.versionNameForApi
import javax.inject.Inject

class SystemLocalDataSource @Inject constructor(context: Context) {

    val androidMainInfo: AndroidMainInfo = loadAndroidMainInfo()

    val androidItems: List<InfoItem> = loadAndroidItems(context.applicationContext)

    private fun loadAndroidMainInfo(): AndroidMainInfo {
        val api = Build.VERSION.SDK_INT
        val versionName = versionNameForApi(api)
        val releaseDate = releaseDateForApi(api)
        val rooted = checkRooted()
        return AndroidMainInfo(api, versionName, releaseDate, rooted)
    }

    private fun loadAndroidItems(context: Context): List<InfoItem> {
        val items = mutableListOf<InfoItem>()
        items.addInfoItem(context, R.string.system_api_level, Build.VERSION.SDK_INT.toString())

        val codename = Build.VERSION.CODENAME
        items.addInfoItem(context, R.string.system_codename, codename)

        runOnApiAtLeast(Build.VERSION_CODES.M) {
            val devPreview = if (Build.VERSION.PREVIEW_SDK_INT == 0) R.string.value_true_yes else R.string.value_false_no
            items.addInfoItem(context, R.string.system_dev_preview, devPreview)
        }

        val relBuild = if(codename == Constants.BUILD_VERSION_CODENAME_REL) R.string.value_true_yes else R.string.value_false_no
        items.addInfoItem(context, R.string.system_rel_build, relBuild)

        return items
    }

    // TODO:
    private fun checkRooted() = false

}