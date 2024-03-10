package com.github.gon2gon2.mdtdd.toolwindow

import com.github.gon2gon2.mdtdd.toolwindow.panel.TimerPanel
import com.github.gon2gon2.mdtdd.toolwindow.panel.TodoListPanel
import com.intellij.openapi.project.Project
import javax.swing.BoxLayout
import javax.swing.JPanel

class UnifiedToolWindow(project: Project) {
    private val mainPanel = JPanel()
    private val timerPanel = TimerPanel().panel
    private val todoListPanel = TodoListPanel(project).panel

    init {
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        mainPanel.add(timerPanel)
        mainPanel.add(todoListPanel)
    }

    val content: JPanel
        get() = mainPanel
}