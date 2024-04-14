package fr.tristandevoille.pokedexcompose.presentation.pokemons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.tristandevoille.pokedexcompose.R
import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel
import fr.tristandevoille.pokedexcompose.presentation.components.Loader
import fr.tristandevoille.pokedexcompose.presentation.components.PokemonsItem

@Composable
fun PokemonsScreen(uiState : PokemonsUiState, onEvent : (PokemonsEvent) -> Unit){

    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit){
        onEvent(PokemonsEvent.LoadPokemons)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TextField(
            value = uiState.query,
            placeholder = {stringResource(id = R.string.search_pokemon)},
            onValueChange = {onEvent(PokemonsEvent.OnQueryChanged(it))},
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.search), contentDescription = null)
            },
            keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
                .padding(bottom = 4.dp)
        )

        if (uiState.loading){
            Loader(
                modifier = Modifier.fillMaxSize(),
                message = uiState.loadingMessage
            )
        }
        else {
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiState.pokemons.filter {
                    it.name.contains(uiState.query, ignoreCase = true) ||
                    it.id.toString().contains(uiState.query, ignoreCase = true)
                }){ pokemon ->
                    PokemonsItem(
                        modifier = Modifier.fillMaxWidth(),
                        name = pokemon.name,
                        id = pokemon.id
                    ){
                        //onEvent(PokemonsEvent.OnPokemonSelected)
                    }
                    if (pokemon.id == uiState.pokemons.last().id)
                        onEvent(PokemonsEvent.LoadPokemons)
                }
            }
        }
    }
}

@Composable
@Preview
private fun PokemonsScreenPreview2(){
    Surface {
        PokemonsScreen(
            uiState = PokemonsUiState(loading = true),
            onEvent = {}
        )
    }
}

@Composable
@Preview
private fun PokemonsScreenPreview(){
    Surface {
        PokemonsScreen(
            uiState = PokemonsUiState(pokemons = listOf(
                PokemonListModel(
                    id = 1,
                    name = "bulbasaur"
                )
            )),
            onEvent = {}
        )
    }
}