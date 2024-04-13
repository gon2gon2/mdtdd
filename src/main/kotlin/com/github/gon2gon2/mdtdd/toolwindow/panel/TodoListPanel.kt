package com.github.gon2gon2.mdtdd.toolwindow.panel

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBList
import java.awt.FlowLayout
import javax.swing.AbstractListModel
import javax.swing.BoxLayout
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField

class CustomListModel(private val backingList: MutableList<String>) : AbstractListModel<String>() {
    override fun getSize(): Int = backingList.size

    override fun getElementAt(index: Int): String = backingList[index]

    fun addElement(element: String) {
        backingList.add(element)
        fireIntervalAdded(this, backingList.size - 1, backingList.size - 1)
    }

    fun removeElement(element: String) {
        val index = backingList.indexOf(element)
        if (index != -1) {
            backingList.removeAt(index)
            fireIntervalRemoved(this, index, index)
        }
    }

    fun removeElement(index: Int) {
        if (index != -1) {
            backingList.removeAt(index)
            fireIntervalRemoved(this, index, index)
        }
    }
}


class TodoListPanel(project: Project) {
    val panel = JPanel()

    private val todoList = mutableListOf<String>()
    private val todoListModel = CustomListModel(todoList)
    private val todoJbList = JBList(todoListModel)

    private val alreadyDoneListModel = DefaultListModel<String>()
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
                todoListModel.removeElement(selectedIndex)
            }
        }
//
//        doneButton.addActionListener {
//            val selectedIndex = shouldBeDoneList.selectedIndex
//            if (selectedIndex != -1) {
//                alreadyDoneListModel.addElement(shouldBeDoneList.selectedValue)
//                shouldBeDoneListModel.removeElementAt(selectedIndex)
//            }
//        }
    }
}
