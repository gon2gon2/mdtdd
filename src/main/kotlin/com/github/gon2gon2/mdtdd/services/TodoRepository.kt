package com.github.gon2gon2.mdtdd.services

interface TodoRepository {
    fun add(todo: String)
    fun remove(todo: String)
    fun remove(todoIndex: Int)
    fun getAll(): List<String>
}