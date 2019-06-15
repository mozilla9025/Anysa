package app.anysa.util.annotation

import androidx.annotation.LayoutRes
import app.anysa.ui.base.abs.AbsViewModel


object AnnotationUtil {

    fun findViewModelClass(any: Any): Class<out AbsViewModel> {
        return any.javaClass.getAnnotation(RequiresViewModel::class.java).value.java
    }

    @LayoutRes
    fun findViewId(any: Any): Int {
        return if (any.javaClass.isAnnotationPresent(RequiresView::class.java))
            any.javaClass.getAnnotation(RequiresView::class.java).value
        else
            -1
    }

    fun hasViewModel(any: Any) = any.javaClass.isAnnotationPresent(RequiresViewModel::class.java)

    fun hasInject(any: Any) = any.javaClass.isAnnotationPresent(RequiresInject::class.java)

    fun isNavFragment(any: Any) = any.javaClass.isAnnotationPresent(NavFragment::class.java)

}