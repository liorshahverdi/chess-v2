package chess;
import java.util.ArrayList;

public class Board {
	private static String[][] board;
	
	public Board(){
		board = new String[][] { 
				
				/*{"-",  "b_k"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-","-"  ,"-"  ,"-"},
				{"-",  "w_p"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"w_p"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"w_p"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-", "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"}*/
				
				{"b_c","b_h","b_b","b_q","b_k","b_b","b_h","b_c"},
				{"b_p","b_p","b_p","b_p","b_p","b_p","b_p","b_p"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"-",  "-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"  ,"-"},
				{"w_p","w_p","w_p","w_p","w_p","w_p","w_p","w_p"},
				{"w_c","w_h","w_b","w_q","w_k","w_b","w_h","w_c"} };
	}
	
	public String[][] getBoard() { return board; }
	public void setBoard(String[][] b) { board = b; }	
	public void setLocation(BoardCell x, String v){ board[x.getRow()][x.getCol()] = v; }
	
	public static void flipMat(String[][] x)
	{
		String[][] newMat = new String[8][8];
		int oj = 0;
		int oi = 0;
		for (int j=7; j>-1; j--){
			for (int i=7; i>-1; i--){
				if (oi == 8) oi = 0;
				newMat[oj][oi] = board[j][i];
				oi++;
			}
			if (oj == 8) oj = 0;
			else oj++;
		}
		board = newMat;
	}
	
	public enum ChessPiece {
		//possible pieces or empty tiles
		WHITE_CASTLE("w_c"), WHITE_KNIGHT("w_h"), WHITE_BISHOP("w_b"), WHITE_QUEEN("w_q"), WHITE_KING("w_k"), WHITE_PAWN("w_p"),
		BLACK_CASTLE("b_c"), BLACK_KNIGHT("b_h"), BLACK_BISHOP("b_b"), BLACK_QUEEN("b_q"), BLACK_KING("b_k"), BLACK_PAWN("b_p"), 
		EMPTY("-");
		
		private String str;
		public String getStr(){ return str; }
		
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
		
		public static ArrayList<BoardCell> whitePawnPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			int turn = ChessGame.getThisTurnsPlayer();
			if (turn == 1){
				if (row-1 >= 0){
					ChessPiece op = ChessPiece.getEnum(b[row-1][col]);
					if (op == ChessPiece.EMPTY){
						BoardCell up = new BoardCell(row-1, col);
						p.add(up);
						if (row-2 >= 0){
							ChessPiece up2 = ChessPiece.getEnum(b[row-2][col]);
							if (up2 == ChessPiece.EMPTY && row == 6){
								BoardCell upo = new BoardCell(row-2, col);
								p.add(upo);
							}
						}
					}
					if (col-1 >= 0){
						ChessPiece opa = ChessPiece.getEnum(b[row-1][col-1]);
						if (opa != ChessPiece.EMPTY && isBlack(opa)){
							BoardCell up_left = new BoardCell(row-1,col-1);
							up_left.setIsKill(true);
							p.add(up_left);
						}
					}
					if (col+1 <= 7){
						ChessPiece opb = ChessPiece.getEnum(b[row-1][col+1]);
						if (opb != ChessPiece.EMPTY && isBlack(opb)){
							BoardCell up_right = new BoardCell(row-1,col+1);
							up_right.setIsKill(true);
							p.add(up_right);
						}
					}
				}
			}
			else {
				if (row+1 <= 7){
					ChessPiece op = ChessPiece.getEnum(b[row+1][col]);
					if (op == ChessPiece.EMPTY){
						BoardCell down = new BoardCell(row+1, col);
						p.add(down);
						if (row+2 <= 7){
							ChessPiece down2 = ChessPiece.getEnum(b[row+2][col]);
							if (down2 == ChessPiece.EMPTY && row == 1){
								BoardCell upo = new BoardCell(row+2, col);
								p.add(upo);
							}
						}
					}
					if (col-1 >= 0){
						ChessPiece opa = ChessPiece.getEnum(b[row+1][col-1]);
						if (opa != ChessPiece.EMPTY && isBlack(opa)){
							BoardCell up_left = new BoardCell(row+1,col-1);
							up_left.setIsKill(true);
							p.add(up_left);
						}
					}
					if (col+1 <= 7){
						ChessPiece opb = ChessPiece.getEnum(b[row+1][col+1]);
						if (opb != ChessPiece.EMPTY && isBlack(opb)){
							BoardCell up_right = new BoardCell(row+1,col+1);
							up_right.setIsKill(true);
							p.add(up_right);
						}
					}
				}
			}
			return p;
		}
		
		public static ArrayList<BoardCell> whiteBishopPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_tl = false;
			while (!collision_tl){
				if (col - dt <= -1 || row - dt <= -1) break;
				if (ChessPiece.getEnum(b[row-dt][col-dt]) != ChessPiece.EMPTY){
					if (isBlack(ChessPiece.getEnum(b[row-dt][col-dt]))){
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
				if ((ChessPiece.getEnum(b[row+dt][col-dt])) != ChessPiece.EMPTY){
					if (isBlack(ChessPiece.getEnum(b[row+dt][col-dt]))){
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
				if (ChessPiece.getEnum(b[row-dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isBlack(ChessPiece.getEnum(b[row-dt][col+dt]))){
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
				if (ChessPiece.getEnum(b[row+dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isBlack(ChessPiece.getEnum(b[row+dt][col+dt]))){
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
		
		public static ArrayList<BoardCell> whiteCastlePossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			if (col >= 0 && col <= 7 && row >= 0 && row <= 7){
				boolean collision_left = false;
				while (!collision_left){
					if (col-dt <= -1) break;
					if (ChessPiece.getEnum(b[row][col-dt]) != ChessPiece.EMPTY){
						if (isBlack(ChessPiece.getEnum(b[row][col-dt]))){
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
					if (ChessPiece.getEnum(b[row][col+dt]) != ChessPiece.EMPTY){
						if (isBlack(ChessPiece.getEnum(b[row][col+dt]))){
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
						if (ChessPiece.getEnum(b[row-dt][col]) != ChessPiece.EMPTY){
							if (isBlack(ChessPiece.getEnum(b[row-dt][col]))){
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
						if (ChessPiece.getEnum(b[row+dt][col]) != ChessPiece.EMPTY){
							if (isBlack(ChessPiece.getEnum(b[row+dt][col]))){
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
		
		public static ArrayList<BoardCell> whiteKnightPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-2 >= 0 && col-1 >= 0){
				ChessPiece pm = ChessPiece.getEnum(b[row-2][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col-2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col-2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+2][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-2][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col+2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col+2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+2][col+1]);
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
		
		public static ArrayList<BoardCell> whiteQueenPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_up = false;
			while (!collision_up){
				if (row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(b[row-dt][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-dt][col+dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col+dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+dt][col+dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+dt][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+dt][col-dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col-dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-dt][col-dt]);
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
		
		public static ArrayList<BoardCell> whiteKingPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			if (row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col-1]);
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
		
		public static ArrayList<BoardCell> blackPawnPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			int turn = ChessGame.getThisTurnsPlayer();
			if (turn == 2){
				if (row-1 >= 0){
					ChessPiece op = ChessPiece.getEnum(b[row-1][col]);
					if (op == ChessPiece.EMPTY){
						BoardCell up = new BoardCell(row-1, col);
						p.add(up);
						if (row-2 >= 0){
							ChessPiece up2 = ChessPiece.getEnum(b[row-2][col]);
							if (up2 == ChessPiece.EMPTY && row == 6){
								BoardCell upo = new BoardCell(row-2, col);
								p.add(upo);
							}
						}
					}
					if (col-1 >= 0){
						ChessPiece opa = ChessPiece.getEnum(b[row-1][col-1]);
						if (opa != ChessPiece.EMPTY && isWhite(opa)){
							BoardCell up_left = new BoardCell(row-1,col-1);
							up_left.setIsKill(true);
							p.add(up_left);
						}
					}
					if (col+1 <= 7){
						ChessPiece opb = ChessPiece.getEnum(b[row-1][col+1]);
						if (opb != ChessPiece.EMPTY && isWhite(opb)){
							BoardCell up_right = new BoardCell(row-1,col+1);
							up_right.setIsKill(true);
							p.add(up_right);
						}
					}
				}
			}
			else {
				if (row+1 <= 7){
					ChessPiece op = ChessPiece.getEnum(b[row+1][col]);
					if (op == ChessPiece.EMPTY){
						BoardCell down = new BoardCell(row+1, col);
						p.add(down);
						if (row+2 <= 7){
							ChessPiece down2 = ChessPiece.getEnum(b[row+2][col]);
							if (down2 == ChessPiece.EMPTY && row == 1){
								BoardCell upo = new BoardCell(row+2, col);
								p.add(upo);
							}
						}
					}
					if (col-1 >= 0){
						ChessPiece opa = ChessPiece.getEnum(b[row+1][col-1]);
						if (opa != ChessPiece.EMPTY && isWhite(opa)){
							BoardCell up_left = new BoardCell(row+1,col-1);
							up_left.setIsKill(true);
							p.add(up_left);
						}
					}
					if (col+1 <= 7){
						ChessPiece opb = ChessPiece.getEnum(b[row+1][col+1]);
						if (opb != ChessPiece.EMPTY && isWhite(opb)){
							BoardCell up_right = new BoardCell(row+1,col+1);
							up_right.setIsKill(true);
							p.add(up_right);
						}
					}
				}
			}
			return p;
		}
		
		public static ArrayList<BoardCell> blackBishopPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_tl = false;
			while (!collision_tl){
				if (col - dt <= -1 || row - dt <= -1) break;
				if (ChessPiece.getEnum(b[row-dt][col-dt]) != ChessPiece.EMPTY){
					if (isWhite(ChessPiece.getEnum(b[row-dt][col-dt]))){
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
				if ((ChessPiece.getEnum(b[row+dt][col-dt])) != ChessPiece.EMPTY){
					if (isWhite(ChessPiece.getEnum(b[row+dt][col-dt]))){
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
				if (ChessPiece.getEnum(b[row-dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isWhite(ChessPiece.getEnum(b[row-dt][col+dt]))){
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
				if (ChessPiece.getEnum(b[row+dt][col+dt]) != ChessPiece.EMPTY){
					//check for kill option
					if (isWhite(ChessPiece.getEnum(b[row+dt][col+dt]))){
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
		
		public static ArrayList<BoardCell> blackCastlePossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			if (col >= 0 && col <= 7  && row >= 0 && row <= 7){
				boolean collision_left = false;
				while (!collision_left){
					if (col-dt == -1) break;
					if (ChessPiece.getEnum(b[row][col-dt]) != ChessPiece.EMPTY){
						if (isWhite(ChessPiece.getEnum(b[row][col-dt]))){
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
					if (ChessPiece.getEnum(b[row][col+dt]) != ChessPiece.EMPTY){
						if (isWhite(ChessPiece.getEnum(b[row][col+dt]))){
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
						if (ChessPiece.getEnum(b[row-dt][col]) != ChessPiece.EMPTY){
							if (isWhite(ChessPiece.getEnum(b[row-dt][col]))){
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
						if (ChessPiece.getEnum(b[row+dt][col]) != ChessPiece.EMPTY){
							if (isWhite(ChessPiece.getEnum(b[row+dt][col]))){
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
		
		public static ArrayList<BoardCell> blackKnightPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			if (row-2 >= 0 && col-1 >= 0){
				ChessPiece pm = ChessPiece.getEnum(b[row-2][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col-2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col-2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+2][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-2][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col+2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col+2]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+2][col+1]);
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

		public static ArrayList<BoardCell> blackQueenPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			
			int dt = 1;
			boolean collision_up = false;
			while (!collision_up){
				if (row-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(b[row-dt][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-dt][col+dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
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
				ChessPiece pm = ChessPiece.getEnum(b[row+dt][col+dt]);
				if (pm != ChessPiece.EMPTY){
					if (isWhite(pm)){
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
				ChessPiece pm = ChessPiece.getEnum(b[row+dt][col]);
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
				if(row+dt >= 8 || col-dt <=-1) break;
				ChessPiece pm = ChessPiece.getEnum(b[row+dt][col-dt]);
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
				if (col-dt <= -1) break;
				ChessPiece pm = ChessPiece.getEnum(b[row][col-dt]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-dt][col-dt]);
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
		
		public static ArrayList<BoardCell> blackKingPossibleMoves(int row, int col, String[][] b){
			ArrayList<BoardCell> p = new ArrayList<BoardCell>();
			if (row-1 != -1){
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col+1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row+1][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row][col-1]);
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
				ChessPiece pm = ChessPiece.getEnum(b[row-1][col-1]);
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
		
		//computes full moves for each piece on the board
		public static ArrayList<Move> possibleMoves(String[][] b){
			ArrayList<Move> p = new ArrayList<Move>();
			
			for (int i=0; i<8; i++){
				for (int k=0; k<8; k++){
					ChessPiece pm = ChessPiece.getEnum(b[i][k]);
					if (pm != ChessPiece.EMPTY) 
					{
						BoardCell f = new BoardCell(i,k);
						//if (ChessGame.getThisTurnsPlayer() == 1) {
							if (pm == ChessPiece.WHITE_PAWN){
								ArrayList<BoardCell> possiblet = whitePawnPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new WhitePawn(), tpp));
									}
									else p.add(new Move(f,n, new WhitePawn()));
								}
							}
							if (pm == ChessPiece.WHITE_BISHOP){
								ArrayList<BoardCell> possiblet = whiteBishopPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new WhiteBishop(), tpp));
									}
									else p.add(new Move(f,n, new WhiteBishop()));
								}
							}
							if (pm == ChessPiece.WHITE_CASTLE){
								ArrayList<BoardCell> possiblet = whiteCastlePossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new WhiteCastle(), tpp));
									}
									else p.add(new Move(f,n, new WhiteCastle()));
								}
							}
							if (pm == ChessPiece.WHITE_KNIGHT){
								ArrayList<BoardCell> possiblet = whiteKnightPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new WhiteHorse(), tpp));
									}
									else p.add(new Move(f,n, new WhiteHorse()));
								}
							}
							if (pm == ChessPiece.WHITE_QUEEN){
								ArrayList<BoardCell> possiblet = whiteQueenPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new WhiteQueen(), tpp));
									}
									else p.add(new Move(f,n, new WhiteQueen()));
								}
							}
							if (pm == ChessPiece.WHITE_KING){
								ArrayList<BoardCell> possiblet = whiteKingPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new WhiteKing(), tpp));
									}
									else p.add(new Move(f,n, new WhiteKing()));
								}
							}
							if (pm == ChessPiece.BLACK_PAWN){
								ArrayList<BoardCell> possiblet = blackPawnPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new BlackPawn(), tpp));
									}
									else p.add(new Move(f,n, new BlackPawn()));
								}
							}
							if (pm == ChessPiece.BLACK_BISHOP){
								ArrayList<BoardCell> possiblet = blackBishopPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new BlackBishop(), tpp));
									}
									else p.add(new Move(f,n, new BlackBishop()));
								}
							}
							if (pm == ChessPiece.BLACK_CASTLE){
								ArrayList<BoardCell> possiblet = blackCastlePossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new BlackCastle(), tpp));
									}
									else p.add(new Move(f,n, new BlackCastle()));
								}
							}
							if (pm == ChessPiece.BLACK_KNIGHT){
								ArrayList<BoardCell> possiblet = blackKnightPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new BlackHorse(), tpp));
									}
									else p.add(new Move(f,n, new BlackHorse()));
								}
							}
							if (pm == ChessPiece.BLACK_QUEEN){
								ArrayList<BoardCell> possiblet = blackQueenPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new BlackQueen(), tpp));
									}
									else p.add(new Move(f,n, new BlackQueen()));
								}
							}
							if (pm == ChessPiece.BLACK_KING){
								ArrayList<BoardCell> possiblet = blackKingPossibleMoves(i,k,b);
								for (BoardCell n : possiblet){
									if (n.getIsKill()){
										ChessPiece tp = ChessPiece.getEnum(b[n.getRow()][n.getCol()]);
										Piece tpp = equivalentPiece(tp);
										p.add(new Move(f,n,new BlackKing(), tpp));
									}
									else p.add(new Move(f,n, new BlackKing()));
								}
							}
						//}
					}
				}
			}
			
			
			return p;
		}
		
		public static Piece equivalentPiece(ChessPiece p){
			switch (p){
				case WHITE_PAWN: return new WhitePawn();
				case WHITE_BISHOP: return new WhiteBishop();
				case WHITE_CASTLE: return new WhiteCastle();
				case WHITE_QUEEN: return new WhiteQueen();
				case WHITE_KING: return new WhiteKing();
				case BLACK_PAWN: return new BlackPawn();
				case BLACK_BISHOP: return new BlackBishop();
				case BLACK_CASTLE: return new BlackCastle();
				case BLACK_QUEEN: return new BlackQueen();
				case BLACK_KING: return new BlackKing();
				default: return null;
			}
		}
		
		public static String[][] deepCopy(String[][] x) {
			String[][] temp = new String[8][8];
			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++) {
					temp[i][j] = board[i][j];
				}
			}
			return temp;
		}
		
		public static ArrayList<BoardCell> boardCellEdit(ArrayList<BoardCell> x, BoardCell g){
			ArrayList<BoardCell> temp = new ArrayList<BoardCell>();
			
			for (BoardCell bc : x){
				String[][] myBoardDeepCopy = deepCopy(board);
				myBoardDeepCopy[bc.getRow()][bc.getCol()] = myBoardDeepCopy[g.getRow()][g.getCol()];
				myBoardDeepCopy[g.getRow()][g.getCol()] = "-";
				if (!willLeaveUsInCheck(myBoardDeepCopy)) {
					temp.add(bc);
				}
			}
			
			return temp;
		}
		
		public static ArrayList<Move> edit(ArrayList<Move> g){
			ArrayList<Move> temp = new ArrayList<Move>();
			
			for (Move m : g){
				BoardCell src = m.getFromCell(); BoardCell dest = m.getToCell();
				String[][] myBoardDeepCopy = deepCopy(board);
				myBoardDeepCopy[dest.getRow()][dest.getCol()] = myBoardDeepCopy[src.getRow()][src.getCol()];
				myBoardDeepCopy[src.getRow()][src.getCol()] = "-";
				if (!willLeaveUsInCheck(myBoardDeepCopy)) temp.add(m);
			}
			
			return temp;
		}
		
		public static boolean willLeaveUsInCheck(String[][] b){
			ArrayList<Move> moves = ChessPiece.possibleMoves(b);
			for (Move c : moves){
				Piece destPiece = c.getTargetedPiece();
				if (ChessGame.getThisTurnsPlayer() == 1 && destPiece instanceof WhiteKing) return true;
				if (ChessGame.getThisTurnsPlayer() == 2 && destPiece instanceof BlackKing) return true;
			}
			return false;
		}
		
		public static ArrayList<BoardCell> smartPossibleMoves(BoardCell x){
			ArrayList<BoardCell> temp = new ArrayList<BoardCell>();
			int row = x.getRow();
			int col = x.getCol();
			String piece_str = board[row][col];
			ChessPiece piece = getEnum(piece_str);
			if (ChessGame.getThisTurnsPlayer() == 1){//white's moves
				if (piece == ChessPiece.WHITE_PAWN) temp.addAll(whitePawnPossibleMoves(row,col, board));
				if (piece == ChessPiece.WHITE_BISHOP) temp.addAll(whiteBishopPossibleMoves(row,col,board));
				if (piece == ChessPiece.WHITE_CASTLE) temp.addAll(whiteCastlePossibleMoves(row,col,board));
				if (piece == ChessPiece.WHITE_KNIGHT) temp.addAll(whiteKnightPossibleMoves(row,col,board));
				if (piece == ChessPiece.WHITE_QUEEN) temp.addAll(whiteQueenPossibleMoves(row,col,board));
				if (piece == ChessPiece.WHITE_KING) temp.addAll(whiteKingPossibleMoves(row,col,board));
			}
			else {//black's moves
				if (piece == ChessPiece.BLACK_PAWN) temp.addAll(blackPawnPossibleMoves(row,col,board));
				if (piece == ChessPiece.BLACK_BISHOP) temp.addAll(blackBishopPossibleMoves(row,col,board));
				if (piece == ChessPiece.BLACK_CASTLE) temp.addAll(blackCastlePossibleMoves(row,col,board));
				if (piece == ChessPiece.BLACK_KNIGHT) temp.addAll(blackKnightPossibleMoves(row,col,board));	
				if (piece == ChessPiece.BLACK_QUEEN) temp.addAll(blackQueenPossibleMoves(row,col,board));
				if (piece == ChessPiece.BLACK_KING) temp.addAll(blackKingPossibleMoves(row,col,board));
			}
			
			temp = boardCellEdit(temp, x);
			
			return temp;
		}
	}


public static boolean isCheckMove(Move s){
	ChessPiece pm = ChessPiece.getEnum(board[s.getToCell().getRow()][s.getToCell().getCol()]);
	int turn = ChessGame.getThisTurnsPlayer();
	if (turn == 1 && pm == ChessPiece.BLACK_KING) return true;
	if (turn == 2 && pm == ChessPiece.WHITE_KING) return true;
	return false;
}

public static void printForP1(String[][] b)
{
	int c = 8;
	System.out.println("\ta\tb\tc\td\te\tf\tg\th\t");
	System.out.println("---------------------------------------------------------------------------");
	for (String[] row : b)
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

public static void printForP2(String[][] b)
{
	System.out.println("\th\tg\tf\te\td\tc\tb\ta\t");
	System.out.println("---------------------------------------------------------------------------");
	for (int j=7; j>-1; j--){
		System.out.print((j+1)+"|\t");
		for (int i=7; i>-1; i--){
			if (b[j][i] == null) System.out.println("null\t");
	    	if (b[j][i].equals("-")) System.out.print("-\t");
	    	else System.out.print(board[j][i].toString()+"\t");
		}
		System.out.print("|"+(j+1)+"\n\n");
	}
	System.out.println("---------------------------------------------------------------------------");
	System.out.println("\th\tg\tf\te\td\tc\tb\ta\t\n");
}

}