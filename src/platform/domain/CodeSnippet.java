package platform.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.util.DateFormatter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CodeSnippet {

    private String id;

    private String code;

    private String date;

    public CodeSnippet(NewCodeSnippetDTO newCodeSnippet) {
        this.code = newCodeSnippet.code();
        this.date = DateFormatter.formatWithPattern(LocalDateTime.now());
    }

}
