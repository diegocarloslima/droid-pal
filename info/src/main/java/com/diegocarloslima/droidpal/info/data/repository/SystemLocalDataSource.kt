package com.diegocarloslima.droidpal.info.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.diegocarloslima.droidpal.base.util.runOnApiAtLeast
import com.diegocarloslima.droidpal.base.util.runOnApiBelow
import com.diegocarloslima.droidpal.info.R
import com.diegocarloslima.droidpal.info.data.model.AndroidMainInfo
import com.diegocarloslima.droidpal.info.data.model.InfoItem
import com.diegocarloslima.droidpal.info.data.util.addInfoItem
import com.diegocarloslima.droidpal.info.data.util.releaseDateForApi
import com.diegocarloslima.droidpal.info.data.util.versionNameForApi
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GoogleApiAvailability
import com.scottyab.rootbeer.RootBeer
import javax.inject.Inject

class SystemLocalDataSource @Inject constructor(context: Context) {

    val androidMainInfo: AndroidMainInfo = loadAndroidMainInfo(context)

    val androidItems: List<InfoItem> = loadAndroidItems(context)

    private fun loadAndroidMainInfo(context: Context): AndroidMainInfo {
        val api = Build.VERSION.SDK_INT
        val versionName = versionNameForApi(api)
        val releaseDate = releaseDateForApi(api)
        val rooted = RootBeer(context).isRootedWithoutBusyBoxCheck
        return AndroidMainInfo(api, versionName, releaseDate, rooted)
    }

    private fun loadAndroidItems(context: Context): List<InfoItem> {
        val items = mutableListOf<InfoItem>()

        items.addInfoItem(context, R.string.system_api_level, Build.VERSION.SDK_INT.toString())

        runOnApiAtLeast(Build.VERSION_CODES.M) {
            val devPreview = if (Build.VERSION.PREVIEW_SDK_INT == 0) R.string.value_yes else R.string.value_no
            items.addInfoItem(context, R.string.system_dev_preview, devPreview)
        }

        val codename = Build.VERSION.CODENAME
        items.addInfoItem(context, R.string.system_codename, codename)

        val relBuild = if (codename == "REL") R.string.value_yes else R.string.value_no
        items.addInfoItem(context, R.string.system_rel_build, relBuild)

        runOnApiBelow(Build.VERSION_CODES.O) {
            items.addInfoItem(context, R.string.system_android_id, androidId(context))
        }

        items.addInfoItem(context, R.string.system_gsf_id, gsfId(context))

        try {
            val pm = context.packageManager
            val version = pm.getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0).versionName
            items.addInfoItem(context, R.string.system_play_services_version, version)
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        // TODO: needs to be called in bg thread
        val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(context)
        items.addInfoItem(context, R.string.system_advertiser_id, advertisingId.id)

        val adDnt = if (advertisingId.isLimitAdTrackingEnabled) R.string.value_yes else R.string.value_no
        items.addInfoItem(context, R.string.system_ad_dnt, adDnt)

        return items
    }

    @SuppressLint("HardwareIds")
    private fun androidId(context: Context) =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    private fun gsfId(context: Context): String {
        // TODO: check security exception
        val uri = Uri.parse("content://com.google.android.gsf.gservices")
        try {
            context.contentResolver.query(uri, null, null, arrayOf("android_id"), null).use {
                if(it != null && it.moveToFirst() && it.columnCount >= 2) {
                    it.getString(1).toLong().toString(16) // TODO: check if uppercase is needed
                }
            }
        } catch (ignored: SecurityException) {
        }
        return ""
    }
}