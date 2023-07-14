package com.yelldev.testapiapp.data

import com.google.gson.annotations.SerializedName

data class ItemData(
	@SerializedName("id")
	val id: Int,
	@SerializedName("name")
	val name: String
)