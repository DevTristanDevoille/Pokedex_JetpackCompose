package fr.tristandevoille.pokedexcompose.domain.usecases

import fr.tristandevoille.pokedexcompose.R
import fr.tristandevoille.pokedexcompose.commons.helpers.ResourcesHelper
import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel
import fr.tristandevoille.pokedexcompose.domain.models.Resource
import fr.tristandevoille.pokedexcompose.domain.repositories.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get
import retrofit2.HttpException
import java.net.UnknownHostException

class GetPokemonListUseCase (
    private val pokemonRepository: PokemonRepository = get(PokemonRepository::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java)
){
    suspend operator fun invoke() : Resource<List<PokemonListModel>> = withContext(ioDispatcher) {
        try {
            val characters = pokemonRepository.getPokemonList()
            Resource.Success(characters)
        }
        catch (e : UnknownHostException){
            Resource.Error(resourcesHelper.getString(R.string.error_network))
        }
        catch (e : HttpException){
            Resource.Error(resourcesHelper.getString(R.string.error_occured_code, e.code()))
        }
        catch (e : Exception){
            Resource.Error(resourcesHelper.getString(R.string.error_occured))
        }
    }
}