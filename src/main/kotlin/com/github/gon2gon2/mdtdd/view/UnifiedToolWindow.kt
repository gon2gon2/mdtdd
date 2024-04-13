package com.github.gon2gon2.mdtdd.view

import com.github.gon2gon2.mdtdd.infra.TodoStateRepository
import com.github.gon2gon2.mdtdd.view.timer.TimerPanel
import com.github.gon2gon2.mdtdd.view.todo.TodoListPanel
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import javax.swing.BoxLayout
import javax.swing.JPanel

class UnifiedToolWindow(project: Project) {
    private val mainPanel = JPanel()
    private val timerPanel = TimerPanel().panel

    private val todoListPanel = TodoListPanel(
            project,
            project.service<TodoStateRepository>().state.todoList,
            project.service<TodoStateRepository>().state.doneList,
    ).panel

    init {
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        mainPanel.add(timerPanel)
        mainPanel.add(todoListPanel)
    }

    val content: JPanel
        get() = mainPanel
}