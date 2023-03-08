package platform.domain;

public record CodeSnippetResponseDTO(String code, String date) {

    public CodeSnippetResponseDTO(CodeSnippet codeSnippet) {
        this(codeSnippet.getCode(), codeSnippet.getDate());
    }

}
