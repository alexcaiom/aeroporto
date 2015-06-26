package com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bo.UsuarioBO;
import com.model.usuario.Usuario;

@RequestScoped
@ManagedBean
public class LoginMB extends AbstractMB {
	private Usuario usuario;

	private String email;
	private String password;
	public Boolean isDefaultUser;
	public Boolean isAdmin;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		UsuarioBO bo = new UsuarioBO();

		Usuario user = bo.autenticar(email, password);
		
		if(user != null){
			setUsuarioMB(user);
			putUsuarioToSessao(user);
			return "/pages/protected/index.xhtml";
		}

		displayErrorMessageToUser("Verifique seu e-mail/ senha");
		
		return null;
	}
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "/pages/public/login.xhtml";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}


	public void setUsuarioMB(Usuario usuario) {
		this.usuario = usuario;
	}	
	
	public Usuario getUsuario() {
		if(this.usuario == null){
			setUsuarioMB(getUsuarioFromSessao());
		}
		return this.usuario;
	}
	
	public Boolean getIsDefaultUser() {
		isDefaultUser = getUsuario().isUser();
		return isDefaultUser;
	}

	public void setIsDefaultUser(Boolean isDefaultUser) {
		this.isDefaultUser = isDefaultUser;
	}

	public Boolean getIsAdmin() {
		isAdmin = getUsuario().isAdmin();
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	private void putUsuarioToSessao(Usuario usuario){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		request.getSession().setAttribute("usuario", usuario);
	}
	
	public static Usuario getUsuarioFromSessao(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		if(request.getSession().getAttribute("usuario") != null){
			return (Usuario) request.getSession().getAttribute("usuario");
		}
		return null;
	}
}