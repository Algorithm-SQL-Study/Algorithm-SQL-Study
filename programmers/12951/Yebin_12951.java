import java.lang.StringBuilder;


class Solution {
    public String solution(String s) {
        String answer = "";
        int n = s.length();
        boolean isFrontSpace = true;

        String[] tokens = s.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String temp = tokens[i];
            System.out.println(temp);

            if (isFrontSpace) {
                if (temp.equals(" ")) {
                    sb.append(temp);
                } else {
                    if (isInteger(temp)) {
                        sb.append(temp);
                    } else {
                        sb.append(temp.toUpperCase());
                    }
                    isFrontSpace = false;
                }
            } else {
                if (temp.equals(" ")) {
                    sb.append(temp);
                    isFrontSpace = true;
                } else {
                    sb.append(temp.toLowerCase());
                }
            }
        }

        return sb.toString();
    }

    private boolean isInteger(String c) {
        try {
            Integer.parseInt(c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}