package platform.domain;

public record CodeSnippetResponseDTO(String code) {

    public CodeSnippetResponseDTO(CodeSnippet codeSnippet) {
        this(codeSnippet.getCode());
    }

}
