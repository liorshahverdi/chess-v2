package chess;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class ChessGame {
	
	private static Board model;//model
	private static GUI view;//view
	
	public static int ct;
	public static boolean check;
	
	public Board getBoard() { return model; }
	
	public ChessGame(){
		ct = 1;
		check = false;
		model = new Board();
		view = new GUI();
	}
	
	public static int getThisTurnsPlayer(){
		if (ct%2 != 0) return 1;
		else return 2;
	}
	
	public static boolean isWhitePiece(Piece t){
		if (t instanceof WhitePawn ||
			t instanceof WhiteHorse ||
			t instanceof WhiteBishop ||
			t instanceof WhiteCastle ||
			t instanceof WhiteQueen ||
			t instanceof WhiteKing) return true;
		else return false;
	}
	
	public static boolean isBlackPiece(Piece t){
		if (t instanceof BlackPawn ||
			t instanceof BlackHorse ||
			t instanceof BlackBishop ||
			t instanceof BlackCastle ||
			t instanceof BlackQueen ||
			t instanceof BlackKing) return true;
		else return false;
	}
	
	public static boolean isCurrentPlayersPiece(Piece t){
		if (getThisTurnsPlayer() == 1){
			if (isWhitePiece(t)) return true;
		}
		if (getThisTurnsPlayer() == 2){
			if (isBlackPiece(t)) return true;
		}
		return false;
	}
	
	public static ArrayList<BoardCell> getMoves(Piece t){
		ArrayList<BoardCell> moves = new ArrayList<BoardCell>();
		
		if (t instanceof Piece){
			//JOptionPane.showMessageDialog(view, t.getClass()+" "+t.getCurrentCellOccupied().toString());
			moves.addAll(Board.ChessPiece.possibleMoves(t.getCurrentCellOccupied()));
			return moves;
		}
		return null;
	}
	
	public static boolean gameOver(){
		/*ArrayList<Move> moves = Board.ChessPiece.possibleMoves();
		int numOfCurrentPlayersMoves = 0;
		for (Move r : moves){
			Piece p = r.getMovedPiece();
			if (getThisTurnsPlayer() == 1){
				if (isWhitePiece(p)) numOfCurrentPlayersMoves++;
			}
			else if (getThisTurnsPlayer() == 2){
				if (isBlackPiece(p)) numOfCurrentPlayersMoves++;
			}
		}
		if (numOfCurrentPlayersMoves == 0 && !check){
			if (getThisTurnsPlayer() == 1){
				JOptionPane.showMessageDialog(view, "Stalemate! White has no possible moves and they're\nnot in check.");
				System.exit(0);
			}
			if (getThisTurnsPlayer() == 2){
				JOptionPane.showMessageDialog(view, "Stalemate! Black has no possible moves and they're\nnot in check.");
				System.exit(0);
			}
		}
		if (numOfCurrentPlayersMoves == 0 && check) return true;
		if (check) JOptionPane.showMessageDialog(view, "Check.");*/
		return true;
	}
	
	public static void startGameLoop(){
		boolean keepPlaying = true;
		while (keepPlaying){
			System.out.println("Current Player's Turn -> "+getThisTurnsPlayer());
			if (gameOver()){
				keepPlaying = false;			
			}
			else{
				ct++;
				continue;
				//flipMat();
			}
		}
	}
	
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChessGame cg = new ChessGame();
            	view.createAndShowUI();
                view.loadDataToBoardUI(model);
                try {
					startGameLoop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}
}
