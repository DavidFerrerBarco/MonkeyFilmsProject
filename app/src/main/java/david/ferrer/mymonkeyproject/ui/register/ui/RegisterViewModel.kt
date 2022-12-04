package david.ferrer.mymonkeyproject.ui.register.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import david.ferrer.mymonkeyproject.ui.login.ui.LoginViewModel

class RegisterViewModel : LoginViewModel() {
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable : LiveData<Boolean> = _registerEnable

    private val _generoAccion = MutableLiveData<Boolean>()
    val generoAccion : LiveData<Boolean> = _generoAccion
    private val _generoTerror = MutableLiveData<Boolean>()
    val generoTerror : LiveData<Boolean> = _generoTerror
    private val _generoSuspense = MutableLiveData<Boolean>()
    val generoSuspense : LiveData<Boolean> = _generoSuspense
    private val _generoAnime = MutableLiveData<Boolean>()
    val generoAnime : LiveData<Boolean> = _generoAnime
    private val _generoDrama = MutableLiveData<Boolean>()
    val generoDrama : LiveData<Boolean> = _generoDrama
    private val _generoRomance = MutableLiveData<Boolean>()
    val generoRomance : LiveData<Boolean> = _generoRomance

    private fun isValidEmail(email: String) : Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun onRegisterChanged(user: String, email: String, password: String){
        _user.value = user
        _password.value = password
        _email.value = email
        _registerEnable.value = isValidPassword(password) && isValidUser(user) && isValidEmail(email)
    }

    fun onAccionChanged(accion: Boolean){
        _generoAccion.value = !accion
    }

    fun onTerrorChanged(terror: Boolean){
        _generoTerror.value = !terror
    }

    fun onSuspenseChanged(suspense: Boolean){
        _generoSuspense.value = !suspense
    }

    fun onAnimeChanged(anime: Boolean){
        _generoAnime.value = !anime
    }

    fun onDramaChanged(drama: Boolean){
        _generoDrama.value = !drama
    }

    fun onRomanceChanged(romance: Boolean){
        _generoRomance.value = !romance
    }
}