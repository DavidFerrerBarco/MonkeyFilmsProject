package david.ferrer.mymonkeyproject.ui.home.data.domain

import androidx.lifecycle.LiveData
import david.ferrer.mymonkeyproject.MediaModel
import david.ferrer.mymonkeyproject.ui.home.data.MovieRepository

class HomeUseCase {
    private val repository = MovieRepository()

    suspend operator fun invoke(): ArrayList<MediaModel> {
        return repository.doMovies()
    }
}