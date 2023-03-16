package platform.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.util.DateFormatter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "code_snippets")
public class CodeSnippet {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uuid;

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
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

}
