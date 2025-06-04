package opensource.DreamingLibrary.group.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.global.entity.TimeStamp;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdTime;  // 날짜+시간으로 변경

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedTime;  // 날짜+시간으로 변경

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupUser> groupUsers;

    @OneToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
}