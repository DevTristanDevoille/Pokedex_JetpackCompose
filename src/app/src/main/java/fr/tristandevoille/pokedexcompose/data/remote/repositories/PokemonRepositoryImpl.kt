package fr.tristandevoille.pokedexcompose.data.remote.repositories

import fr.tristandevoille.pokedexcompose.commons.helpers.PreferencesHelper
import fr.tristandevoille.pokedexcompose.data.remote.ApiService
import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel
import fr.tristandevoille.pokedexcompose.domain.repositories.PokemonRepository
import org.koin.java.KoinJavaComponent.get

class PokemonRepositoryImpl(
    private val apiService: ApiService = get(ApiService::class.java),
    private val preferencesHelper: PreferencesHelper = get(PreferencesHelper::class.java),
) : PokemonRepository {
    override suspend fun getPokemonList(): List<PokemonListModel> {
        val result = apiService.getCharacters(1)


        val result2 = result.results.map {
            PokemonListModel(
                name = it.name,
                id = it.url.replace("https://pokeapi.co/api/v2/pokemon/","").replace("/","")
            )
        }

        return result2

    }

    override suspend fun synchronizeCharacters() {
        TODO("Not yet implemented")
    }

}