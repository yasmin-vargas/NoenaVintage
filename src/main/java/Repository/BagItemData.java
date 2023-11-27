package Repository;
import Model.BagItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface BagItemData extends JpaRepository<BagItem, Integer> {
    Optional<BagItem> findByBagItemID(Long bagItemID);
    Optional<BagItem> deleteByBagItemID(Long bagItemID);
}
