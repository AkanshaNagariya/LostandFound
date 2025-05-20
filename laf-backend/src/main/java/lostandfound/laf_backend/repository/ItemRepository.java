package lostandfound.laf_backend.repository;
import lostandfound.laf_backend.Model.Item; // Import your Claim entity
import lostandfound.laf_backend.Model.Enums.ItemStatus; // Import ClaimStatus enum
import org.springframework.data.jpa.repository.JpaRepository; // Add this import
import java.util.List;
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(ItemStatus status);
}
