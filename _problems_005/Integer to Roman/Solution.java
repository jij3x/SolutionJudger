public class Solution {
    public String intToRoman(int num) {
        String[][] roman = {
            {"I", "V"},
            {"X", "L"},
            {"C", "D"},
            {"M"}
        };
        int digits = 0;
        String r = "";
        while (num > 0) {
            int dig = num%10;
            if (dig == 0) {
            } else if (dig <= 3) {
                for (int i = 0; i < dig; i++)
                    r = roman[digits][0] + r;
            } else if (dig == 4) {
                r = roman[digits][0] + roman[digits][1] + r;
            } else if (dig <= 8) {
                String temp = roman[digits][1];
                for (int i = 5; i < dig; i++)
                    temp += roman[digits][0];
                r = temp + r;
            } else if (dig == 9) {
                r = roman[digits][0] + roman[digits+1][0] + r;
            }
            digits++;
            num /= 10;
        }
        return r;
    }
}