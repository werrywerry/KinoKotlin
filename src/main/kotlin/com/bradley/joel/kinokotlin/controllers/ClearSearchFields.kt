package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.layouts.GenreSelectBox
import com.bradley.joel.kinokotlin.layouts.MainLayout

fun clearSearchFields(mainLayout: MainLayout, genreBox: GenreSelectBox) {
    mainLayout.title.text = ""
    mainLayout.actor.text = ""
    mainLayout.year.text = ""
    for (checkBox in genreBox.checkBoxes) {
        checkBox.isSelected = false
    }
}
