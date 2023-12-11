package Controller;
import Model.Return;
import Repository.OrderData;
import Repository.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/returns")
public class ReturnController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private ReturnData returnData;
    @GetMapping("/history/{userID}")
    public ResponseEntity<List<Return>> getReturnHistoryByUser(@PathVariable Long userID) {
        List<Return> returnHistory = returnData.findByUserID(userID);
        if (returnHistory.isEmpty()) {
            // Handle the case where no orders are found for the user
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(returnHistory);
    }
}
