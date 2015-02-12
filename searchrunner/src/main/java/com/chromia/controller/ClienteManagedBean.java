package com.chromia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.springframework.dao.DataAccessException;

import com.chromia.model.Cliente;
import com.chromia.service.ICategoriaService;
import com.chromia.service.ICiudadService;
import com.chromia.service.IClienteService;

@ManagedBean(name = "clienteMBean")
@ViewScoped
public class ClienteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// -- Spring User Service is injected... --//
	@ManagedProperty(value = "#{CiudadService}")
	private ICiudadService ciudadService;
	@ManagedProperty(value = "#{CategoriaService}")
	private ICategoriaService categoriaService;
	@ManagedProperty(value = "#{ClienteService}")
	private IClienteService clienteService;
	private String nombre;
	private Integer ciudadId;
	private Integer categoriaId;
	private String direccion;
	private String telefono;
	private String celular;
	private String ruc;
	private String email;
	private String cedula;
	private String comentario;

	private List<Cliente> clientes;

	private Cliente selectedCliente;

	private List<Cliente> filteredClientes;

	private List<SelectItem> selectOneItemCliente;

	// -- TODO: Constructor --//
	public ClienteManagedBean() {

	}

	@PostConstruct
	public void inicializar() {
		onReset();
	}

	// -- TODO: Getter y setter del servicio --//
	public IClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(IClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public List<Cliente> getClientes() {
		clientes = getClienteService().getClientes();
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getSelectedCliente() {
		if (selectedCliente == null)
			selectedCliente = getClienteService().getClientes().get(0);
		return selectedCliente;
	}

	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}

	public List<Cliente> getFilteredClientes() {
		return filteredClientes;
	}

	public void setFilteredClientes(List<Cliente> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

	// -- TODO: Acciones de la vista --//

	public String getNombre() {
		return nombre;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoria) {
		this.categoriaId = categoria;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Integer ciudadId) {
		this.ciudadId = ciudadId;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public ICiudadService getCiudadService() {
		return ciudadService;
	}

	public void setCiudadService(ICiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	public List<SelectItem> getSelectOneItemCliente() {
		selectOneItemCliente = new ArrayList<SelectItem>();
		List<Cliente> clientes = getClienteService().getClientes();
		for (Cliente c : clientes) {
			SelectItem selectItem = new SelectItem(c.getId(), c.getNombre());
			selectOneItemCliente.add(selectItem);
		}
		return selectOneItemCliente;
	}

	// TODO: Action Listener

	public void onCreate(ActionEvent actionEvent) {
		if (verificarDuplicado(getNombre(), getRuc())) {
			try {
				Cliente clienteAdd = new Cliente();
				clienteAdd.setNombre(getNombre());
				clienteAdd.setRuc(getRuc());
				clienteAdd.setCedula(getRuc());
				clienteAdd.setDireccion(getDireccion());
				clienteAdd.setTelefono(getTelefono());
				clienteAdd.setCelular(getCelular());
				clienteAdd.setEmail(getEmail());
				clienteAdd.setComentario(getComentario());
				clienteAdd.setCiudad(getCiudadService().getCiudadById(
						getCiudadId()));
				clienteAdd.setCategoria(getCategoriaService().getCategoriaById(
						getCategoriaId()));

				if (getClienteService().addCliente(clienteAdd)) {
					onReset();
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Exito : ",
							"El Cliente " + clienteAdd.getNombre()
									+ " se guardo con éxito :)");
					FacesContext.getCurrentInstance().addMessage(null, message);
				} else {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Error : ",
							"El Cliente " + clienteAdd.getNombre()
									+ " no se pudo guardo :(");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} catch (DataAccessException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",
						"Error en el acceso a la base de datos, Detalle: "
								+ e.getMessage() + " x(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}finally{
				onReset();
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Error : ", "El Cliente " + getNombre()
							+ " ya se encuntra registrado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onEdit(ActionEvent actionEvent) {
		try {
			if (getClienteService().updateCliente(getSelectedCliente())) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El Cliente "
								+ this.selectedCliente.getNombre()
								+ " se modifico con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "El Cliente "
								+ this.selectedCliente.getNombre()
								+ " no se pudo modificar :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}finally{
			onReset();
		}
	}

	public void onDelete(ActionEvent actionEvent) {
		try {
			if (getClienteService().deleteCliente(getSelectedCliente())) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El cliente "
								+ getSelectedCliente().getNombre()
								+ " fue eliminado con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ", "El cliente "
								+ getSelectedCliente().getNombre()
								+ " no se pudo eliminar :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}finally{
			onReset();
		}
	}

	public void onReset() {
		nombre = null;
		direccion = null;
		telefono = null;
		celular = null;
		ruc = null;
		comentario = null;
		cedula = null;
		email = null;
		ciudadId = null;
		categoriaId = null;
		clientes = new ArrayList<Cliente>();
		clientes.addAll(getClienteService().getClientes());
		filteredClientes = new ArrayList<Cliente>();
		filteredClientes.addAll(clientes);
	}

	protected boolean verificarDuplicado(String nombre, String ruc) {
		for (Cliente c : getClientes()) {
			if (c.getNombre().equals(nombre) || c.getRuc().equals(ruc)) {
				return false;
			}
		}
		return true;
	}

}
