package platform.service;

import platform.domain.dto.view.CodeSnippetViewDTO;

import java.util.List;

public interface CodeSharingViewService {

    CodeSnippetViewDTO getCodeSnippet(String id);

    List<CodeSnippetViewDTO> getLatestCodeSnippets();

}
