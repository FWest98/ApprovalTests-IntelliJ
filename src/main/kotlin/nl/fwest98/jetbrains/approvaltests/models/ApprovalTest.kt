package nl.fwest98.jetbrains.approvaltests.models

import java.nio.file.Path

data class ApprovalTest(
    val name: String,
    val received: Path?,
    val approved: Path?,
)
