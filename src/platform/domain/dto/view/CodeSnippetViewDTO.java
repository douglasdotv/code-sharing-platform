package platform.domain.dto.view;

import platform.domain.dto.api.CodeSnippetResponseDTO;

public record CodeSnippetViewDTO(String code, String date) {

    public CodeSnippetViewDTO(CodeSnippetResponseDTO codeSnippet) {
        this(codeSnippet.code(), codeSnippet.date());
    }

}
