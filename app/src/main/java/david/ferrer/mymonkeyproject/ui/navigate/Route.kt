package david.ferrer.mymonkeyproject.ui.navigate

sealed class Routes(val route: String){
    object Login: Routes("login")
    object Registro: Routes("registro")
    object Home: Routes("home")
    object Favourite: Routes("favourite")
    object Film: Routes("film")
    object NewFilm: Routes("newfilm")
}
