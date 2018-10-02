package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.Film
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane

interface OnFilmClickListener {
    fun onFilmClicked(film: Film)
}

class GridLayout: GridPane() {

    var mOnFilmClickListener: OnFilmClickListener? = null

    fun setOnFilmClickListener (onFilmClickListener: OnFilmClickListener) {
        mOnFilmClickListener = onFilmClickListener

    }

    init {

        style = "-fx-background-color: #333"
        hgap = 40.0
        vgap = 40.0
        padding = Insets(20.0, 200.0, 20.0, 50.0)

    }

    fun populateGrid(films: ArrayList<Film>, images: HashMap<String, ImageView>, cols: Int) {

        children.clear()
        var r = -1
        var c = 0
        for (film in films) {
            if (c % cols == 0) {
                r++
            }
            val gridElement = GridElement(film, images[film.Title])
            gridElement.setOnMouseClicked { mOnFilmClickListener!!.onFilmClicked(film) }
            add(gridElement, c % cols, r)
            c++
        }
        if (films.size <= cols && films.size != 0) {
            val offset = cols * 2 - films.size
            for (i in 0 until offset) {
                if (c % cols == 0) {
                    r++
                }
                val gridElement = GridElement(films[0], ImageView())
                gridElement.isVisible = false
                add(gridElement, c % cols, r)
                c++
            }
        }
        if (films.size == 0) {
            val emptyFilm = Film("","","","","","","","","","","","","","")
            val label = Label("No films to display...")
            setValignment(label, VPos.TOP)
            label.id = "title-label"
            add(label, c, 0)
            c++
            r++
            for (i in 1 until cols * 2) {
                if (c % cols == 0) {
                    r++
                }
                val gridElement = GridElement(emptyFilm, ImageView())
                gridElement.isVisible = false
                add(gridElement, c % cols, r)
                c++
            }
        }
    }
}