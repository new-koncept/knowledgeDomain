package koncept.knowledgedomain.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import koncept.knowledgedomain.Knowledge
import koncept.knowledgedomain.KnowledgeService
import koncept.knowledgedomain.web.dto.KnowledgeDto
import koncept.knowledgedomain.web.mapper.KnowledgeMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/knowledge")
class KnowledgeController(private val knowledgeService: KnowledgeService) {

    @GetMapping("/{id}")
    @Operation(
        responses = [
            ApiResponse(
                description = "Get knowledge by id", responseCode = "200", content = [Content(
                    mediaType = "application/json", schema = Schema(
                        implementation = Knowledge::class
                    )
                )]
            ),
            ApiResponse(
                description = "No knowledge for provided id", responseCode = "404"
            )]
    )
    fun getKnowledge(@PathVariable id: Long): ResponseEntity<KnowledgeDto> {
        val knowledge = knowledgeService.getKnowledge(id)
        val knowledgeDto = KnowledgeMapper.entityToDto(knowledge)
        return ResponseEntity<KnowledgeDto>(knowledgeDto, HttpStatus.OK)
    }

    @PostMapping("/")
    @Operation(
        responses = [
            ApiResponse(
                description = "Creates new knowledge", responseCode = "201", content = [Content(
                    mediaType = "application/json", schema = Schema(
                        implementation = Knowledge::class
                    )
                )]
            )]
    )

    fun createKnowledge(@RequestBody createKnowledgeRequest: CreateKnowledgeRequest): ResponseEntity<KnowledgeDto> {
        val knowledge = knowledgeService.createKnowledge(createKnowledgeRequest)
        val knowledgeDto = KnowledgeMapper.entityToDto(knowledge)
        return ResponseEntity<KnowledgeDto>(knowledgeDto, HttpStatus.CREATED)
    }
}