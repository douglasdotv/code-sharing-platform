package platform.service;

import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.CodeSnippetResponseDTO;


@Service
public class CodeSharingApiServiceImpl implements CodeSharingApiService {

    private final CodeSnippet codeSnippet = new CodeSnippet();

    public CodeSnippetResponseDTO getCodeSnippet() {
        return new CodeSnippetResponseDTO(codeSnippet);
    }

}
