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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String location;
    @Enumerated(EnumType.STRING)
    private Enums.ItemStatus status;
    private LocalDateTime dateReported;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reporter;
}
