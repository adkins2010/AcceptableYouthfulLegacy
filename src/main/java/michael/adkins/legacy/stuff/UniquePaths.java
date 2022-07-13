package michael.adkins.legacy.stuff;

import java.util.Map;

/**
 * Given a graph, and you can only go down or right, what are the total number of paths that you can take to reach the end?
 */
public class UniquePaths {

    public static int uniquePaths(int m, int n) {
        if(m==1 || n==1) {
            return 1;
        }
        return uniquePaths(m-1, n) + uniquePaths(m, n-1);
    }

    public static int uniquePathsDP(int m, int n) {
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            cache[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            cache[0][j] = 1;
        }
        for(int i = 1; i<m; i++) {
            for(int j = 1; j<n; j++) {
                cache[i][j] = cache[i][j-1] + cache[i-1][j];
            }
        }
        return cache[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(5, 3));
        System.out.println(uniquePathsDP(5, 3));
    }
}
