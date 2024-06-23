package nl.fwest98.jetbrains.approvaltests.actions

import com.intellij.execution.testframework.AbstractTestProxy
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

abstract class TestTreeItemAction : AnAction() {
    override fun update(e: AnActionEvent) {
        if(e.project == null) {
            e.presentation.isEnabledAndVisible = false
            return
        }

        val testSpec = e.getData(AbstractTestProxy.DATA_KEY)
        if(testSpec == null) {
            e.presentation.isEnabledAndVisible = false
            return
        }

        updateForTest(e, testSpec)
    }

    abstract fun updateForTest(e : AnActionEvent, testSpec: AbstractTestProxy)

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}
