package br.com.alura.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.alura.livraria.modelo.Usuario;

public class UsuarioDao {

	public boolean existeUsuario(Usuario usuario) {

		EntityManager entityManager = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = entityManager.createQuery(
				"select usuario from Usuario usuario " + "where usuario.email = :pEmail and usuario.senha = :pSenha",
				Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());

		try {
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException noResultException) {
			return false;
		}

		entityManager.close();

		return true;
	}

}
