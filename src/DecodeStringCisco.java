import util.StringDataParser;

import java.util.ArrayList;
import java.util.List;

public class DecodeStringCisco {
    // （ab){3} 输出ababab 输入 (ab(c){2}){2} 输出abccabcc
    private static class Solution {
        int index = -1;

        public String DecodeString(String s) {
            return reverse(DecodeString2(s));
        }

        public String DecodeString2(String s) {
            if (index == -1) this.index = s.length() - 1;
            StringBuilder sb = new StringBuilder();
            while (index >= 0 && s.charAt(index) != '(') {
                if (s.charAt(index) == '}') {
                    index--;
                } else if (!Character.isDigit(s.charAt(index)) && s.charAt(index) != '(' && s.charAt(index) != ')') {
                    sb.append(s.charAt(index--));
                } else if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                    index--;
                } else {
                    int k = 0;
                    int base = 1;
                    while (index >= 0 && Character.isDigit(s.charAt(index))) {
                        k += (s.charAt(index--) - '0') * base;
                        base *= 10;
                    }
                    index--;
                    String sub = this.DecodeString2(s);
                    index--;
                    for (int i = 0; i < k; i++) {
                        sb.append(sub);
                    }
                }
            }
            return sb.toString();
        }

        private String reverse(String s) {
            StringBuilder sb = new StringBuilder();
            int i = s.length() - 1;
            while (i >= 0) {
                sb.append(s.charAt(i--));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        runtest();
    }

    private static void runtest() {
        String filename = "data/DecodeStringCiscoData";
        StringDataParser stringDataParser = new StringDataParser(filename);
        Solution solution = new Solution();
        List<String> input = stringDataParser.getInput();
        List<String> key = stringDataParser.getAnswer();
        for (int i = 0; i < input.size(); i++) {
            String str = input.get(i);
            String output = solution.DecodeString(str);
            if (!output.equals(key.get(i))) {
                System.out.println("test failed on " + str);
                System.out.println("output = " + output);
                System.out.println("answer = " + key.get(i));
                return;
            }
        }
        System.out.println("all tests passed");
    }
}
