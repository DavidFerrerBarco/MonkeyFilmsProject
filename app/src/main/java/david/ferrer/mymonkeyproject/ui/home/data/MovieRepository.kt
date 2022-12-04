package david.ferrer.mymonkeyproject.ui.home.data

import android.util.Log
import david.ferrer.mymonkeyproject.MediaModel
import david.ferrer.mymonkeyproject.ui.home.data.network.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieRepository {
    private val api = MovieService()

    suspend fun doMovies(): ArrayList<MediaModel>{
        val itemsArray : ArrayList<MediaModel> = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            val items = api.doMovies()

            withContext(Dispatchers.IO){
                for(i in 0 until items.count()){
                    val id = items[i].id
                    val title = items[i].title
                    val cartel = items[i].catel
                    val desc = items[i].description
                    val score = items[i].score
                    val favourites = items[i].favorite
                    val categoria = items[i].category
                    val genre = items[i].genre
                    itemsArray.add(MediaModel(id, title, desc, cartel, score, genre, categoria, favourites))
                }
            }
        }
        return itemsArray
    }
}