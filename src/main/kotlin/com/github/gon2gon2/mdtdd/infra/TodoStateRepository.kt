package com.github.gon2gon2.mdtdd.infra

import com.github.gon2gon2.mdtdd.services.TodoRepository
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil


interface TodoListObserver {
    fun onTodoListChanged()
}

@Service(Service.Level.PROJECT)
@State(
    name = "com.github.gon2gon2.mdtdd.TodoListState",
    storages = [Storage("TodoList.xml")]
)
class TodoStateRepository : PersistentStateComponent<TodoStateRepository>, TodoRepository {
    private val observers = mutableListOf<TodoListObserver>()
    private val todoList = mutableListOf<String>()
    private val doneList = mutableListOf<String>()

    fun addObserver(observer: TodoListObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: TodoListObserver) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.onTodoListChanged() }
    }

    override fun addTodo(todo: String) {
        todoList.add(todo)
        notifyObservers()

    }

    override fun removeTodo(todo: String) {
        todoList.remove(todo)
        notifyObservers()

    }

    override fun removeTodo(todoIndex: Int): String {
        val task = todoList.removeAt(todoIndex)
        notifyObservers()
        return task
    }

    override fun getAllTodo(): List<String> {
        return todoList

    }

    override fun getAllDoneTask(): List<String> {
        return doneList
    }

    override fun addDoneTask(todo: String) {
        doneList.add(todo)
        notifyObservers()
    }

    override fun removeDoneTask(todoIndex: Int): String {
        val task = doneList.removeAt(todoIndex)
        notifyObservers()
        return task
    }


    override fun getState(): TodoStateRepository = this

    override fun loadState(state: TodoStateRepository) {
        XmlSerializerUtil.copyBean(state, this)
    }
}