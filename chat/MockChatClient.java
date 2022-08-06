package chat;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import chat.*;

public class MockChatClient implements ChatClientInterface
{
	//we changed the name of some of the methods in this page, and used them in different classes or we haven't used some of the methods.
	public static boolean botReadyMessage;
	public static boolean countingSeconds;
	
	public static int goalSecond;
	private boolean loggedIn;
	private ChatMessageListener chatMessageListener;
	private LinkedList<MessageResponse> messages;
	private ArrayList<String> usernames;
	private String username;
	
	
	private static final String[] robots = {"ali", "veli", "ayse", "pelin", "murat", "fatma"};
	private static final String[] texts = {
			"Hello.",
			"Hi.",
			"How are you people?",
			"So what's up?",
			"Why is nobody responding?",
			"I just arrived.",
			"I am sorry.",
			"I don't know what to say.",
			"How is life?",
			"This is nice.",
			"How would we do this?",
			"Okay.",
			"Where is everybody?",
			"Oh..."
	};
	
	private static Random random = new Random();
	
	private static String randomString(String[] strings) {
		return strings[random.nextInt( strings.length )] ;
	}
	
	private static String randomRobot() {
		return randomString(robots);
	}
	
	private static String randomText() {
		return randomString(texts);
	}
	
	
	public MockChatClient() {
		this.chatMessageListener = new NullChatMessageListener();
		this.messages = new LinkedList<MessageResponse>();
		this.usernames = new ArrayList<String>();
		
		for (String robot : robots) {
			this.usernames.add(robot);
		}
	}
	
	private synchronized boolean isLoggedIn() {
		return loggedIn;
	}

	private synchronized void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	

	private synchronized String getUsername() {
		return username;
	}

	private synchronized void setUsername(String username) {
		this.username = username;
		this.usernames.add(username);
	}



	/**
	 * This is here so that null checks are not necessary when trying to
	 * call methods of the listener. If the program has not registered, 
	 * the methods of this inner static class will be called, doing nothing.
	 * Otherwise, the proper methods will end up being called.
	 * @author safkan
	 *
	 */
	private static class NullChatMessageListener implements ChatMessageListener {
		@Override
		public void loginResponseReceived(LoginResponse loginResponse) {
		}

		@Override
		public void messageHistoryResponseReceived(MessageHistoryResponse messageHistoryResponse) {
		}

		@Override
		public void messageResponseReceived(MessageResponse messageResponse) {
		}
		
		@Override
		public void personListResponseReceived(PersonListResponse personListResponse) {
		}

	}
	

	/*@Override
	public void connect(String address, int port) {
		
		if (this.runner == null) {
			this.runner = new Thread(this);

			this.setLoggedIn(false);
			
			this.runner.start();
		} else {
			// Do nothing if calling connect more than once.
			// This could be a problem for production, but for
			// testing purposes this is fine.
		}
	}*/

	@Override
	public void registerChatMessageListener(ChatMessageListener chatMessageListener) {
		
		// If the current listener is NullChatMessageListener, we accept the new one.
		// Otherwise this is being called twice, so we throw an exception.
		if (this.chatMessageListener instanceof NullChatMessageListener) {
			this.chatMessageListener = chatMessageListener;
		} else {
			throw new IllegalStateException("A ChatMessageListener is already registered.");
		}
	}

	@Override
	public void sendMessage(Object message) {
		if (message instanceof LoginRequest) {
			this.handleLoginRequest((LoginRequest)message);
		} else if (message instanceof MessageHistoryRequest) {
			this.handleMessageHistoryRequest((MessageHistoryRequest)message);
		} else if (message instanceof MessageRequest) {
			this.handleMessageRequest((MessageRequest)message);
		} else if (message instanceof PersonListRequest) {
			this.handlePersonListRequest((PersonListRequest)message);
		} else {
			throw new IllegalArgumentException("An unknown message type was sent. Type is: " + message.getClass().getName());
		}
		
	}

	private synchronized MessageResponse[] getMessages(int count) {
		int n = Math.min(count, this.messages.size());
		
		return this.messages.subList(0, n).toArray(MessageResponse[]::new);
	}

	private synchronized String[] getUsernames() {
		return this.usernames.toArray(String[]::new);
	}
	
	private synchronized void addMessage(MessageResponse messageResponse) {
		this.messages.addFirst(messageResponse);
	}
	
	private void handleLoginRequest(LoginRequest loginRequest) {
		this.setLoggedIn(true);
		this.setUsername(loginRequest.username);
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.success = true;
		
		this.chatMessageListener.loginResponseReceived(loginResponse);
	}

	private void handleMessageHistoryRequest(MessageHistoryRequest messageHistoryRequest) {
		MessageHistoryResponse messageHistoryResponse = new MessageHistoryResponse();
		
		messageHistoryResponse.messages = this.getMessages(messageHistoryRequest.count);
		
		this.chatMessageListener.messageHistoryResponseReceived(messageHistoryResponse);
		
	}

	private void handleMessageRequest(MessageRequest message) {
		MessageResponse messageResponse = new MessageResponse();
		messageResponse.message = message.message;
		messageResponse.sender = this.getUsername();
		messageResponse.time = localTimeString();
		
		this.addMessage(messageResponse);
		
		this.chatMessageListener.messageResponseReceived(messageResponse);
		
	}

	private void handlePersonListRequest(PersonListRequest message) {
		PersonListResponse personListResponse = new PersonListResponse();
		
		personListResponse.personList = this.getUsernames();
		
		this.chatMessageListener.personListResponseReceived(personListResponse);
	}

	public static String localTimeString() {
		return LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
	}
	
//we use randomSleep()
	public static void randomSleep() {
		try {
			LocalDateTime date = LocalDateTime.now();
			
			if(!countingSeconds) {	
			int randomNum = random.nextInt(7) + 3;
			int firstSeconds = date.toLocalTime().toSecondOfDay();
			goalSecond = randomNum + firstSeconds;
			countingSeconds = true;
			}
			
			int currentSecond = date.toLocalTime().toSecondOfDay();
			if(goalSecond <= currentSecond) {
				CSbackend_manager.sendMessage = true;
				countingSeconds = false;
			}
		} catch (Exception e) {
			// Not going to happen, I think.
		}
	}
	
	@Override
	public void connect(String address, int port) {
		// TODO Auto-generated method stub	
	}
}