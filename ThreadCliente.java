
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadCliente implements Runnable {

	Socket socket;
	DataInputStream in;
	DataOutputStream out;

	private List<String> pedido = new ArrayList<>();
	private Integer precioPedido = 0;

	public ThreadCliente(Socket socket, DataInputStream in2, DataOutputStream out2) {
		this.socket = socket;
		this.in = in2;
		this.out = out2;
	}

	@Override
	public void run() {
		Integer recibido;

		while (true) {
			try {
				menuBienvenida(out);

				/*
				 * System.out.println(
				 * "Hola bienvenido a McAlberto que desea pedir, escriba el numero de la opción que desee agregar a su pedido ? \n"
				 * + "1. Hamburguesa\n" + "2. Patatas\n" + "3. Bebida\n" + "4. Postre\n" +
				 * "5. Acabar Pedido");
				 */

				recibido = in.readInt();

				switch (recibido) {
				case 1:
					System.out.println("Hamburguesa agregada 10€");
					pedido.add("Hamburguesa");
					precioPedido += 10;
					break;
				case 2:
					out.writeUTF("Patatas agregas 4€");
					pedido.add("Patatas");
					precioPedido += 4;
					break;
				case 3:
					out.writeUTF("Bebida agregada 3€");
					pedido.add("Bebida");
					precioPedido += 3;
					break;
				case 4:
					out.writeUTF("Postre agregado 2€");
					pedido.add("Postre");
					precioPedido += 2;
					break;
				case 5:
					out.writeUTF("Imprimiento pedido ");
					out.writeUTF(pedido.toString().strip());
					out.writeUTF("Total: " + precioPedido.toString() + " €");
					System.out.println("Cliente " + this.socket + " ha salido!");
					System.out.println("Cerrando Conexion");
					this.socket.close();
					System.out.println("Conexion Cerrada");
					break;
				default:
					out.writeUTF("Por favor escriba una opcion correcta");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			try {
				this.out.close();
				this.in.close();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}

	}

	private void menuBienvenida(DataOutputStream out2) throws IOException {
		out2.writeUTF(
				"Hola bienvenido a McAlberto que desea pedir, escriba el numero de la opción que desee agregar a su pedido ? \n"
						+ "1. Hamburguesa\n" + "2. Patatas\n" + "3. Bebida\n" + "4. Postre\n" + "5. Acabar Pedido");
	}

}