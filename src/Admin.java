
// Note: This is an active class and must implement runnable
public class Admin implements Runnable{
	
	private static int sleepScale = 100;
	//admins name
	private String name;
	//the chat server the admin is assigned to
	private ChatServer chatServer;
	//the number of actions an admin can carry out in a single thread
	private int numOfActions = 15;  //Could be either opening or closing a chatroom
	//a check whether they have already opened the server once
	private boolean openedServer;
	//a check whether they have already opened the main room once
	private boolean openedMainRoom;


	/**
	 *
	 * The constructor for an admin. Initialises a new instance of an admin after being provided with necessary information.
	 *
	 * @param  name  The name of the admin.
	 */
	public Admin(String name) {
		// Set the initial value of class variables
		this.name = name;
	}

	/**
	 *
	 * Assigns a new server for the admin to control
	 *
	 * @param  chatServer  The new server the admin controls
	 */
	public void assignServer(ChatServer chatServer) {
		// Store given Chat Server in Class Attribute
		this.chatServer = chatServer;
	}

	/**
	 * Runs a thread for the admin
	 *
	 */

	public void run() {
		
		// you need to open the chat server and the chat rooms

		// run actions randomly (HINT: you may use a randomised sleep time before doing the action)
		//close the chat server and the chat rooms

		try {
			while (numOfActions > 0) {
				if (!openedServer){
					chatServer.open();
					Thread.sleep(1000);
					numOfActions--;
					openedServer = true ;

				}else if (!openedMainRoom){
					for (int i = 0; i < chatServer.getNumberOfRooms();i++){
						chatServer.openChatRoom(i, 10);
						Thread.sleep(1000);
						numOfActions--;
						chatServer.closeChatRoom(i);
					}
					openedMainRoom = true;
				}else{
					numOfActions = 0;
				}
			}
			chatServer.close();
		} catch (InterruptedException ex) {
			System.out.println("Interrupted User Thread (" + name + ")");
		}
		System.out.println("Admin Thread (" + name + ") has ended!");
	}
}