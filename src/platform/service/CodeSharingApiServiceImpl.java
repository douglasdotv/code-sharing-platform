package platform.service;

import org.springframework.stereotype.Service;
import platform.domain.CodeSnippet;
import platform.domain.dto.api.CodeSnippetResponseDTO;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.domain.dto.api.NewCodeSnippetResponseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CodeSharingApiServiceImpl implements CodeSharingApiService {

    private final List<CodeSnippet> codeSnippetList = new ArrayList<>();

    public CodeSnippetResponseDTO getCodeSnippet(String id) {
        int idAsInt = Integer.parseInt(id);
        CodeSnippet codeSnippet = codeSnippetList.get(idAsInt - 1);
        return new CodeSnippetResponseDTO(codeSnippet);
    }

    public NewCodeSnippetResponseDTO addCodeSnippet(NewCodeSnippetDTO newCodeSnippet) {
        String nextCodeSnippetId = getNextCodeSnippetId();

        CodeSnippet codeSnippet = new CodeSnippet(newCodeSnippet);
        codeSnippet.setId(nextCodeSnippetId);
        codeSnippetList.add(codeSnippet);

        return new NewCodeSnippetResponseDTO(codeSnippet);
    }

    public List<CodeSnippetResponseDTO> getLatestCodeSnippets() {
        List<CodeSnippet> latestCodeSnippets = getLatestCodeSnippetsList();

        List<CodeSnippetResponseDTO> latestCodeSnippetsAsDtos = mapCodeSnippetsToDtos(latestCodeSnippets);
        Collections.reverse(latestCodeSnippetsAsDtos);

        return latestCodeSnippetsAsDtos;
    }

    private String getNextCodeSnippetId() {
        return String.valueOf(Integer.parseInt(getLatestCodeSnippetId()) + 1);
    }

    private String getLatestCodeSnippetId() {
        return codeSnippetList.isEmpty() ? "0" : codeSnippetList.get(codeSnippetList.size() - 1).getId();
    }

    private int getStartingIndexForLatestCodeSnippetsSubList() {
        return codeSnippetList.size() >= 10 ? codeSnippetList.size() - 10 : 0;
    }

    private List<CodeSnippet> getLatestCodeSnippetsList() {
        int startingIndex = getStartingIndexForLatestCodeSnippetsSubList();
        return codeSnippetList.subList(startingIndex, codeSnippetList.size());
    }

    private List<CodeSnippetResponseDTO> mapCodeSnippetsToDtos(List<CodeSnippet> codeSnippets) {
        List<CodeSnippetResponseDTO> codeSnippetsAsDtos = new ArrayList<>();
        for (CodeSnippet codeSnippet : codeSnippets) {
            codeSnippetsAsDtos.add(new CodeSnippetResponseDTO(codeSnippet));
        }
        return codeSnippetsAsDtos;
    }

}
