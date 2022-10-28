package koncept.knowledgedomain.web.mapper

import koncept.knowledgedomain.persistence.Knowledge
import koncept.knowledgedomain.web.dto.KnowledgeDto

object KnowledgeMapper {
    fun entityToDto(knowledgeEntity: Knowledge, knowledgeChildrenEntities: MutableList<Knowledge>?): KnowledgeDto {
        val parentKnowledgeDto = knowledgeEntity.parentKnowledge?.let { entityToDto(it, null) }
        val knowledgeChildrenDtos = knowledgeChildrenEntities?.map {
            entityToDto(it, null)
        }?.toMutableList() ?: mutableListOf()
        return KnowledgeDto(knowledgeEntity.id, parentKnowledgeDto, knowledgeEntity.name, knowledgeEntity.namespace, knowledgeChildrenDtos)
    }
}