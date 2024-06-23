package nl.fwest98.jetbrains.approvaltests.parsers

import com.intellij.execution.testframework.AbstractTestProxy
import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest

object TestTreeParser : ApprovalTestParser<AbstractTestProxy> {
    override fun parseTests(input: AbstractTestProxy): List<ApprovalTest> {
        if(input.isLeaf)
            return TestParser.parseTests(input)

        return input.children.flatMap { parseTests(it) }.distinct()
    }
}
