import java.io.IOException;

import javax.swing.JFrame;


public class main extends JFrame{

	/**
	 * Karl Coe
	 * http://steamcommunity.com/id/cyberXwarrior
	 * New Mission Notifier
	 * Notifies the user of a new mission
	 * 
	 * To Do:
	 * @throws IOException 
	 * 
	 */
	
	public static void main(String[] args) throws IOException
	{
		double versionNumber=1.0;
		
		main window = new main();
		
		window.setTitle("New Mission Notifier "+versionNumber);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //window is hidden on close
        window.setContentPane(new menu());
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
	}

}
