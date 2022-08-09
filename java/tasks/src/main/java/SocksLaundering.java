import java.util.HashSet;
import java.util.Set;

public class SocksLaundering {
    public static void main(String[] args) {
        System.out.println(solution(3, new int[] {1, 2}, new int[] {8,8,8,8,9}));
        System.out.println(solution(2, new int[] {1,2,1,1}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{1,2,1,1,3}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{1,2,1,2,3}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{0,1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(10, new int[]{0,1,2,1,1,3,8}, new int[]{1,4,3,2,4}));
        System.out.println(solution(2, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(1, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));
        System.out.println(solution(3, new int[]{1,2,1,1,3,5}, new int[]{1,4,3,2,4}));

    }

    //given an integer K (the number of socks that the washing machine can clean),
    // two arrays C and D (containing the color representations of N clean and M dirty socks respectively),
    // returns the maximum number of pairs of socks that Bob can take on the trip.
    public static int solution(int K, int[] C, int[] D) {

        final Set<Integer> cleanWithoutPair = new HashSet<>();
        int cleanPair = 0;

        for (int i = 0; i < C.length; i++) {
            if(cleanWithoutPair.contains(C[i])) {
                cleanPair++;
                cleanWithoutPair.remove(C[i]);
            } else {
                cleanWithoutPair.add(C[i]);
            }
        }

        final Set<Integer> dirty = new HashSet<>();
        int pairAfterLaunder = 0;
        int socksInMachine = 0;

        for (int i = 0; i < D.length; i++) {

            if(cleanWithoutPair.contains(D[i])) {

                if(socksInMachine + 1 <= K) {

                    pairAfterLaunder++;
                    socksInMachine++;
                    cleanWithoutPair.remove(D[i]);

                } else {
                    break;
                }

            } else {

                if(dirty.contains(D[i])) {

                    if((socksInMachine + 2) <= K) {
                        pairAfterLaunder++;
                        socksInMachine += 2;
                        dirty.remove(D[i]);
                    }

                } else {

                    dirty.add(D[i]);

                }
            }
        }

        return cleanPair + pairAfterLaunder;
    }
}
