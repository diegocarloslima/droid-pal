package com.diegocarloslima.droidpal.info.data.util

import android.content.Context
import androidx.annotation.StringRes
import com.diegocarloslima.droidpal.info.data.model.InfoItem

internal fun MutableList<InfoItem>.addInfoItem(context: Context, title: String, value: String) {
    this.add(InfoItem(title, value))
}

internal fun MutableList<InfoItem>.addInfoItem(context: Context, @StringRes strResId: Int, value: String) {
    this.add(InfoItem(context.getString(strResId), value))
}

internal fun MutableList<InfoItem>.addInfoItem(context: Context, @StringRes strResId: Int, @StringRes value: Int) {
    this.add(InfoItem(context.getString(strResId), context.getString(value)))
}