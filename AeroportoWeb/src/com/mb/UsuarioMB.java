package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bo.UsuarioBO;
import com.model.Role;
import com.model.usuario.Cliente;
import com.model.usuario.EstadoCivil;
import com.model.usuario.Usuario;
import com.sun.faces.context.flash.ELFlash;

@SessionScoped
@ManagedBean(name="usuarioMB")
public class UsuarioMB extends AbstractMB implements Serializable {
	public static final String INJECTION_NAME = "#{usuarioMB}";
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> allUsuarios;
	private UsuarioBO usuarioBO;
	private String SELECTED_USUARIO = "selectedCliente";
	private List<Role> tiposUsuario;
	private List<EstadoCivil> estadosCivis;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario user) {
		this.usuario = user;
	}

	public List<Usuario> getAllUsuarios() {
		if(allUsuarios == null){
			allUsuarios = new ArrayList<Usuario>();
		}
		
		atualizarUsuarios();
		
		return allUsuarios;
	}

	private void atualizarUsuarios() {
		List<Usuario> listaUsuariosBD = new UsuarioBO().listar();
		if(listaUsuariosBD != null){
			allUsuarios = listaUsuariosBD; 
		}
	}
	
	public void detalharUsuario(){
		if (usuario == null) {
			usuario = (Cliente) ELFlash.getFlash().get(SELECTED_USUARIO);
			usuario = getUsuarioBO().pesquisar(usuario.getId());
		}
	}

	public void setAllUsuarios(List<Usuario> allUsuarios) {
		this.allUsuarios = allUsuarios;
	}
	
	public void resetUsuario(){
		this.usuario = new Usuario();
	}
	
	public void deleteUsuario(){
		try{
			getUsuarioBO().excluir(usuario);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			atualizarUsuarios();
			resetUsuario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	public void updateUsuario(){
		try{
			getUsuarioBO().atualizar(usuario);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			atualizarUsuarios();
			resetUsuario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void create(){
		try{
			getUsuarioBO().inserir(usuario);
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			atualizarUsuarios();
			resetUsuario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	private UsuarioBO getUsuarioBO(){
		if (usuarioBO == null){
			usuarioBO = new UsuarioBO();
		}
		return usuarioBO;
	}
	
	public List<Role> getTiposUsuario(){
		if(tiposUsuario == null){
			tiposUsuario = new ArrayList<Role>();
			tiposUsuario = Arrays.asList(Role.values());
		}
		return tiposUsuario;
	}

	public List<EstadoCivil> getEstadosCivis() {
		if(estadosCivis == null){
			estadosCivis = new ArrayList<EstadoCivil>();
			estadosCivis = Arrays.asList(EstadoCivil.values());
		}
		return estadosCivis;
	}

	public void setEstadosCivis(List<EstadoCivil> estadosCivis) {
		this.estadosCivis = estadosCivis;
	}
}