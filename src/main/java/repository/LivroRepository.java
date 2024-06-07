package repository;

import java.util.List;

import javax.persistence.EntityManager;

import controller.JPAUtil;
import model.Livro;

public class LivroRepository {

	public void salvar(Livro livro) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			if(livro.getId() == null) {
				em.persist(livro);
			}else {
				em.merge(livro);
			}
			em.getTransaction().commit();
			
		}finally {
			em.close();
		}
	}
	
	public Livro buscarPorId(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			 return em.find(Livro.class, id);
		} finally {
			em.close();
		}
	}
	
	public List<Livro> buscarTodos(){
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return em.createQuery("from Livro", Livro.class).getResultList();
		}finally {
			em.close();
		}
	
	}
	
	public void removeLivro(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			Livro livro = em.find(Livro.class, id);
			if(livro != null) {
				em.remove(livro);
			}
			em.getTransaction().commit();
		
		}finally {
			em.close();
		}
	}
}
