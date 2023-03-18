package platform.domain.dto.view;

import platform.domain.CodeSnippet;

public record CodeSnippetViewDTO(String code,
                                 String date,
                                 long time,
                                 long views,
                                 boolean isTimeRestricted,
                                 boolean isViewsRestricted) {

    public CodeSnippetViewDTO(CodeSnippet codeSnippet) {
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
