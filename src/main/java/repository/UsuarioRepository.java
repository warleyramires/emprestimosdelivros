package repository;

import javax.persistence.EntityManager;

import java.util.List;

import controller.JPAUtil;
import model.Usuario;

public class UsuarioRepository {

	public void salvarUsuario(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			if (usuario.getId() != null) {
				em.persist(usuario);
			} else {
				em.merge(usuario);
			}
			em.getTransaction().commit();
		} finally {
			em.clear();
		}
	}

	public Usuario buscarPorId(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}
	}
	
	public List<Usuario> listarUsuarios(){
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return em.createQuery("from Usuario", Usuario.class).getResultList();
		}finally {
			em.close();
		}
		
	}
	
	public void removeUsuario(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			Usuario user = em.find(Usuario.class, id);
			if(user != null) {
				em.remove(user);	
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
}
