package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.domain.CodeSnippet;

import java.util.UUID;

public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, UUID> {

}
