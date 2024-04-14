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

        var offset = 0
        var again = true

        while (again){
            val pokemonsDto = apiService.getPokemons(offset)
            again = pokemonsDto.next != null

            offset += 20

            val pokemonsEntity = pokemonsDto.results.map {
                PokemonEntity(
                    id = it.url.replace("https://pokeapi.co/api/v2/pokemon/","").replace("/","").toLong(),
                    name = it.name,
                    url = it.url
                )
            }
            pokemonDao.insertOrUpdate(pokemonsEntity)
        }
        preferencesHelper.pokemonsSynchronized = true
    }


    override suspend fun getPokemonList(): List<PokemonListModel> {

        val result = pokemonDao.getPokemons()

        return result.map {
            PokemonListModel(
                id = it.id,
                name = it.name,
                url = it.url
            )
        }

    }

}