package com.diegocarloslima.droidpal.info.data.util

import android.os.Build
import com.diegocarloslima.droidpal.base.util.VERSION_CODES_Q
import java.text.SimpleDateFormat
import java.util.*

internal val androidApi = Build.VERSION.SDK_INT

internal val apiVersionName = versionNameForApi(androidApi)

internal val apiReleaseDate = releaseDateForApi(androidApi)

private fun versionNameForApi(api: Int) =
    when (api) {
        Build.VERSION_CODES.KITKAT -> formatVersionName("Kitkat")
        Build.VERSION_CODES.KITKAT_WATCH -> formatVersionName("Kitkat wearable")
        Build.VERSION_CODES.LOLLIPOP -> formatVersionName("Lollipop")
        Build.VERSION_CODES.LOLLIPOP_MR1 -> formatVersionName("Lollipop MR1")
        Build.VERSION_CODES.M -> formatVersionName("Marshmallow")
        Build.VERSION_CODES.N -> formatVersionName("Nougat")
        Build.VERSION_CODES.N_MR1 -> formatVersionName("Nougat MR1")
        Build.VERSION_CODES.O -> formatVersionName("Oreo")
        Build.VERSION_CODES.O_MR1 -> formatVersionName("Oreo MR1")
        Build.VERSION_CODES.P -> formatVersionName("Pie")
        VERSION_CODES_Q -> formatVersionName("Q")
        else -> Build.VERSION.RELEASE
    }

private fun formatVersionName(name: String) = "$name (${Build.VERSION.RELEASE})"

// From Wikipedia article: https://en.wikipedia.org/wiki/Android_version_history
private fun releaseDateForApi(api: Int) =
    when (api) {
        Build.VERSION_CODES.KITKAT -> createDate("31-10-2013")
        Build.VERSION_CODES.KITKAT_WATCH -> createDate("25-06-2014")
        Build.VERSION_CODES.LOLLIPOP -> createDate("12-11-2014")
        Build.VERSION_CODES.LOLLIPOP_MR1 -> createDate("09-03-2015")
        Build.VERSION_CODES.M -> createDate("05-10-2015")
        Build.VERSION_CODES.N -> createDate("22-08-2016")
        Build.VERSION_CODES.N_MR1 -> createDate("04-10-2016")
        Build.VERSION_CODES.O -> createDate("21-08-2017")
        Build.VERSION_CODES.O_MR1 -> createDate("05-12-2017")
        Build.VERSION_CODES.P -> createDate("06-08-2018")
        else -> null
    }

private fun createDate(str: String) = SimpleDateFormat("dd-MM-YYYY", Locale.US).parse(str)