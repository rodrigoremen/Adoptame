package com.example.adoptame.utils;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class CodeGenerator {
    private static final int CODE_LENGTH = 6;
    private static final Random random = new Random();

    public String generateRandomCode() {
        StringBuilder sb = new StringBuilder();
        Set<Integer> usedNumbers = new HashSet<>();

        while (sb.length() < CODE_LENGTH) {
            int randomNum = random.nextInt(10);
            if (!usedNumbers.contains(randomNum)) {
                sb.append(randomNum);
                usedNumbers.add(randomNum);
            }
        }

        return sb.toString();
    }
}
