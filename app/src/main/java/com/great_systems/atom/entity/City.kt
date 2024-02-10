package com.great_systems.atom.entity

import com.google.gson.annotations.SerializedName

class City (
    @SerializedName("city")
    val city: String,

    @SerializedName("charger")
    val charger: Charger
)