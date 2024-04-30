package com.example.testwireless.data.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    val name: Name?,
    val capital: List<String>?,
    @SerializedName("flags")
    val flag: FlagModel
)