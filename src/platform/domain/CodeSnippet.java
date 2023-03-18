package platform.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import platform.domain.dto.api.NewCodeSnippetDTO;
import platform.util.DateFormatter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "code_snippets")
public class CodeSnippet {

    @Id
    @GeneratedValue(generator = "hibernateuuid")
    @GenericGenerator(name = "hibernateuuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "code")
    private String code;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "max_time_allowed")
    private long time;

    @Column(name = "remaining_time")
    private long remainingTime;

    @Column(name = "max_views_allowed")
    private long views;

    @Column(name = "views_count")
    private long viewsCount;

    @Column(name = "remaining_views")
    private long remainingViews;

    @Column(name = "time_restricted")
    private boolean isTimeRestricted;

    @Column(name = "views_restricted")
    private boolean isViewsRestricted;

    @Column(name = "restricted")
    private boolean isRestricted;

    public CodeSnippet(NewCodeSnippetDTO codeSnippet) {
        this.code = codeSnippet.code();
        this.creationDate = DateFormatter.formatWithPattern(LocalDateTime.now());

        this.time = codeSnippet.time();
        this.remainingTime = codeSnippet.time();

        this.views = codeSnippet.views();
        this.viewsCount = 0L;
        this.remainingViews = codeSnippet.views();

        this.isTimeRestricted = codeSnippet.time() > 0;
        this.isViewsRestricted = codeSnippet.views() > 0;
        this.isRestricted = this.isTimeRestricted || this.isViewsRestricted;
    }

    public void updateRemainingTimeAndViews() {
        if (isRestricted()) {
            if (isTimeRestricted()) {
                LocalDateTime creationDate = DateFormatter.parseWithPattern(this.creationDate);
                long diff = ChronoUnit.SECONDS.between(creationDate, LocalDateTime.now());
                this.remainingTime = this.time - diff;
            }
            if (isViewsRestricted()) {
                this.remainingViews = this.views - this.viewsCount;
            }
        }
    }

    public boolean isExpired() {
        return remainingTime < 0;
    }

    public boolean isViewsLimitReached() {
        return remainingViews < 0;
    }

    public void incrementViewsCount() {
        this.viewsCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CodeSnippet that = (CodeSnippet) o;
        return getUuid() != null && Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
