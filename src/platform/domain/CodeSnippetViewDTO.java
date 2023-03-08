package platform.domain;

public record CodeSnippetViewDTO(String code, String date) {

    public CodeSnippetViewDTO(CodeSnippetResponseDTO codeSnippet) {
        this(codeSnippet.code(), codeSnippet.date());
    }

}
