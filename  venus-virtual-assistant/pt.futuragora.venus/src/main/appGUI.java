package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import org.alicebot.ab.*;

@SuppressWarnings("serial")
public class appGUI extends JFrame{
	
	//private objects for use in the GUI
	private JFrame frame;
	private JPanel panel;
	private static JTextField txtUserMessage;
	private static JLabel lblVenusMessage;
	static Bot bot;
	static Chat chatSession;
//	private Button button;
	
	//private variables for external access

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					appGUI window = new appGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// gets the path of the root folder for this project
		String path = System.getProperty("user.dir");
		
		//System.out.println(path); //Prints in console the path to root folder -- just for me to check
		path +="/src";
		System.out.println(path);
		
		//Names the bot
		String botname="Venus";
		
		//creates a AIML bot based on the files that it finds in path
		bot = new Bot(botname, path);
				
		//creates a new chat session
		chatSession = new Chat(bot);	
	}

	/**
	 * Create the application.
	 * Constructor Method; initialises all the window positions, sizes and so on of the bot output text and user input message.
	 */
	public appGUI() {
		initialize();
		frame.setVisible(true);
		txtUserMessage.setText("Type input text here.");
		lblVenusMessage.setText("Venus Personal Assistant V.0.0.6");
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame(); // Java Frame in SWING
		frame.setTitle("Venus AIML BOT by Futuragora.pt");  //Sets the title of the window
		frame.setBounds(100, 100, 450, 300);				//Sets Window Size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Closes the frame when you close app
		frame.getContentPane().setLayout(null);
		
		// Buttons
/*		button = new Button("Apps");
		frame.add(button); 
		frame.setLayout(new FlowLayout());*/
			  
		panel = new JPanel();								
		panel.setBounds(12, 12, 426, 263);
		frame.getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[][342.00,grow,center][]", "[][][156.00,top][]"));
		
		lblVenusMessage = new JLabel("VenusMessage");
		lblVenusMessage.setVerticalAlignment(SwingConstants.TOP);
		lblVenusMessage.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblVenusMessage, "cell 1 2");
		//System.out.println("LblVenusMessage thread is: "+Thread.currentThread().getName() );-- checks the thread
		
		
		//sets TextField userMessage on the GUI window
		txtUserMessage = new JTextField(); //Creates a new object and assigns to the variable txtUserMessage defined at top of file.
		txtUserMessage.setText("UserMessage");//sets the default user message that shows up inside the TextField in the GUI
		panel.add(txtUserMessage, "cell 1 3,growx");//Adds it to the panel so we can see the actual TextField on screen
		txtUserMessage.setColumns(10);// sets the size of the textField?
		
		//When user press enter what to do with the TextField message
		txtUserMessage.addActionListener(new ActionListener()
		{
			@Override
			public  void actionPerformed(ActionEvent e)
			{
						setVenusOutput(txtUserMessage.getText());
						txtUserMessage.setText("");
						
			}
		});
	}
	
	
	public static void setVenusOutput(String venusOutput) {
			
			lblVenusMessage.setText(chatSession.multisentenceRespond(venusOutput));
			
		};
		
	public String getUserMessage() {
		
		return txtUserMessage.getText();
	}

}


