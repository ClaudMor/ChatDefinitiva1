package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class DealingThread7 implements Runnable {

	private Thread DealingThread;
	private Socket DealingSocket;
	private Socket NameSocket;
	private BufferedReader br;
	private BufferedReader Namebr;
	public OutputStream Nameout;
	public OutputStream out;
	public String name = null;
	private boolean running = true;

	public DealingThread7(Socket socket, Socket namesocket) {
		DealingThread = new Thread(this, "DealingThread");
		this.DealingSocket = socket;
		this.NameSocket = namesocket;
		try {
			this.br = new BufferedReader(new InputStreamReader(DealingSocket.getInputStream()));
			this.Namebr = new BufferedReader(new InputStreamReader(NameSocket.getInputStream()));
			this.out = DealingSocket.getOutputStream();
			this.Nameout = NameSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		getCheer();
		
		DealingThread.start();
	}

	private void getCheer() {
		try {
			String ReceivedName = br.readLine();
			this.name = ReceivedName.substring(ReceivedName.indexOf(")") + 2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void Names() {
		Thread NameThread = new Thread("NameThread") {
			public void run() {
				while (running) {
					String names = "";
					for (DealingThread7 deal1 : Server7.dealingthreads) {
						names += deal1.name + " ! ";
					}
					names += "\n";

					try {
						Nameout.write(names.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					Nameout.write("quit \n".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		NameThread.start();
	}

	@Override
	public void run() {

		Names();

		String ReceivedText = null;
		while (true) {
			try {
				ReceivedText = br.readLine() + "\n";

			} catch (IOException e) {
				//e.printStackTrace();
				
				
			}
			if (!ReceivedText.startsWith("quit")) {
				for (DealingThread7 deal : Server7.dealingthreads) {
					try {
						deal.out.write(ReceivedText.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else {
				try {

					this.out.write("quit \n".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}

				Server7.dealingthreads.remove(this);

				try {
					running = false;
					br.close();
					DealingSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;
			}

		}

	}

}
