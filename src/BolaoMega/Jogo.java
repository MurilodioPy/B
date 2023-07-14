package BolaoMega;

import java.util.Arrays;

/**
 *
 * @author Murilo
 */
public class Jogo {

    private int numeros[];
    private int qtd;

    public Jogo(int qtdNumber) {
        numeros = new int[qtdNumber];
        qtd = qtdNumber;
    }

    @Override
    public String toString() {
        return "Jogo de " + qtd + " numeros"
                + "\nNumeros = " + Arrays.toString(numeros);
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtd() {
        return qtd;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }

}
