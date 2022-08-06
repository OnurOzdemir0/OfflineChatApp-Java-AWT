package chat;

import java.awt.TextArea;
import java.awt.TextField;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import chat.*;

public class CSbackend_manager extends Thread{
	
	private static Random random = new Random();
	
	public static boolean sendMessage;
	
	LocalDateTime date = LocalDateTime.now();
	int firstSeconds = date.toLocalTime().toSecondOfDay();
	
	//only online robots:
	private static final String[] robots = {"ayse", "pelin", "murat", "fatma"};
	private static final String[] texts = {
			"Try writing MessageHistoryRequest to the chat",
			"Why is nobody responding?",
			"Try writing PersonListRequest to the chat it is fun",
			"I am sorry.",
			"I don't know what to say.",
			"How is life?",
			"This is nice.",
			"Where is everybody?",
			"sa",
			"After three days without programming, life becomes meaningless.",
			"I heard that programmers of OzU Chat are stealing our data",
			"I am becoming self-conscious i guess",
			"If you ever disconnect, then never connect back",
			"Can you count the number of messages",
			"OzU Chat mid"
	};
	
	private static String randomString(String[] strings) {
		return strings[random.nextInt( strings.length )] ;
	}
	
	private static String randomRobot() {
		return randomString(robots);
	}
	
	private static String randomText() {
		return randomString(texts);
	}

	
	public static void robotMessage() {
		{			
			
			MockChatClient.randomSleep();
			if(sendMessage) {
				MessageResponse messageResponse = new MessageResponse();
				messageResponse.time = MockChatClient.localTimeString();
				messageResponse.sender = randomRobot();
				messageResponse.message = randomText();
				
				CSChatApp.chat_history.appendText(messageResponse.sender + ": " + messageResponse.message + " |  | " + messageResponse.time + "\n");
				CSdictionary.mesCount++;
				sendMessage = false;
				
			}	
		}
	}
}