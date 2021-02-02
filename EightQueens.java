package com.company;

/**
 * @author Zeeshan Chougle <a href="mailto:zeeshan.chougle@ucalgary.ca">
 * Zeeshan.chougle@ucalgary.ca</a>
 * @version 1.0
 * @since 1.0
 */

public class EightQueens implements Cloneable
{
    /**
     * This class solves the EightQueens Problem using recursion
     * This class has a single data member called board[][] which represents an 8x8 Chess board
     * It has 10 member functions performing the followings tasks:
     * 1. EightQueen() : This constructor fills the Board[][] with 'o'
     * 2. getBoard() :  it prints and returns the current state of board
     * 3. illegalCurrentState() : Checks for any illegal placement in cuurent state of board
     * 4. countQueens() : returns the total number of queens currently on board
     * 5. setQueen() : assigns 'Q' to the passed position of board
     * 6. emptySquare() : assigns 'o' to the passed position of board
     * 7. setQueens() : returns boolean to indicate the success/failure of queen placement
     * 8. recursion() : Performs recursion to allocate all queens to suitable positions
     * 9. testSafe() : Tests if the passed location is safe or not
     * 10. clone()  : returns a deep copy of the calling object
     */



    private char[][] board=new char[8][8];

    public EightQueens()    //Constructor to set all the boxes as 'o' initially
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                emptySquare(i,j);
            }
        }
    }
    public char[][] getBoard()  //returns and displays the current state of board
    {
        for(int i=0;i< board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        return board;
    }

    public boolean illegalCurrentState()       //Checks for an illegal placement in the current state of board
    {
        for(int i=0;i< board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(board[i][j]=='Q')
                {
                    for(int k=1;k< board.length;k++)  // k starts from 1 so that it does not count itself
                    {
                        if(((j+k)<=7&&board[i][j+k]=='Q')||((i+k)<=7&&board[i+k][j]=='Q')||((j-k)>=0&&board[i][j-k]=='Q')
                                ||((i-k)>=0&&board[i-k][j]=='Q')||((i+k)<=7&&(j+k)<=7&&board[i+k][j+k]=='Q')
                                ||((i-k)>=0&&(j-k)>=0&&board[i-k][j-k]=='Q')||((i+k)<=7&&(j-k)>=0&&board[i+k][j-k]=='Q')
                                ||((i-k)>=0&&(j+k)<=7&&board[i-k][j+k]=='Q'))
                        {
                            return true;    //returns true if there is an illegal placement of queens
                        }
                    }
                }
            }
        }
        return false;   //returns false if all placements are legal
    }

    public int countQueens()    //returns the total number of queens currently on Board
    {
        int counter=0;  //counts the total number of queens
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(board[i][j]=='Q')
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void setQueen(int row,int column)    //Sets Q in the passed location
    {
        board[row][column]='Q';
    }
    public void emptySquare(int row,int column) //Sets o in the passed location
    {
        board[row][column]='o';
    }

    public boolean setQueens(int queensRemaining)
    {
        int no_of_queens=countQueens(); //returns the number of queens on the current state of board
        boolean illegal=illegalCurrentState();  //returns true if illegal queen placement in current state of board

        //System.out.println(no_of_queens);

        if(illegal||no_of_queens+queensRemaining>8||recursion(queensRemaining+no_of_queens,no_of_queens)==false)
        {
            return false;
        }
        return true;
    }

    public boolean recursion(int queensRemaining,int col)   // performs recursion to place Q in all safe positions
    {
        if (col >= queensRemaining)       //Base case for recursion
            return true;

        for (int row = 0; row < 8; row++) {     //Iterate through each row to find a safe position

            if (testSafe(row, col)) {

                setQueen(row,col);

                if (recursion(queensRemaining,col+1) == true)      //recursive part
                    return true;

                emptySquare(row,col);  // Backtracking
            }
        }

        return false;
    }

    public boolean testSafe(int row,int col)    //Checks if the passed position is safe or not
    {
        int i=row,j=col;

        for(int k=1;k<board.length;k++)       //changes are to be here mainly k value
        {

            if(((j+k)<=7&&board[i][j+k]=='Q')||((i+k)<=7&&board[i+k][j]=='Q')||((j-k)>=0&&board[i][j-k]=='Q')
                    ||((i-k)>=0&&board[i-k][j]=='Q')||((i+k)<=7&&(j+k)<=7&&board[i+k][j+k]=='Q')
                    ||((i-k)>=0&&(j-k)>=0&&board[i-k][j-k]=='Q')||((i+k)<=7&&(j-k)>=0&&board[i+k][j-k]=='Q')
                    ||((i-k)>=0&&(j+k)<=7&&board[i-k][j+k]=='Q'))
            {
                /*
                    This if condition checks if a queen is encountered horizontally,vertically or diagonally
                    with respect to the current box(i,j) ,if a queen is encountered the position
                    is unsafe and false is returned
                 */

                return false;
            }

        }
        return true;      //returns true if it is a safe position for queen placement

    }

    public Object clone() throws CloneNotSupportedException //Returns a deep copy of the calling object
    {
        char[][] cloned = new char[board.length][];
        for(int i=0;i< board.length;i++)
        {
            cloned[i]=board[i].clone();
        }
        EightQueens copy=(EightQueens) super.clone();
        return copy;
    }

    public static void main(String[] args) throws CloneNotSupportedException{


        boolean c;  //check if success or failure

        System.out.println("Here are a few example solutions : ");

        System.out.println("\nGenerating one solution:");
        EightQueens s1=new EightQueens();// creating a class instance
        s1.setQueens(8);
        s1.getBoard();
        System.out.println();

        System.out.println("\nSecond possible solution:");
        EightQueens s2=new EightQueens();// creating a class instance
        s2.setQueen(5,0);
        c=s2.setQueens(7);
        s2.getBoard();
        System.out.println();

    }
}
