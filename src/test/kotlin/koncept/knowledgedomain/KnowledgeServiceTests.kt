package koncept.knowledgedomain

import koncept.knowledgedomain.web.dto.request.CreateKnowledgeRequest
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
        val createKnowledgeRequest = CreateKnowledgeRequest(name = "Mathematics", namespace = "mathematics")
        val createRootKnowledge = knowledgeService.createKnowledge(createKnowledgeRequest)
        assertNotNull(createRootKnowledge)
        with(createRootKnowledge) {
            assertNotNull(id)
            assertNull(parentKnowledge)
            assertEquals("Mathematics", name)
            assertEquals("koncept.mathematics", namespace)
        }
    }

    @Test
    fun createKnowledge_childrenKnowledge() {
        val createKnowledgeRequest = CreateKnowledgeRequest(name = "Chemistry", namespace = "chemistry")
        val rootKnowledge = knowledgeService.createKnowledge(createKnowledgeRequest)
        val createChildrenKnowledgeRequest = CreateKnowledgeRequest(parentId = rootKnowledge.id, name = "Inorganic chemistry", namespace = "inorganic")
        val childrenKnowledge = knowledgeService.createKnowledge(createChildrenKnowledgeRequest)
        assertNotNull(childrenKnowledge)
        with(childrenKnowledge) {
            assertNotNull(id)
            assertEquals("Inorganic chemistry", name)
            assertEquals("koncept.chemistry.inorganic", namespace)
            assertNotNull(parentKnowledge)
            assertEquals("Chemistry", parentKnowledge!!.name)
            assertEquals("koncept.chemistry", parentKnowledge!!.namespace)
        }
    }

    @Test
    fun getKnowledge_ok() {
        val createKnowledgeRequest = CreateKnowledgeRequest(name = "Biology", namespace = "biology")
        val knowledgeId = knowledgeService.createKnowledge(createKnowledgeRequest).id
        val fetchedKnowledge = knowledgeService.getKnowledge(knowledgeId)
        assertNotNull(fetchedKnowledge)
        assertNull(fetchedKnowledge.parentKnowledge)
        assertEquals("Biology", fetchedKnowledge.name)
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