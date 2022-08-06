package chat;


import java.awt.TextArea;
import java.awt.TextField;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import chat.*;
public class CSMultithreading extends Thread {
	public void run() {
		while(true) {
			if(CSChatApp.isConnected && CSLoginPage.Login){
				CSbackend_manager.robotMessage();
			}	
		}
	}
			
}

//Thread sleep method was putting the whole application on freeze, so we used multithreading 