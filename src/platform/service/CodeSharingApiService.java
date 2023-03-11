package platform.service;

import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;

public interface CodeSharingApiService {

    CodeSnippetResponseDTO getCodeSnippet();
    NewCodeSnippetResponseDTO updateCodeSnippet(NewCodeSnippetDTO codeSnippet);

}
