import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr;
        inputStr = scanner.nextLine();
        if (isValid(inputStr))
            System.out.println(strConvertion(inputStr.toCharArray()));
        else
            System.out.println("the string is not valid");
    }

    public static String strConvertion(char[] inputStr) {
        String strToReturn = "";
        // nested parenthesis counter
        int numOfBrackets = 0;
        // opening position
        int indexOfFirstBracket = 0;
        // rate repeater of repeat char
        int repeater = 0;

        for (int i = 0; i < inputStr.length; i++) {
            if (Character.isLetter(inputStr[i]) && numOfBrackets == 0) {
                strToReturn += inputStr[i];
            }

            if (Character.isDigit(inputStr[i]) && inputStr[i] != '[' && numOfBrackets == 0) {
                repeater = Integer.parseInt(String.valueOf(inputStr[i]));

            }

            if (inputStr[i] == '[') {
                numOfBrackets++;
                if (numOfBrackets == 1)
                    indexOfFirstBracket = i;
            }

            if (inputStr[i] == ']') {
                numOfBrackets--;
                if (numOfBrackets == 0) {
                    strToReturn += strConvertion(Arrays.copyOfRange(inputStr, indexOfFirstBracket + 1, i)).repeat(repeater);
                    indexOfFirstBracket = 0;
                    repeater = 0;
                }
            }
        }
        return strToReturn;
    }

    public static boolean isValid(String inputStr) {
        int numOfCurlyBrace = 0;
        int numOfNumbers = 0;
        for (int i = 0; i < inputStr.length(); i++) {
            //number a counter?
            if (Character.isDigit(inputStr.toCharArray()[i])) {
                numOfNumbers++;
            }

            if (inputStr.toCharArray()[i] == '[') {
                numOfCurlyBrace++;
            }

            // have ']'?
            if (inputStr.toCharArray()[i] == ']') {
                numOfCurlyBrace--;
                if (numOfCurlyBrace < 0) {
                    return false;
                }
            }
        }

        // have opening parentheses?
        if (numOfCurlyBrace != 0) {
            return false;
        }
        return (Pattern.matches("[\\w\\[\\]]+", inputStr));
    }
}
