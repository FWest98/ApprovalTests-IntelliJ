package nl.fwest98.jetbrains.approvaltests.resolvers

import nl.fwest98.jetbrains.approvaltests.models.ApprovalTest
import kotlin.io.path.copyTo

class ApproveResolver : ApprovalTestResolver {
    override fun resolveTest(test: ApprovalTest) {
        // Approving is a matter of copying the received to the approved path
        if(test.received == null || test.approved == null) return
        test.received.copyTo(test.approved, overwrite = true)
    }
}
