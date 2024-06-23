package nl.fwest98.jetbrains.approvaltests.actions

import com.intellij.execution.testframework.AbstractTestProxy
import com.intellij.openapi.actionSystem.AnActionEvent
import nl.fwest98.jetbrains.approvaltests.parsers.TestTreeParser
import nl.fwest98.jetbrains.approvaltests.resolvers.ApproveResolver

class ApproveTestAction : TestTreeItemAction() {
    override fun updateForTest(e: AnActionEvent, testSpec: AbstractTestProxy) {
        val approvalTests = TestTreeParser.parseTests(testSpec)

        e.presentation.isVisible = testSpec.isLeaf
        e.presentation.isEnabled = approvalTests.isNotEmpty()
    }

    override fun actionPerformed(e: AnActionEvent) {
        val testSpec = e.getData(AbstractTestProxy.DATA_KEY) ?: return
        val approvalTests = TestTreeParser.parseTests(testSpec)

        ApproveResolver().resolveTests(approvalTests)
    }
}
