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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;

import com.chromia.model.Proveedor;
import com.chromia.service.ICiudadService;
import com.chromia.service.IPaisService;
import com.chromia.service.IProveedorService;

@ManagedBean(name = "proveedorMBean")
@ViewScoped
public class ProveedorManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// -- Spring User Service is injected... --//
	@ManagedProperty(value = "#{CiudadService}")
	private ICiudadService ciudadService;
	@ManagedProperty(value = "#{PaisService}")
	private IPaisService paisService;
	@ManagedProperty(value = "#{ProveedorService}")
	private IProveedorService proveedorService;
	private String nombre;
	private Integer ciudadId;
	private Integer paisId;
	private String direccion;
	private String telefono;
	private String celular;
	private String ruc;
	private String email;
	@Value("1")
	private Integer iva;

	private List<Proveedor> proveedores;

	private Proveedor selectedProveedor;

	private List<Proveedor> filteredProveedores;

	private List<SelectItem> selectOneItemProveedor;

	// -- TODO: Constructor --//
	public ProveedorManagedBean() {

	}

	@PostConstruct
	public void inicializar() {
		onReset();

	}

	// -- TODO: Getter y setter del servicio --//
	public IProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(IProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public List<Proveedor> getProveedores() {
		proveedores = getProveedorService().getProveedores();
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Proveedor getSelectedProveedor() {
		if (selectedProveedor == null)
			selectedProveedor = getProveedorService().getProveedores().get(0);
		return selectedProveedor;
	}

	public void setSelectedProveedor(Proveedor selectedProveedor) {
		this.selectedProveedor = selectedProveedor;
	}

	public List<Proveedor> getFilteredProveedores() {
		return filteredProveedores;
	}

	public void setFilteredProveedores(List<Proveedor> filteredProveedores) {
		this.filteredProveedores = filteredProveedores;
	}

	// -- TODO: Acciones de la vista --//

	public IPaisService getPaisService() {
		return paisService;
	}

	public void setPaisService(IPaisService paisService) {
		this.paisService = paisService;
	}

	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public String getNombre() {
		return nombre;
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

	public ICiudadService getCiudadService() {
		return ciudadService;
	}

	public void setCiudadService(ICiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	public List<SelectItem> getSelectOneItemProveedor() {
		selectOneItemProveedor = new ArrayList<SelectItem>();
		List<Proveedor> proveedores = getProveedorService().getProveedores();
		for (Proveedor p : proveedores) {
			SelectItem selectItem = new SelectItem(p.getId(), p.getNombre());
			selectOneItemProveedor.add(selectItem);
		}
		return selectOneItemProveedor;
	}

	// TODO: Action Listener

	public void onCreate(ActionEvent actionEvent) {
		try {
			Proveedor proveedorAdd = new Proveedor();
			proveedorAdd.setNombre(getNombre());
			proveedorAdd.setRuc(getRuc());
			proveedorAdd.setDireccion(getDireccion());
			proveedorAdd.setTelefono(getTelefono());
			proveedorAdd.setCelular(getCelular());
			proveedorAdd.setEmail(getEmail());
			proveedorAdd.setIva(getIva());
			proveedorAdd.setCiudad(getCiudadService().getCiudadById(
					getCiudadId()));
			proveedorAdd.setPais(getPaisService().getPaisById(getPaisId()));

			if (getProveedorService().addProveedor(proveedorAdd)) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El Proveedor "
								+ proveedorAdd.getNombre()
								+ " se guardo con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ",
						"El Proveedor " + proveedorAdd.getNombre()
								+ " no se pudo guardo :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión: ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void onEdit(ActionEvent actionEvent) {
		try {

			if (getProveedorService().updateProveedor(getSelectedProveedor())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El Proveedor "
								+ this.selectedProveedor.getNombre()
								+ " se modifico con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ",
						"El Proveedor " + this.selectedProveedor.getNombre()
								+ " no se pudo modificar :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onDelete(ActionEvent actionEvent) {
		try {

			if (getProveedorService().deleteProveedor(getSelectedProveedor())) {
				onReset();
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Exito : ", "El Proveedor "
								+ getSelectedProveedor().getNombre()
								+ " fue eliminado con éxito :)");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error : ",
						"El Proveedor " + getSelectedProveedor().getNombre()
								+ " no se pudo eliminar :(");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error de Conexión : ",
					"Error en el acceso a la base de datos, Detalle: "
							+ e.getMessage() + " x(");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onReset() {
		proveedores = new ArrayList<Proveedor>();
		proveedores.addAll(getProveedorService().getProveedores());
		filteredProveedores = new ArrayList<Proveedor>();
		filteredProveedores.addAll(proveedores);
	}

}
