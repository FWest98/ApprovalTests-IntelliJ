package nl.fwest98.jetbrains.approvaltests.parsers

import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest

interface ApprovalTestParser<T> {
    fun parseTests(input: T): List<ApprovalTest>
}
