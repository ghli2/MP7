import java.util.Scanner;
/**
 * Write a description of class Battleship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battleship
{
    String[][] board = new String[6][6];
    String[][] playerBoard = new String[6][6];
    String[][] guessBoard = new String[6][6];
    int[][] playerOptions = new int[6][6];
    int[][] computerOptions = new int[6][6];
    int[][] computeraihelp = new int[6][6];

    public Battleship()
    {
        for(int x=0; x<6; x++)
        {
            for(int y=0; y<6; y++)
            {
                board[x][y] = "-";
            }
        }
        for(int x=0; x<6; x++)
        {
            for(int y=0; y<6; y++)
            {
                guessBoard[x][y] = "-";
            }
        }
        int vertical = (int)(Math.random()*2);
        if(vertical == 0)
        {
            int i = (int)(Math.random()*6);
            int j = (int)(Math.random()*3);
            for(int y=j; y<j+3; y++)
            {
                board[i][y] = "*";
            }
        }
        else if(vertical == 1)
        {
            int i = (int)(Math.random()*3);
            int j = (int)(Math.random()*6);
            for(int x=i; x<i+3; x++)
            {
                board[x][j] = "*";
            }
        }
    }

    public Battleship(int vertical, int i, int j)
    {
        for(int x=0; x<6; x++)
        {
            for(int y=0; y<6; y++)
            {
                playerBoard[x][y] = "-";
            }
        }
        if(vertical == 0)
        {
            for(int y=j-1; y<j+2; y++)
            {
                playerBoard[i-1][y] = "*";
            }
        }
        else if(vertical == 1)
        {
            for(int x=i-1; x<i+2; x++)
            {
                playerBoard[x][j-1] = "*";
            }
        }
    }

    public boolean guess(int i, int j)
    {
        if(board[i-1][j-1].equals("*"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void printBoard()
    {
        for(int a=0; a<6; a++)
        {
            for(int b=0; b<6; b++)
            {
                System.out.print(board[a][b]+"  ");
            }
            System.out.println("");
        }
    }

    public void printPlayerBoard()
    {
        for(int a=0; a<6; a++)
        {
            for(int b=0; b<6; b++)
            {
                System.out.print(playerBoard[a][b]+"  ");
            }
            System.out.println("");
        }
    }

    public void printGuessBoard()
    {
        for(int a=0; a<6; a++)
        {
            for(int b=0; b<6; b++)
            {
                System.out.print(guessBoard[a][b]+"  ");
            }
            System.out.println("");
        }
    }
    
    public static int xConverter(String x) {
    	int i = 0; 
    	if(x.equals("A") || x.equals("a")) {
            i = 1;
        }
        else if(x.equals("B") || x.equals("b")) {
            i = 2;
        }
        else if(x.equals("C") || x.equals("c")) {
            i = 3;
        }
        else if(x.equals("D") || x.equals("d")) {
            i = 4;
        }
        else if(x.equals("E") || x.equals("e")) {
            i = 5;
        }
        else if(x.equals("F") || x.equals("f")) {
            i = 6;
        }
    	return i;
    }
    
    public static int yConverter(String y) {
    	int j = 0;
    	if(y.equals("1")) {
            j = 1;
        }
        else if(y.equals("2")) {
            j = 2;
        }
        else if(y.equals("3")) {
            j = 3;
        }
        else if(y.equals("4")) {
            j = 4;
        }
        else if(y.equals("5")) {
            j = 5;
        }
        else if(y.equals("6")) {
            j = 6;
        }
    	return j;
    }
    
    public static void step1(Battleship computer, int i, int j) {
    	if(computer.guess(i,j) == true) {
            computer.guessBoard[i-1][j-1] = "X";
            computer.playerOptions[i-1][j-1] = 1;
            System.out.println("Hit!");
            System.out.println("Here is your opponent's board:");
            computer.printGuessBoard();
            return;
        } else {
        	computer.guessBoard[i-1][j-1] = "O";
            computer.playerOptions[i-1][j-1] = 1;
            System.out.println("Miss!");
            System.out.println("Here is your opponent's board:");
            computer.printGuessBoard();
            return;
        }
    }

    public static void main(String args[])
    {
    	int i = 1;
        int j = 1;
        int game = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count5 = 0;
        int computerGuessX = 0;
        int computerGuessY = 0;
        int airandom = 0;
        int storX = 0;
        int storY = 0;
        Scanner in = new Scanner(System.in);
        Battleship computer = new Battleship();
        System.out.print("Would you like your ship to be vertical(1 for yes, 0 for no): ");
        int vertical = in.nextInt();
        System.out.print("Enter the upper left point of your ship: ");
        String location = in.next();
        i = xConverter(location.substring(0,1));
        j = yConverter(location.substring(1, location.length()));
        Battleship player = new Battleship(vertical, i, j);
        System.out.println("Here is your board:");
        player.printPlayerBoard();
        System.out.println("Here is your opponent's board:");
        computer.printGuessBoard();
        while(game == 0)
        {
            while(count2 == 0)
            {
                System.out.print("Enter your guess: ");
                location = in.next();
                i = xConverter(location.substring(0,1));
                j = yConverter(location.substring(1, location.length()));
                if(computer.playerOptions[i-1][j-1]==0)
                {
                    step1(computer, i, j);
                    count2++;
                }
                else
                {
                    System.out.println("You already tried that");
                }
            }
            count2 = 0;
            count1 = 0;
            for(int u=0; u<6; u++)
            {
                for(int v=0; v<6; v++)
                {
                    if(computer.guessBoard[u][v].equals("X"))
                    {
                        count1++;
                    }
                }
            }
            if(count1 == 3)
            {
                game = 1;
            }
            count1 = 0;
            //ai below
            for(int u=0; u<6; u++)
            {
                for(int v=0; v<6; v++)
                {
                    if(player.playerBoard[u][v].equals("X"))
                    {
                        count1++;
                    }
                }
            }
            if(count1 == 0)
            {
                while(count3 == 0)
                {
                    computerGuessX = (int)(Math.random()*6);
                    computerGuessY = (int)(Math.random()*6);
                    if(!player.playerBoard[computerGuessX][computerGuessY].equals("O") &&
                    !player.playerBoard[computerGuessX][computerGuessY].equals("X"))
                    {
                        if(computer.computeraihelp[computerGuessX][computerGuessY]==0)
                        {
                            if(player.playerBoard[computerGuessX][computerGuessY].equals("*"))
                            {
                                System.out.println("Computer hit!");
                                player.playerBoard[computerGuessX][computerGuessY] = "X";
                                System.out.println("Here is your board:");
                                player.printPlayerBoard();
                                count3++;
                                if(computerGuessY!=5)
                                {
                                    computer.computeraihelp[computerGuessX][computerGuessY+1]=1;
                                }
                                if(computerGuessY!=0)
                                {
                                    computer.computeraihelp[computerGuessX][computerGuessY-1]=1;
                                }
                                if(computerGuessX!=5)
                                {
                                    computer.computeraihelp[computerGuessX+1][computerGuessY]=1;
                                }
                                if(computerGuessX!=0)
                                {
                                    computer.computeraihelp[computerGuessX-1][computerGuessY]=1;
                                }
                            }
                            else
                            {
                                System.out.println("Computer miss!");
                                player.playerBoard[computerGuessX][computerGuessY] = "O";
                                System.out.println("Here is your board:");
                                player.printPlayerBoard();
                                count3++;
                                computer.computeraihelp[computerGuessX][computerGuessY]=1;
                                if(computerGuessY!=5)
                                {
                                    computer.computeraihelp[computerGuessX][computerGuessY+1]=1;
                                }
                                if(computerGuessY!=0)
                                {
                                    computer.computeraihelp[computerGuessX][computerGuessY-1]=1;
                                }
                                if(computerGuessX!=5)
                                {
                                    computer.computeraihelp[computerGuessX+1][computerGuessY]=1;
                                }
                                if(computerGuessX!=0)
                                {
                                    computer.computeraihelp[computerGuessX-1][computerGuessY]=1;
                                }
                            }
                        }
                    }
                }
                count3 = 0;
            }
            if(count1 == 1)
            {
                for(int u=0; u<6; u++)
                {
                    for(int v=0; v<6; v++)
                    {
                        if(player.playerBoard[u][v].equals("X"))
                        {
                            storX = u;
                            storY = v;
                        }
                    }
                }
                while(count3==0)
                {
                    airandom = (int)(Math.random()*4);
                    if(airandom == 0)
                    {
                        if(storY!=5)
                        {
                            if(!player.playerBoard[storX][storY+1].equals("X") &&
                            !player.playerBoard[storX][storY+1].equals("O"))
                            {
                                if(player.playerBoard[storX][storY+1].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX][storY+1] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                                else
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX][storY+1] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                    }
                    if(airandom == 1)
                    {
                        if(storY!=0)
                        {
                            if(!player.playerBoard[storX][storY-1].equals("X") &&
                            !player.playerBoard[storX][storY-1].equals("O"))
                            {
                                if(player.playerBoard[storX][storY-1].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX][storY-1] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                                else
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX][storY-1] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                    }
                    if(airandom == 2)
                    {
                        if(storX!=5)
                        {
                            if(!player.playerBoard[storX+1][storY].equals("X") &&
                            !player.playerBoard[storX+1][storY].equals("O"))
                            {
                                if(player.playerBoard[storX+1][storY].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX+1][storY] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                                else
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX+1][storY] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                    }
                    if(airandom == 3)
                    {
                        if(storX!=0)
                        {
                            if(!player.playerBoard[storX-1][storY].equals("X") &&
                            !player.playerBoard[storX-1][storY].equals("O"))
                            {
                                if(player.playerBoard[storX-1][storY].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX-1][storY] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                                else
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX-1][storY] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                    }
                }
                count3 = 0;
            }
            if(count1 == 2)
            {
                while(count5==0)
                {
                    for(int u=0; u<6; u++)
                    {
                        for(int v=0; v<6; v++)
                        {
                            if(player.playerBoard[u][v].equals("X"))
                            {
                                storX = u;
                                storY = v;
                                count5++;
                            }
                        }
                    }
                }
                count5++;
                while(count3==0)
                {
                    airandom = (int)(Math.random()*2);
                    if(vertical == 1)
                    {
                        if(airandom == 0)
                        {
                            if(storX!=4)
                            {
                                if(player.playerBoard[storX+1][storY].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX+1][storY] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                    game = 2;
                                }
                                else if(player.playerBoard[storX+1][storY].equals("-"))
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX+1][storY] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                        if(airandom == 1)
                        {
                            if(storX!=0 && storX!=1)
                            {
                                if(player.playerBoard[storX-2][storY].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX-2][storY] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                    game = 2;
                                }
                                else if(player.playerBoard[storX-2][storY].equals("-"))
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX-2][storY] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                    }
                    else
                    {
                        if(airandom == 0)
                        {
                            if(storY!=1 && storY!=0)
                            {
                                if(player.playerBoard[storX][storY-2].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX][storY-2] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                    game = 2;
                                }
                                else if(player.playerBoard[storX][storY-2].equals("-"))
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX][storY-2] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                        if(airandom == 1)
                        {
                            if(storY!=5)
                            {
                                if(player.playerBoard[storX][storY+1].equals("*"))
                                {
                                    System.out.println("Computer hit!");
                                    player.playerBoard[storX][storY+1] = "X";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                    game = 2;
                                }
                                else if(player.playerBoard[storX][storY+1].equals("-"))
                                {
                                    System.out.println("Computer miss!");
                                    player.playerBoard[storX][storY+1] = "O";
                                    System.out.println("Here is your board:");
                                    player.printPlayerBoard();
                                    count3++;
                                }
                            }
                        }
                    }
                }
            }
            count3 = 0;
        }
        count1 = 0;
        if(game == 1)
        {
            System.out.print("Congrats you are a winner");
        }
        if(game == 2)
        {
            System.out.print("You lost to a computer");
        }
    }
}