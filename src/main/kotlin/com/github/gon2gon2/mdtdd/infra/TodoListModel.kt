package com.github.gon2gon2.mdtdd.infra

import javax.swing.AbstractListModel


class TodoListModel(repository: TodoStateRepository) : AbstractListModel<String>(), TodoListObserver {
    private val todos = repository.getAllTodo()

    init {
        repository.addObserver(this)
    }

    override fun getSize(): Int = todos.size
    override fun getElementAt(index: Int): String = todos[index]

    override fun onTodoListChanged() {
        // Notify the list model about data change
        fireContentsChanged(this, 0, getSize() - 1)
    }
}
