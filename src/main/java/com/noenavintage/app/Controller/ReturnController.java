package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Return;
import com.noenavintage.app.Model.User;
import com.noenavintage.app.Repository.OrderData;
import com.noenavintage.app.Repository.ReturnData;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/returns")
public class ReturnController {
    @Autowired
    private OrderData orderData;
    @Autowired
    private ReturnData returnData;
    @Autowired
    public ReturnController() {
    }
    @GetMapping("/history/{userID}")
    public ResponseEntity<List<Return>> getReturnHistoryByUser(@PathVariable User user) {
        List<Return> returnHistory = returnData.findReturnByUser(user);
        if (returnHistory.isEmpty()) {
            // Handle the case where no orders are found for the user
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(returnHistory);
    }
}
