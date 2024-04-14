package fr.tristandevoille.pokedexcompose.data.remote

import fr.tristandevoille.pokedexcompose.data.remote.dto.BaseDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.PokemonDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.PokemonsDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.TypeDtoDown
import fr.tristandevoille.pokedexcompose.data.remote.dto.TypesDtoDown
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemons(@Query("offset") offset : Int,@Query("limit") limit : Int): BaseDtoDown<PokemonsDtoDown>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id")id : Int) : PokemonDtoDown

    @GET("type")
    suspend fun getTypes() : BaseDtoDown<TypesDtoDown>

    @GET("type/{id}")
    suspend fun getType(@Path("id") id : Int) : TypeDtoDown

}