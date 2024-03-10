package com.github.gon2gon2.mdtdd.services

import com.github.gon2gon2.mdtdd.infra.TodoStateRepository
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class TodoService(project: Project) {
    private val todoRepository: TodoRepository = project.service<TodoStateRepository>()

    fun getLastTodoList(): List<String> {
        return todoRepository.getAll()
    }

    fun addTodo(todo: String) {
        todoRepository.add(todo)
    }

    fun remove(todoIndex: Int) {
        todoRepository.remove(todoIndex)
    }

}