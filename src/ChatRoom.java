import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ChatRoom {

	// the id of the chat room
	private int chatRoomID;
	//the capacity of the chat room
	private int capacity;
	//the list of users within the chat room
	private List<User> users = new ArrayList<>();
	//wether the chat room is open or not
	private boolean isOpen;

	/**
	 *
	 * The chat room constructor class. Creates new instances of chat room when given sufficient information.
	 *
	 * @param  chatRoomID  the id of hte new chat room.
	 * @param  capacity the capacity of the new chat room.
	 */
	public ChatRoom(int chatRoomID, int capacity) {
		// Set the initial value of class variables.
		// Think carefully about how to protect users from
		// unintended synchronous activity.
		this.chatRoomID = chatRoomID;
		this.capacity = capacity;
	}

	/**
	 * Opens the chat room for people to enter.
	 */
	public void open() {
		// Code to open the Chat Room.
		isOpen = true;
		System.out.println("Chat Room " + chatRoomID + " open!");
	}

	/**
	 * Closes the chat room meaning no one can enter.
	 */
	public void close() {
		System.out.println("Chat Room " + chatRoomID + " is being closed!");
		if ( users.size() != 0){
			for (int i = 0; i < users.size(); i++) {
				User value = users.get(i);
				this.leaveRoom(value);
			}
			System.out.println("Chat Room " + chatRoomID + " closed!");
			isOpen = false;
		}else{
			System.out.println("Chat Room " + chatRoomID + " closed!");
			isOpen = false;
		}
	}

	/**
	 *
	 * Allows someone to enter then room, adding them to the list of people present.
	 *
	 * @param  user The user who wants to enter the chat room.
	 * @return returns a boolean saying if the person managed to enter the room.
	 */
	public boolean enterRoom(User user) {
		//checks if there is space in the room
		if (users.size() < capacity){
			//checks if the user is already there
			boolean alreadyThere = false;
			for (User value : users) {
				if (value == user) {
					alreadyThere = true;
				}
			}

			if (!alreadyThere){
				System.out.println("User " + user.getID() + " joined Chat Room " + chatRoomID + ". (" + user.getWantToChat() + ")");
				users.add(user);
				return true;
			}else{
				System.out.println("User " + user.getID() + " not joined Chat Room " + chatRoomID + ". (" + user.getWantToChat() + ")");
				return false;
			}
		}else {
			System.out.println("User " + user.getID() + " not joined Chat Room " + chatRoomID + ". (" + user.getWantToChat() + ")");
			return false;
		}
	}

	/**
	 *
	 * Allows someone to leave then room, removing them to the list of people present.
	 *
	 * @param  user The user who wants to leave the chat room.
	 */
	public void leaveRoom(User user) {
		// Code for a User to leave a Chat Room.

		for (int i = 0; i < users.size(); i++) {
			User value = users.get(i);
			if (value == user){
				users.remove(value);
				System.out.println("User " + user.getID() + " left Chat Room " + chatRoomID + ". (" + user.getWantToChat() + ")");
			}
		}
	}

	/**
	 *
	 * Sends the id of the chat room back.
	 *
	 * @return returns the id of the chat room.
	 */
	public int getChatRoomID() {
		return chatRoomID;
	}

	/**
	 *
	 * Gets the value of if the room is open or not.
	 *
	 * @return Returns if the room is open or not.
	 */
	public boolean getIsOpen() {
		return isOpen;
	}

	/**
	 *
	 * Sets the capacity of the room.
	 *
	 * @param capacity The new capacity of the room.
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}