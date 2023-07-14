package com.yelldev.testapiapp.data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MeteorData(
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double,
    @SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachData>,
    @SerializedName("estimated_diameter")
    
    val estimatedDiameter: EstimatedDiameter,
    @SerializedName("id")
    
    val id: String,
    @SerializedName("is_potentially_hazardous_asteroid")
    
    val isPotentiallyHazardousAsteroid: Boolean,
    @SerializedName("is_sentry_object")
    
    val isSentryObject: Boolean,
    @SerializedName("links")
    
    val links: Links,
    @SerializedName("name")
    
    val name: String,
    @SerializedName("nasa_jpl_url")
    
    val nasaJplUrl: String,
    @SerializedName("neo_reference_id")
    
    val neoReferenceId: String
)

