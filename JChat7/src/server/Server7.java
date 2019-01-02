package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server7 {

	private ServerSocket serversocket;
	private ServerSocket NameSocket;
	private int ServerPort = 1600;
	private int NamePort = 1601;
	static ArrayList<DealingThread7> dealingthreads = new ArrayList<DealingThread7>();
	public boolean running = true;

	public Server7() {
		try {
			serversocket = new ServerSocket(ServerPort);  
			
			 System.out.println("serversocket established on IP: " +
			  serversocket.getInetAddress() + " on port: " + serversocket.getLocalPort() +
			  "\n");
			 
			NameSocket = new ServerSocket(NamePort);
			funzioni();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


	public void ListenForJoin() {
		Thread ListenForJoinThread = new Thread("ListenForJoinThread") {
			public void run() {
				while (running) {
					try {

						Socket newSocket = serversocket.accept();
						Socket nameSocket = NameSocket.accept();

						dealingthreads.add(new DealingThread7(newSocket, nameSocket));

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("ListebnForJoin expired");
			}
		};
		ListenForJoinThread.start();
	}

	private void funzioni() {
		ListenForJoin();

	}

	public static void main(String[] args) {
		Server7 server7 = new Server7();

	}

}
