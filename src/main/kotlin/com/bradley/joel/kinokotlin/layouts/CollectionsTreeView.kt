package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.Film
import javafx.scene.control.ScrollPane
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import java.util.*
import kotlin.collections.ArrayList

interface OnCollectionSelectListener {
    fun onCollectionSeleted(collection: TreeMap<String, ArrayList<Film>>)
}


class CollectionsTreeView(collections: TreeMap<String, ArrayList<Film>>) : ScrollPane() {

    var mOnCollectionSelectListener: OnCollectionSelectListener? = null

    fun setOnCollectionSelectListener (onCollectionSelectListener: OnCollectionSelectListener) {
        mOnCollectionSelectListener = onCollectionSelectListener

    }


    val treeView = TreeView<String>()
    val rootItem = TreeItem<String>("Collections")
    val list = mutableListOf<String>()


    init {
        rootItem.isExpanded = true

        refreshTreeView(collections)

        treeView.root = rootItem
        content = treeView
        treeView.maxWidth = 200.0
        isFitToHeight = true
        hbarPolicy = ScrollBarPolicy.NEVER
    }

    fun refreshTreeView(collections: TreeMap<String, ArrayList<Film>>) {
        rootItem.children.clear()
        for (collection in collections) {
            val collectionItem = TreeItem(collection.key)
            rootItem.children.add(collectionItem)
            collectionItem.isExpanded = true
            for (film in collection.value) {
                var filmItem = TreeItem(film.Title)
                collectionItem.children.add(filmItem)
            }
        }
    }

}