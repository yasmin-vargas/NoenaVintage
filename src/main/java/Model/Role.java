package Model;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Define a set of permissions associated with the role
    @ElementCollection(targetClass = PermissionEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission")
    private Set<PermissionEnum> permissions;

    // Constructors, getters, setters
}
