package com.github.gon2gon2.mdtdd.services

interface TodoRepository {
    fun addTodo(todo: String)
    fun removeTodo(todo: String)
    fun removeTodo(todoIndex: Int) : String
    fun getAllTodo(): List<String>

    fun getAllDoneTask(): List<String>
    fun addDoneTask(todo: String)
    fun removeDoneTask(todoIndex: Int):String
}