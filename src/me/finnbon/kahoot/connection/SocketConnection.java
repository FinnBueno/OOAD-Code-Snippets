package me.finnbon.kahoot.connection;

/**
 * @author Finn Bon
 */
public class SocketConnection {

	private final String host;
	private final int port;

	public SocketConnection(String host, int port) {
		this.host = host;
		this.port = port;
		// TODO: Setup socket connection
	}

	protected void send(byte[] packet) {
		// TODO: Send packet of data to socket
	}
}
