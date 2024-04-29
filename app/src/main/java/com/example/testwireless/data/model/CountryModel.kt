package com.example.testwireless.data.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("name") val name: Name,
    @SerializedName("capital") val capital: List<String>,
    @SerializedName("flags") val flag: FlagModel
)