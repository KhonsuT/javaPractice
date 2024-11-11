package moderate;

import java.util.*;
import java.util.stream.Collectors;

public class wordFrequencies {
    public static void main(String[] args) {
        String doc = " Cats are among the most popular pets in the world, admired" +
        "for their independence, grace, and playful nature. Belonging to the family" +
        "Felidae, domestic cats (Felis catus) have lived alongside humans for thousands" + 
        "of years. They are known for their agility, curiosity, and ability to hunt. Cats" + 
        "can be found in a wide variety of breeds, each with distinct characteristics such" + 
        "as different coat colors, patterns, and body types. Cats are often appreciated for" + 
        "their ability to keep homes free of rodents and pests, a skill they inherit from their" + 
        "wild ancestors. Despite their sometimes aloof nature, cats can form deep bonds with their owners." + 
        "They communicate through a combination of vocalizations, body language, and purring. A cat\'s purr" + 
        "is often associated with contentment, but it can also signal other emotions like stress or healing." +
        "In addition to their practical benefits, cats offer companionship and comfort to millions of people." + 
        "Their low-maintenance care makes them ideal pets for both busy households and individuals who prefer" + 
        "quieter animals. Whether chasing a laser pointer or simply curling up in a sunny spot, cats bring joy" + 
        "and relaxation to their owners, enhancing the quality of life in many ways. ";
        List<Character> letters = 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
        Map<String, Integer> map = new HashMap<>();
        String curWord = "";
        for (char c: doc.toCharArray()) {
            if (c == ' ' || !letters.contains(c)) {
                if (curWord.length()>0) map.put(curWord, map.getOrDefault(curWord, 0)+1);
                curWord = "";
                continue;
            }
            curWord += Character.toString(c);
        }
        // map.forEach((key, value) -> System.out.println(key + ": " + value));
        List<Map.Entry<String, Integer>> filtered_map = map.entrySet().stream().filter(entry -> entry.getValue()>4).collect(Collectors.toList());
        filtered_map.forEach(entry -> System.out.println(entry.getKey()+": "+entry.getValue()));

    }
}
