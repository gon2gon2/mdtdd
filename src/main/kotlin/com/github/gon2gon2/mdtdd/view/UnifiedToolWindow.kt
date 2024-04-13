package com.github.gon2gon2.mdtdd.view

import com.github.gon2gon2.mdtdd.view.panel.TimerPanel
import com.github.gon2gon2.mdtdd.view.panel.TodoListPanel
import com.intellij.openapi.project.Project
import javax.swing.BoxLayout
import javax.swing.JPanel

class UnifiedToolWindow(project: Project) {
    private val mainPanel = JPanel()
    private val timerPanel = TimerPanel().panel
    private val todoListPanel = TodoListPanel(project,
            mutableListOf<String>("A", "B"),
            mutableListOf<String>("DONE1", "DONE2")
    ).panel

    init {
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        mainPanel.add(timerPanel)
        mainPanel.add(todoListPanel)
    }

    val content: JPanel
        get() = mainPanel
}