package com.test.babylon.data.model
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post (
	@SerializedName("userId")
	@Expose
	val userId : Int,
	@SerializedName("id")
	@Expose
	val id : Int,
	@SerializedName("title")
	@Expose
	val title : String,
	@SerializedName("body")
	@Expose
	val body : String
):Parcelable