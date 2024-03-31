package fr.tristandevoille.pokedexcompose.presentation.pokemons

import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel

data class PokemonsUiState(
    val loading : Boolean = false,
    val loadingMessage : String = "",

    val pokemons : List<PokemonListModel> = emptyList(),

    val query : String = ""
)