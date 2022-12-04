package david.ferrer.mymonkeyproject.ui.home.data.network

import david.ferrer.mymonkeyproject.MediaModel
import david.ferrer.mymonkeyproject.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doMovies() : List<MediaModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MovieClient::class.java).getMovies()
            response.body()?.success ?: emptyList()
        }
    }
}