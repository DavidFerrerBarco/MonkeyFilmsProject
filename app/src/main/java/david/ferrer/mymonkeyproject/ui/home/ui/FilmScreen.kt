package david.ferrer.mymonkeyproject.ui.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.MediaModel
import david.ferrer.mymonkeyproject.R
import david.ferrer.mymonkeyproject.ui.navigate.Routes
import david.ferrer.mymonkeyproject.ui.theme.*

@Composable
fun FilmScreen(
    navigationController: NavHostController,
    viewModel: HomeViewModel,
    idPelicula: Int?
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MyTopAppBar()},
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(navigationController)},
        content = { MyFilmLazyColumn(viewModel, idPelicula, navigationController) },
        backgroundColor = MaterialTheme.colors.secondary
    )
}

@Composable
fun MyFilmLazyColumn(
    listaPeliculas: HomeViewModel,
    idPelicula: Int?,
    navigationController: NavHostController
) {
    val peliculas = listaPeliculas.peliculas.observeAsState()
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){
        item{
            peliculas.value?.forEach { pelicula ->
                if(pelicula.id == idPelicula)
                    MyFilm(pelicula, navigationController){listaPeliculas.onFavouriteChange(it)}
            }
        }
    }
}

@Composable
fun MyFilm(pelicula: MediaModel, navigationController: NavHostController, onFavouriteChanged: (MediaModel) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(1200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = 5.dp,
            shape = RectangleShape,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight(),
            backgroundColor = Color.Black
        ) {
            Image(
                painter = painterResource(obtenerFoto(pelicula.catel)),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .blur(
                        radiusX = 10.dp,
                        radiusY = 10.dp
                    )
            )
            Column(
                modifier = Modifier
                    .padding(top = 300.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(850.dp)
                        .width(LocalConfiguration.current.screenWidthDp.dp)
                        .padding(horizontal = 20.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(3.dp, azulfuerte)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 150.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            pelicula.title,
                            fontSize = 50.sp,
                            fontFamily = cineExtraBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.padding(10.dp))

                        MyTextoTitulos("Género")
                        MyTextoPlano(pelicula.genre.joinToString(", "))

                        MyTextoTitulos("Categoría")
                        MyTextoPlano(pelicula.category)

                        MyTextoTitulos("Descripción")
                        MyTextoPlano(pelicula.description)

                        Spacer(Modifier.padding(20.dp))

                        Row{
                            IconButton(
                                onClick = {
                                    onFavouriteChanged(pelicula)
                                    refreshView(navigationController)
                                },
                                content = {Icon(
                                    imageVector =
                                    if(pelicula.favorite)
                                        Icons.Default.Star
                                    else
                                        Icons.Default.StarBorder,
                                    contentDescription = "Star",
                                    Modifier
                                        .size(80.dp)
                                        .padding(top = 1.dp),
                                    tint = colorAmarillo
                                )}
                            )
                            Text(
                                pelicula.score.toString(),
                                fontSize = 40.sp,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            Spacer(Modifier.padding(50.dp))
                            IconButton(
                                onClick = {
                                    navigationController.navigate(Routes.Home.route)
                                },
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "SUM",
                                        tint = azulmedio,
                                        modifier = Modifier.size(60.dp)
                                    )
                                },
                                modifier = Modifier.padding(top = 11.dp)
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(50.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(obtenerFoto(pelicula.catel)),
                    contentDescription = null,
                    modifier = Modifier
                        .width(250.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
            }
        }
    }
}

@Composable
fun MyTextoPlano(textoPlano: String){
    Text(
        textoPlano,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 40.dp)
    )
}

@Composable
fun MyTextoTitulos(textoTitulos: String){
    Text(
        textoTitulos,
        fontSize = 45.sp,
        fontFamily = cineBold
    )
}
