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
	
	public static final String CHAVE_LOGIN = "usuario";

	public LoginMB() {
		loginMB = this;
	}
	
	
	public static LoginMB loginMB;
	private Usuario usuario;

	private String email;
	private String password;
	public boolean defaultUser;
	public boolean admin;
	public String mensagem = "";

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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String login() {
		UsuarioBO bo = new UsuarioBO();

		Usuario user = bo.autenticar(email, password);
		
		if(user != null){
			setUsuarioMB(user);
			putUsuarioToSessao(user);
			return "/pages/protected/index.xhtml";
		}

		mensagem = "Verifique seu e-mail/ senha";
		
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
	
	public boolean isDefaultUser() {
		this.defaultUser = getUsuario().isUser();
		return this.defaultUser;
	}

	public void setDefaultUser(boolean isDefaultUser) {
		this.defaultUser = isDefaultUser;
	}

	public boolean isAdmin() {
		this.admin = getUsuario().isAdmin();
		return this.admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

	private void putUsuarioToSessao(Usuario usuario){
		addToSessao(CHAVE_LOGIN, usuario);
	}
	
	public Usuario getUsuarioFromSessao(){
		return (Usuario) getFromSessao(CHAVE_LOGIN); 
	}
	
	public static Usuario getUsuarioLogado (){
		return loginMB.getUsuarioFromSessao(); 
	}
}