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

@Controller
@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
public class CodeSharingViewController {

    private final CodeSharingViewService viewService;

    @Autowired
    public CodeSharingViewController(CodeSharingViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/code/{id}")
    public String getCode(Model model, @PathVariable Long id) {
        CodeSnippetViewDTO codeSnippet = viewService.getCodeSnippet(id);

        model.addAttribute("title", "Code");
        model.addAttribute("codeSnippet", codeSnippet);

        return "code";
    }

    @GetMapping("/code/new")
    public String getNewCode(Model model) {
        model.addAttribute("title", "Create");
        return "newcode";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeSnippets(Model model) {
        List<CodeSnippetViewDTO> codeSnippetList = viewService.getLatestCodeSnippets();

        model.addAttribute("title", "Latest");
        model.addAttribute("codeSnippetList", codeSnippetList);

        return "latest";
    }

    @GetMapping("")
    public String getIndex() {
        return "index";
    }

}
