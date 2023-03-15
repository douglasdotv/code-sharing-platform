package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.domain.CodeSnippet;

@Repository
public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, Long> {

}
