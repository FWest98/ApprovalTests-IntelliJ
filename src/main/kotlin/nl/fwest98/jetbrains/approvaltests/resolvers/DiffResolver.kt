package nl.fwest98.jetbrains.approvaltests.resolvers

import com.intellij.diff.DiffContentFactory
import com.intellij.diff.DiffDialogHints
import com.intellij.diff.DiffManager
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest

class DiffResolver(
    val project: Project
) : ApprovalTestResolver {
    override fun resolveTest(test: ApprovalTest) {
        if(test.approved == null) return

        val diffFiles = listOf(test.received, test.approved)
        val diffContents = diffFiles
            .map { it?.let { VfsUtil.findFile(it, true) } }
            .map { it?.let { DiffContentFactory.getInstance().create(project, it) } ?: DiffContentFactory.getInstance().createEmpty() }

        val request = SimpleDiffRequest(test.name, diffContents, listOf("Received", "Approved"))
        DiffManager.getInstance().showDiff(project, request, DiffDialogHints.DEFAULT)
    }
}
