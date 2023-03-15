package platform.domain;

import lombok.*;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.util.DateFormatter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "code_snippets")
public class CodeSnippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    public CodeSnippet(NewCodeSnippetDTO newCodeSnippet) {
        this.code = newCodeSnippet.code();
        this.date = DateFormatter.formatWithPattern(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeSnippet that = (CodeSnippet) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
