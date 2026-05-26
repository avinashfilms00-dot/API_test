package com.example.API_test.controller;

import com.example.API_test.dto.BfhlRequest;
import com.example.API_test.dto.BfhlResponse;
import com.example.API_test.service.BfhlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @GetMapping({"/bfhl", "/api/bfhl"})
    public ResponseEntity<Map<String, Object>> getOperationCode() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"/bfhl", "/api/bfhl"})
    public ResponseEntity<BfhlResponse> processRequest(@RequestBody(required = false) BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processRequest(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BfhlResponse errResponse = new BfhlResponse();
            errResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
        }
    }
}
