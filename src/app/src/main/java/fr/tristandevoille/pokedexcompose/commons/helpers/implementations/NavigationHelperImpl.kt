package fr.tristandevoille.pokedexcompose.commons.helpers.implementations

import fr.tristandevoille.pokedexcompose.commons.helpers.NavigationHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationHelperImpl : NavigationHelper {
    private val _sharedFlow = MutableSharedFlow<NavigationHelper.NavigationEvent>(extraBufferCapacity = 1)
    override val sharedFlow = _sharedFlow.asSharedFlow()

    override fun navigateTo(route: NavigationHelper.Destination, popupTo : String?) {
        _sharedFlow.tryEmit(NavigationHelper.NavigationEvent.NavigateTo(route, popupTo))
    }

    override fun goBack() {
        _sharedFlow.tryEmit(NavigationHelper.NavigationEvent.GoBack)
    }
}