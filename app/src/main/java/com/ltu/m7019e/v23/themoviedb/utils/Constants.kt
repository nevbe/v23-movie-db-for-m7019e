package com.ltu.m7019e.v23.themoviedb.utils

import android.content.res.Resources.Theme


object Constants {
    const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val BACKDROP_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val POSTER_IMAGE_WIDTH = "w92"
    const val BACKDROP_IMAGE_WIDTH = "w1280"
    const val MOVIE_LIST_BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val IMDB_BASE_URL = "https://imdb.com/title/"
    const val IFRAME_HEAD = "<iframe width=\"100%\" height=\"100%\" src=\""
    const val IFRAME_TAIL = "\" frameborder=\"0\" allowfullscreen></iframe>"
    const val YOUTUBE_VID_BASE_URL = "https://www.youtube.com/embed/"
    const val YOUTUBE_TAIL_URL = "?controls=0&enablejsapi=1"

    //TODO: Verify that this is correct, i've yet to find a trailer that doesn't use youtube
    const val VIMEO_VID_BASE_URL = "https://vimeo.com/embed/"

    const val API_KEY = SECRETS.API_KEY
}