package br.com.alura.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.alura.livraria.dao.UsuarioDao;
import br.com.alura.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public String efetuarLogin() {
		boolean existe = new UsuarioDao().existeUsuario(this.usuario);

		if (existe == true) {
			FacesContext faceContext = FacesContext.getCurrentInstance();
			faceContext.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
