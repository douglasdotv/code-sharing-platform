package platform.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.domain.dto.CodeSnippetDTO;
import platform.domain.dto.NewCodeSnippetRequestDTO;
import platform.domain.dto.NewCodeSnippetResponseDTO;
import platform.service.CodeSharingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeSharingRestController {

    private final CodeSharingService service;

    public CodeSharingRestController(CodeSharingService service) {
        this.service = service;
    }

    @GetMapping("/code/{uuid}")
    public ResponseEntity<CodeSnippetDTO> getCode(@PathVariable UUID uuid) {
        CodeSnippetDTO codeSnippet = service.getCodeSnippet(uuid);
        return ResponseEntity.ok().body(codeSnippet);
    }

    @GetMapping("/code/latest")
    public ResponseEntity<List<CodeSnippetDTO>> getLatestCodeSnippets() {
        List<CodeSnippetDTO> codeSnippets = service.getLatestCodeSnippets();
        return ResponseEntity.ok().body(codeSnippets);
    }

    @PostMapping("/code/new")
    public ResponseEntity<NewCodeSnippetResponseDTO> postCode(@RequestBody NewCodeSnippetRequestDTO newCodeSnippet) {
        NewCodeSnippetResponseDTO codeSnippet = service.addCodeSnippet(newCodeSnippet);
        return ResponseEntity.ok().body(codeSnippet);
    }

}
