package com.github.gon2gon2.mdtdd.toolwindow.panel

import com.github.gon2gon2.mdtdd.services.TodoService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField
import javax.swing.ListModel

class TodoListPanel(project: Project) {
    val panel = JPanel()
    private lateinit var shouldBeDoneListModel: ListModel<String>
    private lateinit var shouldBeDoneList: JBList<String>

    private lateinit var alreadyDoneListModel: ListModel<String>
    private lateinit var alreadyDoneList: JBList<String>

    private val taskInput = JTextField()

    private val buttonPanel = JPanel()
    private val addButton = JButton("Add")
    private val doneButton = JButton("Done")
    private val removeButton = JButton("Remove")

    private val todoService = project.service<TodoService>()

    init {
        loadState()
        setupUI()
        setupActions()
    }

    private fun loadState() {
        thisLogger().debug("GON: start load State")
        shouldBeDoneListModel = DefaultListModel<String>()
        shouldBeDoneList = JBList(shouldBeDoneListModel)

        thisLogger().debug("GON: {}", shouldBeDoneList)
        alreadyDoneListModel = DefaultListModel<String>()
        alreadyDoneList = JBList(alreadyDoneListModel)
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
                todoService.addTodo(task)
                taskInput.text = ""
            }
        }

        removeButton.addActionListener {
            val selectedIndex = shouldBeDoneList.selectedIndex
            if (selectedIndex != -1) {
                todoService.remove(selectedIndex)
            }
        }

        doneButton.addActionListener {
            val selectedIndex = shouldBeDoneList.selectedIndex
            if (selectedIndex != -1) {
                todoService.markAsDone(selectedIndex)
            }
        }
    }
}
