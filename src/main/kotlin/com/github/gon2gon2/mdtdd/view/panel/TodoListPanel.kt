package com.github.gon2gon2.mdtdd.view.panel

import com.github.gon2gon2.mdtdd.view.CustomListModel
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField


class TodoListPanel(project: Project,
                    todoList: MutableList<String>,
                    doneList: MutableList<String>
) {
    val panel = JPanel()

    private val todoListModel = CustomListModel(todoList)
    private val todoJbList = JBList(todoListModel)

    private val alreadyDoneListModel = CustomListModel(doneList)
    private val alreadyDoneList = JBList(alreadyDoneListModel)

    private val taskInput = JTextField()

    private val buttonPanel = JPanel()
    private val addButton = JButton("Add")
    private val doneButton = JButton("Done")
    private val removeButton = JButton("Remove")

    init {
        setupUI()
        setupActions()
    }

    private fun setupUI() {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        buttonPanel.layout = FlowLayout(FlowLayout.LEFT)
        panel.add(JScrollPane(todoJbList))
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
                todoListModel.addElement(task)
                taskInput.text = ""
            }
        }

        removeButton.addActionListener {
            val selectedIndex = todoJbList.selectedIndex
            if (selectedIndex != -1) {
                todoListModel.removeElementAt(selectedIndex)
            }
        }

        doneButton.addActionListener {
            val selectedIndex = todoJbList.selectedIndex
            if (selectedIndex != -1) {
                alreadyDoneListModel.addElement(todoJbList.selectedValue)
                todoListModel.removeElementAt(selectedIndex)
            }
        }
    }
}
