package platform.service;

import platform.domain.CodeSnippetResponseDTO;
import platform.domain.NewCodeSnippetDTO;
import platform.domain.NewCodeSnippetResponseDTO;

public interface CodeSharingApiService {

    CodeSnippetResponseDTO getCodeSnippet();
    NewCodeSnippetResponseDTO updateCodeSnippet(NewCodeSnippetDTO codeSnippet);

}
