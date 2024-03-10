package com.github.gon2gon2.mdtdd.infra

import com.github.gon2gon2.mdtdd.services.TodoRepository
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@Service(Service.Level.PROJECT)
@State(
        name = "com.github.gon2gon2.mdtdd.TodoListState",
        storages = [Storage("TodoList.xml")]
)
class TodoStateRepository : PersistentStateComponent<TodoStateRepository>, TodoRepository {
    private var todoList: MutableList<String> = mutableListOf()

    override fun add(todo: String) {
        todoList.add(todo)
    }

    override fun remove(todo: String) {
        todoList.remove(todo)
    }

    override fun remove(todoIndex: Int) {
        todoList.removeAt(todoIndex)
    }

    override fun getAll(): List<String> {
        return todoList
    }

    override fun getState(): TodoStateRepository = this

    override fun loadState(state: TodoStateRepository) {
        XmlSerializerUtil.copyBean(state, this)
    }
}