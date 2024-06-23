package nl.fwest98.jetbrains.approvaltests.parsers

import com.intellij.openapi.diagnostic.thisLogger
import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest
import java.nio.file.InvalidPathException
import kotlin.io.path.Path
import kotlin.io.path.name

/**
 * Parser for the error messages from the test output
 */
object TestErrorParser : ApprovalTestParser<String> {
    override fun parseTests(input: String): List<ApprovalTest> {
        if(!input.contains("Failed Approval"))
            return emptyList()

        val approvedString = input.substringAfter("Approved:").substringBefore('\n').trim()
        val receivedString = input.substringAfter("Received:").substringBefore('\n').trim()

        val approvedPath = try { Path(approvedString) } catch (_: InvalidPathException) { return emptyList() }
        val receivedPath = try { Path(receivedString) } catch (_: InvalidPathException) { return emptyList() }

        val testName = approvedPath.name.substringBefore(".approved")
        if(!receivedPath.name.startsWith(testName)) {
            thisLogger().warn("Parsed Approval Test does not share base name!\n$input")
            return emptyList()
        }

        return listOf(
            ApprovalTest(
                testName, receivedPath, approvedPath
            )
        )
    }
}
