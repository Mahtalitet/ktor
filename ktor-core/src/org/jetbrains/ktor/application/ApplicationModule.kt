package org.jetbrains.ktor.application

import org.jetbrains.ktor.util.*

@Deprecated("Use module function instead")
abstract class ApplicationModule : ApplicationFeature<Application, Unit, ApplicationModule> {
    override val key = AttributeKey<ApplicationModule>(javaClass.simpleName)

    final override fun install(pipeline: Application, configure: Unit.() -> Unit): ApplicationModule {
        Unit.configure()
        pipeline.install()
        return this
    }

    protected abstract fun Application.install()
}

@Deprecated("Use module function instead")
fun module(body: Application.() -> Unit) = object : ApplicationModule() {
    override fun Application.install(): Unit = body(this)
}