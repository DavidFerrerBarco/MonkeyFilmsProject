package david.ferrer.mymonkeyproject.ui.home.data.network

import david.ferrer.mymonkeyproject.ui.home.data.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieClient {
    @GET("/v3/ba19d40a-9750-4413-bd70-9c6e703cc9e9/")
    suspend fun getMovies() : Response<MoviesResponse>
}
