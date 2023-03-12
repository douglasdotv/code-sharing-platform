package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.view.CodeSnippetViewDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeSharingViewServiceImpl implements CodeSharingViewService {

    private final CodeSharingApiService apiService;

    @Autowired
    public CodeSharingViewServiceImpl(CodeSharingApiService apiService) {
        this.apiService = apiService;
    }

    public CodeSnippetViewDTO getCodeSnippet(String id) {
        CodeSnippetResponseDTO codeSnippet = apiService.getCodeSnippet(id);
        return new CodeSnippetViewDTO(codeSnippet);
    }

    public List<CodeSnippetViewDTO> getLatestCodeSnippets() {
        List<CodeSnippetResponseDTO> codeSnippets = apiService.getLatestCodeSnippets();
        return mapCodeSnippetsToViewDtos(codeSnippets);
    }

    private List<CodeSnippetViewDTO> mapCodeSnippetsToViewDtos
            (List<CodeSnippetResponseDTO> codeSnippetsAsResponseDtos) {

        List<CodeSnippetViewDTO> codeSnippetsAsViewDtos = new ArrayList<>();
        for (CodeSnippetResponseDTO codeSnippetAsResponseDto : codeSnippetsAsResponseDtos) {
            codeSnippetsAsViewDtos.add(new CodeSnippetViewDTO(codeSnippetAsResponseDto));
        }
        return codeSnippetsAsViewDtos;
    }

}
