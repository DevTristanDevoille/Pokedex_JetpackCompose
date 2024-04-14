package fr.tristandevoille.pokedexcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.tristandevoille.pokedexcompose.data.local.entities.TypeEntity

@Dao
interface TypeDao {

    @Query("SELECT * FROM typeentity ORDER BY id")
    suspend fun getTypes(): List<TypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(typeEntity : TypeEntity)

    @Query("DELETE FROM typeentity")
    suspend fun nuke()
}