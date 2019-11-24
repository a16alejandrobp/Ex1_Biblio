package baseDatos;

import org.hibernate.*;
import javax.persistence.TypedQuery;
import java.util.*;

public class OperacionsUsuario {
	private static SessionFactory sesion = HibernateUtil.getSessionFactory();
	private static Session session;
	
	public static Session getSession() {
		return session;
	}
	
	public static void abrirConexion() {
		session = sesion.openSession();
	}
	
	public static void cerrarConexion() {
		session.close();
	}
	
	public static void engadirUsuario(Usuario usuario) {
			Transaction tx = session.beginTransaction();
			session.save(usuario);
			tx.commit();
	}
	
	public static int modificarUsuario(Usuario usuario) {
		if(consultaUsuario(usuario.getIdUsuario())==null) {
			return 2;
		}else {
			Transaction tx = session.beginTransaction();
			Usuario u = consultaUsuario(usuario.getIdUsuario());
			u.setDni(usuario.getDni());
			u.setNome(usuario.getNome());
			u.setCorreoe(usuario.getCorreoe());
			session.update(u);
			tx.commit();
			return 1;
		}
	}
	
	public static int borrarUsuario(int idUsuario) {
		if(consultaUsuario(idUsuario)==null) {
			return 2;
		}else if(!consultaUsuario(idUsuario).getPrestamos().isEmpty()){
			return 3;
		}else {
			Transaction tx = session.beginTransaction();
			Usuario u = consultaUsuario(idUsuario);
			session.delete(u);
			tx.commit();
			return 1;
		}
	}
	
	public static Usuario consultaUsuario(int idUsuario) {
		TypedQuery<Usuario> query = session.createQuery("select a from Usuario a where a.idUsuario=?1",Usuario.class);
        query.setParameter(1, idUsuario);
        try {
        	Usuario usuario = query.getSingleResult();
        	return usuario;
        }catch(javax.persistence.NoResultException ex) {
        	return null;
        }
	}
	
	public static List<Usuario> listadoUsuarios(){
		TypedQuery<Usuario> query = session.createQuery("select a from Usuario a",Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
}
