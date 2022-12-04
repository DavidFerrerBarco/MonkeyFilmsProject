package david.ferrer.mymonkeyproject.ui.favourite.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.ui.home.ui.HomeViewModel
import david.ferrer.mymonkeyproject.ui.home.ui.MyBottomNavigation
import david.ferrer.mymonkeyproject.ui.home.ui.MyHome
import david.ferrer.mymonkeyproject.ui.home.ui.MyTopAppBar

@Composable
fun FavouriteScreen(viewModel: HomeViewModel, navigationController: NavHostController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = { MyTopAppBar() },
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(navigationController) },
        content = {MyFavouriteLazyColumn(viewModel, navigationController)},
        backgroundColor = MaterialTheme.colors.secondary
    )
}

@Composable
fun MyFavouriteLazyColumn(listaPeliculas: HomeViewModel, navigationController: NavHostController) {
    val peliculas = listaPeliculas.peliculas.observeAsState()
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){
        item{
            peliculas.value?.forEach { pelicula ->
                if(pelicula.favorite)
                    MyHome(pelicula, navigationController){listaPeliculas.onFavouriteChange(it)}
            }
        }
    }
}
