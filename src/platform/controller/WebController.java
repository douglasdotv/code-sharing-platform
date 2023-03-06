package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.service.WebService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    private final WebService webService;

    @Autowired
    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/code")
    public ModelAndView getCode() {
        String codeSnippet = webService.getCodeSnippet();

        Map<String, Object> model = new HashMap<>();
        model.put("title", "Code");
        model.put("codeSnippet", codeSnippet);

        // When returning a ModelAndView object, the Content-Type header is set to text/html by default.
        return new ModelAndView("code", model);
    }

}
