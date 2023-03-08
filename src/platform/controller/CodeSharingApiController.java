package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.domain.CodeSnippetResponseDTO;
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

}
