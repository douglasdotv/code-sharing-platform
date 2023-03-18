package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.domain.dto.view.CodeSnippetViewDTO;
import platform.service.CodeSharingViewService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
public class CodeSharingViewController {

    private final CodeSharingViewService viewService;

    @Autowired
    public CodeSharingViewController(CodeSharingViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/code/{uuid}")
    public String getCode(Model model, @PathVariable UUID uuid) {
        CodeSnippetViewDTO codeSnippet = viewService.getCodeSnippet(uuid);

        model.addAttribute("title", "Code");
        model.addAttribute("codeSnippet", codeSnippet);

        return "code";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeSnippets(Model model) {
        List<CodeSnippetViewDTO> codeSnippetList = viewService.getLatestCodeSnippets();

        model.addAttribute("title", "Latest");
        model.addAttribute("codeSnippetList", codeSnippetList);

        return "latest";
    }

    @GetMapping("/code/new")
    public String getNewCode(Model model) {
        model.addAttribute("title", "Create");
        return "new";
    }

}
