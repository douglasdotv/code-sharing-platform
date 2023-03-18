package platform.domain.dto.api;

import platform.domain.CodeSnippet;

public record CodeSnippetResponseDTO(String code, String date, long time, long views) {

    public CodeSnippetResponseDTO(CodeSnippet codeSnippet) {
        this(
                codeSnippet.getCode(),
                codeSnippet.getCreationDate(),
                codeSnippet.getRemainingTime(),
                codeSnippet.getRemainingViews()
        );
    }

}
