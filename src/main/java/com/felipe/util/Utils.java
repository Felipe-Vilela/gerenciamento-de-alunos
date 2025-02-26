package com.felipe.util;

import com.felipe.modelo.Aluno;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Utils {

    private static final Scanner sc = new Scanner(System.in);

    public static void listarTodosAlunos(List<Aluno> alunos){

        if (alunos.isEmpty()) throw new NoSuchElementException();

        System.out.println("\nExibindo todos os alunos: \n");

        for(Aluno aluno : alunos) {
            System.out.printf("Nome: %s \n", aluno.getNome());
            System.out.printf("Email: %s \n", aluno.getEmail());
            System.out.printf("Ra: %s \n", aluno.getRa());
            System.out.printf("Notas: %.2f - %.2f - %.2f \n", aluno.getNota1(), aluno.getNota2(), aluno.getNota3());

            BigDecimal valorSoma = aluno.getNota1().add(aluno.getNota2().add(aluno.getNota3()))  ;
            BigDecimal media = valorSoma.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

            System.out.printf("Media: %.2f \n", media);

            String status = (media.compareTo(new BigDecimal("4.0")) < 0)  ? "Reprovado"
                    : (media.compareTo(new BigDecimal("6.0")) < 0) ? "Recuperação" : "Aprovado";

            System.out.printf("Situação: %s \n\n", status);
        }
    }



    public static void listarDadosAluno(Aluno aluno){
        System.out.println("Dados do aluno:");
        System.out.printf("Nome: %s \n", aluno.getNome());
        System.out.printf("Email: %s \n", aluno.getEmail());
        System.out.printf("Ra: %s \n", aluno.getRa());
        System.out.printf("Notas: %.2f - %.2f - %.2f \n", aluno.getNota1(), aluno.getNota2(), aluno.getNota3());
    }

    private static BigDecimal digitarNota(String mensagem) {

        BigDecimal nota = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print(mensagem);
                nota = sc.nextBigDecimal();
                sc.nextLine();

                if (nota.compareTo(BigDecimal.ZERO) < 0 || nota.compareTo(BigDecimal.TEN) > 0) {
                    throw new IllegalArgumentException();
                }

                entradaValida = true;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Nota com formato inválido ou fora do intervalo. Deve ser entre 0 e 10.Digite novamente... ");
                sc.nextLine();
            }
        }
        return nota;
    }

    public static void digitarDadosAlunos(Aluno aluno){
        System.out.print("Digite o nome: ");
        aluno.setNome(sc.nextLine());
        System.out.print("Digite o RA: ");
        aluno.setRa(sc.nextLine());
        System.out.print("Digite o email: ");
        aluno.setEmail(sc.nextLine());
        aluno.setNota1(digitarNota("Digite a nota 1: "));
        aluno.setNota2(digitarNota("Digite a nota 2: "));
        aluno.setNota3(digitarNota("Digite a nota 3: "));
    }
}
