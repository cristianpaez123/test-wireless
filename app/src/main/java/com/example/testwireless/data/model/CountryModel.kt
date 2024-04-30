package com.example.testwireless.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryModel(
    val name: Name?,
    val capital: List<String>?,
    @SerializedName("flags")
    val flag: FlagModel,
    val region: String?,
    val subregion: String?,
    val area: Double?,
    val fifa: String?,
    @SerializedName("maps")
    val map:MapModel
) : Serializable