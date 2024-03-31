package fr.tristandevoille.pokedexcompose.presentation.pokemons

import fr.tristandevoille.pokedexcompose.domain.models.PokemonListModel

sealed class PokemonsEvent {
    data object LoadPokemons : PokemonsEvent()
    data class OnPokemonSelected(val pokemon : PokemonListModel) : PokemonsEvent()
    data class OnQueryChanged(val query : String) : PokemonsEvent()
}