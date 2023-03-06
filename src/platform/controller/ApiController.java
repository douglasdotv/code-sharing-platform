package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.domain.CodeSnippetResponseDTO;
import platform.service.ApiService;

@RestController
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api/code")
    public ResponseEntity<CodeSnippetResponseDTO> getCode() {
        CodeSnippetResponseDTO codeSnippet = apiService.getCodeSnippet();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(codeSnippet);
    }

}
