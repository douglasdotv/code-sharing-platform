package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.domain.CodeSnippetViewDTO;
import platform.service.CodeSharingViewService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CodeSharingViewController {

    private final CodeSharingViewService viewService;

    @Autowired
    public CodeSharingViewController(CodeSharingViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/code")
    public ModelAndView getCode() {
        CodeSnippetViewDTO codeSnippetInfo = viewService.getCodeSnippet();

        Map<String, Object> model = new HashMap<>();
        model.put("title", "Code");
        model.put("codeSnippet", codeSnippetInfo.code());
        model.put("date", codeSnippetInfo.date());

        // When dealing with a ModelAndView object, the Content-Type header is set to text/html by default.
        return new ModelAndView("code", model);
    }

}
