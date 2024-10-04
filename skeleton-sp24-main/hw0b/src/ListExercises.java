import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (int number : L) {
            sum += number;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenList = new ArrayList<>();
        for (int number : L) {
            if (number % 2 == 0) {
                evenList.add(number);            
            }
        }
        return evenList;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // 用HashSet则查找时间复杂度为O(1)
        Set<Integer> setL2 = new HashSet<>();
        List<Integer> commonList = new ArrayList<>();
        for (Integer item : L1) {
            if (setL2.contains(item)) {
                commonList.add(item);
                setL2.remove(item);
            }
        }
        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // // countOccurrencesOfC
        // List<String> words = List.of("hello", "world", "example", "test");
        // char c = 'e';
        // System.out.println("The character '" + c + "' appears " + countOccurrencesOfC(words, c) + " times.");
    }
}
