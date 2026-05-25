package com.example.API_test.controller;

import com.example.API_test.dto.BfhlRequest;
import com.example.API_test.dto.BfhlResponse;
import com.example.API_test.dto.OperationCodeResponse;
import com.example.API_test.util.BfhlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

        private static final String USER_ID = "manish_yadav_24121996";
        private static final String EMAIL = "manishyadav241296@acropolis.in";
        private static final String ROLL_NUMBER = "0827CI243D06";

        @GetMapping
        public ResponseEntity<OperationCodeResponse> getOperationCode() {
                return ResponseEntity.ok(new OperationCodeResponse(1));
        }

        @PostMapping
        public ResponseEntity<BfhlResponse> processRequest(@RequestBody(required = false) BfhlRequest request) {
                List<String> data = request != null && request.getData() != null ? request.getData()
                                : Collections.emptyList();

                List<String> numbers = data.stream()
                                .filter(BfhlUtils::isNumeric)
                                .collect(Collectors.toList());

                List<String> alphabets = data.stream()
                                .filter(BfhlUtils::isAlphabet)
                                .collect(Collectors.toList());

                Optional<String> highestLowercase = alphabets.stream()
                                .filter(BfhlUtils::isLowercaseAlphabet)
                                .max(Comparator.naturalOrder());

                List<String> highestLowercaseAlphabet = highestLowercase
                                .map(Collections::singletonList)
                                .orElseGet(ArrayList::new);

                boolean primeFound = numbers.stream()
                                .anyMatch(BfhlUtils::isPrime);

                boolean fileValid = false;
                String fileMimeType = null;
                String fileSizeKb = null;

                if (request != null && request.getFile_b64() != null && !request.getFile_b64().isBlank()) {
                        BfhlUtils.FileInfo fileInfo = BfhlUtils.decodeFile(request.getFile_b64()).orElse(null);
                        if (fileInfo != null) {
                                fileValid = fileInfo.isValid();
                                fileMimeType = fileInfo.getMimeType();
                                fileSizeKb = fileInfo.getSizeKb();
                        }
                }

                BfhlResponse response = new BfhlResponse(
                                true,
                                USER_ID,
                                EMAIL,
                                ROLL_NUMBER,
                                numbers,
                                alphabets,
                                highestLowercaseAlphabet,
                                primeFound,
                                fileValid,
                                fileMimeType,
                                fileSizeKb);

                return ResponseEntity.ok(response);
        }
}
