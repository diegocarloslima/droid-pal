package com.diegocarloslima.droidpal.info.data.repository

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import com.diegocarloslima.droidpal.base.util.runOnApiAtLeast
import com.diegocarloslima.droidpal.base.util.runOnApiBelow
import com.diegocarloslima.droidpal.info.R
import com.diegocarloslima.droidpal.info.data.model.AndroidMainInfo
import com.diegocarloslima.droidpal.info.data.model.InfoItem
import com.diegocarloslima.droidpal.info.data.util.*
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GoogleApiAvailability
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SystemLocalDataSource @Inject constructor(private val context: Application) {

    val androidMainInfo: MutableLiveData<AndroidMainInfo> = MutableLiveData()

    val androidItems: MutableLiveData<List<InfoItem>> = MutableLiveData()

    val buildItems: MutableLiveData<List<InfoItem>> = MutableLiveData()

    val systemItems: MutableLiveData<List<InfoItem>> = MutableLiveData()

    val kernelItems: MutableLiveData<List<InfoItem>> = MutableLiveData()

    init {
        loadAll()
    }

    private fun loadAll() {
        val job = Job()
        val scope = CoroutineScope(Dispatchers.IO + job)
        scope.launch {
            androidMainInfo.postValue(loadAndroidMainInfo())
            androidItems.postValue(loadAndroidItems())
            buildItems.postValue(loadBuildItems())
            systemItems.postValue(loadSystemItems())
            kernelItems.postValue(loadKernelItems())
        }
    }

    private fun loadAndroidMainInfo(): AndroidMainInfo {
        val api = Build.VERSION.SDK_INT
        val versionName = versionNameForApi(api)
        val releaseDate = releaseDateForApi(api)
        val rooted = RootBeer(context).isRootedWithoutBusyBoxCheck
        return AndroidMainInfo(api, versionName, releaseDate, rooted)
    }

    private fun loadAndroidItems(): List<InfoItem> {
        val items = mutableListOf<InfoItem>()

        items.addInfoItem(context, R.string.system_api_level, Build.VERSION.SDK_INT.toString())

        runOnApiAtLeast(Build.VERSION_CODES.M) {
            items.addInfoItem(context, R.string.system_dev_preview, devPreview())
        }

        items.addInfoItem(context, R.string.system_codename, Build.VERSION.CODENAME)

        items.addInfoItem(context, R.string.system_rel_build, Build.VERSION.CODENAME.yesStrResIf("REL"))

        runOnApiBelow(Build.VERSION_CODES.O) {
            items.addInfoItem(context, R.string.system_android_id, androidId(context))
        }

        items.addInfoItem(context, R.string.system_gsf_id, gsfId(context))

        items.addInfoItem(context, R.string.system_play_services_version, playServicesVersion(context))

        val advertisingId = AdvertisingIdClient.getAdvertisingIdInfo(context)
        items.addInfoItem(context, R.string.system_advertiser_id, advertisingId.id)

        items.addInfoItem(context,
            R.string.system_ad_dnt, advertisingId.isLimitAdTrackingEnabled.yesStrResIf(true))

        return items
    }

    private fun loadBuildItems(): List<InfoItem> {
        val items = mutableListOf<InfoItem>()

        items.addInfoItem(context, R.string.system_build_id, Build.ID)

        items.addInfoItem(context, R.string.system_build_display, Build.DISPLAY)

        items.addInfoItem(context, R.string.system_build_fingerprint, Build.FINGERPRINT)

        items.addInfoItem(context, R.string.system_build_tags, Build.TAGS)

        items.addInfoItem(context, R.string.system_build_incremental, Build.VERSION.INCREMENTAL)

        items.addInfoItem(context, R.string.system_build_time, buildTime(context))

        return items
    }

    private fun loadSystemItems(): List<InfoItem> {
        val items = mutableListOf<InfoItem>()

        items.addInfoItem(context, R.string.system_bootloader, Build.BOOTLOADER)

        items.addInfoItem(context, R.string.system_vm_runtime, vmRuntime())

        items.addInfoItem(context, R.string.system_path, System.getenv("PATH") ?: "")

        items.addInfoItem(context, R.string.system_uptime, systemUptime(context))

        return items
    }

    private fun loadKernelItems(): List<InfoItem> {
        val items = mutableListOf<InfoItem>()

        items.addInfoItem(context, R.string.system_kernel_name, System.getProperty("os.name") ?: "")

        items.addInfoItem(context, R.string.system_kernel_version, System.getProperty("os.version") ?: "")

        items.addInfoItem(context, R.string.system_kernel_arch, System.getProperty("os.arch") ?: "")

        return items
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun devPreview() = Build.VERSION.PREVIEW_SDK_INT.yesStrResIf(0)

    @SuppressLint("HardwareIds")
    private fun androidId(context: Context) =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    private fun gsfId(context: Context): String {
        // TODO: check security exception
        try {
            val uri = Uri.parse("content://com.google.android.gsf.gservices")
            context.contentResolver.query(uri, null, null, arrayOf("android_id"), null).use {
                if (it != null && it.moveToFirst() && it.columnCount >= 2) {
                    return it.getString(1).toLong().toString(16) // TODO: check if uppercase is needed
                }
            }
        } catch (ignored: SecurityException) {
        }
        return ""
    }

    private fun playServicesVersion(context: Context) =
        try {
            context.packageManager.getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }

    private fun buildTime(context: Context): String {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy hh:mm:ss aa", context.currentLocale)
        return dateFormat.format(Date(Build.TIME))
    }

    private fun vmRuntime() = vmDescForVersion(System.getProperty("java.vm.version") ?: "")

    private fun systemUptime(context: Context): String {
        val aDayInSeconds = TimeUnit.DAYS.toSeconds(1)
        val anHourInSeconds = TimeUnit.HOURS.toSeconds(1)
        val aMinuteInSeconds = TimeUnit.MINUTES.toSeconds(1)
        val elapsedTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime())
        val dayCount = elapsedTimeInSeconds / aDayInSeconds
        var remainder = elapsedTimeInSeconds % aDayInSeconds
        val hourCount = remainder / anHourInSeconds
        remainder %= anHourInSeconds
        val minuteCount = remainder / aMinuteInSeconds
        remainder %= aMinuteInSeconds
        val day = StringBuilder()
        if (dayCount > 0) {
            day.append(context.resources.getQuantityString(R.plurals.value_unit_day, dayCount.toInt()))
            day.append(", ")
        }

        return "%s, %02d:%02d:%02d".format(day.toString(), hourCount, minuteCount, remainder)
    }
}