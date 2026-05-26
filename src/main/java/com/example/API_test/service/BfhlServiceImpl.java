package com.example.API_test.service;

import com.example.API_test.dto.BfhlRequest;
import com.example.API_test.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.user-id:john_doe_17091999}")
    private String userId;

    @Value("${bfhl.email:john@xyz.com}")
    private String email;

    @Value("${bfhl.roll-number:ABCD123}")
    private String rollNumber;

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        if (request == null || request.getData() == null) {
            return new BfhlResponse(true, userId, email, rollNumber,
                    Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                    Collections.emptyList(), "0", "");
        }

        List<String> data = request.getData();
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        List<Character> letters = new ArrayList<>();

        for (String element : data) {
            if (element == null) {
                continue;
            }

            // Check if purely numeric (integer)
            if (element.matches("^-?\\d+$")) {
                BigInteger number = new BigInteger(element);
                if (number.abs().mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }
                sum = sum.add(number);
            } 
            // Check if purely alphabetic (words/letters)
            else if (element.matches("^[a-zA-Z]+$")) {
                alphabets.add(element.toUpperCase());
            } 
            // Otherwise, it's a special character
            else {
                specialCharacters.add(element);
            }

            // Extract all letters present in this element for concat_string
            for (char c : element.toCharArray()) {
                if (Character.isLetter(c)) {
                    letters.add(c);
                }
            }
        }

        // Reverse letters and apply alternating caps
        Collections.reverse(letters);
        StringBuilder concatBuilder = new StringBuilder();
        for (int i = 0; i < letters.size(); i++) {
            char c = letters.get(i);
            if (i % 2 == 0) {
                concatBuilder.append(Character.toUpperCase(c));
            } else {
                concatBuilder.append(Character.toLowerCase(c));
            }
        }

        return new BfhlResponse(true, userId, email, rollNumber,
                oddNumbers, evenNumbers, alphabets, specialCharacters,
                sum.toString(), concatBuilder.toString());
    }
}
