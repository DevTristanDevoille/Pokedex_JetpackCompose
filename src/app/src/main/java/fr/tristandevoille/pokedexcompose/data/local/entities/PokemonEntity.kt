package fr.tristandevoille.pokedexcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity (
    @PrimaryKey
    val id : Long,
    val name : String,
    val url : String
)