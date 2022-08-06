package chat;

import chat.LoginResponse;
import chat.MessageHistoryResponse;
import chat.MessageResponse;
import chat.PersonListResponse;

public interface ChatMessageListener {
	public void loginResponseReceived(LoginResponse loginResponse);
	public void messageHistoryResponseReceived(MessageHistoryResponse messageHistoryResponse);
	public void messageResponseReceived(MessageResponse messageResponse);
	public void personListResponseReceived(PersonListResponse personListResponse);
	
}
