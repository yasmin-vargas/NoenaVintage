package Repository;
import Model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleData extends JpaRepository<RoleEnum, Long> {
    RoleEnum findByRoleName(String roleName);
}
