package platform.domain.dto.view;

import platform.domain.CodeSnippet;

public record CodeSnippetViewDTO(String code,
                                 String date,
                                 long time,
                                 long views,
                                 long remainingTime,
                                 long remainingViews,
                                 boolean isTimeRestricted,
                                 boolean isViewsRestricted) {

    public CodeSnippetViewDTO(CodeSnippet codeSnippet) {
        this(
                codeSnippet.getCode(),
                codeSnippet.getCreationDate(),
                codeSnippet.getTime(),
                codeSnippet.getViews(),
                codeSnippet.getRemainingTime(),
                codeSnippet.getRemainingViews(),
                codeSnippet.isTimeRestricted(),
                codeSnippet.isViewsRestricted()
        );
    }

}
