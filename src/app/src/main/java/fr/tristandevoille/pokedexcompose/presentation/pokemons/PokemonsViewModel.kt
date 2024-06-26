package fr.tristandevoille.pokedexcompose.presentation.pokemons

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.tristandevoille.pokedexcompose.R
import fr.tristandevoille.pokedexcompose.commons.helpers.ErrorHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.NavigationHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.ResourcesHelper
import fr.tristandevoille.pokedexcompose.domain.models.Resource
import fr.tristandevoille.pokedexcompose.domain.usecases.GetPokemonListUseCase
import fr.tristandevoille.pokedexcompose.domain.usecases.GetTypeListUseCase
import fr.tristandevoille.pokedexcompose.domain.usecases.SynchronizePokemonsUseCase
import fr.tristandevoille.pokedexcompose.domain.usecases.SynchronizeTypesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class PokemonsViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase = get(GetPokemonListUseCase::class.java),
    private val getTypeListUseCase: GetTypeListUseCase = get(GetTypeListUseCase::class.java),
    private val synchronizePokemonsUseCase: SynchronizePokemonsUseCase = get(SynchronizePokemonsUseCase::class.java),
    private val synchronizeTypesUseCase: SynchronizeTypesUseCase = get(SynchronizeTypesUseCase::class.java),
    private val navigationHelper: NavigationHelper = get(NavigationHelper::class.java),
    private val errorHelper: ErrorHelper = get(ErrorHelper::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java),
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonsUiState())
    val uiState: StateFlow<PokemonsUiState> = _uiState.asStateFlow()

    fun onEvent(event: PokemonsEvent){
        when(event){
            is PokemonsEvent.LoadPokemons ->{
                loadTypes()
            }
            is PokemonsEvent.OnQueryChanged -> {
                _uiState.update { it.copy(query = event.query) }
            }
            is PokemonsEvent.OnPokemonSelected -> {
                navigationHelper.navigateTo(NavigationHelper.Destination.Pokemon(id = event.pokemon.id.toString()))
            }
        }
    }

    private fun loadTypes(){
        viewModelScope.launch{
            _uiState.update { it.copy(loading = true, loadingMessage = resourcesHelper.getString(R.string.loading_types)) }

            when(val result = synchronizeTypesUseCase()){
                is Resource.Success -> {
                    _uiState.update { it.copy(loading = true, loadingMessage = resourcesHelper.getString(R.string.loading_pokemons)) }
                    loadPokemons()
                }
                is Resource.Error -> {
                    errorHelper.showError(result.message)
                    _uiState.update { it.copy(loading = false) }
                }
            }

        }
    }

    private suspend fun loadPokemons(){
        when(val result = synchronizePokemonsUseCase()){
            is Resource.Success -> {
                getPokemons()
            }
            is Resource.Error -> {
                errorHelper.showError(result.message)
                _uiState.update { it.copy(loading = false) }
            }
        }
    }

    private suspend fun getPokemons(){
        when(val result = getPokemonListUseCase()){
            is Resource.Success -> {
                _uiState.update { it.copy(loading = false, pokemons = result.data) }
            }
            is Resource.Error -> {
                errorHelper.showError(result.message)
                _uiState.update { it.copy(loading = false) }
            }
        }
    }

}