package fr.tristandevoille.pokedexcompose.data.remote

import fr.tristandevoille.pokedexcompose.data.remote.dto.BaseDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.PokemonDtoDown
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemons(@Query("offset") offset : Int): BaseDtoDown<PokemonDtoDown>

}