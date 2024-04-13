package com.github.gon2gon2.mdtdd

import com.github.gon2gon2.mdtdd.view.UnifiedToolWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class MdtddToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val timerToolWindow = UnifiedToolWindow(project)
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(timerToolWindow.content, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
