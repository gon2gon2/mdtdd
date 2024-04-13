package com.github.gon2gon2.mdtdd.services

import com.github.gon2gon2.mdtdd.infra.TodoStateRepository
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class TodoService(project: Project) {
//    private val todoRepository: TodoRepository = project.service<TodoStateRepository>()
    private val todoList = mutableListOf<String>()

    fun getLastTodoList(): List<String> {
//        return todoRepository.getAll()
        return todoList
    }

    fun addTodo(todo: String) {
//        todoRepository.add(todo)
        todoList.add(todo)
    }

    fun remove(todoIndex: Int) {
//        todoRepository.remove(todoIndex)
        todoList.removeAt(todoIndex)
    }

}