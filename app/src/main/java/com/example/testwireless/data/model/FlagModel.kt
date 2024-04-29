package com.example.testwireless.data.model

import com.google.gson.annotations.SerializedName

data class FlagModel (
    @SerializedName("svg") val svg : String,
    @SerializedName("png") val png : String
)