package fr.tristandevoille.pokedexcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.tristandevoille.pokedexcompose.data.local.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemonentity ORDER BY id")
    suspend fun getPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(pokemonEntity : PokemonEntity)

    @Query("DELETE FROM pokemonentity")
    suspend fun nuke()
}