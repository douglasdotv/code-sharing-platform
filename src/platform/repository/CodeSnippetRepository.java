package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.domain.CodeSnippet;

public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, Long> {

}
