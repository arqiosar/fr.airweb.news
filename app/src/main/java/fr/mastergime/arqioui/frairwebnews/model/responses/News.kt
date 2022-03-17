package fr.mastergime.arqioui.frairwebnews.model.responses

data class NewsList (
    val news : List<News>
)

data class News (
    val nid : Int,
    var type : String,
    val date : String,
    val title : String,
    val picture : String,
    val content : String,
    val dateformated : String
)