package fr.tristandevoille.pokedexcompose.commons.helpers

import kotlinx.coroutines.flow.SharedFlow

interface NavigationHelper {
    val sharedFlow : SharedFlow<NavigationEvent>

    fun navigateTo(route: Destination, popupTo : String? = null)

    fun goBack()

    sealed class NavigationEvent {
        data class NavigateTo(val destination: Destination, val popupTo : String? = null) : NavigationEvent()
        data object GoBack : NavigationEvent()
    }


    sealed class Destination(val route: String) {
        data object Characters : Destination("pokemons")
        data class Pokemon(val id : String) : Destination("pokemon/$id")
    }
}