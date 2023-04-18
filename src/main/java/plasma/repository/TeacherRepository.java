package plasma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plasma.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
