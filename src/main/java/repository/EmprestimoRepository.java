package repository;

import java.util.List;

import javax.persistence.EntityManager;

import controller.JPAUtil;
import model.Emprestimo;
public class EmprestimoRepository {

    public void salvar(Emprestimo emprestimo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (emprestimo.getId() == null) {
                em.persist(emprestimo);
            } else {
                em.merge(emprestimo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Emprestimo buscarPorId(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Emprestimo.class, id);
        } finally {
            em.close();
        }
    }

    public List<Emprestimo> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("from Emprestimo", Emprestimo.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void remover(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Emprestimo emprestimo = em.find(Emprestimo.class, id);
            if (emprestimo != null) {
                em.remove(emprestimo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void devolverEmprestimo(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Emprestimo emprestimo = em.find(Emprestimo.class, id);
            if (emprestimo != null) {
                // Marcar o livro como disponível
                emprestimo.getLivro().setDisponivel(true);
                em.merge(emprestimo.getLivro());
                em.remove(emprestimo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Emprestimo> buscarPorUsuario(Integer usuarioId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Emprestimo e WHERE e.usuario.id = :usuarioId", Emprestimo.class)
                     .setParameter("usuarioId", usuarioId)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Emprestimo> buscarPorLivro(Integer livroId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Emprestimo e WHERE e.livro.id = :livroId", Emprestimo.class)
                     .setParameter("livroId", livroId)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}