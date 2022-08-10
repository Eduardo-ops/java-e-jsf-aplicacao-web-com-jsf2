package br.com.alura.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.util.ForwardView;
import br.com.alura.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

//	public String gravar() {
//		System.out.println("Gravando autor " + this.autor.getNome());
//
//		new DAO<Autor>(Autor.class).adiciona(this.autor);
//
//		this.autor = new Autor();
//
//		return "livro?faces-redirect=true";
//	}

	public ForwardView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);

		this.autor = new Autor();

		return new ForwardView("livro");
	}

	public void carregarAutorPeloId() {
		if (this.autor == null) {
			this.autor = new Autor();
		}
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
	}

	public RedirectView voltarMenuLivro() {
		return new RedirectView("livro");
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
