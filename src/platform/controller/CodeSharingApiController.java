package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;
import platform.service.CodeSharingApiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeSharingApiController {

    private final CodeSharingApiService apiService;

    @Autowired
    public CodeSharingApiController(CodeSharingApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/code/{uuid}")
    public ResponseEntity<CodeSnippetResponseDTO> getCode(@PathVariable UUID uuid) {
        CodeSnippetResponseDTO codeSnippet = apiService.getCodeSnippet(uuid);
        return ResponseEntity.ok().body(codeSnippet);
    }

    @GetMapping("/code/latest")
    public ResponseEntity<List<CodeSnippetResponseDTO>> getLatestCodeSnippets() {
        List<CodeSnippetResponseDTO> codeSnippets = apiService.getLatestCodeSnippets();
        return ResponseEntity.ok().body(codeSnippets);
    }

    @PostMapping("/code/new")
    public ResponseEntity<NewCodeSnippetResponseDTO> postCode(@RequestBody NewCodeSnippetDTO newCodeSnippet) {
        NewCodeSnippetResponseDTO codeSnippet = apiService.addCodeSnippet(newCodeSnippet);
        return ResponseEntity.ok().body(codeSnippet);
    }

}
