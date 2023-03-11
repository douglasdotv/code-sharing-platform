package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;
import platform.service.CodeSharingApiService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeSharingApiController {

    private final CodeSharingApiService apiService;

    @Autowired
    public CodeSharingApiController(CodeSharingApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/code")
    public ResponseEntity<CodeSnippetResponseDTO> getCode() {
        CodeSnippetResponseDTO codeSnippet = apiService.getCodeSnippet();

        return ResponseEntity.ok().body(codeSnippet);
    }

    @PostMapping("/code/new")
    public ResponseEntity<NewCodeSnippetResponseDTO> postCode(@RequestBody NewCodeSnippetDTO newCodeSnippet) {
        NewCodeSnippetResponseDTO codeSnippet = apiService.updateCodeSnippet(newCodeSnippet);

        return ResponseEntity.ok().body(codeSnippet);
    }

}
