package fr.tristandevoille.pokedexcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.tristandevoille.pokedexcompose.data.local.dao.PokemonDao
import fr.tristandevoille.pokedexcompose.data.local.entities.PokemonEntity

@Database(
    entities = [
        PokemonEntity::class
    ],
    version = 1,
)

@TypeConverters(DateConverter::class)
abstract class ApplicationDatabase : RoomDatabase(){
    abstract fun pokemonDao(): PokemonDao
}