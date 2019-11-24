package baseDatos;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Transaction;

public class OperacionsPrestamo {
	
	public static Libro consultaLibro(int idLibro) {
		try {
			TypedQuery<Libro> query = OperacionsUsuario.getSession().createQuery("select a from Libro a where a.idLibro=?1",Libro.class);
			query.setParameter(1, idLibro);
			Libro libro = query.getSingleResult();
			return libro;
		}catch(javax.persistence.NoResultException ex) {
			return null;
		}
	}
	
	public static Prestamo consultaPrestamo(int idPrestamo) {
		try {
			TypedQuery<Prestamo> query = OperacionsUsuario.getSession().createQuery("select a from Prestamo a where a.idPrestamo=?1",Prestamo.class);
			query.setParameter(1, idPrestamo);
			Prestamo prestamo = query.getSingleResult();
			return prestamo;
    	}catch(javax.persistence.NoResultException ex) {
    		return null;
    	}
	}
	
	public static boolean isDevolto(int idLibro) {
		boolean devolto = true;
		Libro libro = consultaLibro(idLibro);
		Set<Prestamo> prestamos = libro.getPrestamos();
		for(Prestamo prestamo : prestamos) {
			if(!prestamo.getDevolto()) {
				devolto = false;
			}
		}
		return devolto;
	}
	
	public static int engadirPrestamo(int idLibro, int idUsuario) {
		if(consultaLibro(idLibro)==null) {
			return 3;
		}else if(!isDevolto(idLibro)) {
			return 2;
		}else if(OperacionsUsuario.consultaUsuario(idUsuario)==null) {
			return 4;
		}else {
			Transaction tx = OperacionsUsuario.getSession().beginTransaction();
			OperacionsUsuario.getSession().save(new Prestamo(consultaLibro(idLibro),OperacionsUsuario.consultaUsuario(idUsuario),new Date(),false));
			tx.commit();
			return 1;
		}
	}
	
	public static int realizarDevolucion(int idPrestamo) {
		Prestamo prestamo = consultaPrestamo(idPrestamo);
		if(prestamo.getDevolto()) {
			return 2;
		}else {
			Transaction tx = OperacionsUsuario.getSession().beginTransaction();
			prestamo.setDevolto(true);
			OperacionsUsuario.getSession().update(prestamo);
			tx.commit();
			return 1;
		}
	}
	
	public static List<Prestamo> listadoPrestamosActivos(){
		TypedQuery<Prestamo> query = OperacionsUsuario.getSession().createQuery("select a from Prestamo a where a.devolto=?1",Prestamo.class);
		query.setParameter(1, false);
		List<Prestamo> prestamos = query.getResultList();
		return prestamos;
	}
}
