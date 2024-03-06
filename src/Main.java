import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
//        System.out.println(isBracketInCorrectOrder(""));
//        System.out.println(isBracketInCorrectOrder("{]"));
//        System.out.println(isBracketInCorrectOrder("(({}))"));
//        System.out.println(isBracketInCorrectOrder("(([{]}))"));
//        System.out.println(isBracketInCorrectOrder("{}{}[]([])"));
        System.out.println(isPalindrome());
    }

    private static boolean isBracketInCorrectOrder(String bracketOnlyString) {
        char[] bracketList = bracketOnlyString.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char bracket : bracketList) {
            switch (bracket) {
                case '{', '[', '(':
                    stack.push(bracket);
                    break;
                case '}':
                    if(stack.pop() != '{') {return false;}
                    break;
                case ']':
                    if(stack.pop() != '[') {return false;}
                    break;
                case ')':
                    if(stack.pop() != '(') {return false;}
                    break;
            }
        }
        return true;
    }

    private static boolean isPalindrome() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string so that I may check if it's a palindrome (symbols are ignored)"); 
        String word = sc.nextLine();

        ArrayList<Character> filteredSplittedWord = getCharacters(word);
        int wordLength = filteredSplittedWord.size();

        return (wordLength % 2 == 0) ?
                checkPalindromeEven(wordLength, filteredSplittedWord) :
                checkPalindromeOdd(wordLength, filteredSplittedWord);
    }

    private static boolean checkPalindromeOdd(int wordLength, ArrayList<Character> listedWord) {
        char expectedChar;
        Stack<Character> characterStack = new Stack<>();

        for (int i=0; i < wordLength / 2; i++) { // adds to stack for the first half
            characterStack.push(listedWord.get(i));
        }
        for (int i=wordLength / 2 + 1; i < wordLength; i++) { // checks against stack. Ignores middle element
            expectedChar = characterStack.pop();
            if (listedWord.get(i) != expectedChar) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkPalindromeEven(int wordLength, ArrayList<Character> listedWord) {
        char expectedChar;
        Stack<Character> characterStack = new Stack<>();

        for (int i=0; i < wordLength / 2; i++) { // adds to stack for the first half
            characterStack.push(listedWord.get(i));
        }
        for (int i=wordLength / 2; i < wordLength; i++) { // checks second half of the list against the stack
            expectedChar = characterStack.pop();
            if (listedWord.get(i) != expectedChar) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Character> getCharacters(String word) {
        String lowerCaseWord = word.toLowerCase(); // makes it easier to compare items in stack
        char[] splittedWord = lowerCaseWord.toCharArray();
        // there's no easy filter function in Java Grrrr
        ArrayList<Character> filteredSplittedWord = new ArrayList<>();

        for (char c : splittedWord) { //filters out anything that isn't an alphabet
            if (c >= 'a' && c <= 'z') {
                filteredSplittedWord.add(c);
            }
        }
        return filteredSplittedWord;
    }
}
