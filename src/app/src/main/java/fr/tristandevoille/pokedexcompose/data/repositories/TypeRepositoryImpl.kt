package fr.tristandevoille.pokedexcompose.data.repositories

import fr.tristandevoille.pokedexcompose.commons.helpers.PreferencesHelper
import fr.tristandevoille.pokedexcompose.data.local.dao.TypeDao
import fr.tristandevoille.pokedexcompose.data.local.entities.TypeEntity
import fr.tristandevoille.pokedexcompose.data.remote.ApiService
import fr.tristandevoille.pokedexcompose.domain.models.TypeListModel
import fr.tristandevoille.pokedexcompose.domain.repositories.TypeRepository
import org.koin.java.KoinJavaComponent.get

class TypeRepositoryImpl (
    private val apiService: ApiService = get(ApiService::class.java),
    private val preferencesHelper: PreferencesHelper = get(PreferencesHelper::class.java),
    private val typeDao: TypeDao = get(TypeDao::class.java)
) : TypeRepository {
    override suspend fun getTypeList(): List<TypeListModel> {

        val result = typeDao.getTypes()

        return result.map {
            TypeListModel(
                id = it.id,
                name = it.name,
            )
        }
    }

    override suspend fun synchronizeTypes() {

        if (preferencesHelper.typesSynchronized)
            return

        typeDao.nuke()

        var index = 0

        val typesDtoDown = apiService.getTypes()

        while (typesDtoDown.count > index){

            val typeDtoDown = apiService.getType(typesDtoDown.results[index++].url.replace("https://pokeapi.co/api/v2/type","").replace("/","").toInt())

            val typeEntity = TypeEntity(
                id = typeDtoDown.id,
                name = typeDtoDown.name
            )

            typeDao.insertOrUpdate(typeEntity)

        }
        preferencesHelper.typesSynchronized = true

    }
}