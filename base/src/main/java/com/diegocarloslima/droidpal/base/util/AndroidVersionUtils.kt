package com.diegocarloslima.droidpal.base.util

import android.os.Build

// TODO: Remove when Build.VERSION_CODES.Q is added
const val VERSION_CODES_Q = 29

fun supportsApi21() = supportsApiX(Build.VERSION_CODES.LOLLIPOP)

fun supportsApi22() = supportsApiX(Build.VERSION_CODES.LOLLIPOP_MR1)

fun supportsApi23() = supportsApiX(Build.VERSION_CODES.M)

fun supportsApi24() = supportsApiX(Build.VERSION_CODES.N)

fun supportsApi25() = supportsApiX(Build.VERSION_CODES.N_MR1)

fun supportsApi26() = supportsApiX(Build.VERSION_CODES.O)

fun supportsApi27() = supportsApiX(Build.VERSION_CODES.O_MR1)

fun supportsApi28() = supportsApiX(Build.VERSION_CODES.P)

fun supportsApi29() = supportsApiX(VERSION_CODES_Q)

inline fun runOnApiAtLeast(api: Int, f: () -> Unit) {
    if (supportsApiX(api)) {
        f()
    }
}

inline fun runOnApiBelow(api: Int, f: () -> Unit) {
    if (belowApiX(api)) {
        f()
    }
}

inline fun runOnApiOrElse(api: Int, f: () -> Unit, orElse: () -> Unit) {
    if (supportsApiX(api)) {
        f()
    } else {
        orElse()
    }
}

@PublishedApi
internal fun supportsApiX(api: Int) = Build.VERSION.SDK_INT >= api

@PublishedApi
internal fun belowApiX(api: Int) = Build.VERSION.SDK_INT < api

