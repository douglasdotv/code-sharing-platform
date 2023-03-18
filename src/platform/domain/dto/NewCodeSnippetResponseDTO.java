package platform.domain.dto;

import platform.domain.CodeSnippet;

import java.util.UUID;

public record NewCodeSnippetResponseDTO(UUID id) {

    public NewCodeSnippetResponseDTO(CodeSnippet codeSnippet) {
        this(codeSnippet.getUuid());
    }

}
