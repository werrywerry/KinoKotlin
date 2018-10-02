package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import java.awt.Desktop
import java.io.File

fun watchFilm(film: Film, path: String, slash: String) {

    var extension = ""
    var title = film.Title
    title = title.replace(":", "")

    if (title.contains("(")) {
        title = title + "(" + film.Year + ")"
    }
    val filmPath = path + slash + title + slash + title

    if (File("$filmPath.avi").isFile) {
        extension = ".avi"
    }
    if (File("$filmPath.mp4").isFile) {
        extension = ".mp4"
    }
    if (File("$filmPath.mov").isFile) {
        extension = ".mov"
    }
    if (File("$filmPath.flv").isFile) {
        extension = ".flv"
    }
    if (File("$filmPath.wmv").isFile) {
        extension = ".wmv"
    }
    if (File("$filmPath.asf").isFile) {
        extension = ".asf"
    }
    if (File("$filmPath.mpg").isFile) {
        extension = ".mpg"
    }
    if (File("$filmPath.mkv").isFile) {
        extension = ".mkv"
    }

    val filmFile = File(filmPath + extension)
    Desktop.getDesktop().open(filmFile)
}


