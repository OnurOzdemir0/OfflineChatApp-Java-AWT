package chat;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.Border;
import  chat.*;

public class CSChatApp extends CSdictionary
{
	public static boolean isConnected = false;
	public static Color p3_p4_color = new Color(72, 72, 72);
	public static TextArea chat_history;
	
	@SuppressWarnings("deprecation")
	public static void main (String[] args) 

	{
		System.out.println(isConnected);
		//--------------------------------
		
		Font f1 =  new Font("Calibri", Font.ITALIC , 40);
		Font f2 =  new Font("", Font.BOLD, 24);
		
		int maxX = 600;
		int maxY = 600;
	
		Color lightblu = new Color(80,120,222);
		Color blu = new Color(120,160,250);
		
		
		JFrame frame = new JFrame("OzU Chat");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(maxX,maxY);
		frame.setResizable(false);

		Panel background = new Panel();
		panelCreator(background, maxX,maxY,0, 0 , lightblu );
//Connection Panel = p2:		
		Panel p2 = new Panel();
		panelCreator(p2, maxX, (int)(maxY*.14), 0, (int)(.05 * maxY), blu );
		Label banner = new Label("OzU Chat");
		banner.setFont(f1);
		banner.setForeground(lightblu);
		p2.add(banner);
		banner.setLocation(115,5);
		
		Button butConn = new Button("  Connect  "); 
		butConn.setFont(f2);		
// --------------------------------------------------------------
//Mid Section		
	//Messages = p3
		Panel p3 = new Panel();		
		panelCreator(p3, (int)(maxX*.7), (int)(maxY*.6), 5, (int) (maxY*.2), p3_p4_color);
	//Contact List = p4
		Panel p4 = new Panel();
		panelCreator(p4, (int)(maxX*.27), (int)(maxY*.6), (int)(maxX*.72), (int)(maxY*.2), p3_p4_color);
		
	//Lower-->Input Panel = p5
		Panel p5 = new Panel();
		panelCreator(p5, (int)(maxX*.7), (int)(maxY*.08), 5, (int)(maxY*.82), p3_p4_color);
// --------------------------------------------------------------
/*Input enters in a TextArea (input),
after the event, it becomes appended to a list*/
		TextField input = new TextField(55);
		chat_history = new TextArea( (int)(maxY*.035), (int)(maxX*.09) );
		chat_history.setEditable(false);
		input.setEditable(isConnected);
		input.selectAll();
		
		p5.add(input);
		p3.add(chat_history);
		
		
		appendMessage(chat_history, input);
// --------------------------------------------------------------		
		refreshFrame(frame, background, p2, p3, p4, p5);
		p2.add(butConn);
//contacts
		p4.add(new Label("Ayþe"));
		p4.add(new Label("Pelin"));
		p4.add(new Label("Murat"));
		p4.add(new Label("Fatma"));
		Label ali = new Label("Ali");
		ali.setForeground(Color.gray);
		Label veli = new Label("Veli");
		veli.setForeground(Color.gray);
		p4.add(ali);
		p4.add(veli);
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
//	
		buttonSwitch(butConn,frame, background, p2, p3, p4, p5, input, chat_history);		
//---------------------------------------------------------------
		frame.setVisible(true);
	}	
	
}