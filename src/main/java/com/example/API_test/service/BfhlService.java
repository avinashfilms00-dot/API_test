package com.example.API_test.service;

import com.example.API_test.dto.BfhlRequest;
import com.example.API_test.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processRequest(BfhlRequest request);
}
