package koncept.knowledgedomain.web.mapper

import koncept.knowledgedomain.Knowledge
import koncept.knowledgedomain.web.dto.KnowledgeDto

object KnowledgeMapper {
    fun entityToDto(knowledgeEntity: Knowledge): KnowledgeDto {
        val parentDto = knowledgeEntity.parentKnowledge?.let {
            entityToDto(it)
        }
        return KnowledgeDto(knowledgeEntity.id, parentDto, knowledgeEntity.name, knowledgeEntity.uri)
    }
}