package fr.tristandevoille.pokedexcompose.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TypeEntity (
    @PrimaryKey
    val id : Long,
    val name : String,
)