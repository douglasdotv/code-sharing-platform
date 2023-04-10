package platform.service;

import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.dto.CodeSnippetDTO;
import platform.domain.dto.NewCodeSnippetRequestDTO;
import platform.domain.dto.NewCodeSnippetResponseDTO;
import platform.exceptionhandling.CodeSharingPlatformException;
import platform.repository.CodeSnippetRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CodeSharingServiceImpl implements CodeSharingService {

    private final CodeSnippetRepository repository;

    public CodeSharingServiceImpl(CodeSnippetRepository repository) {
        this.repository = repository;
    }

    public CodeSnippetDTO getCodeSnippet(UUID uuid) {
        CodeSnippet codeSnippet = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Code " + uuid + " not found."));

        if (codeSnippet.isRestricted()) {
            updateViewsCount(codeSnippet);
            validateCodeSnippet(codeSnippet);
        }

        return new CodeSnippetDTO(codeSnippet);
    }

    public NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetRequestDTO newCodeSnippet) {
        CodeSnippet codeSnippet = new CodeSnippet(newCodeSnippet);
        repository.save(codeSnippet);
        return new NewCodeSnippetResponseDTO(codeSnippet);
    }

    public List<CodeSnippetDTO> getLatestCodeSnippets() {
        List<CodeSnippet> allCodeSnippets = repository.findAll();
        List<CodeSnippet> filteredCodeSnippets = filterCodeSnippets(allCodeSnippets);

        if (filteredCodeSnippets.isEmpty()) {
            throw new CodeSharingPlatformException("No code snippets found.");
        }

        List<CodeSnippet> tenLatestCodeSnippets = getTenLatestCodeSnippets(filteredCodeSnippets);
        return mapCodeSnippetsToJsonDTOs(tenLatestCodeSnippets);
    }

    private void validateCodeSnippet(CodeSnippet codeSnippet) {
        codeSnippet.updateRemainingTimeAndViews();

        boolean isExpiredOrReachedMaxViewsLimit = checkCodeSnippetTimeAndViews(codeSnippet);
        if (isExpiredOrReachedMaxViewsLimit) {
            throw new CodeSharingPlatformException("Code" + codeSnippet.getUuid() + " not found.");
        }
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

    private List<CodeSnippetDTO> mapCodeSnippetsToJsonDTOs(List<CodeSnippet> codeSnippets) {
        List<CodeSnippetDTO> codeSnippetsAsDTOs = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            codeSnippetsAsDTOs.add(new CodeSnippetDTO(codeSnippet));
        }
        return codeSnippetsAsDTOs;
    }

}
