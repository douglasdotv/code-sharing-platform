package platform.service;

import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;


@Service
public class CodeSharingApiServiceImpl implements CodeSharingApiService {

    private final CodeSnippet codeSnippet = new CodeSnippet();

    public CodeSnippetResponseDTO getCodeSnippet() {
        return new CodeSnippetResponseDTO(codeSnippet);
    }

    public NewCodeSnippetResponseDTO updateCodeSnippet(NewCodeSnippetDTO codeSnippet) {
        this.codeSnippet.update(codeSnippet.code());
        return new NewCodeSnippetResponseDTO();
    }

}
