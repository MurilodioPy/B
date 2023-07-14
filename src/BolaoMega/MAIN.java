package BolaoMega;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Murilo
 */
public class MAIN {

    public MAIN() throws IOException {
        programa();
    }

    private void programa() throws IOException {
        double valorArrecadado = 10000;

        List<Jogo> jogos = this.criaListaDeJogos(valorArrecadado);

        CarregaDoArquivo carregaDoArquivo = new CarregaDoArquivo();
        String arquivo = "arquivoLido.txt";

        List<Aposta> apostas = carregaDoArquivo.carregaApostas(arquivo);
        for (Aposta aposta : apostas) {
            System.out.println(Arrays.toString(aposta.getNumeros()));
        }

        System.out.println("\n\n................................................................................................................");
        System.out.println("...............................................JOGOS GERADOS....................................................");
        System.out.println("................................................................................................................\n");

        Map<Integer, Integer> mapaApostas = carregaDoArquivo.criaMapa(apostas);

        jogos = carregaDoArquivo.montaJogo(jogos, mapaApostas);

        for (Jogo jogo : jogos) {
            System.out.println(jogo);
        }
    }

    public static void main(String[] args) throws IOException {
        new MAIN();
    }

    public static List<Jogo> criaListaDeJogos(double valor) {
        List<Jogo> jogos = new ArrayList<>();
        while (valor >= Tabela.SEIS.getValor()) {

            if (valor >= Tabela.VINTE.getValor()) {
                valor = valor - Tabela.VINTE.getValor();
                Jogo j = new Jogo(20);
                jogos.add(j);
            } else if (valor >= Tabela.DEZENOVE.getValor()) {
                valor = valor - Tabela.DEZENOVE.getValor();
                Jogo j = new Jogo(19);
                jogos.add(j);
            } else if (valor >= Tabela.DEZOITO.getValor()) {
                valor = valor - Tabela.DEZOITO.getValor();
                Jogo j = new Jogo(18);
                jogos.add(j);
            } else if (valor >= Tabela.DEZESSETE.getValor()) {
                valor = valor - Tabela.DEZESSETE.getValor();
                Jogo j = new Jogo(17);
                jogos.add(j);
            } else if (valor >= Tabela.DEZESSEIS.getValor()) {
                valor = valor - Tabela.DEZESSEIS.getValor();
                Jogo j = new Jogo(16);
                jogos.add(j);
            } else if (valor >= Tabela.QUINZE.getValor()) {
                valor = valor - Tabela.QUINZE.getValor();
                Jogo j = new Jogo(15);
                jogos.add(j);
            } else if (valor >= Tabela.QUATORZE.getValor()) {
                valor = valor - Tabela.QUATORZE.getValor();
                Jogo j = new Jogo(14);
                jogos.add(j);
            } else if (valor >= Tabela.TREZE.getValor()) {
                valor = valor - Tabela.TREZE.getValor();
                Jogo j = new Jogo(13);
                jogos.add(j);
            } else if (valor >= Tabela.DOZE.getValor()) {
                valor = valor - Tabela.DOZE.getValor();
                Jogo j = new Jogo(12);
                jogos.add(j);
            } else if (valor >= Tabela.ONZE.getValor()) {
                valor = valor - Tabela.ONZE.getValor();
                Jogo j = new Jogo(11);
                jogos.add(j);
            } else if (valor >= Tabela.DEZ.getValor()) {
                valor = valor - Tabela.DEZ.getValor();
                Jogo j = new Jogo(10);
                jogos.add(j);
            } else if (valor >= Tabela.NOVE.getValor()) {
                valor = valor - Tabela.NOVE.getValor();
                Jogo j = new Jogo(9);
                jogos.add(j);
            } else if (valor >= Tabela.OITO.getValor()) {
                valor = valor - Tabela.OITO.getValor();
                Jogo j = new Jogo(8);
                jogos.add(j);
            } else if (valor >= Tabela.SETE.getValor()) {
                valor = valor - Tabela.SETE.getValor();
                Jogo j = new Jogo(7);
                jogos.add(j);
            } else if (valor >= Tabela.SEIS.getValor()) {
                valor = valor - Tabela.SEIS.getValor();
                Jogo j = new Jogo(6);
                jogos.add(j);
            }
        }
        System.out.println("Restante: " + valor + "\n\n");
        return jogos;
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    public static int getRandomNumberInArray(int[] array) {
        Random r = new Random();
        int randomNumber = r.nextInt(array.length);
        return array[randomNumber];
    }
}
