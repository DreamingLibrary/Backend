package opensource.DreamingLibrary.group.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.global.entity.TimeStamp;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.user.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`group`")  // 예약어이므로 백틱(`) 사용
@Getter
@Setter
@NoArgsConstructor
public class Group extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "name", nullable = false, unique = true)
    private String groupName;
    
    @OneToOne
    @JoinColumn(name = "adminId", nullable = true)
    private User admin;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupUser> groupUsers;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @Builder
    public Group(Long groupId, String groupName, User admin) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.admin = admin;
    }
}