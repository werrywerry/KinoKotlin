package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File
import java.io.FileInputStream

class GetImages {


    fun getImages(films: ArrayList<Film>, path: String, slash: String): HashMap<String, ImageView> {
        val images = hashMapOf<String, ImageView>()

        for (film in films) {
            val yearParameter = "(" + film.Year + ")"
            var imv = ImageView()
            var title = film.Title.replace(":", "")

            if (File(path + slash + title).isDirectory) {
                val image = Image(FileInputStream("$path$slash$title$slash$title.jpg"))
                imv.image = image
            }
            if (File(path + slash + title + yearParameter).isDirectory) {
                val image = Image(FileInputStream("$path$slash$title$yearParameter$slash$title$yearParameter.jpg"))
                imv.image = image
            }
            images[film.Title] = imv
        }

        return images
    }

}
