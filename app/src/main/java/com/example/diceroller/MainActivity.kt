package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

// MainActivity est le point d'entrée principal de l'application.
class MainActivity : ComponentActivity() {
    // La méthode onCreate est appelée lors de la création de l'activité.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Permet à l'application de s'afficher en plein écran (edge-to-edge).
        enableEdgeToEdge()
        // Définit le contenu de l'interface utilisateur avec Jetpack Compose.
        setContent {
            // Applique le thème personnalisé de l'application.
            DiceRollerTheme {
                // Appelle le composant principal qui affiche l'application.
                DiceRollerApp()
            }
        }
    }

    /**
     * Un composant qui affiche une image de dé et un bouton pour le lancer.
     * @param modifier Le modificateur à appliquer à ce composant.
     */
    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
        // Crée une variable d'état 'result' pour stocker le résultat du lancer de dé.
        // 'remember' garde la valeur de 'result' lors des recompositions (mises à jour de l'UI).
        // La valeur initiale est un nombre aléatoire entre 1 et 6.
        var result by remember { mutableIntStateOf((1..6).random()) }

        // Détermine quelle image afficher en fonction de la valeur de 'result'.
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // 'Column' arrange ses éléments enfants verticalement.
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally // Centre les éléments horizontalement.
        ) {
            // Affiche l'image du dé.
            Image(
                painter = painterResource(id = imageResource), // La ressource de l'image à afficher.
                contentDescription = result.toString() // Description pour l'accessibilité.
            )

            // Ajoute un espace vertical de 16 dp entre l'image et le bouton.
            Spacer(modifier = Modifier.height(16.dp))

            // Affiche un bouton cliquable.
            Button(
                // L'action à exécuter lorsque le bouton est cliqué.
                onClick = { result = (1..6).random() }, // Lance le dé et met à jour la variable 'result'.
            ) {
                // Le texte affiché sur le bouton.
                Text(text = stringResource(R.string.roll))
            }
        }
    }

    /**
     * Le composant racine de l'application qui configure la mise en page principale.
     * L'annotation @Preview permet de voir un aperçu de ce composant dans Android Studio.
     */
    @Preview(showBackground = true)
    @Composable
    fun DiceRollerApp() {
        // Applique le thème de l'application.
        DiceRollerTheme {
            // Appelle le composant principal de l'interface.
            DiceWithButtonAndImage(
                modifier = Modifier
                    .fillMaxSize() // Le composant prend toute la taille de l'écran.
                    .wrapContentSize(Alignment.Center) // Centre le contenu à l'intérieur du composant.
            )
        }
    }
}
