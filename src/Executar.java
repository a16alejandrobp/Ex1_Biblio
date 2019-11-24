import baseDatos.*;
import java.io.*;
import java.util.*;

public class Executar {

	public static void main(String[] args) {
		try {
			OperacionsUsuario.abrirConexion();
			int idUsuario, idLibro, idPrestamo, resultado;
			String dni, nome, correoe;
			Scanner sc = new Scanner(System.in);
			boolean running = true;
			System.out.println("###################################################"); 
			System.out.println("#                                                 #"); 
			System.out.println("#                                                 #"); 
			System.out.println("#                                                 #"); 
			System.out.println("#               Xestión Biblioteca                #"); 
			System.out.println("#                                                 #"); 
			System.out.println("#                                                 #"); 
			System.out.println("#                                                 #"); 
			System.out.println("###################################################");
			while(running) {
				System.out.println("");
				System.out.println("[1] Información de usuario");
				System.out.println("[2] Engadir usuario");
				System.out.println("[3] Modificar alumno");
				System.out.println("[4] Borrar alumno");
				System.out.println("[5] Listado de usuarios");
				System.out.println("[6] Préstamos non devoltos");
				System.out.println("[7] Realizar préstamo");
				System.out.println("[8] Realizar devolución");
				System.out.println("[9] Saír");
				System.out.println("");
				System.out.print("Escolle unha opción: ");
				int ans = sc.nextInt();
				System.out.println("");
				switch(ans) {
					case 1:
						System.out.println("Información de Usuario: ");
						System.out.println("--------------------------------------");
						System.out.print("Introduce ID de Usuario: ");
						idUsuario=sc.nextInt();
						Usuario usuario = OperacionsUsuario.consultaUsuario(idUsuario);
						if(usuario != null) {
							System.out.println("");
							System.out.println("Información do Usuario con ID "+usuario.getIdUsuario()+":");
							System.out.println("");
							System.out.println("Nome: "+usuario.getNome());
							System.out.println("DNI: "+usuario.getDni());
							System.out.println("Correo Electrónico: "+usuario.getCorreoe());
							if(usuario.getPrestamos().isEmpty()) {
								System.out.println("Non hai préstamos asociados a este usuario");
							}else {
								System.out.println("Préstamos asociados:");
								for(Prestamo p : usuario.getPrestamos()) {
									System.out.println("\tID: "+p.getIdPrestamo()+", Título do Libro: "+p.getLibro().getTitulo()+", Nome do Usuario: "+p.getUsuario().getNome()+", Data: "+p.getData()+", Devolto: "+(p.getDevolto() ? "Sí" : "Non"));
								}
							}
						}else {
							System.out.println("ERRO: Ese usuario non existe");
						}
						break;
					case 2:
						System.out.println("Engadir Usuario: ");
						System.out.println("--------------------------------------");
						System.out.print("Introduce Nome: ");
						nome = sc.next();
						System.out.print("Introduce DNI: ");
						dni = sc.next();
						System.out.print("Introduce Correo Electrónico: ");
						correoe = sc.next();
						OperacionsUsuario.engadirUsuario(new Usuario(dni, nome, correoe, null));
						System.out.println("Engadiuse o usuario correctamente");
						break;
					case 3:
						System.out.println("Modificar Usuario: ");
						System.out.println("--------------------------------------");
						System.out.print("Introduce ID de Usuario: ");
						idUsuario=sc.nextInt();
						System.out.print("Introduce o novo Nome: ");
						nome = sc.next();
						System.out.print("Introduce o novo DNI: ");
						dni = sc.next();
						System.out.print("Introduce o novo Correo Electrónico: ");
						correoe = sc.next();
						resultado = OperacionsUsuario.modificarUsuario(new Usuario(idUsuario ,dni ,nome ,correoe ,null));
						if(resultado == 1) {
							System.out.println("Modificouse o usuario correctamente");
						}else {
							System.out.println("ERRO: Ese usuario non existe");
						}
						break;
					case 4:
						System.out.println("Borrar Usuario: ");
						System.out.println("--------------------------------------");
						System.out.print("Introduce ID de Usuario: ");
						idUsuario=sc.nextInt();
						resultado = OperacionsUsuario.borrarUsuario(idUsuario);
						if(resultado == 1) {
							System.out.println("Eliminouse o usuario correctamente");
						}else if(resultado == 2) {
							System.out.println("ERRO: Ese usuario non existe");
						}else {
							System.out.println("ERRO: Ese usuario ten préstamos asociados");							
						}
						break;
					case 5:
						System.out.println("Listado de Usuarios: ");
						System.out.println("--------------------------------------");
						OperacionsUsuario.listadoUsuarios().forEach(System.out::println);
						break;
					case 6:
						System.out.println("Listado de Préstamos non devoltos: ");
						System.out.println("--------------------------------------");
						OperacionsPrestamo.listadoPrestamosActivos().forEach(System.out::println);
						break;
					case 7:
						System.out.println("Realizar Préstamo: ");
						System.out.println("--------------------------------------");
						System.out.print("Introduce ID de Libro: ");
						idLibro = sc.nextInt();
						System.out.print("Introduce ID de Usuario: ");
						idUsuario = sc.nextInt();
						resultado = OperacionsPrestamo.engadirPrestamo(idLibro, idUsuario);
						if(resultado == 1) {
							System.out.println("Engadiuse o préstamo correctamente");
						}else if(resultado == 2) {
							System.out.println("ERRO: O libro xa está prestado");
						}else if(resultado == 3) {
							System.out.println("ERRO: O libro non existe");
						}else {
							System.out.println("ERRO: O usuario non existe");
						}
						break;
					case 8:
						System.out.println("Realizar Devolución: ");
						System.out.println("--------------------------------------");
						System.out.print("Introduce ID de Préstamo: ");
						idPrestamo = sc.nextInt();
						resultado = OperacionsPrestamo.realizarDevolucion(idPrestamo);
						if(resultado == 1) {
							System.out.println("Devolveuse o libro correctamente");
						}else {
							System.out.println("ERRO: O libro xa está devolto");							
						}
						break;
					case 9:
						System.out.println("Desconectouse correctamente");
						running = false;
						break;
				}
			}
			OperacionsUsuario.cerrarConexion();
		}catch(Exception ex) {
			System.out.println("Erro: "+ex.getMessage());
			ex.printStackTrace();
		}
	}

}
