package com.github.gon2gon2.mdtdd.infra

import com.intellij.openapi.components.BaseState
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(
        name = "TodoList",
        storages = [Storage("TodoList.xml")]
)
class TodoStateRepository : SimplePersistentStateComponent<TodoStateRepository.State>(State()) {
    class State : BaseState() {
        val todoList: MutableList<String> = mutableListOf()
        val doneList: MutableList<String> = mutableListOf()
    }
}

