package koncept.knowledgedomain

import koncept.knowledgedomain.web.CreateKnowledgeRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class KnowledgeServiceTests {

    @Autowired
    lateinit var knowledgeService: KnowledgeService

    @Test
    fun createKnowledge_rootKnowledge() {
        val createKnowledgeRequest = CreateKnowledgeRequest(name = "Root knowledge", uri = "koncept")
        val createRootKnowledge = knowledgeService.createKnowledge(createKnowledgeRequest)
        assertNotNull(createRootKnowledge)
        with(createRootKnowledge) {
            assertNotNull(id)
            assertNull(parentKnowledge)
            assertEquals("Root knowledge", name)
            assertEquals("koncept", uri)
        }
    }

    @Test
    fun createKnowledge_childrenKnowledge() {
        val createKnowledgeRequest = CreateKnowledgeRequest(name = "Root knowledge for children", uri = "koncept")
        val rootKnowledge = knowledgeService.createKnowledge(createKnowledgeRequest)
        val createChildrenKnowledgeRequest = CreateKnowledgeRequest(parentId = rootKnowledge.id, name = "Children knowledge", uri = "koncept")
        val childrenKnowledge = knowledgeService.createKnowledge(createChildrenKnowledgeRequest)
        assertNotNull(childrenKnowledge)
        with(childrenKnowledge) {
            assertNotNull(id)
            assertNotNull(parentKnowledge)
            assertEquals("Root knowledge for children", parentKnowledge!!.name)
            assertEquals("Children knowledge", name)
            assertEquals("koncept", uri)
        }
    }

    @Test
    fun getKnowledge_ok() {
        val createKnowledgeRequest = CreateKnowledgeRequest(name = "Root knowledge for get", uri = "koncept")
        val knowledgeId = knowledgeService.createKnowledge(createKnowledgeRequest).id
        val fetchedKnowledge = knowledgeService.getKnowledge(knowledgeId)
        assertNotNull(fetchedKnowledge)
        assertNull(fetchedKnowledge.parentKnowledge)
        assertEquals("Root knowledge for get", fetchedKnowledge.name)
    }

    @Test
    fun getKnowledge_notExisting() {
        val exception = assertThrows<NoSuchElementException> {
            knowledgeService.getKnowledge(123L)
        }
        assertNotNull(exception)
        assertEquals("No value present", exception.message)
    }
}