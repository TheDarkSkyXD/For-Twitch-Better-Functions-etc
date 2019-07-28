package com.github.exact7.xtra.model.kraken.game


import android.os.Parcelable
import com.github.exact7.xtra.model.Preview
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
        @SerializedName("_id")
        val id: Int,
        val box: Preview,
        @SerializedName("giantbomb_id")
        val giantbombId: Int,
        val logo: Preview,
        val name: String,
        val popularity: Int) : Parcelable