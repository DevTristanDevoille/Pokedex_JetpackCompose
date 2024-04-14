package fr.tristandevoille.pokedexcompose.domain.usecases

import fr.tristandevoille.pokedexcompose.R
import fr.tristandevoille.pokedexcompose.commons.helpers.ResourcesHelper
import fr.tristandevoille.pokedexcompose.domain.models.Resource
import fr.tristandevoille.pokedexcompose.domain.repositories.PokemonRepository
import fr.tristandevoille.pokedexcompose.domain.repositories.TypeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get
import retrofit2.HttpException
import java.net.UnknownHostException

class SynchronizeTypesUseCase (
    private val typeRepository: TypeRepository = get(TypeRepository::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java)
) {

    suspend operator fun invoke() : Resource<Unit> = withContext(ioDispatcher){
        try {
            typeRepository.synchronizeTypes()
            Resource.Success(Unit)
        }
        catch (e : UnknownHostException){
            Resource.Error(resourcesHelper.getString(R.string.error_network))
        }
        catch (e : HttpException){
            Resource.Error(resourcesHelper.getString(R.string.error_occured_code, e.code()))
        }
        catch (e : Exception){
            e.printStackTrace()
            Resource.Error(resourcesHelper.getString(R.string.error_occured))
        }
    }

}