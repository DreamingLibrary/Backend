package opensource.DreamingLibrary.rent.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.book.entity.Book;
import opensource.DreamingLibrary.global.entity.TimeStamp;
import opensource.DreamingLibrary.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
@Getter
@Setter
@NoArgsConstructor
public class Rent extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentId")
    private Long rentId; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
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

    public void calculateReturnAt() {
        this.returnAt = getCreatedAt().plusDays(rentalPeriod);
    }

    @Builder
    public Rent(
            Long rentId,
            User user,
            Book book,
            Integer rentalPeriod,
            LocalDateTime returnAt,
            Integer rentalCount
    ) {
        this.rentId = rentId;
        this.user = user;
        this.book = book;
        this.rentalPeriod = rentalPeriod;
        this.returnAt = returnAt;
        this.rentalCount = rentalCount;
    }
}
