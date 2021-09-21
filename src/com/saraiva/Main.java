package com.saraiva;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class Main {

    private static final int[] IMAGEM1 = {1, 1, 1, 1, 1, -1, -1, -1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, -1};
    private static final int[] IMAGEM2 = {1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1};
    private static final int[] IMAGEM3 = {-1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, -1};
    private static final int[] IMAGEM4 = {-1, 1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1};
    private static final int[] IMAGEM5 = {1, 1, 1, 1, 1, 1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1};
    private static final int[] IMAGEM6 = {1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1};
    private static final int[] IMAGEM7 = {-1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, 1, -1, 1, 1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static final int[] PADRAO_DESCONHECIDO = {1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, 1, 1, -1};
    private static final int[][] padroesExercicio = {IMAGEM1, IMAGEM2, IMAGEM3, IMAGEM4, IMAGEM5, IMAGEM6, IMAGEM7};


    // arrays de teste
    private static final int[] A = {1, 1, 1, -1};
    private static final int[] B = {1, 1, 1, 1};
    private static final int[] C = {-1, -1, 1, 1};
    private static final int[][] padroes = {A, B, C};
    private static final int[] padraoDesconhecido = {1, 1, -1, -1};


    public static void main(String[] args) {
//        printar(IMAGEM1, IMAGEM2, IMAGEM3, IMAGEM4, IMAGEM5, IMAGEM6, IMAGEM7, PADRAO_DESCONHECIDO);
        var pesos = gerarMatrizPesos();
        treinar(pesos, PADRAO_DESCONHECIDO);
    }

    private static int[][] gerarMatrizPesos() {
        int[][] padroes = {IMAGEM1, IMAGEM2, IMAGEM3, IMAGEM4, IMAGEM5, IMAGEM7};
        int[][] matrizPeso = new int[padroes[0].length][padroes[0].length];
        for (int i = 0; i < matrizPeso.length; i++) {
            for (int j = 0; j < matrizPeso[i].length; j++) {
                if (j >= i) break;
                var soma = 0;
                for (int k = 0; k < padroes.length; k++) {
                    soma += (padroes[k][i] * padroes[k][j]);
                }
                matrizPeso[i][j] = matrizPeso[j][i] = soma;
            }
        }
        return matrizPeso;
    }

    private static final int MAX_ITERATIONS = 10;
    private static int counter = 0;

    private static void treinar(int[][] pesos, int[] padraoDesconhecido) {
        if (counter == MAX_ITERATIONS) return;

        counter++;

        int[] padraoY = new int[padraoDesconhecido.length];
        for (int i = 0; i < pesos.length; i++) {
            var soma = 0;
            for (int j = 0; j < pesos[i].length; j++) {
                soma += padraoDesconhecido[j] * pesos[j][i];
            }
            padraoY[i] = soma >= 0 ? 1 : -1;
        }
        printar(padraoY);
        boolean convergiu = false;
        for (int i = 0; i < padroesExercicio.length; i++) {
            convergiu = Arrays.equals(padraoY, padroesExercicio[i]);
            if (convergiu) break;
        }

        if (!convergiu) treinar(pesos, padraoY);
    }

    private static void printar(int[]... array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("==========IMAGEM" + (i + 1) + "==========");
            for (int j = 0; j < array[i].length; j++) {
                var number = array[i][j];
                if ((j + 1) % 6 == 0) {
                    if (number == -1) {
                        System.out.println("\u2B1C");
                    } else {
                        System.out.println("\u2B1B");
                    }
                } else {
                    if (number == -1) {
                        System.out.print("\u2B1C");
                    } else {
                        System.out.print("\u2B1B");
                    }
                }
            }
        }

    }
}
