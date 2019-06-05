package com.diegocarloslima.droidpal.info.data.model

data class InfoItem(
    val title: String,
    val value: String,
    val description: String = "",
    val hint: String = ""
)