package david.ferrer.mymonkeyproject.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.ui.navigate.Routes
import kotlinx.coroutines.delay

open class LoginViewModel : ViewModel(){

    protected val _user = MutableLiveData<String>()
    val user : LiveData<String> = _user

    protected val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun onLoginChanged(user: String, password: String){
        _user.value = user
        _password.value = password
        _loginEnable.value = isValidPassword(password) && isValidUser(user)
    }

    protected fun isValidPassword(password: String) : Boolean = password.length > 6
    protected fun isValidUser(user: String) : Boolean = user.length > 8
    suspend fun onLoginSelected(navigationController: NavHostController) {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
        navigationController.navigate(Routes.Home.route)
    }
}