package com.felipe.dao;

import com.felipe.modelo.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AlunoDao {

    private EntityManager em;

    public AlunoDao(EntityManager em) {this.em = em;}

    public void cadastrar(Aluno aluno) {em.persist(aluno);}

    public void deletar(Aluno aluno){em.remove(aluno);}

    public void alterar(Aluno aluno) {em.merge(aluno);}

    public Aluno buscarPorNome(String nome){
        return em.find(Aluno.class, nome);
    }

    public List<Aluno> buscarTodos() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Aluno buscarPeloNome(String nome) throws NoResultException {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = ?1";
        return em.createQuery(jpql, Aluno.class).setParameter(1,nome).getSingleResult();
    }

    public boolean existeAlunoComNome(String nome) {
        String jpql = "SELECT COUNT(a) FROM Aluno a WHERE a.nome = :nome";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("nome", nome)
                .getSingleResult();
        return count > 0;
    }

}
