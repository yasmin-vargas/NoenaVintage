package Controller;
import Model.StockItem;
import Repository.StockItemData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockItemController {
    private StockItemData stockData;

    @Autowired
    public StockItemController(StockItemData stockData) {
        this.stockData = stockData;
    }

    @GetMapping
    public List<StockItem> getAllStockItems() {
        return stockData.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockItem> getStockItemById(@PathVariable Long stockID) {
        Optional<StockItem> stockItemOptional = stockData.findById(stockID);
        return stockItemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<StockItem> createStockItem(@RequestBody StockItem stockItem) {
        StockItem createdStockItem = stockData.save(stockItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStockItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockItem> updateStockItem(@PathVariable Long stockID, @RequestBody StockItem updatedStockItem) {
        Optional<StockItem> existingStockItemOptional = stockData.findById(stockID);
        if (existingStockItemOptional.isPresent()) {
            StockItem existingStockItem = existingStockItemOptional.get();
            existingStockItem.setStockID(stockID);  // Ensure that the stockID is set properly
            existingStockItem.setProductName(updatedStockItem.getProductName());
            existingStockItem.setProductBrand(updatedStockItem.getProductBrand());
            existingStockItem.setStockPrice(updatedStockItem.getStockPrice());
            existingStockItem.setStockColour(updatedStockItem.getStockColour());
            existingStockItem.setStockSize(updatedStockItem.getStockSize());
            existingStockItem.setStockQty(updatedStockItem.getStockQty());
            StockItem updated = stockData.save(existingStockItem);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long stockID) {
        Optional<StockItem> existingStockItemOptional = stockData.findById(stockID);
        if (existingStockItemOptional.isPresent()) {
            stockData.deleteById(stockID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}