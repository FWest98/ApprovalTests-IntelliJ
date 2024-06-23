package nl.fwest98.jetbrains.approvaltests.resolvers

import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest

interface ApprovalTestResolver {
    fun resolveTest(test: ApprovalTest)
    fun resolveTests(tests: List<ApprovalTest>) {
        tests.forEach { resolveTest(it) }
    }
}
