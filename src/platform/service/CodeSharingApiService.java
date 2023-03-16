package platform.service;

import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CodeSharingApiService {

    CodeSnippetResponseDTO getCodeSnippet(UUID uuid);

    NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetDTO codeSnippet);

    List<CodeSnippetResponseDTO> getLatestCodeSnippets();

}
