import java.util.*;

public class Words {
    public static void main(String[] args) {
        //Φέρε το κείμενο ως ένα Array απο String
        String[] words = getWords();
        //Τύπωσε ποιο είναι το μήκος της κάθε λέξης του κειμένου.
        countWordsLength(words);
        //Τύπωσε πόσες φορές εμφανίζεται η κάθε λέξη στο κείμενο.
        countWordsOccurrences(words);
        //Τύπωσε τη μεγαλυτερη σε μήκος λέξη και το μήκος της.
        findLongestWord(words);
        //Τύπωσε τη λέξη που εμφανίζεται περισσότερες φορές στο κείμενο και πόσες είναι.
        findMostOccurredWord(words);
        //Τύπωσε τις διακριτές λέξεις που εμφανίζονται στο κείμενο
        // καθώς και τις διπλότυπες με τη σειρά εμφάνισης τους
        countDistinctAndPrintNonDistinctWords(words);
        //Τύπωσε τις διακριτές λέξεις που εμφανίζονται στο κείμενο με λεξικογραφική (φυσική) σειρά
        printDistinctWordsInLexicographicOrder(words);
        //Τύπωσε τις διακριτές λέξεις με τη σειρά που εμφανίζονται στο κείμενο
        // αφου αφαιρεσεις (φιλτραρεις) οσες εμφανίζονται ακριβώς 3 φορές
        removeWordsThatOccurExactlyThreeTimes(words);
        //Τύπωσε με λεξικογραφική (φυσική) σειρά τις διακριτές λέξεις που εμφανίζονται στο κείμενο
        // αφου αφαιρεσεις (φιλτραρεις) οσες εχουν μήκος μεγαλύτερο απο 3 γράμματα
        removeWordsLongerThanThreeLetters(words);
    }

    private static String[] getWords() {
        String jfk = "We choose to go to the moon. " +
                "We choose to go to the moon in this decade and do the other things, " +
                "not because they are easy, " +
                "but because they are hard, " +
                "because that goal will serve to organize and measure the best of our energies and skills, " +
                "because that challenge is one that we are willing to accept, " +
                "one we are unwilling to postpone, " +
                "and one which we intend to win, " +
                "and the others, too.";
        return jfk
                .toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .split(" ");
    }

    private static void countWordsLength(String[] words) {
        LinkedHashMap<String, Integer> map1 = new LinkedHashMap<>();
        for (String word : words) {
            map1.put(word, word.length());
        }
        //Used LinkedHashMap so that the words are printed in almost the same order as the initial extract
        System.out.println(map1);
    }

    private static void countWordsOccurrences(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        System.out.println(map);

    }

    private static void findLongestWord(String[] words) {
        //First Way  - it works only when there is *only one* longest word
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (String word : words) {
            treeMap.put(word.length(), word);
        }
        System.out.println("The word '" + treeMap.lastEntry().getValue() + "' having length of " + treeMap.lastEntry().getKey() + " characters, is the longest one appearing in the extract.");

        //Second Way
        int maxValue = 0;
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, word.length());
            if (maxValue < word.length()) {
                maxValue = word.length();
            }
        }
        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();
        List<String> listKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {

            if (entry.getValue().equals(maxValue)) {
                listKeys.add(entry.getKey());
            }


        }
        if (listKeys.size() > 1) {
            System.out.println("The longests words, having length of " + maxValue + " characters,  are: " + listKeys.toString());
        } else {
            System.out.println("The word '" + listKeys.toString() + "' having length of " + maxValue + " characters, is the longest one appearing in the extract.");
        }
    }

    private static void findMostOccurredWord(String[] words) {
        int maxValue = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
                if (maxValue < map.get(word)) maxValue = map.get(word);
            } else {
                map.put(word, 1);
            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        List<String> listKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {

            if (entry.getValue().equals(maxValue)) {
                listKeys.add(entry.getKey());
            }


        }
        if (listKeys.size() > 1) {
            System.out.println("The most occured words, having appeared " + maxValue + " times,  are: " + listKeys.toString());
        } else {
            System.out.println("The most occured word, having appeared " + maxValue + " times, is :  " + listKeys.toString());
        }


    }

    private static void countDistinctAndPrintNonDistinctWords(String[] words) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        LinkedHashSet distinctSet = new LinkedHashSet();
        LinkedHashSet nonDistinctSet = new LinkedHashSet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() > 1) {
                nonDistinctSet.add(entry.getKey());
            } else
                distinctSet.add(entry.getKey());


        }
        System.out.println("Distinct words in the extract sorted as appering: " + map.keySet());
        System.out.println("Words in the extract occuring only once and sorted as appering: " + distinctSet);
        System.out.println("Non-distinct words found in the extract as appearing: " + nonDistinctSet);


    }

    private static void printDistinctWordsInLexicographicOrder(String[] words) {
        TreeSet<String> treeSet = new TreeSet<>();
        Collections.addAll(treeSet, words);
        System.out.println(treeSet);
    }

    private static void removeWordsThatOccurExactlyThreeTimes(String[] words) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (!entry.getValue().equals(3)) {
                linkedHashSet.add(entry.getKey());
            }
        }
        System.out.println("Distinct words in the extract after removing the ones occuring exactly 3 times: " + linkedHashSet);


    }

    private static void removeWordsLongerThanThreeLetters(String[] words) {
        TreeSet treeSet = new TreeSet();
        LinkedHashMap<String, Integer> map1 = new LinkedHashMap<>();
        for (String word : words) {
            map1.put(word, word.length());
        }
        Set<Map.Entry<String, Integer>> entries = map1.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (!entry.getValue().equals(3)) {
                treeSet.add(entry.getKey());
            }

        }
        System.out.println("Lexicographical order for the distinct words appearing in the extract, after removing the ones with 3 characters length: " + treeSet);
    }
}