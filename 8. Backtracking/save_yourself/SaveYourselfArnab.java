/**
 * SaveYourselfArnab.java
 * given a string,print all possible palindromic partitions of the given string.
 * Description:-
 * Its a typical backtracking problem using recursion in order to calculate all possible palindromic partitions of substrings.
 * We just need to make an Arraylist that will store all such possible partitions and a Deque which will store palindromic partitions
 * of each possibility.Now create a separate function for implementing recursive backtracking.First start a loop from start index 
 * to end where we will collect every substring.Then check the string is palindrome or not using a separate method.Then if it 
 * comes palindrome then add it to Deque and start recursion from after the last palindromic string in order to check palindrome for
 * remaining string.Now if at any point the string doesn't comes palindrome,backtrack from that point of recursion by removing the
 * last string added to deque and again start from a new index for checking palindrome.
 * Time Complexity-O(n*(2^n)) (because outer loop runs entire length of string hence O(n) and inner recursion runs adds and 
 * removes string which takes O(2^n) time)
 * Space Complexity-O(n*m) where m is the no of partitions.
 * @author [codebook-2000](https://github.com/codebook-2000)
 */
 
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class SaveYourselfArnab {
    // Function to print all possible
    // palindromic partitions of s.
    // It mainly creates vectors and
    // calls allPalindromesUtil()
    static String allPalindromes(String s) {
        int n = s.length();

        // To Store all palindromic partitions
        ArrayList<ArrayList<String>> entire = new ArrayList<ArrayList<String>>();

        // To store current palindromic partition
        Deque<String> row = new ArrayDeque<String>();

        // Call recursive function to generate
        // all partitions and store in entire 
        allPalindromesUtil(entire, row, 0, n, s);

        //Append the ans to this
        StringBuilder sb = new StringBuilder();
        // Print all partitions generated by above call
        for (int i = 0; i < entire.size(); i++) {
            for (int j = 0; j < entire.get(i).size(); j++) {
                sb.append(entire.get(i).get(j) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();//return the string value
    }

    // Recursive function to find all palindromic
    // partitions of input[start..n-1] 
    // entire--> A
    // ArrayList of Deque of strings. Every Deque
    // inside it stores a partition
    // row --> A
    // Deque of strings to store current partition
    static void allPalindromesUtil(ArrayList<ArrayList<String>> entire,
                                   Deque<String> row, int start, int n, String s) {
        // If 'start' has reached len
        if (start >= n) {
            entire.add(new ArrayList<>(row));//If we have reached end of row,then just add the
            return;                          //entire string to arraylist and return from method
        }

        // Here we will select all possible substrings by recursion inside loop
        for (int i = start; i < n; i++) {

            // If substring str[start..i] is palindrome
            if (isPalindrome(s, start, i))  //If the substring is a palindrome
            {

                // Add the substring to the ans of that row
                row.addLast(s.substring(start, i + 1));

                // Recur for remaining substring
                allPalindromesUtil(entire, row, i + 1, n, s);

                //After completion of the substring addition,make the string remove from row part
                //to make space for calculating the new palindrome substring
                row.removeLast();
            }
        }
    }

    // the function checks
    // if a string is Palindrome or not
    static boolean isPalindrome(String s,
                                int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }


    public static void main(String[] args) throws java.lang.Exception {
        // your code goes here
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(buf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = buf.readLine();
            String ans = allPalindromes(s);
            sb.append(ans);//Append the ans to the respective test case.
        }
        System.out.println(sb);  //Printing it
    }
}
/*
Input
2
aab
nitin

Output:-
a a b 
aa b 
n i t i n 
n iti n 
nitin 
*/