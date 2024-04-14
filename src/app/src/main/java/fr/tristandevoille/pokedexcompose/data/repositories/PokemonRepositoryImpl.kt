package fr.tristandevoille.pokedexcompose.data.repositories

import fr.tristandevoille.pokedexcompose.commons.helpers.PreferencesHelper
import fr.tristandevoille.pokedexcompose.data.local.dao.PokemonDao
import fr.tristandevoille.pokedexcompose.data.local.entities.PokemonEntity
import fr.tristandevoille.pokedexcompose.data.remote.ApiService
import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel
import fr.tristandevoille.pokedexcompose.domain.repositories.PokemonRepository
import org.koin.java.KoinJavaComponent.get

class PokemonRepositoryImpl(
    private val apiService: ApiService = get(ApiService::class.java),
    private val preferencesHelper: PreferencesHelper = get(PreferencesHelper::class.java),
    private val pokemonDao: PokemonDao = get(PokemonDao::class.java)
) : PokemonRepository {

    override suspend fun synchronizePokemons(){

        if (preferencesHelper.pokemonsSynchronized)
            return

        pokemonDao.nuke()

        var index = 1

        val pokemonsDto = apiService.getPokemons()

        while (index < pokemonsDto.results.count()){
            val pokemonDto = apiService.getPokemon(pokemonsDto.results[index++].url.replace("https://pokeapi.co/api/v2/pokemon/","").replace("/","").toInt())

            val pokemonEntity = PokemonEntity(
                id = pokemonDto.id,
                name = pokemonDto.name,
                baseExperience = pokemonDto.baseExperience,
                height = pokemonDto.height,
                order = pokemonDto.order,
                isDefault = pokemonDto.isDefault,
                locationAreaEncounters = pokemonDto.locationAreaEncounters,
                weight = pokemonDto.weight
            )
            pokemonDao.insertOrUpdate(pokemonEntity)
        }
        preferencesHelper.pokemonsSynchronized = true
    }


    override suspend fun getPokemonList(): List<PokemonListModel> {

        val result = pokemonDao.getPokemons()

        return result.map {
            PokemonListModel(
                id = it.id,
                name = it.name,
            )
        }

    }

}