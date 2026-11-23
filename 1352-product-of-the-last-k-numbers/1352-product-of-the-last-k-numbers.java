
import java.util.*;

class ProductOfNumbers {

    List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
    }

    public void add(int num) {

        // Zero resets the prefix products
        if (num == 0) {
            prefix.clear();
            return;
        }

        // First number after zero
        if (prefix.isEmpty()) {
            prefix.add(num);
        } 
        else {
            int last = prefix.get(prefix.size() - 1);
            prefix.add(last * num);
        }
    }

    public int getProduct(int k) {

        int n = prefix.size();

        // Requested range contains a zero
        if (k > n) {
            return 0;
        }

        // Product of the entire current segment
        if (k == n) {
            return prefix.get(n - 1);
        }

        // Divide prefix products
        return prefix.get(n - 1) / prefix.get(n - k - 1);
    }
}
