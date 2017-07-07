package ClientInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerGUI
{
	// General Frame Components
	private static JFrame		sInterface;
	private static JScrollPane	scrollPane;
	private static JTextArea	textArea;
	
	// Buttons
	private static JButton		btnClose;
	private static JButton		btnPause;
	
	// Labels
	private static JLabel		lblClients;
	
	private static int			clientsConnected	= 0;
	
	public static JScrollPane getScrollPane ()
	{
		return scrollPane;
	}
	
	public static JTextArea getTextArea ()
	{
		return textArea;
	}
	
	public static JFrame getsInterface ()
	{
		return sInterface;
	}
	
	public static void addClient ()
	{
		clientsConnected++;
		numClientsDisp();
	}
	
	public static void removeClient ()
	{
		clientsConnected--;
		numClientsDisp();
	}
	
	private static void numClientsDisp ()
	{
		String lblText = String.valueOf( clientsConnected + " " );
		
		if ( clientsConnected != 1 )
		{
			lblText += "Clients Connected";
		}
		else
		{
			lblText += "Client Connected";
		}
		
		lblClients.setText( lblText );
	}
	
	private static void shutdown ()
	{
		Server.toggleConnections();
		
		if ( clientsConnected == 0 )
		{
			System.exit( 0 );
		}
		else
		{
			try
			{
				Thread.sleep( 5000 );
				shutdown();
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}
	
	public ServerGUI()
	{
		initialize();
		numClientsDisp();
		sInterface.setVisible( true );
	}
	
	private void initialize ()
	{
		sInterface = new JFrame();
		sInterface.setBounds( 100, 100, 450, 300 );
		sInterface.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		sInterface.setLayout( null );
		sInterface.setExtendedState( JFrame.MAXIMIZED_BOTH );
		sInterface.setUndecorated( true );
		sInterface.setVisible( true );
		scrollPane = new JScrollPane();
		scrollPane.setBounds( 0, 0, sInterface.getWidth(), sInterface.getHeight() - 70 );
		sInterface.getContentPane().add( scrollPane );
		textArea = new JTextArea();
		textArea.setEditable( false );
		scrollPane.setViewportView( textArea );
		lblClients = new JLabel();
		lblClients.setBounds( 120, sInterface.getHeight() - 60, 200, 50 );
		sInterface.getContentPane().add( lblClients );
		btnClose = new JButton();
		btnClose.setBounds( 10, sInterface.getHeight() - 60, 100, 50 );
		btnClose.setText( "Shutdown" );
		btnClose.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed ( ActionEvent e )
			{
				shutdown();
			}
		} );
		sInterface.getContentPane().add( btnClose );
		btnPause = new JButton();
		btnPause.setBounds( ( sInterface.getWidth() / 2 ) - 75, sInterface.getHeight() - 60, 150, 50 );
		btnPause.setText( "Pause Connections" );
		btnPause.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed ( ActionEvent e )
			{
				Server.toggleConnections();
			}
		} );
		sInterface.getContentPane().add( btnPause );
	}
}
