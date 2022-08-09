public class TennisTournament {
    public static void main(String[] args) {
        System.out.println(solution(5, 7));
    }

//    given the number of players P and the number of reserved courts C,
//    returns the maximum number of games that can be played in parallel

    public static int solution(int P, int C) {
        int possibleGames = P/2;
        return possibleGames > C ? C : possibleGames;
    }
}
