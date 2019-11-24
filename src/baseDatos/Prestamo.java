package baseDatos;
// Generated 23 nov. 2019 17:27:21 by Hibernate Tools 5.2.12.Final

import java.util.Date;

/**
 * Prestamo generated by hbm2java
 */
public class Prestamo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPrestamo;
	private Libro libro;
	private Usuario usuario;
	private Date data;
	private Boolean devolto;

	public Prestamo() {
	}

	public Prestamo(Libro libro, Usuario usuario, Date data, Boolean devolto) {
		this.libro = libro;
		this.usuario = usuario;
		this.data = data;
		this.devolto = devolto;
	}

	public Integer getIdPrestamo() {
		return this.idPrestamo;
	}

	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getDevolto() {
		return this.devolto;
	}

	public void setDevolto(Boolean devolto) {
		this.devolto = devolto;
	}

	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", libro=" + libro + ", usuario=" + usuario + ", data=" + data
				+ ", devolto=" + devolto + "]";
	}
	

}
