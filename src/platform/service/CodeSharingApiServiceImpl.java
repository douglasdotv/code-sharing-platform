package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;
import platform.repository.CodeSnippetRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class CodeSharingApiServiceImpl implements CodeSharingApiService {

    private final CodeSnippetRepository repository;

    @Autowired
    public CodeSharingApiServiceImpl(CodeSnippetRepository repository) {
        this.repository = repository;
    }

    public CodeSnippetResponseDTO getCodeSnippet(Long id) {
        CodeSnippet codeSnippet = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Code with id " + id + " not found."));
        return new CodeSnippetResponseDTO(codeSnippet);
    }

    public NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetDTO newCodeSnippet) {
        CodeSnippet codeSnippet = new CodeSnippet(newCodeSnippet);
        repository.save(codeSnippet);
        return new NewCodeSnippetResponseDTO(codeSnippet);
    }

    public List<CodeSnippetResponseDTO> getLatestCodeSnippets() {
        List<CodeSnippet> allCodeSnippets = repository.findAll();

        List<CodeSnippet> latestCodeSnippets = new ArrayList<>();
        for (int i = allCodeSnippets.size() - 1; i >= 0; i--) {
            latestCodeSnippets.add(allCodeSnippets.get(i));
            if (latestCodeSnippets.size() == 10) {
                break;
            }
        }

        return mapCodeSnippetsToDTOs(latestCodeSnippets);
    }

    private List<CodeSnippetResponseDTO> mapCodeSnippetsToDTOs(List<CodeSnippet> codeSnippets) {
        List<CodeSnippetResponseDTO> codeSnippetsAsDTOs = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            codeSnippetsAsDTOs.add(new CodeSnippetResponseDTO(codeSnippet));
        }
        return codeSnippetsAsDTOs;
    }

}
