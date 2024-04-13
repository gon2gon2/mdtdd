package com.github.gon2gon2.mdtdd.infra

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
class TodoStateRepository : PersistentStateComponent<TodoStateRepository> {
    public val todoList: MutableList<String> = mutableListOf()
        get() = field
    public val doneList: MutableList<String> = mutableListOf()
        get() = field

    override fun getState(): TodoStateRepository = this

    override fun loadState(state: TodoStateRepository) {
        XmlSerializerUtil.copyBean(state, this)
    }
}