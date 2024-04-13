package com.github.gon2gon2.mdtdd.services

import com.github.gon2gon2.mdtdd.infra.TodoStateRepository
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class TodoService(project: Project) {
    private val todoRepository: TodoRepository = project.service<TodoStateRepository>()

    fun getAllTodoTask(): List<String> {
        return todoRepository.getAllTodo()
    }

    fun addTodo(todo: String) {
        todoRepository.addTodo(todo)
    }

    fun remove(todoIndex: Int) {
        todoRepository.removeTodo(todoIndex)
    }

    fun markAsDone(todoIndex: Int) {
        val todo = todoRepository.removeTodo(todoIndex)
        todoRepository.addDoneTask(todo)
    }

    fun getAllDoneTask(): List<String> {
        return todoRepository.getAllDoneTask()
    }
}