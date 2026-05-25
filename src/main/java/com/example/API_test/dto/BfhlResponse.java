package com.example.API_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BfhlResponse {

    @JsonProperty("is_success")
    private boolean success;

    @JsonProperty("user_id")
    private String userId;

    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    private List<String> numbers;
    private List<String> alphabets;

    @JsonProperty("highest_lowercase_alphabet")
    private List<String> highestLowercaseAlphabet;

    @JsonProperty("is_prime_found")
    private boolean primeFound;

    @JsonProperty("file_valid")
    private boolean fileValid;

    @JsonProperty("file_mime_type")
    private String fileMimeType;

    @JsonProperty("file_size_kb")
    private String fileSizeKb;

    public BfhlResponse() {
    }

    public BfhlResponse(boolean success, String userId, String email, String rollNumber,
                        List<String> numbers, List<String> alphabets, List<String> highestLowercaseAlphabet,
                        boolean primeFound, boolean fileValid, String fileMimeType, String fileSizeKb) {
        this.success = success;
        this.userId = userId;
        this.email = email;
        this.rollNumber = rollNumber;
        this.numbers = numbers;
        this.alphabets = alphabets;
        this.highestLowercaseAlphabet = highestLowercaseAlphabet;
        this.primeFound = primeFound;
        this.fileValid = fileValid;
        this.fileMimeType = fileMimeType;
        this.fileSizeKb = fileSizeKb;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<String> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(List<String> alphabets) {
        this.alphabets = alphabets;
    }

    public List<String> getHighestLowercaseAlphabet() {
        return highestLowercaseAlphabet;
    }

    public void setHighestLowercaseAlphabet(List<String> highestLowercaseAlphabet) {
        this.highestLowercaseAlphabet = highestLowercaseAlphabet;
    }

    public boolean isPrimeFound() {
        return primeFound;
    }

    public void setPrimeFound(boolean primeFound) {
        this.primeFound = primeFound;
    }

    public boolean isFileValid() {
        return fileValid;
    }

    public void setFileValid(boolean fileValid) {
        this.fileValid = fileValid;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public String getFileSizeKb() {
        return fileSizeKb;
    }

    public void setFileSizeKb(String fileSizeKb) {
        this.fileSizeKb = fileSizeKb;
    }
}
