package platform.service;

import platform.domain.dto.view.CodeSnippetViewDTO;

import java.util.List;
import java.util.UUID;

public interface CodeSharingViewService {

    CodeSnippetViewDTO getCodeSnippet(UUID uuid);

    List<CodeSnippetViewDTO> getLatestCodeSnippets();

}
