package david.ferrer.mymonkeyproject.ui.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.ui.favourite.ui.FavouriteScreen
import david.ferrer.mymonkeyproject.ui.home.ui.FilmScreen
import david.ferrer.mymonkeyproject.ui.home.ui.HomeScreen
import david.ferrer.mymonkeyproject.ui.home.ui.HomeViewModel
import david.ferrer.mymonkeyproject.ui.home.ui.NewFilmScreen
import david.ferrer.mymonkeyproject.ui.login.ui.LoginScreen
import david.ferrer.mymonkeyproject.ui.login.ui.LoginViewModel
import david.ferrer.mymonkeyproject.ui.register.ui.RegisterScreen
import david.ferrer.mymonkeyproject.ui.register.ui.RegisterViewModel


@Composable
fun LoginNavigation(navigationController: NavHostController, loginViewModel: LoginViewModel){
    LoginScreen(loginViewModel, navigationController)
}

@Composable
fun RegisterNavigation(
    navigationController: NavHostController,
    registerViewModel: RegisterViewModel
){
    RegisterScreen(registerViewModel, navigationController)
}

@Composable
fun HomeNavigation(navigationController: NavHostController, homeViewModel: HomeViewModel) {
    HomeScreen(homeViewModel, navigationController)
}

@Composable
fun FavouriteNavigation(
    navigationController: NavHostController,
    homeViewModel: HomeViewModel
){
    FavouriteScreen(homeViewModel, navigationController)
}

@Composable
fun FilmNavigation(navigationController: NavHostController, homeViewModel: HomeViewModel, idPelicula: Int?) {
    FilmScreen(navigationController, homeViewModel, idPelicula)
}

@Composable
fun NewFilmNavigation(navigationController: NavHostController, homeViewModel: HomeViewModel) {
    NewFilmScreen(navigationController, homeViewModel)
}
