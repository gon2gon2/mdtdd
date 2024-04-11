package com.github.gon2gon2.mdtdd.infra

import javax.swing.AbstractListModel
import javax.swing.event.ListDataEvent
import javax.swing.event.ListDataListener

class MutableListModel<T>(private val list: MutableList<T>) : AbstractListModel<T>() {

    override fun getSize(): Int = list.size

    override fun getElementAt(index: Int): T = list[index]

    fun add(element: T) {
        list.add(element)
        val index = list.size - 1
        fireIntervalAdded(this, index, index)
    }

    fun remove(element: T) {
        val index = list.indexOf(element)
        if (index >= 0) {
            list.remove(element)
            fireIntervalRemoved(this, index, index)
        }
    }
}
