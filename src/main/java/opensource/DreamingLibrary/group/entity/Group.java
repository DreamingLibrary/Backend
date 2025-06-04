package opensource.DreamingLibrary.group.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.global.entity.TimeStamp;
import opensource.DreamingLibrary.user.entity.User;

import java.util.Set;

@Entity
@Table(name = "`group`")  // 예약어이므로 백틱(`) 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "name", nullable = false, unique = true)
    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupUser> groupUsers;

    @OneToOne
    @JoinColumn(name = "adminId", nullable = false)
    private User admin;
}