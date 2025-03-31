package opensource.DreamingLibrary.example.book.entity;

import jakarta.persistence.*;
import lombok.*;
import opensource.DreamingLibrary.example.group.entity.Group;

import java.time.LocalDateTime;


@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

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
    private String category;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;  // LocalTime → LocalDateTime

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;  // LocalTime → LocalDateTime
}