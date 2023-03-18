package platform.service;

import platform.domain.dto.CodeSnippetDTO;
import platform.domain.dto.NewCodeSnippetRequestDTO;
import platform.domain.dto.NewCodeSnippetResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CodeSharingService {

    CodeSnippetDTO getCodeSnippet(UUID uuid);

    NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetRequestDTO codeSnippet);

    List<CodeSnippetDTO> getLatestCodeSnippets();

}
