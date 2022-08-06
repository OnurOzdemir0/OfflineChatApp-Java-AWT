package chat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.util.*;

import chat.*;
public class CSdictionary{
	
	//this is the class that most methods that are in use are written. 
	
int maxX, maxY;
static int mesCount = 0;


static CSMultithreading multi = new CSMultithreading(); //for sleep method (robot messaging delay)

	public static void panelCreator(Panel p,int sizeX, int sizeY, int locationX, int locationY, Color c)
	{	
		p.setSize(sizeX, sizeY);
		p.setLocation(locationX, locationY);
		p.setBackground(c);
	}
	
	public static void refreshFrame(Frame f, Panel p1, Panel p2, Panel p3,Panel p4,Panel p5) {
		
		f.add(p2);
		f.add(p3);
		f.add(p4);
		f.add(p5);
		f.add(p1);
	}
	

public static void appendMessage(TextArea ta, TextField tf) {
    tf.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
        	String text = tf.getText();
        	if(text.equals("PersonListRequest")) {
        		ta.appendText("Ali, Veli, Ayþe, Pelin, Murat, Fatma, 6 \n");
        	} else if (text.equals("MessageHistoryRequest")) {
        		ta.appendText("" + mesCount + "\n");
        	}
        	else {
        		ta.appendText(CSLoginPage.user + ": " + text + " |  | " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + "\n" );
        		tf.setText("");
        		mesCount++;}
        }
    }
    });
}	

//connect/disconnect button
	public static void buttonSwitch(Button butConn ,Frame f, Panel p1, Panel p2, Panel p3,Panel p4,Panel p5, TextField inp, TextArea chat_his) {
	butConn.addActionListener(new ActionListener() 
	{    
		public void actionPerformed (ActionEvent e) 
		{    
			CSLoginPage.loginInit(butConn,f, p1, p2, p3, p4, p5, inp, chat_his);
			CSChatApp.isConnected = !CSChatApp.isConnected;	
			}
		});	
	refreshFrame(f,p1, p2, p3, p4, p5);
	}

//con resembles the connect method of the original back end, even though it also sets the front end to other colors and stuff
	public static void con(Button butConn ,Frame f, Panel p1, Panel p2, Panel p3,Panel p4,Panel p5, TextField inp, TextArea chat_his)
	{
		
		if (CSLoginPage.Login&&CSChatApp.isConnected) 
		{
			butConn.setLabel("Disconnect");
			CSChatApp.p3_p4_color = new Color(225, 225, 225);
			p3.setBackground(CSChatApp.p3_p4_color);
			p4.setBackground(CSChatApp.p3_p4_color);
			p5.setBackground(CSChatApp.p3_p4_color);
			inp.setEditable(true);
			refreshFrame(f, p1, p2, p3, p4, p5);
			multi.start();
		}    	
		else{
			butConn.setLabel("Connect");
			CSChatApp.p3_p4_color = new Color(72, 72, 72);
			p3.setBackground(CSChatApp.p3_p4_color);
			p4.setBackground(CSChatApp.p3_p4_color);
			p5.setBackground(CSChatApp.p3_p4_color);
			inp.setEditable(false);
			refreshFrame(f, p1, p2, p3, p4, p5);
		}
	}
}