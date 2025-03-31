package opensource.DreamingLibrary.rent.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.global.entity.TimeStamp;
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
public class Rent extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentId")
    private Long rentId; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    // 대여 기간(기간)
    @Column(name = "rentalPeriod", nullable = false)
    private Integer rentalPeriod;

    // 반납 시각
    @Column(name = "returnAt")
    private LocalDateTime returnAt;

    // 연장 횟수
    @Column(name = "rentalCount")
    private Integer rentalCount;
}
