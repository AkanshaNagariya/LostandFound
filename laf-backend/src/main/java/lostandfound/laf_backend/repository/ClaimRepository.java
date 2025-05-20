package lostandfound.laf_backend.repository;
import lostandfound.laf_backend.Model.Claim; // Import your Claim entity
import lostandfound.laf_backend.Model.Enums.ClaimStatus; // Import ClaimStatus enum
import org.springframework.data.jpa.repository.JpaRepository; // Add this import
import java.util.List;
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByStatus(ClaimStatus status);
}
