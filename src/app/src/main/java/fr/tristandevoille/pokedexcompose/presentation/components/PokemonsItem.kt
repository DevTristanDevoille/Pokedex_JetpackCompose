package fr.tristandevoille.pokedexcompose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PokemonsItem(
    modifier : Modifier = Modifier,
    name: String,
    id: String,
    onClick : () -> Unit = { }
){
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(120.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png")
                .crossfade(true)
                .build(),
            contentDescription = "Character Image",
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Preview
@Composable
private fun PokemonsItemPreview() = PreviewComponents {
    PokemonsItem(
        modifier = Modifier.fillMaxWidth(),
        name = "Bulbasaur",
        id = "1",
    )
}