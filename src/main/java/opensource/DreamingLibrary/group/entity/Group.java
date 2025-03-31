package opensource.DreamingLibrary.group.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`group`")  // 예약어이므로 백틱(`) 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "name", nullable = false)
    private String groupName;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdTime;  // 날짜+시간으로 변경

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedTime;  // 날짜+시간으로 변경
}