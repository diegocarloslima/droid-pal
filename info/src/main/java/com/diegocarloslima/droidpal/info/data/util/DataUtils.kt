package com.diegocarloslima.droidpal.info.data.util

import android.content.Context
import androidx.annotation.StringRes
import com.diegocarloslima.droidpal.base.util.supportsApi21
import com.diegocarloslima.droidpal.base.util.supportsApi24
import com.diegocarloslima.droidpal.info.R
import com.diegocarloslima.droidpal.info.data.model.InfoItem

internal val Context.currentLocale
    get() = if (supportsApi24()) resources.configuration.locales[0]
    else resources.configuration.locale


internal fun Any.yesStrResIf(expectedValue: Any) = if (this == expectedValue) R.string.value_yes else R.string.value_no

internal fun Any.noStrResIf(expectedValue: Any) = if (this == expectedValue) R.string.value_no else R.string.value_yes

internal fun String.isValidValue() = this.isNotBlank() && !this.equals("UNKNOWN", true)

internal fun MutableList<InfoItem>.addInfoItem(context: Context, title: String, value: String) {
    this.addIfValid(InfoItem(title, value))
}

internal fun MutableList<InfoItem>.addInfoItem(context: Context, @StringRes strResId: Int, value: String) {
    this.addIfValid(InfoItem(context.getString(strResId), value))
}

internal fun MutableList<InfoItem>.addInfoItem(context: Context, @StringRes strResId: Int, @StringRes value: Int) {
    this.addIfValid(InfoItem(context.getString(strResId), context.getString(value)))
}

private fun MutableList<InfoItem>.addIfValid(item: InfoItem) {
    if (item.title.isNotBlank() && item.value.isNotBlank() && item.value.isValidValue()) {
        this.add(item)
    }
}