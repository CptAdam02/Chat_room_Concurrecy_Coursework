import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ChatServer {

	//stores all the room in the server
	private ArrayList<ChatRoom> rooms = new ArrayList<>();

	//stores all the users in the server
	private List<User> users = new ArrayList<>();

	//stores the admin of the server
	private Admin admin;

	//stores the main room of the server
	private int mainRoom;

	//stores the total capacity of the server
	private int capacity;

	//stores wether the server is open or not
	private boolean isOpen;


	/**
	 *
	 * The constructor for a Chat server. Initialises a new instance of the server after being provided with necessary information.
	 *
	 * @param  capacity  The capacity of the new chat server.
	 * @param  numOfRooms The number of rooms in the new server.
	 * @param  admin The admin of the new server.
	 */
	public ChatServer(int capacity, int numOfRooms, Admin admin) {
		this.capacity = capacity;
		for (int i = 0; i < numOfRooms; i++){
			if (rooms.size() == 0 ){
				mainRoom = i;
			}
			rooms.add(new ChatRoom(i,10));
		}
		this.admin = admin;
		admin.assignServer(this);
	}
	/**
	 * Opens the chat server for people to enter.
	 */
	public void open() {
		// Code to open the Chat Room.
		isOpen = true;
		System.out.println("Chat Server is Opened.");
	}
	/**
	 * Closes the chat server meaning people cannot enter.
	 */
	public void close() {
		System.out.println("Chat Server is being Closed.");
		boolean empty = false;

		for (ChatRoom value : rooms) {
			if (value.getIsOpen()) {
				empty = true;
				System.out.println("Chat Server cannot close as " + value.getChatRoomID() + " is still open");
			}
		}

		if (!empty){
			isOpen = false;
			System.out.println("Chat Server is Closed.");
		}


		// Code to close the Chat Server.
		// Think carefully about when you can successfully 
		// close the Chat Server.

	}


	/**
	 * Allows a user to enter the server after checking that the server is open, and they are not intact in the server already.
	 *
	 * @param user The user who wants to join the server.
	 * @return returns a boolean saying if the user joined the server or not.
	 */
	public boolean join(User user) {
		// Consider conditions that need to be true for this
		// to be successful.
		// Returns true if joined successfully, false otherwise.
		boolean alreadyThere = false;

		if (isOpen){
			if (users.size() != capacity){
				if (users.size() != 0){
					for (int i = 0; i < users.size(); i++) {
						User value = users.get(i);
						if (value.getID() == user.getID()) {
							alreadyThere = true;
						}
					}
				}
				if (alreadyThere){
					System.out.println("User " + user.getID() + " failed to join Chat Server (" + user.getWantToChat() + ").");
				}else{
					users.add(user);
					System.out.println("User " + user.getID() + " admitted to Chat Server (" + user.getWantToChat() + ").");
				}
			}else {

				System.out.println("User " + user.getID() + " failed to join Chat Server (" + user.getWantToChat() + ").");

			}
		}
		return !alreadyThere;
	}

	/**
	 * Allows a user leave the server.
	 *
	 *
	 * @param user The user who wants to leave the server.
	 */
	public void leave(User user) {
		boolean alreadyThere = false;

		for (int i = 0; i < users.size(); i++) {
			User value = users.get(i);
			if (value.getID() == user.getID()) {
				alreadyThere = true;
			}
		}
		if (alreadyThere){
			users.remove(user);
			System.out.println("User " + user.getID() + " left the Chat Server.");
		}else{
			System.out.println("Could not remove User " + user.getID() + " as is not in the Chat Server.");
		}

	}

	/**
	 * Opens a chat room that is within the server.
	 *
	 * @param chatRoomID the id of the chat room being opened.
	 * @param capacity The capacity of the chat room being opened.
	 */
	public void openChatRoom(int chatRoomID, int capacity) {
		if (isOpen){
			for (int i = 0; i < rooms.size(); i++) {
				ChatRoom value = rooms.get(i);
				if (value.getChatRoomID() == chatRoomID) {
					value.open();
					value.setCapacity(capacity);
				}
			}
		}

		if (isOpen){

		}

	}

	/**
	 * Closes a chat room that is within the server.
	 *
	 * @param chatRoomID the id of the chat room being Closed.
	 */
	public void closeChatRoom(int chatRoomID) {
		if (isOpen){
			for (int i = 0; i < rooms.size(); i++) {
				ChatRoom value = rooms.get(i);
				if (value.getChatRoomID() == chatRoomID) {
					value.close();
				}
			}
		}


		// Code to close Chat Room.
	}

	/**
	 * Allows a user to enter a chat room.
	 *
	 * @param user The user who wants enter the chat room.
	 * @param chatRoomID The chat room the user wants to enter.
	 * @return Returns a boolean saying if the user joined the room or not.
	 */
	public boolean enterRoom(User user, int chatRoomID) {
		if (isOpen){
			return rooms.get(chatRoomID).enterRoom(user);
		}
		return false;
	}

	/**
	 * Allows a user to leave a chat room.
	 *
	 * @param user The user who wants leave the chat room.
	 * @param chatRoomID The id of the chat room wanting to be left.
	 */
	public void leaveRoom(User user, int chatRoomID) {
		if (isOpen){
			rooms.get(chatRoomID).leaveRoom(user);
		}
	}

	/**
	 * Returns the number of rooms in the server.
	 *
	 * @return the number of the rooms in the server.
	 */
	public int getNumberOfRooms() {
		return rooms.size();
	}

	/**
	 * returns if a room is open.
	 *
	 * @return if the room is Open.
	 */
	public boolean isRoomOpen(int chatRoomID) {
		return rooms.get(chatRoomID).getIsOpen();
	}

	/**
	 * returns the number of users.
	 *
	 * @return the number of users.
	 */
	public int getNumberOfUsers() {
		return users.size();
	}

	/**
	 * Returns the main room.
	 *
	 * @return the main room.
	 */
	public int getMainRoom() {
		return mainRoom;
	}

}