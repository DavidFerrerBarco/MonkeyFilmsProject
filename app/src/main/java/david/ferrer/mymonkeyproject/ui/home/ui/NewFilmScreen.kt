package david.ferrer.mymonkeyproject.ui.home.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.R
import david.ferrer.mymonkeyproject.ui.navigate.Routes
import david.ferrer.mymonkeyproject.ui.theme.azulfuerte

@Composable
fun NewFilmScreen(navigationController: NavHostController, viewModel: HomeViewModel) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MyTopAppBar()},
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(navigationController)},
        content = { MyNewFilmContent(navigationController, viewModel) },
        backgroundColor = MaterialTheme.colors.secondary
    )
}

@Composable
fun MyNewFilmContent(navigationController: NavHostController, listaPeliculas: HomeViewModel) {

    var titulo by remember{ mutableStateOf("") }
    var categoria by remember{ mutableStateOf("") }
    var generos by remember{ mutableStateOf("") }
    var descripcion by remember{ mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = R.drawable.cineinterior),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .border(BorderStroke(3.dp, azulfuerte))
        )
        Spacer(Modifier.padding(10.dp))
        MyFilmTextFields("Título", titulo){titulo = it}
        MyFilmTextFields("Categoría", categoria){categoria = it}
        MyFilmTextFields("Géneros", generos){generos = it}
        MyFilmTextFields("Descripción", descripcion){descripcion = it}
        
        Spacer(Modifier.padding(30.dp))

        Button(
            onClick = {
                listaPeliculas.onButtonPressed(listaPeliculas.getSize()+1,titulo, categoria, generos, descripcion)
                navigationController.navigate(Routes.Home.route)
            },
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                disabledBackgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                disabledContentColor = Color.White
            )
        ) {
            Text("Añadir")
        }
    }
}

@Composable
fun MyFilmTextFields(titulo: String, variable: String, onTextFieldChanged: (String) -> Unit){
    TextField(
        value = variable,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(titulo) },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
