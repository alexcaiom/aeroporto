package com.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.model.usuario.Usuario;

@SessionScoped
@ManagedBean(name="userMB")
public class UserMB implements Serializable {
	public static final String INJECTION_NAME = "#{userMB}";
	private static final long serialVersionUID = 1L;

	private Usuario user;

	public boolean isAdmin() {
		return user.isAdmin();
	}

	public boolean isDefaultUser() {
		return user.isUser();
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "/pages/public/login.xhtml";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}