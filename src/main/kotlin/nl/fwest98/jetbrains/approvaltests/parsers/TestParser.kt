package nl.fwest98.jetbrains.approvaltests.parsers

import com.intellij.execution.testframework.AbstractTestProxy
import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest

object TestParser : ApprovalTestParser<AbstractTestProxy> {
    override fun parseTests(input: AbstractTestProxy): List<ApprovalTest> {
        val sources = listOf(input.errorMessage, input.stacktrace).filterNotNull()
        val tests = sources.flatMap { TestErrorParser.parseTests(it) }.distinct()

        return tests
    }
}
