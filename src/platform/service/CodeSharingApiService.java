package platform.service;

import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;

import java.util.List;

public interface CodeSharingApiService {

    CodeSnippetResponseDTO getCodeSnippet(String id);

    NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetDTO codeSnippet);

    List<CodeSnippetResponseDTO> getLatestCodeSnippets();

}
