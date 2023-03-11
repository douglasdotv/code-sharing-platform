package platform.domain.dto.api;

import platform.domain.CodeSnippet;

public record CodeSnippetResponseDTO(String code, String date) {

    public CodeSnippetResponseDTO(CodeSnippet codeSnippet) {
        this(codeSnippet.getCode(), codeSnippet.getDate());
    }

}
