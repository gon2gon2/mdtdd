package com.github.gon2gon2.mdtdd.toolwindow.panel

import com.intellij.ui.components.JBList
import javax.swing.BoxLayout
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField

class TodoListPanel {
    val panel = JPanel()
    private val listModel = DefaultListModel<String>()
    private val todoList = JBList(listModel)
    private val addButton = JButton("Add")
    private val removeButton = JButton("Remove")
    private val taskInput = JTextField(10)

    init {
        setupUI()
        setupActions()
    }

    private fun setupUI() {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(JScrollPane(todoList))
        panel.add(taskInput)
        panel.add(addButton)
        panel.add(removeButton)
    }

    private fun setupActions() {
        addButton.addActionListener {
            val task = taskInput.text
            if (task.isNotBlank()) {
                listModel.addElement(task)
                taskInput.text = "" // Clear input field
            }
        }

        removeButton.addActionListener {
            val selectedIndex = todoList.selectedIndex
            if (selectedIndex != -1) {
                listModel.removeElementAt(selectedIndex)
            }
        }
    }
}
