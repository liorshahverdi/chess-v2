package chess;

import javax.swing.*;
import java.awt.*;

public class PossibleMove extends JLabel{
	private static ImageIcon img = new ImageIcon("Images\\possible-move.png");
	
	public PossibleMove(){
		super(img);
	}
}