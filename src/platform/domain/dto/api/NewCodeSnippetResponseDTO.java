package platform.domain.dto.api;

import platform.domain.CodeSnippet;

public record NewCodeSnippetResponseDTO(String id) {

    public NewCodeSnippetResponseDTO(CodeSnippet codeSnippet) {
        this(codeSnippet.getId());
    }

}
