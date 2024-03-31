package fr.tristandevoille.pokedexcompose.domain.repositories

import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.jvm.Throws

interface PokemonRepository {
    @Throws(HttpException::class, UnknownHostException::class)
    suspend fun getPokemonList(): List<PokemonListModel>

    @Throws(HttpException::class, UnknownHostException::class)
    suspend fun synchronizeCharacters()
}