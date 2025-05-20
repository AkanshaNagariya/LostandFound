package lostandfound.laf_backend.Model;
import jakarta.persistence.*; // Or use javax.persistence.* depending on your setup
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User claimant;
    private LocalDateTime claimDate;
    private String proofDescription;
    @Enumerated(EnumType.STRING)
    private Enums.ClaimStatus status;
}
