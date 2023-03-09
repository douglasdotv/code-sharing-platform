package platform.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import platform.util.DateFormatter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CodeSnippet {

    private String code =
            """
            public class Main {
                public static void main(String[] args) {
                    System.out.println("Hello World!");
                }
            }
            """;

    private String date = DateFormatter.formatWithPattern(LocalDateTime.now());

    public void update(String code) {
        this.setCode(code);
        this.setDate(DateFormatter.formatWithPattern(LocalDateTime.now()));
    }

}
