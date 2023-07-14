package BolaoMega;

import java.util.Arrays;

/**
 *
 * @author Murilo
 */
public class Aposta {

    private String nome; //nome do dono dos numeros
    private int numeros[];

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }

    @Override
    public String toString() {
        return "Nome: "
                + nome
                + "\nNumeros:" + Arrays.toString(numeros)
                + "\n";
    }

}
