package Repository;
import Model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReturnData extends JpaRepository<Return, Long> {
    List<Return> findByUserID(Long userID);
}