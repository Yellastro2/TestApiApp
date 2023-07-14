package com.yelldev.testapiapp.data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class RelativeVelocity(
    @SerializedName("kilometers_per_hour")
    val kilometersPerHour: String,
    @SerializedName("kilometers_per_second")
    val kilometersPerSecond: String,
    @SerializedName("miles_per_hour")
    val milesPerHour: String
)