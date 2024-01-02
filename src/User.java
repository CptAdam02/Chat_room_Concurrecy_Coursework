
public class User implements Runnable {

	private static int sleepScale = 100;
	//the users id
	private int userID;
	// the chat server the user is in
	private ChatServer chatServer;
	//a check whether the user has joined a server
	private boolean joinedServer;
	//a check whether the user has joined hte main room
	private boolean joinedMainRoom;
	//how much the user wants to carry on chating
	private int wantToChat;

	/**
	 *
	 * The constructor for a user. Initialises a new instance of an user after being provided with necessary information.
	 *
	 * @param  userID  The id of the user.
	 * @param  chatServer  The chat server they are in.
	 */
	public User(int userID, ChatServer chatServer) {
		this.userID = userID;
		this.chatServer = chatServer;
		wantToChat = (int)(Math.random() * (15-10+1) + 10);
	}


	/**
	 * Gets how mcu the user still wants to chat.
	 *
	 * @return how much the user wants to chat
	 */
	public int getWantToChat() {
		return wantToChat;
	}

	/**
	 * Gets the user's id.
	 *
	 * @return the users id
	 */
	public int getID() {
		return userID;
	}

	/**
	 *
	 * Runs a thread of the user.
	 *
	 */
	public void run() {
		try {
			// While the user is still interested in chatting
			while (wantToChat > 0) {
				wantToChat = wantToChat -1;
				double d = ((Math.random())*4+1)*1000;
				long time = (long) d;
				int timeInt = (int) (d/1000);
				if (!joinedServer) {
					if (chatServer.join(this)){
						wantToChat = Math.toIntExact(Math.round(wantToChat - 0.5));
						joinedServer = true;
					}else{
						wantToChat = 0;
					}
				} else if (!joinedMainRoom) {
					if (chatServer.enterRoom(this,chatServer.getMainRoom())){
						wantToChat = wantToChat - (1+timeInt);
						joinedMainRoom = true;
						Thread.sleep(time);
						chatServer.leaveRoom(this,chatServer.getMainRoom());
					}
				} else {
					int randomRoomNum = (int) (Math.random()*(chatServer.getNumberOfRooms()-1) + 1);
					chatServer.enterRoom(this,randomRoomNum);
					Thread.sleep(time);
					wantToChat = wantToChat - (2+timeInt);
					chatServer.leaveRoom(this,randomRoomNum);
				}
			}
			chatServer.leave(this);
			// What should happen when the user no longer wants to chat?
		} catch (InterruptedException ex) {
			System.out.println("Interrupted User Thread (" + userID + ")");
		}
		System.out.println("User Thread (" + userID + ") has ended!");
	}

}