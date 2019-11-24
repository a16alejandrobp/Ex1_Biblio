package baseDatos;
// Generated 23 nov. 2019 17:27:21 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private String dni;
	private String nome;
	private String correoe;
	private Set prestamos = new HashSet(0);

	public Usuario() {
	}

	public Usuario(String dni, String nome, String correoe, Set prestamos) {
		this.dni = dni;
		this.nome = nome;
		this.correoe = correoe;
		this.prestamos = prestamos;
	}
	
	public Usuario(int idUsuario, String dni, String nome, String correoe, Set prestamos) {
		this.idUsuario = idUsuario;
		this.dni = dni;
		this.nome = nome;
		this.correoe = correoe;
		this.prestamos = prestamos;
	}
	
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCorreoe() {
		return this.correoe;
	}

	public void setCorreoe(String correoe) {
		this.correoe = correoe;
	}

	public Set<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(Set prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", dni=" + dni + ", nome=" + nome + ", correoe=" + correoe + "]";
	}
	
}