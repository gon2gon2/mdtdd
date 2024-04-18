package com.github.gon2gon2.mdtdd.view.todo

import com.intellij.ui.components.JBList
import java.awt.FlowLayout
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField


class TodoListPanel(
    todoList: MutableList<String>,
    doneList: MutableList<String>,
) {
    val panel = JPanel()

    private val todoListModel = CustomListModel(todoList)
    private val todoJbList = JBList(todoListModel)

    private val doneListModel = CustomListModel(doneList)
    private val doneJbList = JBList(doneListModel)

    private val taskInput = JTextField()

    private val buttonPanel = JPanel()
    private val addButton = JButton("Add")
    private val doneButton = JButton("Done")
    private val removeButton = JButton("Remove")
    private val clearButton = JButton("Clear")

    init {
        setupUI()
        setupActions()
    }

    private fun setupUI() {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        buttonPanel.layout = FlowLayout(FlowLayout.LEFT)
        panel.add(JScrollPane(todoJbList))
        panel.add(JScrollPane(doneJbList))
        panel.add(taskInput)
        panel.add(addButton)
        panel.add(doneButton)
        panel.add(removeButton)
        panel.add(clearButton)
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
                doneListModel.addElement(todoJbList.selectedValue)
                todoListModel.removeElementAt(selectedIndex)
            }
        }

        clearButton.addActionListener {
            doneListModel.clearList()
        }
    }
}
