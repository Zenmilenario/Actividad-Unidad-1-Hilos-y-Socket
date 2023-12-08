
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try {
			Scanner reader = new Scanner(System.in);

			// recibiendo direccion del servidor
			Socket socket = new Socket("localhost", 1234);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			System.out.println(
					"Hola bienvenido a McAlberto que desea pedir, escriba el numero de la opción que desee agregar a su pedido ? \n"
							+ "1. Hamburguesa\n" + "2. Patatas\n" + "3. Bebida\n" + "4. Postre\n" + "5. Acabar Pedido");

			while (true) {
				System.out.println(in.readInt());
				String toSend = reader.nextLine();

				out.writeUTF(toSend);
				if (toSend.equals("5")) {
					System.out.println("Cerrando conexion con el servidor: " + socket);
					socket.close();
					System.out.println("Conexion cerrada");
					break;
				}

				Integer recibido = in.readInt();
				System.out.println(recibido);
			}

			reader.close();
			in.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}