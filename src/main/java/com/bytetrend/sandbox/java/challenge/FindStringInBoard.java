package com.bytetrend.sandbox.java.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
/**
 * Created by db2admin on 1/15/2018.
 */
public class FindStringInBoard {
    public static class Tuple {
        public Tuple(int i , int j){
            x =i;
            y = j;
        }
        public boolean equals(Tuple that){
            if(this.x != that.x && this.y != that.y)
                return true;
            else
                return false;
        }
        public int hashCode(){
            return 31 * x + y;
        }
        public int x = -1;
        public int y = -1;
    }
    public static List<Tuple> findFirstChar(char[][] board, char c  ){
        List<Tuple> locations = new ArrayList<>();
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                if(board[i][j] == c)
                    locations.add(new Tuple(i,j));
        return locations;
    }


    public static void findNext(char[][] board,Stack<Tuple> path,Tuple last,String next){
        List<Tuple> toVisit = new ArrayList();
        if(last.x - 1 > 0) toVisit.add(new Tuple(last.x -1,last.y));
        if(last.x + 1 < board[0].length) toVisit.add(new Tuple(last.x + 1,last.y));
        if(last.y - 1 > 0) toVisit.add(new Tuple(last.x,last.y -1));
        if(last.y + 1 < board.length) toVisit.add(new Tuple(last.x,last.y + 1));

        for(Tuple t1 : toVisit){
            boolean seen = false;
            for(Tuple t2 : path){
                if(t2.equals(t1)) {
                    seen = true;
                    break;
                }
            }
            if(!seen && board[t1.x][t1.y] == next.charAt(0)){

            }
        }

    }
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        while ((line = in.readLine()) != null) {
            // System.out.println(line);
            boolean found = false;
            List<Tuple> starting = findFirstChar(board,line.charAt(0));
            if(starting.size() > 0 ){
                for(Tuple t : starting) {
                    Stack<Tuple> path = new Stack<>();
                    path.push(t);
                    findNext(board, path, t,line.substring(1));
                    if(path.size() == line.length()) {
                        found = true;
                        break;
                    }
                }
            }
            System.out.println(found?"True":"False");
        }
    }
}


