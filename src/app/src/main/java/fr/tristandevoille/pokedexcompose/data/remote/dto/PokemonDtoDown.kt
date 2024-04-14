package fr.tristandevoille.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

class PokemonDtoDown (
    val id : Long,
    val name : String,
    @SerializedName("base_experience")
    val baseExperience : String?,
    val height : String,
    @SerializedName("is_default")
    val isDefault : Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters : String,
    val order : Long,
    val weight : Long
)