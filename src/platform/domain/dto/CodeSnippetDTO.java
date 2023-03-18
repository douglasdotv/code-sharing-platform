package platform.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import platform.domain.CodeSnippet;

public record CodeSnippetDTO(String code,
                                     String date,
                                     long time,
                                     long views,
                                     @JsonIgnore
                                     boolean isTimeRestricted,
                                     @JsonIgnore
                                     boolean isViewsRestricted) {

    public CodeSnippetDTO(CodeSnippet codeSnippet) {
        this(
                codeSnippet.getCode(),
                codeSnippet.getCreationDate(),
                codeSnippet.getRemainingTime(),
                codeSnippet.getRemainingViews(),
                codeSnippet.isTimeRestricted(),
                codeSnippet.isViewsRestricted()
        );
    }

}
