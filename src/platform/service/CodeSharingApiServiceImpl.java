package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;
import platform.repository.CodeSnippetRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CodeSharingApiServiceImpl implements CodeSharingApiService {

    private final CodeSnippetRepository repository;

    @Autowired
    public CodeSharingApiServiceImpl(CodeSnippetRepository repository) {
        this.repository = repository;
    }

    public CodeSnippetResponseDTO getCodeSnippet(UUID uuid) {
        CodeSnippet codeSnippet = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Code " + uuid + " not found."));

        updateViewsCount(codeSnippet);

        codeSnippet.updateRemainingTimeAndViews();

        boolean isExpiredOrReachedMaxViewsLimit = false;
        if (codeSnippet.isRestricted()) {
            isExpiredOrReachedMaxViewsLimit = checkCodeSnippetTimeAndViews(codeSnippet);
        }

        if (isExpiredOrReachedMaxViewsLimit) {
            throw new EntityNotFoundException("Code " + codeSnippet.getUuid() + " not found.");
        }

        return new CodeSnippetResponseDTO(codeSnippet);
    }

    public NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetDTO newCodeSnippet) {
        CodeSnippet codeSnippet = new CodeSnippet(newCodeSnippet);
        repository.save(codeSnippet);
        return new NewCodeSnippetResponseDTO(codeSnippet);
    }

    public List<CodeSnippetResponseDTO> getLatestCodeSnippets() {
        List<CodeSnippet> allCodeSnippets = repository.findAll();
        List<CodeSnippet> filteredCodeSnippets = filterCodeSnippets(allCodeSnippets);

        if (filteredCodeSnippets.isEmpty()) {
            throw new EntityNotFoundException("No code snippets found.");
        }

        List<CodeSnippet> tenLatestCodeSnippets = getTenLatestCodeSnippets(filteredCodeSnippets);
        return mapCodeSnippetsToJsonDTOs(tenLatestCodeSnippets);
    }

    private boolean checkCodeSnippetTimeAndViews(CodeSnippet codeSnippet) {
        if (codeSnippet.isExpired() || codeSnippet.isViewsLimitReached()) {
            repository.delete(codeSnippet);
            return true;
        }
        return false;
    }

    private void updateViewsCount(CodeSnippet codeSnippet) {
        codeSnippet.incrementViewsCount();
        repository.save(codeSnippet);
    }

    private List<CodeSnippet> filterCodeSnippets(List<CodeSnippet> codeSnippets) {
        List<CodeSnippet> filteredCodeSnippets = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            if (!codeSnippet.isRestricted()) {
                filteredCodeSnippets.add(codeSnippet);
            }
        }
        return filteredCodeSnippets;
    }

    private List<CodeSnippet> getTenLatestCodeSnippets(List<CodeSnippet> allCodeSnippets) {
        List<CodeSnippet> latestCodeSnippets = new ArrayList<>();
        for (int i = allCodeSnippets.size() - 1; i >= 0; i--) {
            latestCodeSnippets.add(allCodeSnippets.get(i));
            if (latestCodeSnippets.size() == 10) {
                break;
            }
        }

        return latestCodeSnippets;
    }

    private List<CodeSnippetResponseDTO> mapCodeSnippetsToJsonDTOs(List<CodeSnippet> codeSnippets) {
        List<CodeSnippetResponseDTO> codeSnippetsAsDTOs = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            codeSnippetsAsDTOs.add(new CodeSnippetResponseDTO(codeSnippet));
        }

        return codeSnippetsAsDTOs;
    }

}
