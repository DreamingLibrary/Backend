package opensource.DreamingLibrary.group.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.user.entity.User;

@Entity
@Table(name = "group_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
