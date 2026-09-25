import java.util.*;

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parse();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse() {
        Set<String> result = new HashSet<>();
        Set<String> cur = new HashSet<>();

        cur.add("");

        while (index < expression.length() && expression.charAt(index) != '}') {
            char ch = expression.charAt(index);

            if (ch == ',') {
                // End of one union part
                result.addAll(cur);
                cur = new HashSet<>();
                cur.add("");
                index++;
            }
            else if (ch == '{') {
                // Parse the expression inside {}
                index++; // skip '{'

                Set<String> inside = parse();

                index++; // skip '}'

                cur = concatenate(cur, inside);
            }
            else {
                // Normal character
                String letter = String.valueOf(ch);

                Set<String> single = new HashSet<>();
                single.add(letter);

                cur = concatenate(cur, single);

                index++;
            }
        }

        // Add the last part
        result.addAll(cur);

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String s1 : a) {
            for (String s2 : b) {
                result.add(s1 + s2);
            }
        }

        return result;
    }
}