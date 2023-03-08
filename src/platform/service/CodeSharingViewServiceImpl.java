package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.CodeSnippetResponseDTO;
import platform.domain.CodeSnippetViewDTO;

@Service
public class CodeSharingViewServiceImpl implements CodeSharingViewService {

    private final CodeSharingApiService apiService;

    @Autowired
    public CodeSharingViewServiceImpl(CodeSharingApiService apiService) {
        this.apiService = apiService;
    }

    public CodeSnippetViewDTO getCodeSnippet() {
        CodeSnippetResponseDTO codeSnippet = apiService.getCodeSnippet();
        return new CodeSnippetViewDTO(codeSnippet);
    }

}
