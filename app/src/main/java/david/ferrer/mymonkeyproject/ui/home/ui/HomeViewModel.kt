package david.ferrer.mymonkeyproject.ui.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import david.ferrer.mymonkeyproject.MediaModel
import david.ferrer.mymonkeyproject.ui.home.data.domain.HomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeUseCase = HomeUseCase()

    private val _peliculas = MutableLiveData<ArrayList<MediaModel>>()
    var peliculas : LiveData<ArrayList<MediaModel>> = _peliculas

    init{
        CoroutineScope(Dispatchers.IO).launch{
            _peliculas.postValue(homeUseCase())
        }
    }

    fun onFavouriteChange(peli: MediaModel){
        _peliculas.value?.forEach {pelicula ->
            if(pelicula.id == peli.id){
                pelicula.favorite = !pelicula.favorite
                if(pelicula.favorite)
                    pelicula.score++
                else
                    pelicula.score--
            }
        }
    }

    fun onButtonPressed(indice: Int, titulo: String, categoria: String, genero: String, descripcion: String){
        _peliculas.value?.add(
            MediaModel(
                indice,
                titulo,
                descripcion,
                0,
                0,
                genero.split(",").toList(),

                categoria,
                false
            )
        )
    }

    fun getSize(): Int {
        return peliculas.value!!.size
    }
}
