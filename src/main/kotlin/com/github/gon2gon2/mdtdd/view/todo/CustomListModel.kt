package com.github.gon2gon2.mdtdd.view.todo

import javax.swing.AbstractListModel

class CustomListModel(private val backingList: MutableList<String>) : AbstractListModel<String>() {
    override fun getSize(): Int = backingList.size

    override fun getElementAt(index: Int): String = backingList[index]

    fun addElement(element: String) {
        backingList.add(element)
        fireIntervalAdded(this, backingList.size - 1, backingList.size - 1)
    }

    fun removeElementAt(element: String) {
        val index = backingList.indexOf(element)
        if (index != -1) {
            backingList.removeAt(index)
            fireIntervalRemoved(this, index, index)
        }
    }

    fun removeElementAt(index: Int) {
        if (index != -1) {
            backingList.removeAt(index)
            fireIntervalRemoved(this, index, index)
        }
    }
}
