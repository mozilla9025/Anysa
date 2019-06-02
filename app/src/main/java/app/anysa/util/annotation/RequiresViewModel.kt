package app.anysa.util.annotation

import app.anysa.ui.base.abs.AbsViewModel
import kotlin.reflect.KClass

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(value = AnnotationRetention.RUNTIME)
annotation class RequiresViewModel(val value : KClass<out AbsViewModel>)
