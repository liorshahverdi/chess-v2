public class BoardCell {
	private int row;
	private int col;
	private boolean isKill = false;
	
	public void setIsKill(boolean x){ this.isKill = x; }
	public boolean getIsKill() { return isKill; }

	public BoardCell(int r, int c) {
		row = r;
		col = c;
	}

	public int getRow() { return row; }

	public int getCol() { return col; }

	public static BoardCell translate(int k)
	{
		BoardCell temp;
		
		int row = k/8;
		int col = k%8;
		temp = new BoardCell(row,col);
		
		return temp;
	}
	
	public String toString() { return "row = "+row+" col = "+col; }
}