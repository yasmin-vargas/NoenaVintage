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

    @Enumerated(EnumType.STRING)
    private PermissionEnum permissions;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor
    public Role(long roleID, RoleEnum roleName, PermissionEnum permissions) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.permissions = permissions;
    }

    // Getters and Setters
    public Long getRoleID() {return roleID;}

    public void setRoleID(Long roleID) {this.roleID = roleID;}

    public RoleEnum getRoleName() {return roleName;}

    public void setRoleName(RoleEnum roleName) {this.roleName = roleName;}

    public PermissionEnum getPermissions() {return permissions;}

    public void setPermissions(PermissionEnum permissions) {this.permissions = permissions;}
}
