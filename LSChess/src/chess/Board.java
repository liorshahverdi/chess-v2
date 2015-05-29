package chess;
import java.util.ArrayList;

public class Board {
	private static String[][] board;
	
	public Board(){
		board = new String[][] { 
				{"b_c","b_h","b_b","b_q","b_k","b_b","b_h","b_c"},
				{"b_p","b_p","b_p","b_p","b_p","b_p","b_p","b_p"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"w_c"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"w_p","w_p","w_p","w_p","w_p","w_p","w_p","w_p"},
				{"w_c","w_h","w_b","w_q","w_k","w_b","w_h","w_c"} };
	}
	
	public String[][] getBoard() { return board; }
	public void setBoard(String[][] b) { board = b; }
	
	public static void printForP1()
	{
		int c = 8;
		System.out.println("\ta\tb\tc\td\te\tf\tg\th\t");
		System.out.println("---------------------------------------------------------------------------");
		for (String[] row : board)
		{
			System.out.print(c+"|\t");
		    for (String value : row)
		    {
		    	if (value == null) System.out.println("null\t");
		    	if (value.equals("-")) System.out.print("-\t");
		    	else System.out.print(value+"\t");
		    }
		    System.out.print("|"+c+"\n\n");
		    c--;
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\ta\tb\tc\td\te\tf\tg\th\t\n");
	}
	
	public static void printForP2()
	{
		System.out.println("\th\tg\tf\te\td\tc\tb\ta\t");
		System.out.println("---------------------------------------------------------------------------");
		for (int j=7; j>-1; j--){
			System.out.print((j+1)+"|\t");
			for (int i=7; i>-1; i--){
				if (board[j][i] == null) System.out.println("null\t");
		    	if (board[j][i].equals("-")) System.out.print("-\t");
		    	else System.out.print(board[j][i].toString()+"\t");
			}
			System.out.print("|"+(j+1)+"\n\n");
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\th\tg\tf\te\td\tc\tb\ta\t\n");
	}
	
	public enum ChessPiece {
		//possible pieces or empty tiles
		WHITE_CASTLE("w_c"), WHITE_KNIGHT("w_h"), WHITE_BISHOP("w_b"), WHITE_QUEEN("w_q"), WHITE_KING("w_k"), WHITE_PAWN("w_p"),
		BLACK_CASTLE("b_c"), BLACK_KNIGHT("b_h"), BLACK_BISHOP("b_b"), BLACK_QUEEN("b_q"), BLACK_KING("b_k"), BLACK_PAWN("b_p"), 
		EMPTY("-");
		
		private String str;
		private String getStr(){ return str; }
		
		private ChessPiece(String s){ this.str=s; }
		
		private static ChessPiece getEnum(String str){
			for (ChessPiece t: ChessPiece.values()){
				if (str.equals(t.getStr())) return t;
			}
			return null;
		}
		
		private static boolean isWhite(ChessPiece x){
			if (x == ChessPiece.WHITE_PAWN ||
					x == ChessPiece.WHITE_BISHOP ||
					x == ChessPiece.WHITE_CASTLE ||
					x == ChessPiece.WHITE_KNIGHT ||
					x == ChessPiece.WHITE_QUEEN ||
					x == ChessPiece.WHITE_KING) return true;
			else return false;
		}
		
		private static boolean isBlack(ChessPiece x){
			if (x == ChessPiece.BLACK_PAWN ||
					x == ChessPiece.BLACK_BISHOP ||
					x == ChessPiece.BLACK_CASTLE ||
					x == ChessPiece.BLACK_KNIGHT ||
					x == ChessPiece.BLACK_QUEEN ||
					x == ChessPiece.BLACK_KING) return true;
			else return false;
		}
		
		public static ArrayList<BoardCell> whitePawnPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-1 >= 0){
				//move up 1
				if (ChessPiece.getEnum(board[row-1][col]) == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1, col);
					p.add(move);
				}
				if (col-1 >= 0){
					if (ChessPiece.getEnum(board[row-1][col-1]) != ChessPiece.EMPTY && isBlack(ChessPiece.getEnum(board[row-1][col-1]))){
						BoardCell move = new BoardCell(row-1, col-1);
						move.setIsKill(true);//kill black piece in relative top-left cell
						p.add(move);
					}
				}
				if (col+1 <= 7){
					if (ChessPiece.getEnum(board[row-1][col+1]) != ChessPiece.EMPTY && isBlack(ChessPiece.getEnum(board[row-1][col+1]))){
						BoardCell move = new BoardCell(row-1, col+1);
						move.setIsKill(true);//kill black piece in relative top-right cell
						p.add(move);
					}
				}
				if (row-2 >= 0){
					//move up 2
					if (row==6 && ChessPiece.getEnum(board[row-2][col]) == ChessPiece.EMPTY &&
							ChessPiece.getEnum(board[row-1][col]) == ChessPiece.EMPTY){
						BoardCell move = new BoardCell(row-2, col);//move up 2 from initial starting cell
						p.add(move);
					}
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> whiteBishopPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_tl = false;
			while (!collision_tl){
				if (col - dt <= -1 || row - dt <= -1) break;
				if (ChessPiece.getEnum(board[row-dt][col-dt]) != ChessPiece.EMPTY){
					if (isBlack(ChessPiece.getEnum(board[row-dt][col-dt]))){
						BoardCell move = new BoardCell(row-dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tl = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt, col-dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_bl = false;
			while (!collision_bl){
				if (col-dt <= -1 || row+dt>=8) break;
				if ((ChessPiece.getEnum(board[row+dt][col-dt])) != ChessPiece.EMPTY){
					if (isBlack(ChessPiece.getEnum(board[row+dt][col-dt]))){
						BoardCell move = new BoardCell(row+dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_bl = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col-dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_tr = false;
			while (!collision_tr){
				if (col+dt >=8 || row-dt <= 1) break;
				if (ChessPiece.getEnum(board[row-dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isBlack(ChessPiece.getEnum(board[row-dt][col+dt]))){
						BoardCell move = new BoardCell(row-dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tr = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_br = false;
			while (!collision_br){
				if ((col+dt == 8) ||(row+dt == 8)) break;
				if (ChessPiece.getEnum(board[row+dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isBlack(ChessPiece.getEnum(board[row+dt][col+dt]))){
						BoardCell move = new BoardCell(row+dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_br = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 0;
			
			return p;
		}
		
		public static ArrayList<BoardCell> whiteCastlePossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			if (col >= 0 && col <= 7 && row >= 0 && row <= 7){
				boolean collision_left = false;
				while (!collision_left){
					if (col-dt <= -1) break;
					if (ChessPiece.getEnum(board[row][col-dt]) != ChessPiece.EMPTY){
						if (isBlack(ChessPiece.getEnum(board[row][col-dt]))){
							BoardCell move = new BoardCell(row, col-dt);
							move.setIsKill(true);
							p.add(move);
						}
						collision_left = true;
					}
					else
					{
						BoardCell move = new BoardCell(row, col-dt);
						p.add(move);
						dt++;
					}
				}
				dt = 1;
				boolean collision_right = false;
				while (!collision_right){
					if (col+dt >= 8) break;
					if (ChessPiece.getEnum(board[row][col+dt]) != ChessPiece.EMPTY){
						if (isBlack(ChessPiece.getEnum(board[row][col+dt]))){
							BoardCell move = new BoardCell(row, col+dt);
							move.setIsKill(true);
							p.add(move);
						}
						collision_right = true;
					}
					else
					{
						BoardCell move = new BoardCell(row, col+dt);
						p.add(move);
						dt++;
					}
				}
				dt = 1;
				if (row != 0){
					boolean collision_up = false;
					while (!collision_up){
						if (row-dt <= -1) break;
						if (ChessPiece.getEnum(board[row-dt][col]) != ChessPiece.EMPTY){
							if (isBlack(ChessPiece.getEnum(board[row-dt][col]))){
								BoardCell move = new BoardCell(row-dt, col);
								move.setIsKill(true);
								p.add(move);
							}
							collision_up = true;
						}
						else 
						{
							BoardCell move = new BoardCell(row-dt, col);
							p.add(move);
							dt++;
						}
					}
				}
				dt = 1;
				if (row != 7){
					boolean collision_down = false;
					while (!collision_down){
						if (row+dt >= 8) break;
						if (ChessPiece.getEnum(board[row+dt][col]) != ChessPiece.EMPTY){
							if (isBlack(ChessPiece.getEnum(board[row+dt][col]))){
								BoardCell move = new BoardCell(row+dt, col);
								move.setIsKill(true);
								p.add(move);
							}
							collision_down = true;
						}
						else 
						{
							BoardCell move = new BoardCell(row+dt, col);
							p.add(move);
							dt++;
						}
					}
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> whiteKnightPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-2 >= 0 && col-1 >= 0){
				ChessPiece pm = ChessPiece.getEnum(board[row-2][col-1]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-2, col-1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-2, col-1);
					p.add(move);
				}
			}
			
			if (row-1 >= 0 && col-2 >= 0){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col-2]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-1, col-2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-1, col-2);
					p.add(move);
				}
			}
			
			if (row+1 <= 7 && col-2 >= 0){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col-2]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+1, col-2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else{
					BoardCell move = new BoardCell(row+1, col-2);
					p.add(move);
				}
			}
			
			if (row+2 <= 7 && col-1 >= 0) {
				ChessPiece pm = ChessPiece.getEnum(board[row+2][col-1]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+2, col-1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row+2, col-1);
					p.add(move);
				}
			}
			
			if (row-2 >= 0 && col+1 <= 7)
			{
				ChessPiece pm = ChessPiece.getEnum(board[row-2][col+1]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-2, col+1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-2, col+1);
					p.add(move);
				}
			}
			
			if (row-1 >= 0 && col+2 <= 7){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col+2]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-1, col+2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-1,col+2);
					p.add(move);
				}
			}
			
			if (row+1 <= 7 && col+2 <= 7){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col+2]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+1,col+2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row+1,col+2);
					p.add(move);
				}
			}
			
			if (row+2 <= 7 && col+1 <= 7)
			{
				ChessPiece pm = ChessPiece.getEnum(board[row+2][col+1]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+2, col+1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row+2, col+1);
					p.add(move);
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> whiteQueenPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_up = false;
			while (!collision_up){
				if (row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row-dt][col]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-dt, col);
						move.setIsKill(true);
						p.add(move);
					}
					collision_up = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt, col);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_tr = false;
			while (!collision_tr){
				if (col + dt >= 8 || row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row-dt][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tr = true;
				}
				else {
					BoardCell move = new BoardCell(row-dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_right = false;
			while (!collision_right){
				if (col+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_right = true;
				}
				else {
					BoardCell move = new BoardCell(row, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_br = false;
			while (!collision_br){
				if (col+dt >= 8 || row+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row+dt][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_br = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_down = false;
			while (!collision_down){
				if (row+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row+dt][col]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+dt, col);
						move.setIsKill(true);
						p.add(move);
					}
					collision_down = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_bl = false;
			while (!collision_bl){
				if(row+dt >= 8 || col-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row+dt][col-dt]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row+dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_bl = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col-dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_left = false;
			while(!collision_left){
				if (col-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row][col-dt]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row,col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_left = true;
				}
				else{
					BoardCell move = new BoardCell(row, col-dt);
					p.add(move);
					dt++;
				}
			}			
			dt=1;
			boolean collision_tl = false;
			while(!collision_tl){
				if (col-dt <= -1 || row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row-dt][col-dt]);
				if (pm != ChessPiece.EMPTY){
					if (isBlack(pm)){
						BoardCell move = new BoardCell(row-dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tl = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt,col-dt);
					p.add(move);
					dt++;
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> whiteKingPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			if (row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1, col);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row-1, col);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col+1 != 8 && row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col+1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1, col+1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row-1,col+1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row][col+1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row,col+1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row, col+1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col+1 != 8 && row+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col+1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row+1, col+1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row+1, col+1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (row+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row+1, col);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row+1, col);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col-1 != -1 && row+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col-1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row+1, col-1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row+1, col-1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row][col-1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row, col-1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row, col-1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col-1 != -1 && row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col-1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1,col-1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isBlack(pm) && pm != ChessPiece.BLACK_KING){
					BoardCell move = new BoardCell(row-1,col-1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			return p;
		}
		
		public static ArrayList<BoardCell> blackPawnPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-1 >= 0){
				//move up 1
				if (ChessPiece.getEnum(board[row-1][col]) == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1, col);
					p.add(move);
				}
				if (col-1 >= 0){
					if (ChessPiece.getEnum(board[row-1][col-1]) != ChessPiece.EMPTY && isWhite(ChessPiece.getEnum(board[row-1][col-1]))){
						BoardCell move = new BoardCell(row-1, col-1);
						move.setIsKill(true);//kill black piece in relative top-left cell
						p.add(move);
					}
				}
				if (col+1 <= 7){
					if (ChessPiece.getEnum(board[row-1][col+1]) != ChessPiece.EMPTY && isWhite(ChessPiece.getEnum(board[row-1][col+1]))){
						BoardCell move = new BoardCell(row-1, col+1);
						move.setIsKill(true);//kill black piece in relative top-right cell
						p.add(move);
					}
				}
				if (row-2 >= 0){
					//move up 2
					if (row==6 && ChessPiece.getEnum(board[row-2][col]) == ChessPiece.EMPTY &&
							ChessPiece.getEnum(board[row-1][col]) == ChessPiece.EMPTY){
						BoardCell move = new BoardCell(row-2, col);//move up 2 from initial starting cell
						p.add(move);

					}
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> blackBishopPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_tl = false;
			while (!collision_tl){
				if (col - dt <= -1 || row - dt <= -1) break;
				if (ChessPiece.getEnum(board[row-dt][col-dt]) != ChessPiece.EMPTY){
					if (isWhite(ChessPiece.getEnum(board[row-dt][col-dt]))){
						BoardCell move = new BoardCell(row-dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tl = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt, col-dt);
					p.add(move);
				}
			}
			dt = 1;
			boolean collision_bl = false;
			while (!collision_bl){
				if (col-dt <= -1 || row+dt>=8) break;
				if ((ChessPiece.getEnum(board[row+dt][col-dt])) != ChessPiece.EMPTY){
					if (isWhite(ChessPiece.getEnum(board[row+dt][col-dt]))){
						BoardCell move = new BoardCell(row+dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_bl = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col-dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_tr = false;
			while (!collision_tr){
				if (col+dt >=8 || row-dt <= 1) break;
				if (ChessPiece.getEnum(board[row-dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isWhite(ChessPiece.getEnum(board[row-dt][col+dt]))){
						BoardCell move = new BoardCell(row-dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tr = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_br = false;
			while (!collision_br){
				if ((col+dt == 8) ||(row+dt == 8)) break;
				if (ChessPiece.getEnum(board[row+dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isWhite(ChessPiece.getEnum(board[row+dt][col+dt]))){
						BoardCell move = new BoardCell(row+dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_br = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> blackCastlePossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			if (col >= 0 && col <= 7  && row >= 0 && row <= 7){
				boolean collision_left = false;
				while (!collision_left){
					if (col-dt == -1) break;
					if (ChessPiece.getEnum(board[row][col-dt]) != ChessPiece.EMPTY){
						if (isWhite(ChessPiece.getEnum(board[row][col-dt]))){
							BoardCell move = new BoardCell(row, col-dt);
							move.setIsKill(true);
							p.add(move);
						}
						collision_left = true;
					}
					else
					{
						BoardCell move = new BoardCell(row, col-dt);
						p.add(move);
						dt++;
					}
				}
				dt = 1;
				boolean collision_right = false;
				while (!collision_right){
					if (col+dt >= 8) break;
					if (ChessPiece.getEnum(board[row][col+dt]) != ChessPiece.EMPTY){
						if (isWhite(ChessPiece.getEnum(board[row][col+dt]))){
							BoardCell move = new BoardCell(row, col+dt);
							move.setIsKill(true);
							p.add(move);
						}
						collision_right = true;
					}
					else
					{
						BoardCell move = new BoardCell(row, col+dt);
						p.add(move);
						dt++;
					}
				}
				dt = 1;
				if (row != 0){
					boolean collision_up = false;
					while (!collision_up){
						if (row-dt <= -1) break;
						if (ChessPiece.getEnum(board[row-dt][col]) != ChessPiece.EMPTY){
							if (isWhite(ChessPiece.getEnum(board[row-dt][col]))){
								BoardCell move = new BoardCell(row-dt, col);
								move.setIsKill(true);
								p.add(move);
							}
							collision_up = true;
						}
						else 
						{
							BoardCell move = new BoardCell(row-dt, col);
							p.add(move);
							dt++;
						}
					}
				}
				dt = 1;
				if (row != 7){
					boolean collision_down = false;
					while (!collision_down){
						if (row+dt >= 8) break;
						if (ChessPiece.getEnum(board[row+dt][col]) != ChessPiece.EMPTY){
							if (isWhite(ChessPiece.getEnum(board[row+dt][col]))){
								BoardCell move = new BoardCell(row+dt, col);
								move.setIsKill(true);
								p.add(move);
							}
							collision_down = true;
						}
						else 
						{
							BoardCell move = new BoardCell(row+dt, col);
							p.add(move);
							dt++;
						}
					}
				}
			}	
			
			return p;
		}
		
		public static ArrayList<BoardCell> blackKnightPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-2 >= 0 && col-1 >= 0){
				ChessPiece pm = ChessPiece.getEnum(board[row-2][col-1]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-2, col-1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-2, col-1);
					p.add(move);
				}
			}
			
			if (row-1 >= 0 && col-2 >= 0){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col-2]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-1, col-2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-1, col-2);
					p.add(move);
				}
			}
			
			if (row+1 <= 7 && col-2 >= 0){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col-2]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+1, col-2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else{
					BoardCell move = new BoardCell(row+1, col-2);
					p.add(move);
				}
			}
			
			if (row+2 <= 7 && col-1 >= 0) {
				ChessPiece pm = ChessPiece.getEnum(board[row+2][col-1]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+2, col-1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row+2, col-1);
					p.add(move);
				}
			}
			
			if (row-2 >= 0 && col+1 <= 7)
			{
				ChessPiece pm = ChessPiece.getEnum(board[row-2][col+1]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-2, col+1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-2, col+1);
					p.add(move);
				}
			}
			
			if (row-1 >= 0 && col+2 <= 7){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col+2]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-1, col+2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row-1,col+2);
					p.add(move);
				}
			}
			
			if (row+1 <= 7 && col+2 <= 7){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col+2]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+1,col+2);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row+1,col+2);
					p.add(move);
				}
			}
			
			if (row+2 <= 7 && col+1 <= 7)
			{
				ChessPiece pm = ChessPiece.getEnum(board[row+2][col+1]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+2, col+1);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else
				{
					BoardCell move = new BoardCell(row+2, col+1);
					p.add(move);
				}
			}
			
			return p;
		}

		public static ArrayList<BoardCell> blackQueenPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_up = false;
			while (!collision_up){
				if (row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row-dt][col]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-dt, col);
						move.setIsKill(true);
						p.add(move);
					}
					collision_up = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt, col);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_tr = false;
			while (!collision_tr){
				if (col + dt >= 8 || row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row-dt][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tr = true;
				}
				else {
					BoardCell move = new BoardCell(row-dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_right = false;
			while (!collision_right){
				if (col+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else {
					BoardCell move = new BoardCell(row, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_br = false;
			while (!collision_br){
				if (col+dt >= 8 || row+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row+dt][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+dt, col+dt);
						move.setIsKill(true);
						p.add(move);
					}
				}
				else{
					BoardCell move = new BoardCell(row+dt, col+dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_down = false;
			while (!collision_down){
				if (row+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row+dt][col]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+dt, col);
						move.setIsKill(true);
						p.add(move);
					}
					collision_down = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_bl = false;
			while (!collision_bl){
				if(row+dt >= 8) break;
				ChessPiece pm = ChessPiece.getEnum(board[row+dt][col-dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row+dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_bl = true;
				}
				else{
					BoardCell move = new BoardCell(row+dt, col-dt);
					p.add(move);
					dt++;
				}
			}
			dt = 1;
			boolean collision_left = false;
			while(!collision_left){
				if (col-dt <= 1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row][col-dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row,col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_left = true;
				}
				else{
					BoardCell move = new BoardCell(row, col-dt);
					p.add(move);
					dt++;
				}
			}
			dt=1;
			boolean collision_tl = false;
			while(!collision_tl){
				if (col-dt <= -1 || row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(board[row-dt][col-dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
						BoardCell move = new BoardCell(row-dt, col-dt);
						move.setIsKill(true);
						p.add(move);
					}
					collision_tl = true;
				}
				else{
					BoardCell move = new BoardCell(row-dt,col-dt);
					p.add(move);
					dt++;
				}
			}
			
			return p;
		}
		
		public static ArrayList<BoardCell> blackKingPossibleMoves(int row, int col){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1, col);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row-1, col);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col+1 != 8 && row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col+1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1, col+1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row-1,col+1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row][col+1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row,col+1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row, col+1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col+1 != 8 && row+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col+1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row+1, col+1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row+1, col+1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (row+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row+1, col);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row+1, col);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col-1 != -1 && row+1 != 8){
				ChessPiece pm = ChessPiece.getEnum(board[row+1][col-1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row+1, col-1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row+1, col-1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row][col-1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row, col-1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row, col-1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			if (col-1 != -1 && row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(board[row-1][col-1]);
				if (pm == ChessPiece.EMPTY){
					BoardCell move = new BoardCell(row-1,col-1);
					p.add(move);
				}
				else if (pm != ChessPiece.EMPTY && isWhite(pm) && pm != ChessPiece.WHITE_KING){
					BoardCell move = new BoardCell(row-1,col-1);
					move.setIsKill(true);
					p.add(move);
				}
			}
			
			return p;
		}
		
		public static ArrayList<Move> possibleMoves(){
			return new ArrayList<Move>();
		}
		
		public static ArrayList<BoardCell> possibleMoves(BoardCell x){
			ArrayList<BoardCell> temp = new ArrayList<BoardCell>();
			int row = x.getRow();
			int col = x.getCol();
			String piece_str = board[row][col];
			ChessPiece piece = getEnum(piece_str);
			if (ChessGame.getThisTurnsPlayer() == 1){
				//white's moves
				if (piece == ChessPiece.WHITE_PAWN) temp.addAll(whitePawnPossibleMoves(row,col));
				if (piece == ChessPiece.WHITE_BISHOP) temp.addAll(whiteBishopPossibleMoves(row,col));
				if (piece == ChessPiece.WHITE_CASTLE) temp.addAll(whiteCastlePossibleMoves(row,col));
				if (piece == ChessPiece.WHITE_KNIGHT) temp.addAll(whiteKnightPossibleMoves(row,col));
				if (piece == ChessPiece.WHITE_QUEEN) temp.addAll(whiteQueenPossibleMoves(row,col));
				if (piece == ChessPiece.WHITE_KING) temp.addAll(whiteKingPossibleMoves(row,col));
			}
			else {
				//black's moves
				if (piece == ChessPiece.BLACK_PAWN) temp.addAll(blackPawnPossibleMoves(row,col));
				if (piece == ChessPiece.BLACK_BISHOP) temp.addAll(blackBishopPossibleMoves(row,col));
				if (piece == ChessPiece.BLACK_CASTLE) temp.addAll(blackCastlePossibleMoves(row,col));
				if (piece == ChessPiece.BLACK_KNIGHT) temp.addAll(blackKnightPossibleMoves(row,col));	
				if (piece == ChessPiece.BLACK_QUEEN) temp.addAll(blackQueenPossibleMoves(row,col));
				if (piece == ChessPiece.BLACK_KING) temp.addAll(blackKingPossibleMoves(row,col));
			}
			return temp;
		}
	}
}
