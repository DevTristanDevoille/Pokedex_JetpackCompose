package fr.tristandevoille.pokedexcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fr.tristandevoille.pokedexcompose.commons.helpers.ErrorHelper
import fr.tristandevoille.pokedexcompose.commons.helpers.NavigationHelper
import fr.tristandevoille.pokedexcompose.presentation.navigation.navigationGraph
import fr.tristandevoille.pokedexcompose.presentation.theme.PokedexComposeTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class MainActivity(
    private val navigationHelper: NavigationHelper = get(NavigationHelper::class.java),
    private val errorHelper: ErrorHelper = get(ErrorHelper::class.java)
) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {

                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold {
                    LaunchedEffect(Unit) {
                        navigationHelper.sharedFlow.onEach { event ->
                            when(event){
                                is NavigationHelper.NavigationEvent.NavigateTo -> {
                                    navController.navigate(event.destination.route) {
                                        launchSingleTop = true

                                        if(!event.popupTo.isNullOrEmpty())
                                            popUpTo(event.popupTo)
                                    }
                                }
                                is NavigationHelper.NavigationEvent.GoBack -> {
                                    navController.navigateUp()
                                }
                            }
                        }.launchIn(this)
                    }

                    LaunchedEffect(Unit){
                        errorHelper.sharedFlow.onEach { message ->
                            scope.launch {
                                if (snackbarHostState.currentSnackbarData != null)
                                    snackbarHostState.currentSnackbarData!!.dismiss()

                                snackbarHostState.showSnackbar(message, withDismissAction = true)
                            }
                        }.launchIn(this)
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController, startDestination = "pokemons"){
                            navigationGraph()
                        }
                    }
                }
            }
        }
    }
}