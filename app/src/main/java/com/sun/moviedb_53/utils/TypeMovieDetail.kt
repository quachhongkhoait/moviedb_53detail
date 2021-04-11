package com.sun.moviedb_53.utils

enum class TypeMovieDetail(val path: String) {
    MOVIE_DETAILS("?"),
    VIDEO_YOUTUBE("/videos"),
    CASTS("/credits"),
    RECOMMENDATIONS("/recommendations")
}