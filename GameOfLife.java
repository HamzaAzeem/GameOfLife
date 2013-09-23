/**
 * The Game of Life was devised by mathematician John Conway in 1970. It models a very simple world. 
   The Life world is a two-dimensional plane of cells. Each cell may be empty or contain a single creature. 
   Each day, creatures are born or die in each cell according to the number of neighboring creatures on 
   the previous day. A neighbor is a cell that adjoins the cell either horizontally, vertically, or diagonally.
 * Hamza Azeem
 * ICS3UR-A
 * Mr.Brown
 * June 11, 2012
 */
import java.util.*;
public class GameOfLife
{
    private String[][] cells = new String[20][20]; //Current Grid
    private String[][] cells2 = new String[20][20]; //Next day's grid
    int rows, cols, howMany, counter, aliveCells, day, stopGame;
    Scanner input = new Scanner(System.in);
    /**
     * Constructor for objects of class GameOfLife
     */
    public GameOfLife()
    {
        aliveCells = 1;//Number of alive cells initialized to 1 for now
        day = 1;//Starts wth day 1
        
        /*Print empty grid*/
        for (rows = 0; rows < cells.length; rows++) {
            for (cols = 0; cols < cells[0].length; cols++) {
                cells[rows][cols]="O";//All cells (elements) in first array is equal to "O"
                cells2[rows][cols]="O";//All cells (elements) in second array is equal to "O"
                System.out.print(cells[rows][cols]);
            }
            System.out.println();
        }
        
        getInput();
        
        while (stopGame != -1 && aliveCells > 0) { //As long as user does not enter -1 and there are more than 0 cells alive, game keeps running
            System.out.println("Day " + day);//Displays day
            displayBoard();
            countNeighbours();            
            aliveCells = checkAlive(cells);
            if (aliveCells > 0) { //If there are more than 0 cells currently alive, user has choice to continue playing
                System.out.print("Continue on to the next day? Type in any number for YES, or -1 for NO ");
                stopGame = input.nextInt();
            }else {
                System.out.println("All cells have died");
            }
            day++;
        }
        System.out.println("Thanks for playing!");
    }
    
    /**
     * Gets user input
     */
    public void getInput()
    {
        System.out.print("How many cells do you want to be alive? ");
        howMany = input.nextInt();
        System.out.println("Please enter a number from 0 to 19");
        for (int i = 0; i < howMany; i++) {
            System.out.print("Enter the X co-ordinate: ");
            
            /*User MUST enter a number from 0 to 19, otherwise program will keep prompting them to enter the correct value*/
            do { 
                rows = input.nextInt();
                if (rows < 0 || rows > 19) {
                    System.out.print("The number must be from 0 to 19! ");
                }
            }while (rows < 0 || rows > 19);
            System.out.print("Enter the Y co-ordinate: ");
            do {
                cols = input.nextInt();
                if (cols < 0 || cols > 19) {
                    System.out.print("The number must be from 0 to 19! ");
                }
            }while (cols < 0 || cols > 19);
            /*Makes the cells user inputted in the second array equal to "X"*/
            cells2[rows][cols] = "X";
        }
    }
        
    /**
     * Displays updated board
     */
    public void displayBoard()
    {
        for (rows = 0; rows < cells.length; rows++) {
            for (cols = 0; cols < cells[0].length; cols++) {
                cells[rows][cols] = cells2[rows][cols]; //Makes today's grid, tomorrow
                System.out.print(cells[rows][cols]); //Prints today's grid
            }
            System.out.println(); //Goes to next row
        }
    }
    
    /**
     * Counts how many neighbours around each cell are alive and increases counter by 1 for every one that's alive
     */
    public void countNeighbours()
    {
        counter = 0;
        int a, b;//Represents the neighbour's co-ordinates
        
        /*If the neighbouring cell is not off the grid AND if the cell is alive ("X"), then it will increase the counter*/
        for (rows = 0; rows < cells.length; rows++) {
            for (cols = 0; cols < cells[0].length; cols++) {
                a = rows-1;
                b = cols;
                if (a >= 0 && a < 20){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows + 1;
                b = cols;
                if (a >= 0 && a < 20){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows;
                b = cols - 1;
                if (b >= 0 && b < 20){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows;
                b = cols + 1;
                if (b >= 0 && b < 20){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows - 1;
                b = cols - 1;
                if (a >= 0 & b >= 0){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows + 1;
                b = cols + 1;
                if (a < 20 && b < 20){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows - 1;
                b = cols + 1;
                if (a >= 0 && b < 20){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                
                a = rows + 1;
                b = cols - 1;
                if (a < 20 && b >= 0){
                    if (cells[a][b].equals("X")){
                        counter++;
                    }
                }
                makeAliveOrNot();//Makes the cells alive or not depending on the conditions
                counter = 0;//Resets the counter so that the next cell's neighbours can be checked and counted
            }         
        }
    }
   
    /**
     * Changes a cell's condition to dead or alive (O or X) depending on how many neighbours it has
     */
    public void makeAliveOrNot()
    {
       /*Checks value of first array, but makes the changes to the second*/
       if (cells[rows][cols].equals("X")) {
           if (counter == 2 || counter == 3) {
               cells2[rows][cols] = "X";
            }else {
               cells2[rows][cols] = "O";
            }
        }else if (cells[rows][cols].equals("O")) {
            if (counter == 3) {
                cells2[rows][cols] = "X";
            }else {
                cells2[rows][cols]= "O" ;
            }
       }
    }
       
    /**
     * Checks how many cells are currently alive so that the game can keep running if there are more than 0 cells alive
     */
    public int checkAlive(String cells[][])
    {
        int numAlive = 0;//Counter for the number of cells alive
        for (rows = 0; rows < cells.length; rows ++){      
            for (cols = 0; cols < cells[0].length; cols++) {    
                if (cells[rows][cols].equals("X")) {
                    numAlive ++;
                }
            }
        }
        return (numAlive);
    }
}