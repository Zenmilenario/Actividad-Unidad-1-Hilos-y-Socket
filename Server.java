
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	/*
	 * Este es el servidor que se encargará de crear un hilo de comunicación con el
	 * cliente donde se ejecutará toda la conversación
	 */

	public static void main(String[] args) throws IOException {

		// Servidor iniciado en el puerto 1234
		ServerSocket serverSocket = new ServerSocket(1234);
		while (true) {

			Socket socket = null;

			try {
				// Cuando un cliente se conecte al servidor y este lo acepte se creará un hilo
				// con las funciones de este
				socket = serverSocket.accept();

				// recogienco entrada y salida del socket
				System.out.println("Un nuevo cliente se ha conectado: " + socket);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				// Creando hilo con el cliente
				ThreadCliente threadNew = new ThreadCliente(socket, in, out);
				threadNew.run();

			} catch (Exception e) {
				socket.close();
				serverSocket.close();

				e.printStackTrace();
			}

		}
	}
}