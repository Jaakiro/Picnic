package org.example;


import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/java/org/example/input.txt");
            Scanner scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int wordCount = countWordsInFile("src/main/java/org/example/input.txt");
        System.out.println("Количество слов в файле: " + wordCount);

        String longestWord = findLongestWordInFile("src/main/java/org/example/input.txt");
        System.out.println("Самое длинное слово: " + longestWord);

        Map<String, Integer> wordFrequency = analyzeWordFrequencyInFile("src/main/java/org/example/input.txt");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static int countWordsInFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int wordCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            reader.close();
            return wordCount;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String findLongestWordInFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            String longestWord = "";
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
            reader.close();
            return longestWord;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, Integer> analyzeWordFrequencyInFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            Map<String, Integer> frequencyMap = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\s+");
                for (String word : words) {
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }
            reader.close();
            return frequencyMap;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
}