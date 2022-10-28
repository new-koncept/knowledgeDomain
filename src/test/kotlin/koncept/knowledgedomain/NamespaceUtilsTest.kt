package koncept.knowledgedomain

import koncept.knowledgedomain.utils.NamespaceUtils.composeNamespace
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NamespaceUtilsTest {

    @Test
    fun composeNamespace_rootKnowledge() {
        assertEquals("koncept.chemistry", composeNamespace(parentNamespace = "koncept", namespace =  "chemistry"))
        assertEquals("koncept.chemistry.inorganic", composeNamespace("koncept.chemistry", "inorganic"))
        assertEquals("koncept.martial-arts", composeNamespace("koncept", "  martial.arts  "))
        assertEquals("koncept.martial-arts", composeNamespace("koncept", "  martial. . arts  "))
    }
}