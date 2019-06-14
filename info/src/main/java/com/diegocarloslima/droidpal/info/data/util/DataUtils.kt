package com.diegocarloslima.droidpal.info.data.util

import android.content.Context
import androidx.annotation.StringRes
import com.diegocarloslima.droidpal.info.data.model.InfoItem

internal fun MutableList<InfoItem>.addInfoItem(context: Context, title: String, value: String) {
    this.addIfValid(InfoItem(title, value))
}

internal fun MutableList<InfoItem>.addInfoItem(context: Context, @StringRes strResId: Int, value: String) {
    this.addIfValid(InfoItem(context.getString(strResId), value))
}

internal fun MutableList<InfoItem>.addInfoItem(context: Context, @StringRes strResId: Int, @StringRes value: Int) {
    this.addIfValid(InfoItem(context.getString(strResId), context.getString(value)))
}

internal fun String.isValidValue() = this.isNotBlank() && !this.equals("UNKNOWN", true)

private fun MutableList<InfoItem>.addIfValid(item: InfoItem) {
    if (item.title.isNotBlank() && item.value.isNotBlank() && item.value.isValidValue()) {
        this.add(item)
    }
}