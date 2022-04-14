package simpleGa;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JFrame;

public class Drawing extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");//instantiates JFrame
        Canvas canvas = new Drawing();
        canvas.setSize(1000, 1000);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        
    }

    public void paint(Graphics g) {//draws graphics
        Pop a = new Pop();
		a.genPop();
		a.movePop(g,0);
		a.calcFit();
		a.getNewPop();
		for(int i = 1; i < 300; i++)//loop for hoe many generations are drawn
		{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1000, 1000);
			//System.out.print("1:" + a.ind[0].getID());
			//a.ind[0].print(a.ind[0]);
			a.getNewPop();
			a.movePop(g,i);
			a.calcFit();
			System.out.println("Generation" + i +"= ");
			Pop.printFit(a.getPop());//prints population stats in console
		}
	return;	
    }
}