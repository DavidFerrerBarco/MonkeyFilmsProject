package david.ferrer.mymonkeyproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import david.ferrer.mymonkeyproject.ui.home.ui.HomeViewModel
import david.ferrer.mymonkeyproject.ui.login.ui.LoginViewModel
import david.ferrer.mymonkeyproject.ui.navigate.*
import david.ferrer.mymonkeyproject.ui.register.ui.RegisterViewModel

@Composable
fun CustomNavigator(){
    val navigationController = rememberNavController()
    val loginViewModel = LoginViewModel()
    val registerViewModel = RegisterViewModel()
    val homeViewModel = HomeViewModel()
    NavHost(navController = navigationController, startDestination = Routes.Login.route){
        composable(route = Routes.Login.route){
            LoginNavigation(navigationController, loginViewModel)
        }
        composable(route = Routes.Registro.route){
            RegisterNavigation(navigationController, registerViewModel)
        }
        composable(route = Routes.Home.route){
            HomeNavigation(navigationController, homeViewModel)
        }
        composable(route = Routes.Favourite.route){
            FavouriteNavigation(navigationController, homeViewModel)
        }
        composable(
            route = Routes.Film.route + "/{id}",
            arguments = listOf(navArgument("id"){ type = NavType.IntType })
        ){
            FilmNavigation(navigationController, homeViewModel, it.arguments?.getInt("id"))
        }
        composable(route = Routes.NewFilm.route){
            NewFilmNavigation(navigationController, homeViewModel)
        }
    }
}


