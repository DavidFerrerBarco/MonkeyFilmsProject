package david.ferrer.mymonkeyproject.ui.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.MediaModel
import david.ferrer.mymonkeyproject.R
import david.ferrer.mymonkeyproject.ui.navigate.Routes
import david.ferrer.mymonkeyproject.ui.theme.azulfuerte
import david.ferrer.mymonkeyproject.ui.theme.azulmedio
import david.ferrer.mymonkeyproject.ui.theme.colorAmarillo

@Composable
fun HomeScreen(viewModel: HomeViewModel, navigationController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { MyTopAppBar()},
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(navigationController)},
        content = {MyLazyColumn(viewModel, navigationController)},
        backgroundColor = MaterialTheme.colors.secondary,
        floatingActionButton = { MyFAB(viewModel, navigationController)},
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,

    )
}

@Composable
fun MyFAB(listaPeliculas: HomeViewModel, navigationController: NavHostController) {
    FloatingActionButton(
        onClick = {navigationController.navigate(Routes.NewFilm.route) },
        backgroundColor = azulfuerte,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add")
    }
}

@Composable
fun MyLazyColumn(listaPeliculas: HomeViewModel, navigationController: NavHostController) {
    val peliculas = listaPeliculas.peliculas.observeAsState()
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){
        item{
            peliculas.value?.forEach {
                MyHome(it, navigationController){listaPeliculas.onFavouriteChange(it)}
            }
        }
    }

}

@Composable
fun MyBottomNavigation(navigationController: NavHostController) {
    val tamanyo = 33.dp

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White
    ) {
        BottomNavigationItem(selected = true, onClick = { navigationController.navigate(Routes.Home.route) }, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "home",
                modifier = Modifier.size(tamanyo)
            )
        })
        BottomNavigationItem(selected = true, onClick = { navigationController.navigate(Routes.Favourite.route) }, icon = {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "fav",
                modifier = Modifier.size(tamanyo)
            )
        })
    }
}

@Composable
fun MyTopAppBar(){
    TopAppBar(
        title = {
            Text(text = "MonkeyFilms")
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White,
        elevation = 123.dp,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person")
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyHome(pelicula: MediaModel, navigationController: NavHostController, onFavouriteChanged: (MediaModel) -> Unit){

    var mostrarMas by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .height(200.dp)
            .padding(start = 3.dp),
        onClick = {},
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Row{
            Image(
                painterResource(id = obtenerFoto(pelicula.catel)),
                contentDescription = "foto",
                modifier = Modifier
                    .width(130.dp)
                    .height(200.dp)
            )

            Column(
                Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .width(205.dp)){
                Text(pelicula.title, fontSize = 20.sp)
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
                                .size(40.dp)
                                .padding(top = 1.dp),
                            tint = colorAmarillo
                        )}
                    )
                    Text(
                        pelicula.score.toString(),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                if(mostrarMas)
                    MostrarMas(pelicula)
            }
            Row(modifier = Modifier.padding(top = 10.dp)){
                IconButton(
                    onClick = {
                        if(!mostrarMas)
                            mostrarMas = !mostrarMas
                        else
                            navigationController.navigate(Routes.Film.route + "/${pelicula.id}")
                              },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "SUM",
                            tint = azulmedio,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MostrarMas(pelicula: MediaModel) {
    Row{
        Text("Géneros: ")
        pelicula.genre.forEach{ genero ->
            Text(
                genero + " ",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 2.dp)
            )

        }
    }
    Text("Descripción: ")
    Text(
        pelicula.description,
        overflow = TextOverflow.Ellipsis,
        maxLines = 3,
        fontSize = 13.sp,
        modifier = Modifier.padding(top = 2.dp)
    )
}

fun obtenerFoto(catel: Int): Int {
    var imagen = R.drawable.ic_launcher_background
    when(catel){
        1 -> imagen = R.drawable.c1
        2 -> imagen = R.drawable.c2
        3-> imagen = R.drawable.c3
        4-> imagen = R.drawable.c4
        5-> imagen = R.drawable.c5
        6-> imagen = R.drawable.c6
        7-> imagen = R.drawable.c7
        8-> imagen = R.drawable.c8
        9-> imagen = R.drawable.c9
        10-> imagen = R.drawable.c10
        0 -> imagen = R.drawable.ic_launcher_background
    }
    return imagen
}

fun refreshView(navigationController: NavHostController) {
    val idView = navigationController.currentDestination?.id
    navigationController.navigate(idView!!)
    navigationController.popBackStack(idView, inclusive = true)
}
