package com.example.API_test.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Optional;

public class BfhlUtils {

    public static boolean isNumeric(String value) {
        return value != null && value.matches("^[0-9]+$");
    }

    public static boolean isAlphabet(String value) {
        return value != null && value.matches("^[a-zA-Z]$");
    }

    public static boolean isLowercaseAlphabet(String value) {
        return value != null && value.length() == 1 && Character.isLowerCase(value.charAt(0));
    }

    public static boolean isPrime(String value) {
        if (!isNumeric(value)) {
            return false;
        }
        BigInteger number = new BigInteger(value);
        return number.compareTo(BigInteger.ONE) > 0 && number.isProbablePrime(20);
    }

    public static Optional<FileInfo> decodeFile(String base64) {
        if (base64 == null || base64.isBlank()) {
            return Optional.empty();
        }
        try {
            String cleanBase64 = base64.contains(",") ? base64.substring(base64.indexOf(',') + 1) : base64;
            byte[] bytes = Base64.getDecoder().decode(cleanBase64);
            if (bytes.length == 0) {
                return Optional.empty();
            }
            String mimeType = guessMimeType(bytes).orElse("application/octet-stream");
            String sizeKb = String.valueOf(Math.max(1, Math.round(bytes.length / 1024.0f)));
            return Optional.of(new FileInfo(true, mimeType, sizeKb));
        } catch (IllegalArgumentException ignored) {
            return Optional.empty();
        }
    }

    private static Optional<String> guessMimeType(byte[] bytes) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            return Optional.ofNullable(URLConnection.guessContentTypeFromStream(inputStream));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static class FileInfo {
        private final boolean valid;
        private final String mimeType;
        private final String sizeKb;

        public FileInfo(boolean valid, String mimeType, String sizeKb) {
            this.valid = valid;
            this.mimeType = mimeType;
            this.sizeKb = sizeKb;
        }

        public boolean isValid() {
            return valid;
        }

        public String getMimeType() {
            return mimeType;
        }

        public String getSizeKb() {
            return sizeKb;
        }
    }
}
