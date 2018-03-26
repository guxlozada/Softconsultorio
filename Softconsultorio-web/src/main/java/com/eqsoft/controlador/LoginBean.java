package com.eqsoft.controlador;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {

	/** Id por JVM. */
	private static final long serialVersionUID = -5516051153580776334L;
	private String usuario;
	private String clave;

	public String autenticar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Ingreso Correcto!", ""));
		return "inicio";
	}

	public String getClave() {
		return this.clave;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
