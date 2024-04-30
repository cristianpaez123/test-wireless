package com.example.testwireless.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Name(
    val common:String = String(),
    val official:String = String()
): Serializable