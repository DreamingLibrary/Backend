package opensource.DreamingLibrary.book.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.global.entity.TimeStamp;
import opensource.DreamingLibrary.group.entity.Group;

import java.time.LocalDateTime;


@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Long bookId;

    @OneToOne
    @JoinColumn(name = "groupId")  // 1:1 관계
    private Group group;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    /*
    JPA와 빌더를 사용하는 경우 빌더를 클래스 차원에 붙이면NoArgsConstructor와 충돌할 수 있어요
    빌더는 엔티티 내부에 선언해주세요!
     */
    @Builder
    public Book(
            Long bookId,
            Group group,
            String title,
            String author,
            String description,
            Category category
    ){
        this.bookId = bookId;
        this.group = group;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
    }

}