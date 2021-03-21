import java.util.*;
import java.io.*;
class clear
{
	void clsc()
	{
		try
		{	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception E)
		{
			System.out.println(E);
		}
	}
}
class Game extends GameLogic
{
	static Scanner scan= new Scanner(System.in);
	

	int player(char[][] board)
	{
		System.out.println("player enter the position 1-9");
		int Pos=scan.nextInt();
		String playerPos=String.valueOf(Pos);
		if (Pos>0&&Pos<10)
		{
			int check = save(playerPos,"player");
			if(check==0)
			{
				System.out.println("position already entered"); 
				return 1;
			}
			else
			{
			   insert(board,"player",Pos);
			}
		}
		else
		{
			System.out.println("invalid index");
		}
		return 0;
	}
	void cpuPlayer (char board[][])
	{
		
		Random rand = new Random();
		int Pos= rand.nextInt(9)+1;
		
		String cpuPos=String.valueOf(Pos);
		int check =save(cpuPos,"cpu");
		if(check==0)
		{
			while(check<1)
			{
			  Pos= rand.nextInt(9)+1;
			  cpuPos=String.valueOf(Pos);
			  check =save(cpuPos,"cpu");
			}
	        System.out.println("computer is tacking position"+Pos);
			try
			{
			Thread.sleep(4000);
			}
			catch(Exception e)
			{
			}
		    insert(board,"cpu",Pos);
		}
		else
		{
		    System.out.println("computer is tacking position"+Pos);
			try
			{
			Thread.sleep(4000);
			}
			catch(Exception e)
			{
			}
		    insert(board,"cpu",Pos);
		}
	}
	void disp(char [][] board)
	{
		for (int i=0;i<board.length ;i++ )
		{
			for (int j=0;j<board[i].length ;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	void insert(char [][] board,String player,int pos)
	{
		char op;
		if(player=="player")
		{
			op='X';
		}
		else
		{
			op='O';
		}
		switch(pos)
		{
			case 1:
					board[0][0]=op;
				    break;
			case 2:
					board[0][2]=op;
				    break;
			case 3:
					board[0][4]=op;
				    break;
			case 4:
					board[2][0]=op;
				    break;
			case 5:
					board[2][2]=op;
				    break;
			case 6:
					board[2][4]=op;
				    break;
			case 7:
					board[4][0]=op;
				    break;
			case 8:
					board[4][2]=op;
				    break;
			case 9:
					board[4][4]=op;
				    break;
		}
	}
}
class GameLogic
{
	static String pls="";
	static String cls="";
	static String dls="";
	String player1;
	clear c = new clear();
	int save(String playerPos,String player)
	{
		if (player=="player")
		{
			player1= playerPos;
			if((dls.contains(player1)==false)&&(pls.contains(player1)==false)&&(cls.contains(player1)==false))
			{
				dls=dls.concat(player1);
				pls=pls.concat(player1);
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			player1= playerPos;
			if((dls.contains(player1)==false)&&(pls.contains(player1)==false)&&(cls.contains(player1)==false))
			{
				dls=dls.concat(player1);
				cls=cls.concat(player1);
				return 1;
			}
			else
			{
				return 0;
			}
			
		}
		
	 }

	void checkDraw()
	{
		if (dls.length()==9)
		{
			System.out.println("game is draw!!");
			System.exit(0);
		}
		c.clsc();
	}
	void checkWin(String s)
	{
	 String wincomb[]={"123","456","789","147","258","369","159","357"};
	 if(s=="player")
	 {
		 int count=0;
		 for(int i=0;i<wincomb.length;i++)
		 {
			 for(int j=0;j<wincomb[i].length();j++)
			 {
				 String test=String.valueOf(wincomb[i].charAt(j));

				if(pls.contains(test))
				{
					count++;
				}
				if(count==3)
				 {
					 System.out.println("player won the game!!");
					 System.exit(0);
				 }
			 }
			 count=0;
		 }
		 
	 }

	 else
	 {
		 int count=0;
		 for(int i=0;i<wincomb.length;i++)
		 {
			 for(int j=0;j<wincomb[i].length();j++)
			 {
				 String test=String.valueOf(wincomb[i].charAt(j));

				if(cls.contains(test))
				{
					count++;
				}
				if(count==3)
			    {
				 System.out.println("cpu won the game!!");
				 System.exit(0);
				}

			 }
			 count=0;
			
		  }
		 
	   }
	}
}
class TicTacToe 
{
	public static void main(String[] args) 
	{
		char board[][]= {{' ','|',' ','|',' '},
						 {'-','+','-','+','-'},
						 {' ','|',' ','|',' '},
						 {'-','+','-','+','-'},
						 {' ','|',' ','|',' '}};
		Game g = new Game();
		int temp;
		 while(true)
		 {	 
			 g.disp(board);
			 temp=g.player(board);
			 try
			{
			Thread.sleep(4000);
			}
			catch(Exception e)
			{
			}
			 g.checkWin("player");
			 g.checkDraw();
			 g.disp(board);
			 g.cpuPlayer(board);
			 g.checkWin("cpu");
			 g.checkDraw();	 
		 }	
	}
}
