package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.domain.CodeSnippetResponseDTO;
import platform.domain.NewCodeSnippetDTO;
import platform.domain.NewCodeSnippetResponseDTO;
import platform.service.CodeSharingApiService;

@RestController
@RequestMapping("/api")
public class CodeSharingApiController {

    private final CodeSharingApiService apiService;

    @Autowired
    public CodeSharingApiController(CodeSharingApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/code")
    public ResponseEntity<CodeSnippetResponseDTO> getCode() {
        CodeSnippetResponseDTO codeSnippet = apiService.getCodeSnippet();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(codeSnippet);
    }

    @PostMapping("/code/new")
    public ResponseEntity<NewCodeSnippetResponseDTO> postCode(@RequestBody NewCodeSnippetDTO newCodeSnippet) {
        NewCodeSnippetResponseDTO codeSnippet = apiService.updateCodeSnippet(newCodeSnippet);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(codeSnippet);
    }

}
