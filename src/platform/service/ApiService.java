package platform.service;

import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.CodeSnippetResponseDTO;

@Service
public class ApiService {

    private static final String HELLO_WORLD_CODE =
            """
            public class Main {
                public static void main(String[] args) {
                    System.out.println("Hello World!");
                }
            }
            """;

    public CodeSnippetResponseDTO getCodeSnippet() {
        CodeSnippet codeSnippet = new CodeSnippet(HELLO_WORLD_CODE);
        return new CodeSnippetResponseDTO(codeSnippet);
    }

}
