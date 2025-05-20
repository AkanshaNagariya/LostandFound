package lostandfound.laf_backend.repository;
import lostandfound.laf_backend.Model.User;
import lostandfound.laf_backend.Model.Enums.Role;
import org.springframework.data.jpa.repository.JpaRepository; // Add this import
import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
