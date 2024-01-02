import java.util.ArrayList;

public class Application {
	
	public static void main(String[] args) throws InterruptedException {

		// This was a solution I wrote for my coursework from the concurrecy module I was doing

		Admin admin = new Admin("Adam");
		Thread at = new Thread(admin);
		int numOfRooms = (int) (Math.random()*10);

		ChatServer cs = new ChatServer(255, numOfRooms, admin);


		ArrayList<Thread> userThreads = new ArrayList<Thread>();

		at.start();

		// Create 20 users with random UserIDs (1-100) and start their threads
		for (int i = 0; i < 5; i++) {
			int userID = (int)(Math.random() * (100-1+1) + 1);
			User u1 = new User(userID, cs);
			Thread u1t = new Thread(u1);
			userThreads.add(u1t);
			u1t.start();
		}



		// Make sure this waits for all user threads to end
		for (Thread t : userThreads) {
			t.join();
		}

		at.join();

	}
}