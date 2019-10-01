package com.test.babylon.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User (

    @SerializedName("id")
    @Expose
    val id : Int,
    @SerializedName("name")
    @Expose
    val name : String,
    @SerializedName("username")
    @Expose
    val username : String
)