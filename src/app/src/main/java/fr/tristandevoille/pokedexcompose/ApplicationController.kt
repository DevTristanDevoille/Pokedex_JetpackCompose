package fr.tristandevoille.pokedexcompose

import android.app.Application
import fr.tristandevoille.pokedexcompose.commons.helpers.ErrorHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.NavigationHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.PreferencesHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.ResourcesHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.implementations.ErrorHelperImpl
import fr.tristandevoille.pokedexcompose.commons.helpers.implementations.NavigationHelperImpl
import fr.tristandevoille.pokedexcompose.commons.helpers.implementations.PreferencesHelperImpl
import fr.tristandevoille.pokedexcompose.commons.helpers.implementations.ResourcesHelperImpl
import fr.tristandevoille.pokedexcompose.data.remote.ApiClient
import fr.tristandevoille.pokedexcompose.data.remote.repositories.PokemonRepositoryImpl
import fr.tristandevoille.pokedexcompose.domain.repositories.PokemonRepository
import fr.tristandevoille.pokedexcompose.domain.usecases.GetPokemonListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApplicationController : Application() {

    /*private val database: ApplicationDatabase by lazy {
        Room.databaseBuilder(this, ApplicationDatabase::class.java, "PokedexCompose")
            .fallbackToDestructiveMigration() // If migrations needed delete all data and clear schema
            .build()
    }*/

    private val applicationModule = module {
        // Helpers
        single<ResourcesHelper> { ResourcesHelperImpl() }
        single<NavigationHelper> { NavigationHelperImpl() }
        single<ErrorHelper> { ErrorHelperImpl() }
        single<PreferencesHelper> { PreferencesHelperImpl() }

        // UseCases
        single { GetPokemonListUseCase() }

        // Repositories
        single<PokemonRepository> { PokemonRepositoryImpl(get(),get()) }

        // Dao

        // Remote
        single { ApiClient.apiService }

        // https://developer.android.com/kotlin/coroutines/coroutines-best-practices?hl=fr
        single<CoroutineDispatcher> { Dispatchers.IO }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin  {
            modules(applicationModule)
            androidContext(this@ApplicationController)
        }
    }
}