package chat;

import java.awt.*;
import javax.swing.*;

import chat.*;
public class CSLoginPage {	
	public static boolean Login;
	public static int counter = 2;
	public static String user;
	
	public static void loginInit(Button butConn ,Frame fr, Panel p1, Panel p2, Panel p3,Panel p4,Panel p5, TextField inp, TextArea chat_his) 
	{
		if(counter%2 == 0)  //this aims skipping the login part after pressing disconnect, 
						//because in our case connect and disconnect is the same button, 
		{
			Font font1 = new Font("SansSerif", Font.BOLD, 14); 	
			
			JFrame f = new JFrame("Login");
			f.setSize(300,150);
			f.setLocation(400,400);
			
			JDialog feedback = new JDialog();
			feedback.setVisible(false);
			feedback.setSize(200,100);
			feedback.setLocation(450,450);
			feedback.setLayout(new FlowLayout());
			JButton ok = new JButton("Ok");
			
			Label Username = new Label("Username:");
			Username.setFont(font1);
			Label Password = new Label("Password:");
			Password.setFont(font1);
			JTextField field = new JTextField(15);
			JPasswordField field2 = new JPasswordField(15);
			JButton enter = new JButton("Login");	
			
			f.setLayout(new FlowLayout());
			
			f.add(Username);
			f.add(field);
			f.add(Password);
			f.add(field2);		
			f.add(new Label("OzU Chat"));
			f.add(enter);
			f.setVisible(true);
			f.isAlwaysOnTop();
			//enter is for login
			enter.addActionListener(e -> {
					user = field.getText();
					feedback.add(new Label("Welcome back, " + user),font1);
					feedback.add(ok);
					ok.setAlignmentX(feedback.CENTER_ALIGNMENT);
					feedback.isAlwaysOnTop();
					feedback.setVisible(true);
					//ok is for ok button after the welcome back Dialog, most things happen after you press that button
					ok.addActionListener(ev -> {
						f.dispose();
						feedback.dispose();
						Login = true;
						counter++;
						CSdictionary.con(butConn,fr, p1, p2, p3, p4, p5, inp, chat_his);
					});
				}
			);}	
	 else {
		Login = false;
		CSdictionary.con(butConn,fr, p1, p2, p3, p4, p5, inp, chat_his);
		counter++;
	}
		
	}	
}