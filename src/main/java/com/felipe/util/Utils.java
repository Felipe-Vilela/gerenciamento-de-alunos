package com.felipe.util;

import com.felipe.modelo.Aluno;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Utils {

    private static final Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    public static void listarTodosAlunos(List<Aluno> alunos){

        if (alunos.isEmpty()) throw new NoSuchElementException();

        System.out.println("Exibindo todos os alunos: ");

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
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido para a nota.");
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
