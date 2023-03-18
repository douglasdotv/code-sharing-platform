package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.dto.view.CodeSnippetViewDTO;
import platform.repository.CodeSnippetRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CodeSharingViewServiceImpl implements CodeSharingViewService {

    private final CodeSnippetRepository repository;

    @Autowired
    public CodeSharingViewServiceImpl(CodeSnippetRepository repository) {
        this.repository = repository;
    }

    public CodeSnippetViewDTO getCodeSnippet(UUID uuid) {
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

        return new CodeSnippetViewDTO(codeSnippet);
    }

    public List<CodeSnippetViewDTO> getLatestCodeSnippets() {
        List<CodeSnippet> allCodeSnippets = repository.findAll();
        List<CodeSnippet> filteredCodeSnippets = filterCodeSnippets(allCodeSnippets);

        if (filteredCodeSnippets.isEmpty()) {
            throw new EntityNotFoundException("No code snippets found.");
        }

        List<CodeSnippet> tenLatestCodeSnippets = getTenLatestCodeSnippets(filteredCodeSnippets);
        return mapCodeSnippetsToViewDTOs(tenLatestCodeSnippets);
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
        List<CodeSnippet> tenLatestCodeSnippets = new ArrayList<>();
        for (int i = allCodeSnippets.size() - 1; i >= 0; i--) {
            if (tenLatestCodeSnippets.size() == 10) {
                break;
            }
            tenLatestCodeSnippets.add(allCodeSnippets.get(i));
        }
        return tenLatestCodeSnippets;
    }

    private List<CodeSnippetViewDTO> mapCodeSnippetsToViewDTOs(List<CodeSnippet> codeSnippets) {
        List<CodeSnippetViewDTO> codeSnippetViewDTOs = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            codeSnippetViewDTOs.add(new CodeSnippetViewDTO(codeSnippet));
        }
        return codeSnippetViewDTOs;
    }

}
