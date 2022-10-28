package koncept.knowledgedomain.web.mapper

import koncept.knowledgedomain.persistence.Knowledge
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class KnowledgeMapperTest {

    @Test
    fun entityToDto_rootEntity() {
        val knowledgeEntity = createKnowledgeEntity()
        val knowledgeDto = KnowledgeMapper.entityToDto(knowledgeEntity, null)
        assertEquals(0L, knowledgeDto.id)
        assertEquals("Mathematics", knowledgeDto.name)
        assertEquals("koncept.mathematics", knowledgeDto.namespace)
    }

    @Test
    fun entityToDto_childEntity() {
        val rootKnowledgeEntity = createKnowledgeEntity()
        val knowledgeEntity = createKnowledgeEntity(id = 1L, name = "Geometry", namespace = "koncept.mathematics.geometry", parentKnowledge = rootKnowledgeEntity)
        val knowledgeDto = KnowledgeMapper.entityToDto(rootKnowledgeEntity, mutableListOf(knowledgeEntity))
        assertEquals(0L, knowledgeDto.id)
        assertEquals("Mathematics", knowledgeDto.name)
        assertEquals("koncept.mathematics", knowledgeDto.namespace)
        assertEquals(1, knowledgeDto.children!!.size)
    }

    private fun createKnowledgeEntity(id: Long = 0L, name: String = "Mathematics", namespace: String = "koncept.mathematics", parentKnowledge: Knowledge? = null): Knowledge {
        return Knowledge().apply {
            this.id = id
            this.name = name
            this.namespace = namespace
            this.parentKnowledge = parentKnowledge
        }
    }
}