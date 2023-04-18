package plasma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plasma.models.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
}
