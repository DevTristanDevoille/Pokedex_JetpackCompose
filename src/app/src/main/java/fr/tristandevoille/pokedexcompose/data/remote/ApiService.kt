package fr.tristandevoille.pokedexcompose.data.remote

import fr.tristandevoille.pokedexcompose.data.remote.dto.BaseDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.PokemonDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.PokemonsDtoDown
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon?limit=10000")
    suspend fun getPokemons(): BaseDtoDown<PokemonsDtoDown>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id")id : Int) : PokemonDtoDown

}