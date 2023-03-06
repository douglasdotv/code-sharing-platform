package platform.service;

import org.springframework.stereotype.Service;

@Service
public class WebService {

    private static final String HELLO_WORLD_CODE =
            """
            public class Main {
                public static void main(String[] args) {
                    System.out.println("Hello World!");
                }
            }
            """;

    public String getCodeSnippet() {
        return HELLO_WORLD_CODE;
    }

}
