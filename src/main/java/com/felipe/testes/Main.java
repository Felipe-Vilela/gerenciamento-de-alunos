package com.felipe.testes;

import com.felipe.dao.AlunoDao;
import com.felipe.modelo.Aluno;
import com.felipe.util.JPAUtil;
import com.felipe.util.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.*;
import java.util.logging.Level;

public class Main {
    private static final Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        int opcao;
        do {
            System.out.println("** GERENCIAMENTO DE ALUNOS **");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (com status aprovação)");
            System.out.println("6 - FIM");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> excluirAlunos();
                case 3 -> alterarAluno();
                case 4 -> buscarAlunoPorNome();
                case 5 -> listarAlunos();
            }
        } while (opcao != 6);


    }

    public static void cadastrarAluno(){
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao(em);

        System.out.println("CADASTRO DE ALUNOS");

        Aluno aluno = new Aluno();
        Utils.digitarDadosAlunos(aluno);

        em.getTransaction().begin();
        dao.cadastrar(aluno);
        em.getTransaction().commit();
        em.close();

        System.out.println("Aluno cadastrado!");
    }


    public static void excluirAlunos(){
        EntityManager em = JPAUtil.getEntityManager();

        try (em) {
            AlunoDao dao = new AlunoDao(em);
            System.out.println("EXCLUIR ALUNO:");
            System.out.println("Digite o nome:");
            String nome = sc.nextLine();

            em.getTransaction().begin();
            Aluno aluno = dao.buscarPeloNome(nome);
            dao.deletar(aluno);
            em.getTransaction().commit();
            em.close();

            System.out.println("Aluno removido com sucesso!");
        } catch (NoResultException e) {
            System.out.println("\n Aluno não encontado!");
        }
    }

    public static void alterarAluno(){
        EntityManager em = JPAUtil.getEntityManager();

        try (em) {
            AlunoDao dao = new AlunoDao(em);
            System.out.println("ALTERAR ALUNO:");
            System.out.println("Digite o nome:");
            String nome = sc.nextLine();
            Aluno aluno = dao.buscarPeloNome(nome);
            Utils.listarDadosAluno(aluno);
            System.out.println("NOVOS DADOS:");
            Utils.digitarDadosAlunos(aluno);

            em.getTransaction().begin();
            dao.alterar(aluno);
            em.getTransaction().commit();
            em.close();

            System.out.println("Aluno Alterado com sucesso!");

        } catch (NoResultException e) {
            System.out.println("Aluno não encontado!");
        }

    }

    public static void buscarAlunoPorNome(){
        EntityManager em = JPAUtil.getEntityManager();

        try (em) {
            AlunoDao dao = new AlunoDao(em);
            System.out.println("CONSULTAR ALUNO:");
            System.out.println("Digite o nome:");
            String nome = sc.nextLine();

            Aluno aluno = dao.buscarPeloNome(nome);

            Utils.listarDadosAluno(aluno);

        } catch (NoResultException e) {
            System.out.println("\n Aluno não encontado!");
        }
    }

    public static void listarAlunos(){
        EntityManager em = JPAUtil.getEntityManager();

        try (em) {
            AlunoDao dao = new AlunoDao(em);
            List<Aluno> alunos = dao.buscarTodos();
            Utils.listarTodosAlunos(alunos);
        } catch (NoSuchElementException e) {
            System.out.println("Não existem alunos cadastrados");
        }

    }
}