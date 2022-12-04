package david.ferrer.mymonkeyproject

data class MediaModel(
    var id: Int,
    var title: String,
    var description: String,
    var catel: Int,
    var score: Int,
    var genre: List<String>,
    var category: String,
    var favorite: Boolean = false
    )