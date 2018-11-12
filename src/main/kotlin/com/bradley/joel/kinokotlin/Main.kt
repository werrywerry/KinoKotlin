package com.bradley.joel.kinokotlin

import com.bradley.joel.kinokotlin.controllers.*
import com.bradley.joel.kinokotlin.layouts.*
import com.bradley.joel.kinokotlin.models.Film
import com.google.gson.JsonArray
import javafx.application.Application
import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.SplitPane
import javafx.scene.image.ImageView
import javafx.stage.Stage

class Main: Application() {

    val os = System.getProperty("os.name")
    var slash = "/"
    var width = 1400.0
    var height = width * 0.57
    var filmsToDisplay = ArrayList<Film>()
    var masterFilmList = ArrayList<Film>()
    var splitPane = SplitPane()
    var jsonFilmList = JsonArray()
    var collectionsVisible = false
    var collectionsWidth = 0.0
    var collections = CollectionsManager().getCollections()
    var collectionsPane = CollectionsPane(collections)
    private val genreBox = GenreSelectBox()
    private var gridImagesList = HashMap<String, ImageView>()
    private var detailedImagesList = HashMap<String, ImageView>()
    lateinit var mainLayout: MainLayout
    lateinit var scene: Scene
    private var path = ""
    var newColumnCount = 0
    var oldColumnCount = 0

    override fun start(window: Stage) {

        window.title = "Kino@Home"
        window.height = height
        window.width = width
        window.scene = Scene(LoadingScreen())
        window.show()

        // determine operating system
        if (os.startsWith("Windows")) {
            slash = "\\"
        }

        // check data.sav for film path, or prompt user to select film path
        path = getPath(slash, window)

        startUp()

        // setup main layout and add button listeners
        mainLayout = MainLayout(filmsToDisplay, gridImagesList, newColumnCount)
        mainLayout.collapseButton.setOnAction { toggleCollections() }
        mainLayout.goButton.setOnAction { onGoClick() }
        mainLayout.genreButton.setOnAction { genreBox.showAndWait() }
        mainLayout.clearButton.setOnAction {
            clearSearchFields(mainLayout, genreBox)
            filmsToDisplay = resetFilmsToDisplay(masterFilmList)
            mainLayout.gatherSearchCriteria(genreBox.gatherGenres())
        }
        mainLayout.filmGrid.setOnFilmClickListener(object : OnFilmClickListener {
            override fun onFilmClicked(film: Film) {
                val getDetailedLayout = GetDetailedLayout(film, path, detailedImagesList[film.Title]!!, slash, splitPane, mainLayout)
                val detailedLayout = getDetailedLayout.returnDetailedLayout()
                detailedLayout.collapseButton.setOnAction { toggleCollections() }
                detailedLayout.addButton.setOnAction { addFilmToCollection(film) }
            }
        })
        mainLayout.rescanButton.setOnAction {
            GenerateJsonArray().generateJsonArray(arrayOf(path, slash))
            startUp()
            mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, getColumnCount(width, collectionsWidth))
        }

        splitPane.orientation = Orientation.HORIZONTAL
        collectionsPane.newButton.setOnAction { newCollection() }
        collectionsPane.removeButton.setOnAction { removeCollectionItem() }
        setTreeViewListener()
        splitPane.items.addAll(collectionsPane, mainLayout)

        // attach mainLayout to scene, set scene styles, and attach scene to window
        scene = Scene(splitPane, width, height)
        window.scene = scene
        scene.stylesheets.add("Style.css")

        // set width change listener to calculate number of columns in the film grid
        window.widthProperty().addListener { _, _, newValue ->
            width = newValue as Double
            newColumnCount = getColumnCount(width, collectionsWidth)
            if (newColumnCount != oldColumnCount) {
                oldColumnCount = newColumnCount
                mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, newColumnCount)
            }
        }

        mainLayout.top.requestFocus()
//        setWindowWidthListener(window, oldColumnCount, mainLayout, filmsToDisplay, path, slash)
    }

    fun setTreeViewListener() {
        collectionsPane.treeView.treeView.setOnMouseClicked { event ->
            if (event.clickCount == 2) {
                splitPane.items[1] = mainLayout
                if (collectionsPane.treeView.treeView.selectionModel.selectedItem.value == "Collections") {
                    filmsToDisplay = resetFilmsToDisplay(masterFilmList)
                    mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, newColumnCount)
                } else if (collectionsPane.treeView.treeView.selectionModel.selectedItem.children.isEmpty()) {
/*
                    val filmTitle = collectionsPane.treeView.treeView.selectionModel.selectedItem.value
                    lateinit var film: Film
                    for (searchFilm in masterFilmList) {
                        if (filmTitle == searchFilm.Title) {
                            film = searchFilm
                        }
                        val getDetailedLayout = GetDetailedLayout(searchFilm, path, detailedImagesList[film.Title]!!, slash, splitPane, mainLayout)
                        val detailedLayout = getDetailedLayout.returnDetailedLayout()
                        detailedLayout.collapseButton.setOnAction { toggleCollections() }
                        detailedLayout.addButton.setOnAction { addFilmToCollection(film) }
                    }
*/

                } else {
                    val collection = collectionsPane.treeView.treeView.selectionModel.selectedItem.value
                    filmsToDisplay.clear()
                    for (film in collections[collection]!!)
                        filmsToDisplay.add(film.value)
                        mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, newColumnCount)
                }
            }
        }
    }

    private fun addFilmToCollection(film: Film) {
        val selectedItem = collectionsPane.treeView.treeView.selectionModel.selectedItem
        if (selectedItem != null &&
                selectedItem.value != "Collections") {
            collections = CollectionsManager().addFilmToCollection(collections, selectedItem.value, film)
            collectionsPane.treeView.refreshTreeView(collections)
            splitPane.items[1] = mainLayout
        }
        collectionsPane.treeView.treeView.selectionModel.select(selectedItem)
    }

    private fun removeCollectionItem() {
        if (collectionsPane.treeView.treeView.selectionModel.selectedItem != null &&
                collectionsPane.treeView.treeView.selectionModel.selectedItem.value != "Collections") {
            if (collectionsPane.treeView.treeView.selectionModel.selectedItem.parent.value == "Collections") {
                collections = CollectionsManager().removeCollectionItem(collectionsPane.treeView.treeView.selectionModel.selectedItem.value, "", collections)
            } else {
                collections = CollectionsManager().removeCollectionItem(collectionsPane.treeView.treeView.selectionModel.selectedItem.value,
                        collectionsPane.treeView.treeView.selectionModel.selectedItem.parent.value,
                        collections)
            }
        }
        collectionsPane.treeView.refreshTreeView(collections)
    }

    private fun newCollection() {
        val newCollectionDialog = NewCollectionDialog()
        newCollectionDialog.show()
        newCollectionDialog.acceptButton.setOnAction {
            val newCollectionName = newCollectionDialog.getNewCollectionName()
            collections = CollectionsManager().addCollection(newCollectionName, collections)
            newCollectionDialog.close()
            collectionsPane.treeView.refreshTreeView(collections)
        }
    }


    private fun toggleCollections() {
        if (collectionsVisible) {
            collectionsPane.hideCollections()
            collectionsWidth = 0.0
            newColumnCount = getColumnCount(width, collectionsWidth)
            mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, newColumnCount)
            collectionsVisible = false
        } else {
            collectionsPane.showCollections()
            collectionsWidth = 200.0
            newColumnCount = getColumnCount(width, collectionsWidth)
            mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, newColumnCount)
            collectionsVisible = true
        }

    }

    private fun startUp() {
        jsonFilmList = getJsonArray(path, slash)
        masterFilmList = getFilmList(jsonFilmList)
        filmsToDisplay = resetFilmsToDisplay(masterFilmList)
        gridImagesList = GetImages().getImages(masterFilmList, path, slash)
        detailedImagesList = GetImages().getImages(masterFilmList, path, slash)
        newColumnCount = getColumnCount(width, collectionsWidth)
        oldColumnCount = getColumnCount(width, collectionsWidth)
    }

    private fun onGoClick() {
        filmsToDisplay = resetFilmsToDisplay(masterFilmList)
        filmsToDisplay = performSearch(filmsToDisplay, mainLayout.gatherSearchCriteria(genreBox.gatherGenres()))
        mainLayout.filmGrid.populateGrid(filmsToDisplay, gridImagesList, newColumnCount)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Main::class.java)
        }
    }
}
