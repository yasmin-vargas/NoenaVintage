package Repository;

import Model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockData extends JpaRepository<StockItem, Long> {
    List<StockItem> findByStockID(Long stockID);
    List<StockItem> findByStockColour(String stockColour);
    List<StockItem> findByStockSize(String stockSize);
}
