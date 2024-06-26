package fr.tristandevoille.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseDtoDown<T>(
    @SerializedName("count")
    val count : Int,

    @SerializedName("next")
    val next : String?,

    @SerializedName("previous")
    val previous : String?,

    @SerializedName("results")
    val results : List<T>,
)