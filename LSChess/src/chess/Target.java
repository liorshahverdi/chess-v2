package chess;

import javax.swing.*;
import java.awt.*;

public class Target extends JLabel{
	private static ImageIcon img = new ImageIcon("Images\\possible-move.png");
	
	public Target(){
		super(img);
	}
}
