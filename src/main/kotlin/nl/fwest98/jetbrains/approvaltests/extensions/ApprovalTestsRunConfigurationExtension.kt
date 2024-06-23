package nl.fwest98.jetbrains.approvaltests.extensions

import com.intellij.execution.RunConfigurationExtension
import com.intellij.execution.configurations.JavaParameters
import com.intellij.execution.configurations.RunConfigurationBase
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.openapi.externalSystem.service.execution.ExternalSystemRunConfiguration

class ApprovalTestsRunConfigurationExtension : RunConfigurationExtension() {
    override fun isApplicableFor(configuration: RunConfigurationBase<*>) = true
    override fun <T : RunConfigurationBase<*>> updateJavaParameters(
        configuration: T,
        parameters: JavaParameters,
        runnerSettings: RunnerSettings?
    ) {
        parameters.addEnv(ENVIRONMENT_VARIABLE_NAME, PREFERRED_REPORTER)

        if(configuration is ExternalSystemRunConfiguration) {
            configuration.settings.env = configuration.settings.env + (ENVIRONMENT_VARIABLE_NAME to PREFERRED_REPORTER)
        }
    }

    companion object {
        const val ENVIRONMENT_VARIABLE_NAME = "APPROVAL_TESTS_USE_REPORTER"
        const val PREFERRED_REPORTER = "QuietReporter"
    }
}
