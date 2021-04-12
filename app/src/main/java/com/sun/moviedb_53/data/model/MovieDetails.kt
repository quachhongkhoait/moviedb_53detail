package com.sun.moviedb_53.data.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val productionCountry: String,
    val description: String,
    val imagePoster: String,
    val genres: String,
    val releaseDate: String,
    val tagLine: String,

//    val genres: MutableList<Genres>?,
//    val companies: MutableList<Company>?
) {
    var isFavorite: Boolean = false
}

object MoviesDetailsEntry {
    const val ID = "id"
    const val TITLE = "title"
    const val IMAGE_URL = "backdrop_path"
    const val LIST_GENRES = "genres"
    const val LIST_COMPANIES = "production_companies"
    const val RATE = "vote_average"
    const val PRODUCTION_COUNTRY = "production_countries"
    const val COUNTRY_NAME = "name"
    const val DESCRIPTION = "overview"
    const val IMAGE_POSTER = "poster_path"
    const val RELEASE_DATE = "release_date"
    const val TAG_LINE = "tagline"
}
