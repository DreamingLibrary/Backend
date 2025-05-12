package opensource.DreamingLibrary.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @NotBlank
    private String studentNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Role role;

    private String email;

}
