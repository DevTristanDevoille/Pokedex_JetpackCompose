package fr.tristandevoille.pokedexcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity (
    @PrimaryKey
    val id : Long,
    val name : String,
    val baseExperience : String?,
    val height : String,
    val isDefault : Boolean,
    val locationAreaEncounters : String,
    val order : Long,
    val weight : Long
)