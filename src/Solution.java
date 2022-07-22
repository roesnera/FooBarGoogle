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
        for(int i = 0; i<8; i++){
            System.out.println("---------------------------------");
//            System.out.println("Position input: "+i);
//            int[] coords = convertToCoords(i);
//            System.out.println("Coords output: "+Arrays.toString(coords));
//            System.out.println("Reconversion to position output: "+convertToNum(coords));
            for (int j = 0; j < 8; j++) {
                System.out.print(solution(54, i*8+j)+" | ");
            }
            System.out.println();
        }

        System.out.println(solution(54, 63));
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
//        System.out.println("Initial position of getOneMove(): " + initPos);
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

        int[] positions = {firstNewPos, secondNewPos, thirdNewPos, fourthNewPos, fifthNewPos, sixthNewPos, seventhNewPos, eighthNewPos};


        pruneLR(positions, initCoords);
        for(int pos : positions){
            if(pos >=0 && pos<=63){
                oneMovers.add(pos);
            }
            else{
            }
        }

        return oneMovers;
    }

    // prevents from moving invalidly left or right
    private static void pruneLR(int[] positions, int[] initCoords) {
        if(twoCloseRight(initCoords)){
            excludeTwoRight(positions);
        }
        if(oneCloseRight(initCoords)){
            excludeOneRight(positions);
        }
        if(twoCloseLeft(initCoords)){
            excludeTwoLeft(positions);
        }
        if(oneCloseLeft(initCoords)){
            excludeOneLeft(positions);
        }
    }

    //  the following methods adjust values in positions to prevent invalid moves left or right
    // values are set to -1 so they are caught before being inserted into final arraylist
    private static void excludeOneLeft(int[] positions) {
        positions[0] = -1;
        positions[4] = -1;
    }

    private static void excludeTwoLeft(int[] positions) {
        positions[2] = -1;
        positions[6] = -1;
    }

    private static void excludeOneRight(int[] positions) {
        positions[3] = -1;
        positions[7] = -1;
    }

    private static void excludeTwoRight(int[] positions) {
        positions[1] = -1;
        positions[5] = -1;
    }

    // the following methods check to see if the initial position is too close to the left or right
    private static boolean twoCloseLeft(int[] initCoords) {
        return initCoords[1]==0;
    }

    private static boolean oneCloseLeft(int[] initCoords) {
        return initCoords[1]<=1;
    }

    private static boolean twoCloseRight(int[] initCoords) {
        return initCoords[1]==7;
    }

    private static boolean oneCloseRight(int[] initCoords) {
        return initCoords[1]>=6;
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