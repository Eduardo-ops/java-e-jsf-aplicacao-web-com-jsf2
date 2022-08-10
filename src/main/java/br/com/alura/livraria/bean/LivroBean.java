package br.com.alura.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer livroId;
	private Integer autorId;

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
//			throw new RuntimeException("Livro deve ter pelo menos um Autor");

			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Cada livro deve conter pelo menos um Autor"));
		}

		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	public void alterar(Livro livro) {
		System.out.println("Alterando livro...");
		this.livro = livro;
	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro...");
		new DAO<Livro>(Livro.class).remove(livro);
	}

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent compoment, Object object) throws ValidatorException {
		String valor = object.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O primeiro caractere de ISBN deve ser do tipo número 1"));
		}
	}

	public String formAutor() {
		System.out.println("Chamando o formulário de cadastro de autores");
		return "autor?faces-redirect=true";
	}

	public void carregaPelaId() {
		if (this.livro == null) {
			this.livro = new Livro();
		}
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.autorId);
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
