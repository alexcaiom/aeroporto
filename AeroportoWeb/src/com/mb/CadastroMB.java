package com.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bo.UsuarioBO;
import com.model.usuario.Usuario;
import com.util.Constantes;

@RequestScoped
@ManagedBean
public class CadastroMB extends AbstractMB {
	@ManagedProperty(value = UserMB.INJECTION_NAME)
	private UserMB userMB;

	private String nome;
	private String sobrenome;
	private String password;
	private String email;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String cadastrar() {
		UsuarioBO userBO = new UsuarioBO();
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setEmail(email);
		user.setPassword(password);
		user.setSobrenome(sobrenome);
		
		userBO.inserir(user);
		if(user.getId() != null){
			userMB.setUser(user);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().setAttribute(Constantes.USUARIO_SESSAO, user);
			return "/pages/protected/index.xhtml";
		}
		displayErrorMessageToUser("Ocorreu um erro. Tente novamente mais tarde.");
		return null;
	}
}