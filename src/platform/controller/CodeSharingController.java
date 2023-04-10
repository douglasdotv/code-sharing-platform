package platform.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.domain.dto.CodeSnippetDTO;
import platform.service.CodeSharingService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
public class CodeSharingController {

    private final CodeSharingService service;

    public CodeSharingController(CodeSharingService service) {
        this.service = service;
    }

    @GetMapping("/code/{uuid}")
    public String getCode(Model model, @PathVariable UUID uuid) {
        CodeSnippetDTO codeSnippet = service.getCodeSnippet(uuid);

        model.addAttribute("title", "Code");
        model.addAttribute("codeSnippet", codeSnippet);

        return "code";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeSnippets(Model model) {
        List<CodeSnippetDTO> codeSnippetList = service.getLatestCodeSnippets();

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
