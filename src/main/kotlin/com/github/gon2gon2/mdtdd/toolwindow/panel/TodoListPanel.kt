package com.github.gon2gon2.mdtdd.toolwindow.panel

import com.github.gon2gon2.mdtdd.services.TodoService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField

class TodoListPanel(project: Project) {
    val panel = JPanel()
    private val shouldBeDoneListModel = DefaultListModel<String>()
    private val shouldBeDoneList = JBList(shouldBeDoneListModel)

    private val alreadyDoneListModel = DefaultListModel<String>()
    private val alreadyDoneList = JBList(alreadyDoneListModel)

    private val taskInput = JTextField()

    private val buttonPanel = JPanel()
    private val addButton = JButton("Add")
    private val doneButton = JButton("Done")
    private val removeButton = JButton("Remove")

    private val todoService = project.service<TodoService>()

    init {
        setupUI()
        setupActions()
    }

    private fun setupUI() {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        buttonPanel.layout = FlowLayout(FlowLayout.LEFT)
        panel.add(JScrollPane(shouldBeDoneList))
        panel.add(JScrollPane(alreadyDoneList))
        panel.add(taskInput)
        panel.add(addButton)
        panel.add(doneButton)
        panel.add(removeButton)
    }

    private fun setupActions() {
        addButton.addActionListener {
            val task = taskInput.text
            if (task.isNotBlank()) {
                shouldBeDoneListModel.addElement(task)
                taskInput.text = ""
            }
        }

        removeButton.addActionListener {
            val selectedIndex = shouldBeDoneList.selectedIndex
            if (selectedIndex != -1) {
                shouldBeDoneListModel.removeElementAt(selectedIndex)
            }
        }

        doneButton.addActionListener {
            val selectedIndex = shouldBeDoneList.selectedIndex
            if (selectedIndex != -1) {
                alreadyDoneListModel.addElement(shouldBeDoneList.selectedValue)
                shouldBeDoneListModel.removeElementAt(selectedIndex)
            }
        }
    }
}
