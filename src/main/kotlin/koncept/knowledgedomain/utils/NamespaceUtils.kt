package koncept.knowledgedomain.utils

import java.util.Locale

object NamespaceUtils {
    fun composeNamespace(parentNamespace: String, namespace: String): String {
        return "$parentNamespace.${nameToPackageFormat(namespace)}"
    }

    private fun nameToPackageFormat(name: String): String {
        return name.trim().split("[.|\\s]+".toRegex()).toTypedArray().joinToString("-").lowercase(Locale.getDefault())
    }
}