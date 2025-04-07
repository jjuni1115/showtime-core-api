package com.showtime.coreapi.endpoint;

import com.showtime.coreapi.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestEndpoint {

    @PostMapping("")
    public ResponseEntity<ApiResponse<Boolean>> test (@RequestBody test test) {
        return ResponseEntity.ok(ApiResponse.ok(true,"/api/v1/test"));
    }

}
