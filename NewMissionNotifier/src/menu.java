import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import sun.net.www.URLConnection;


public class menu extends JComponent implements ActionListener{
	
	public static String serverMap="UPDATE ME";
	public static String serverPlayers="UPDATE ME";
	JLabel mapLabel=null;
	JLabel playersLabel=null;
	JButton refreshButton=null;
	
	
	public menu() throws IOException
	{
		//set size of window
		setPreferredSize(new Dimension(200, 300));
		new getServerInfo();
		setupComponents();
	}
	
	private void setupComponents()
	{
		if(this.countComponents()>0)
		{
			this.remove(mapLabel);
			this.remove(playersLabel);
			this.remove(refreshButton);
		}
		
		mapLabel=null;
		playersLabel=null;
		refreshButton=null;
		mapLabel = new JLabel("Map: "+serverMap);
		mapLabel.setVisible(true);
		mapLabel.setLocation(0,0);
		mapLabel.setSize(200, 15); 
		mapLabel.setVerticalAlignment(JLabel.TOP);
		mapLabel.setHorizontalAlignment(JLabel.LEFT);
		this.add(mapLabel);
		
		playersLabel = new JLabel("Players: "+serverPlayers);
		playersLabel.setVisible(true);
		playersLabel.setLocation(0,15);
		playersLabel.setSize(200, 15); 
		playersLabel.setVerticalAlignment(JLabel.TOP);
		playersLabel.setHorizontalAlignment(JLabel.LEFT);
		this.add(playersLabel);
		
		refreshButton=new JButton("REFRESH");
		refreshButton.setSize(95, 30);
		refreshButton.setLocation(0,280);
		refreshButton.setVisible(true);
		refreshButton.addActionListener(this);
		this.add(refreshButton);
		
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//If the settings button is clicked
		if(e.getSource()==refreshButton)
		{
			new getServerInfo();
			setupComponents();
			repaint();
			System.out.println("Refreshed");
		}
	}
	
	private static class getServerInfo implements Runnable
	{

		public getServerInfo()
		{
			run();
		}
		
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			try
			{
				URL oracle;
				
				oracle = new URL("http://cache.www.gametracker.com/components/html0/?host=198.144.179.202:2302&width=200&height=297&bgColor=57503E&titleBgColor=403D34&borderColor=272727&fontColor=FFFFFF&titleColor=FFC600&linkColor=FFC600&borderLinkColor=828E6B&showMap=0&showCurrPlayers=1&showTopPlayers=0&showBlogs=0");
				
		        BufferedReader in = new BufferedReader(
		        new InputStreamReader(oracle.openStream()));
	
		        String inputLine;
		        //Go line by line of the HTML file
		        while ((inputLine = in.readLine()) != null)
		        {
		        	//If the line has Map: in it
		        	if(inputLine.contains("Map:"))
		        	{
		        		//Go down 4 lines
		        		for(int i=0;i<4;i++)
		        		{
		        			inputLine = in.readLine();
		        		}
		        		//Save the map name
		        		serverMap=inputLine.trim();		        		
		        		System.out.println("The map is: "+serverMap);

		        	}
		        	//If the line has Players: in it but is not the Online Players: text
		        	else if(inputLine.contains("Players:")&& !inputLine.contains("Online Players:"))
		        	{
		        		//Go down 3 lines
		        		for(int i=0;i<3;i++)
		        		{
		        			inputLine = in.readLine();
		        		}
		        		//Save the player count
		        		serverPlayers=inputLine.trim();		        		
		        		System.out.println("The player count is: "+serverPlayers);
		        	}
		        }
		        in.close();
		        
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
