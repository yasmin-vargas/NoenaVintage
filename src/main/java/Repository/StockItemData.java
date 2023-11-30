package Repository;
import Model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StockItemData extends JpaRepository<StockItem, Long> {
    Optional<StockItem> findByStockID(Long stockID);
    Optional<StockItem> findByStockColour(String stockColour);
    Optional<StockItem> findByStockSize(String stockSize);
    Optional<StockItem> deleteByStockID(Long stockID);
}
