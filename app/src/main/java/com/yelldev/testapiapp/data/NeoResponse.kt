package com.yelldev.testapiapp.data
import com.google.gson.annotations.SerializedName

data class NeoResponse(
    @SerializedName("element_count")
    val elementCount: Int,
    @SerializedName("near_earth_objects")
    val nearEarthObjects: Map<String,List<MeteorData>>
)

data class NearEarthObjects(
    @SerializedName("2015-09-08")
    val x20150908: List<MeteorData>
)