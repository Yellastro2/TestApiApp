package com.yelldev.testapiapp.data

import com.google.gson.annotations.SerializedName

data class Kilometers(

	@SerializedName("estimated_diameter_max")
	val estimatedDiameterMax: Double,

	@SerializedName("estimated_diameter_min")
	val estimatedDiameterMin: Double
)