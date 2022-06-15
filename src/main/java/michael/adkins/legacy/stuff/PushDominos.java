package michael.adkins.legacy.stuff;

public class PushDominos {

    /**
     * Space Complexity: O(n)
     * Time Complexity: O(2n) = O(n) b/c we ignore constant time
     * @param dominoes Set of Dominoes' push directions
     * @return The result of the domino set after they have been pushed.
     */
    public static String pushDominoes(String dominoes) {
        int[] forces = new int[dominoes.length()];
        int force = 0;
        for (int i = 0; i < dominoes.length(); i++) {
            char d = dominoes.charAt(i);
            force = switch (d) {
                case 'R' -> 10;
                case 'L' -> 0;
                default -> Math.max(0, force - 1);
            };
            forces[i] = force;
        }
        for (int j = dominoes.length() - 1; j >= 0; j--) {
            char d = dominoes.charAt(j);
            force = switch (d) {
                case 'R' -> 0;
                case 'L' -> -10;
                default -> Math.min(force + 1, 0);
            };
            forces[j] += force;
        }
        StringBuilder buf = new StringBuilder();
        for (int f : forces) {
            if(f > 0) {
                buf.append('R');
            } else if(f < 0) {
                buf.append('L');
            } else {
                buf.append('.');
            }
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        String dominoes = "..RR.LL..RR";
        System.out.println(pushDominoes(dominoes));
    }
}
