package com.model.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.model.Role;
import com.util.UtilsData;

@Entity
@Inheritance
@DiscriminatorColumn(name="tp_usuario")
@Table(name="TBL_Usuario")
@NamedQueries({
@NamedQuery(name = "Usuario.findUserByEmail", query = "select u from Usuario u where u.email = :email"),
@NamedQuery(name = "Usuario.deleteUserById", query = "delete from Usuario u where u.id = :id")})
public class Usuario 
					implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_EMAIL = "Usuario.findUserByEmail";

	public Usuario() {
	}
	public Usuario(Role role) {
		this.role = role;
	}

	public Usuario(String email, String password, String nome, Role role) {
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	@Column(unique = true)
	public String email;
	public String password;
	@Enumerated(EnumType.STRING)
	public Role role;
	
	@Enumerated(EnumType.STRING)
	public Genero genero;
	
	public Integer idade;
	public String nome;
	public String sobrenome;
	private Date dataNascimento;
	private Integer telefone;
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

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
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
		this.setIdade(UtilsData.getIdadePelaDataDeNascimento(dataNascimento));
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public boolean isAdmin() {
		return Role.ADMINISTRADOR.equals(role);
	}

	public boolean isUser() {
		return Role.OPERADOR.equals(role) || Role.CLIENTE.equals(role);
	}

	@Override
	public int hashCode() {
		return getId().intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario user = (Usuario) obj;
			return user.getId() == id;
		}

		return false;
	}
}