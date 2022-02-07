package pkg2048;

import java.util.*;

public class Game2048 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
//        System.out.println("What will be the size of grid 4-(4X4) or 8-(8X8):");//press 4 for 4*4 or 8 for 8*8
//        int gridSize = s.nextInt();
//        System.out.println("At what number the game should get end 2048 or 4096");// enter any one from 2048 or 4096
//        int highestScore = s.nextInt();
        int gridSize = 4,highestScore = 2048;
        Integer[][] grid = new Integer[gridSize][gridSize];// main game grid
        for(int i=0;i<gridSize;i++){
            Arrays.fill(grid[i],0);
        }
        int fillgrid = gridSize/2;//grid will initially have two or four (depends on size) cells populated at random with a 2 or 4.
        while(fillgrid!=0){
            putnum(grid);
            fillgrid--;
        }
        for(int i=0;i<gridSize;i++){ //printing initials values of grid 
            for(int j=0;j<gridSize;j++){
                System.out.format("%5d",grid[i][j]);
            }
            System.out.println("");
        }
        int score = 0,n; //score used for player score and n will take input for movements
        boolean flag; //if player make a wrong choice
        Integer[][] copyArr = new Integer[gridSize][gridSize];
        while(highestScore!=score){
            flag = true;
            for(int i=0;i<gridSize;i++){
                System.arraycopy(grid[i], 0, copyArr[i], 0, gridSize);
            }
            System.out.println("Press 1-left,2-right,3-up,4-down & 5-exit");
            n = s.nextInt();
            switch (n) {
                case 1:
                    left(grid);//left mavement
                    break;
                case 2:
                    right(grid);//right movement
                    break;
                case 3:
                    up(grid);//up movement
                    break;
                case 4:
                    down(grid);//down movement
                    break;
                case 5:
                    System.out.println("You left the game!!");
                    System.exit(0);
                default:
                    flag = false;
                    System.out.println("Wrong Choice");
                    break;
            }
            if(flag && !check(copyArr,grid)){ //check method used to check wheather previous grid and new grid are same or not if same then addition of new number will not take place
                if(putnum(grid)){ //putnum method used for adding new number 2 or 4 return type is boolean because if grid got full and no place for new number than it will return true and game over
                    System.out.println("Game Over!!");
                    System.exit(0);
                }
                score = printGrid(grid);//print new grid and return highest number in grid which is score of player
            }
        }
        System.out.println("Congratulations, You Won!!");
    }

    private static void left(Integer[][] grid) {
        List<List<Integer>> list = new ArrayList<>();
        for (Integer[] grid1 : grid) {
            list.add(new ArrayList<>(Arrays.asList(grid1)));
        }
        int row = 0;
        for(List<Integer> l:list){
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            for(int i=1;i<l.size();i++){
                if(Objects.equals(l.get(i), l.get(i-1))){ // adding same number
                    l.set(i-1, l.get(i-1)+l.get(i));
                    l.set(i, 0);
                    i++;
                }
            }
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            for(int i=0;i<grid.length;i++){
                if(i>=l.size()){
                    grid[row][i] = 0;
                }else{
                    grid[row][i] = l.get(i);
                }
            }
            row++;
        }
    }

    private static void right(Integer[][] grid) {
        List<List<Integer>> list = new ArrayList<>();
        for (Integer[] grid1 : grid) {
            list.add(new ArrayList<>(Arrays.asList(grid1)));
        }
        int row = 0;
        for(List<Integer> l:list){
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            for(int i=l.size()-1;i>0;i--){
                if(Objects.equals(l.get(i), l.get(i-1))){ // adding same number
                    l.set(i, l.get(i-1)+l.get(i));
                    l.set(i-1, 0);
                    i--;
                }
            }
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            int k = l.size()-1;
            for(int i=grid.length-1;i>=0;i--){
                if(k<0){
                    grid[row][i] = 0;
                }else{
                    grid[row][i] = l.get(k);
                }
                k--;
            }
            row++;
        }
    }

    private static void up(Integer[][] grid) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<grid.length;i++){
            List<Integer> l = new ArrayList<>();
            for (Integer[] grid1 : grid) {
                l.add(grid1[i]);
            }
            list.add(l);
        }
        int col = 0;
        for(List<Integer> l:list){
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            for(int i=1;i<l.size();i++){
                if(Objects.equals(l.get(i), l.get(i-1))){ // adding same number
                    l.set(i-1, l.get(i-1)+l.get(i));
                    l.set(i, 0);
                    i++;
                }
            }
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            for(int i=0;i<grid.length;i++){
                if(i>=l.size()){
                    grid[i][col] = 0;
                }else{
                    grid[i][col] = l.get(i);
                }
            }
            col++;
        }
    }

    private static void down(Integer[][] grid) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<grid.length;i++){
            List<Integer> l = new ArrayList<>();
            for (Integer[] grid1 : grid) {
                l.add(grid1[i]);
            }
            list.add(l);
        }
        int col = 0;
        for(List<Integer> l:list){
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            for(int i=l.size()-1;i>0;i--){
                if(Objects.equals(l.get(i), l.get(i-1))){ // adding same number
                    l.set(i, l.get(i-1)+l.get(i));
                    l.set(i-1, 0);
                    i--;
                }
            }
            for(int i=l.size()-1;i>=0;i--){
                if(l.get(i)==0){ //remove zeros
                    l.remove(i);
                }
            }
            int k = l.size()-1;
            for(int i=grid.length-1;i>=0;i--){
                if(k<0){
                    grid[i][col] = 0;
                }else{
                    grid[i][col] = l.get(k);
                }
                k--;
            }
            col++;
        }
    }

    private static int printGrid(Integer[][] grid) {
        int score = 0;
        for (Integer[] grid1 : grid) {
            for (int j = 0; j<grid.length; j++) {
                score = Math.max(score, grid1[j]);
                System.out.format("%5d", grid1[j]);
            }
            System.out.println("");
        }
        return score;
    }

    private static boolean putnum(Integer[][] grid) {
        List<Position> list = new ArrayList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                if(grid[i][j]==0){
                    list.add(new Position(i,j));//first list will add all position(i,j) where we can add new number(2,4)
                }
            }
        }
        if(list.isEmpty()){ // no position for new number
            return true;
        }
        int pos = (int)Math.floor(Math.random()*(list.size())); // randomly select one position
        int num = (int)Math.floor((Math.random()*2)+1);// randomly select 1 or 2
        grid[list.get(pos).i][list.get(pos).j] = num*2; // grid[row][col] = 0 will change by 2 or 4
        return false;
    }

    private static boolean check(Integer[][] arr, Integer[][] grid) {
        int k = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                if(Objects.equals(arr[i][j], grid[i][j])){
                    k++;
                }
            }
        }
        return k == 16;
    }
    static class Position{ //Position class will help to store two variables in one object
        int i;
        int j;
        Position (int i,int j){
            this.i = i;
            this.j = j;
        }
    }
}
