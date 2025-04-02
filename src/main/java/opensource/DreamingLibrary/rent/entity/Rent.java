package opensource.DreamingLibrary.rent.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.group.entity.Group;
import opensource.DreamingLibrary.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentId")
    private Long rentId; // PK

    // ERD에 따라 userId, groupId, bookId는 각각 User, Group, Book 엔티티와 ManyToOne 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    // 대여 기간(시작 시점 or 기간?)이 DATETIME이라고 하셨으므로 LocalDateTime으로 매핑
    @Column(name = "rentalPeriod", nullable = false)
    private LocalDateTime rentalPeriod;

    // 반납 시각
    @Column(name = "returnAt")
    private LocalDateTime returnAt;

    // 연장 횟수
    @Column(name = "rentalCount")
    private Integer rentalCount;

    // 생성/수정 시각
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}
