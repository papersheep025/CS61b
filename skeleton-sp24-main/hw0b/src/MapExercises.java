import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> letterMap = new HashMap<>();

        for (char letter = 'a'; letter < 'z'; letter++) {
            letterMap.put(letter, (letter - 'a') + 1);
        }
        return letterMap;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> squaredMap = new HashMap<>();

        for (int num : nums) {
            squaredMap.put(num, num * num);
        }
        return squaredMap;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        // getOrDefault这个方法尝试获取指定键对应的值。
        // 如果该键存在于映射中，则返回它的值；如果不存在，则返回你提供的默认值
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
        return wordCountMap;
    }

    public static void main(String[] args) {
        // letterToNum()
        Map<Character, Integer> map = letterToNum();
        System.out.println(map);

        // square
        List<Integer> numbers = List.of(1, 3, 6, 7);
        Map<Integer, Integer> squaredMap = squares(numbers);
        System.out.println("Number to square mapping: " + squaredMap);

        // countWord
        List<String> wordList = List.of("apple", "banana", "apple", "orange", "banana", "banana");
        Map<String, Integer> wordCounts = countWords(wordList);
        System.out.println("Word counts: " + wordCounts);

    }
    
    
}
