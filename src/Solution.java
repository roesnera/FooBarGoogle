import java.util.ArrayList;
import java.util.Arrays;

/*
    Calculate the fewest moves needed to move from location on a chessboard to another
    RESTRICTIONS:
        can only move like a knight
        input is single digit, starting from 0-63 in the upper left-hand corner of the board


 */

public class Solution {
    public static void main(String[] args) {
        System.out.println("Expected: [1,1]");
        System.out.println(Arrays.toString(convertToCoords(9)));

        System.out.println("Expected: 9");
        System.out.println(convertToNum(convertToCoords(9)));

        System.out.println("Expected: 3");
        System.out.println(""+solution(0,1));
    }



    public static int solution(int src, int dest) {
        //Your code here

        // if src and dest are equal, return 0 moves
        int numMoves = 0;
        if(src == dest) {return numMoves;}


        // create some structure to track possible current locations given number of moves made
        // currently zero moves, so this only contains one entry: src
        ArrayList<Integer> possibleLocations = new ArrayList<Integer>();
        possibleLocations.add(src);


        // could have used a for loop here but while loop makes more sense given unknown iterations
        // each iteration through the loop represents branching out by one possible move
        // loop will iterate until it finds a move that represents dest, then it will return numMoves
        while(true){

            // increment num moves at the start of each iteration
            numMoves++;

            // this represents the possible locations one square away from any possible current location, given numMoves
            ArrayList<Integer> oneMovers = new ArrayList<Integer>();

            for(int possibleLocation : possibleLocations){
                oneMovers.addAll(getOneMove(possibleLocation));
            }

            // checks to see if dest is in oneMovers, if so then return
            if(oneMovers.contains(dest)){
                return numMoves;
            }

            // update possible current locations to reflect having made one more move
            possibleLocations = oneMovers;
        }
    }


    // returns an arraylist of possible, allowed moves given a starting location
    private static ArrayList<Integer> getOneMove(int initPos){
        ArrayList<Integer> oneMovers = new ArrayList<Integer>();
        int[] initCoords = convertToCoords(initPos);
        int firstNewPos = convertToNum(new int[]{initCoords[0] - 2, initCoords[1] - 1});
        int secondNewPos = convertToNum(new int[]{initCoords[0] - 2, initCoords[1] + 1});
        int thirdNewPos = convertToNum(new int[]{initCoords[0] - 1, initCoords[1] - 2});
        int fourthNewPos = convertToNum(new int[]{initCoords[0] - 1, initCoords[1] + 2});
        int fifthNewPos = convertToNum(new int[]{initCoords[0] + 2, initCoords[1] - 1});
        int sixthNewPos = convertToNum(new int[]{initCoords[0] + 2, initCoords[1] + 1});
        int seventhNewPos = convertToNum(new int[]{initCoords[0] + 1, initCoords[1] - 2});
        int eighthNewPos = convertToNum(new int[]{initCoords[0] + 1, initCoords[1] + 2});

        oneMovers.add(firstNewPos);
        oneMovers.add(secondNewPos);
        oneMovers.add(thirdNewPos);
        oneMovers.add(fourthNewPos);
        oneMovers.add(fifthNewPos);
        oneMovers.add(sixthNewPos);
        oneMovers.add(seventhNewPos);
        oneMovers.add(eighthNewPos);

        // if location is off the board, disregard
        oneMovers.removeIf(pos -> pos < 0 || pos > 63);
        return oneMovers;
    }

    // converts a single digit position to coordinates for easier move calculation
    private static int[] convertToCoords(int pos){
        int posLRPos = pos%8;
        int posNSPos = pos/8;
        int[] posCoords = {posNSPos, posLRPos};
        return posCoords;
    }

    // converts coords back to single digit position for simpler data structure management
    private static int convertToNum(int[] coords){
        int row = coords[0];
        int col = coords[1];
        int pos = row*8 + col;
        return pos;
    }
}