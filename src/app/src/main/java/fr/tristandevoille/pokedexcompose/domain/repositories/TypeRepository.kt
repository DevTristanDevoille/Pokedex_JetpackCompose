package fr.tristandevoille.pokedexcompose.domain.repositories

import fr.tristandevoille.pokedexcompose.domain.models.TypeListModel
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.jvm.Throws

interface TypeRepository {
    @Throws(HttpException::class, UnknownHostException::class)
    suspend fun getTypeList(): List<TypeListModel>

    @Throws(HttpException::class, UnknownHostException::class)
    suspend fun synchronizeTypes()
}