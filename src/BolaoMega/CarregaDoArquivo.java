package BolaoMega;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Murilo
 */
public class CarregaDoArquivo {

    private List<Aposta> apostas = new ArrayList();

    public Aposta carregaApostasManuais() {
        int n = 0;
        int cont = 0;
        Scanner sc = new Scanner(System.in);

        Aposta aposta = new Aposta();

        System.out.println("Digite o seu nome: ");
        String nome = sc.nextLine();
        aposta.setNome(nome);

        System.out.println("Digite a quantidade de numeros: ");
        do {
            cont = 0;
            n = sc.nextInt();
            sc.nextLine();
            if (n < 6 || n > 15) {
                cont++;
                System.out.println("Valor incorreto! - Tente novamente\n");
            }
        } while (cont != 0);
        int numeros[] = new int[n];

        System.out.println("Digite os numeros: ");
        for (int i = 0; i < n; i++) {
            int num = 0;
            do {
                cont = 0;
                System.out.println("n: " + (i + 1));
                num = sc.nextInt();
                sc.nextLine();
                for (int j = 0; j < n; j++) {
                    if (num == numeros[j] || num > 60 || num < 1) {
                        cont++;
                        System.out.println("Valor repetido ou incorreto! - Tente novamente");
                    }
                }
            } while (cont != 0);

            numeros[i] = num;
        }
        aposta.setNumeros(numeros);
        return aposta;
    }

    public List<Aposta> carregaApostas(String arquivo) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(arquivo); //lê byte

        InputStreamReader isr = new InputStreamReader(is); //lê char

        try (BufferedReader br = new BufferedReader(isr)) {//lê string

            String s = br.readLine();
            while (s != null) { //percorre cada linha do arquivo
                Aposta aposta = new Aposta(); //cria um objeto do tipo Aposta

                String arquivoTxt[];
                arquivoTxt = s.split(" ");//cria um vetor de n posições
                aposta.setNome(arquivoTxt[0]);//insere o nome

                int numeros[] = new int[arquivoTxt.length - 1]; //cria vetor com o a quantidade de numeros da aposta

                for (int i = 1; i < arquivoTxt.length; i++) { //adicionando números no vetor
                    numeros[i - 1] = parseInt(arquivoTxt[i]);
                }
                aposta.setNumeros(numeros);//adiciona o vetor no objeto
                s = br.readLine();

                apostas.add(aposta); //adiciona o objeto na lista 
            }
            return apostas; //retorna a lista preenchida
        }
    }

    //cria uma mapa das apostas com numero da aposta como chave e a quantidade de vezes que aparece como valor
    public Map<Integer, Integer> criaMapa(List<Aposta> apostas) {
        Map<Integer, Integer> mapNumber = new HashMap<>();
        for (Aposta aposta : apostas) {
            for (int i = 0; i < aposta.getNumeros().length; i++) {
                int numero = aposta.getNumeros()[i];
                int quantidade = mapNumber.getOrDefault(numero, 0);
                mapNumber.put(numero, quantidade + 1);
            }
        }
        return mapNumber;
    }

    List<Jogo> montaJogo(List<Jogo> jogos, Map<Integer, Integer> mapaApostas) {
        int chave = 0; //numero da aposta com maior aparição
        for (Jogo jogo : jogos) {
            int[] numeros = new int[jogo.getQtd()];
            for (int i = 0; i < jogo.getQtd(); i++) {
                chave = geraMaior(mapaApostas, numeros);
                numeros[i] = chave;
                int quantidade = mapaApostas.getOrDefault(chave, 0);
                mapaApostas.put(chave, quantidade - 1);
            }
            jogo.setNumeros(numeros);
        }
        return jogos;
    }

    private int geraMaior(Map<Integer, Integer> mapaApostas, int[] numeros) {
        int maior = 0; //pega a maior quantidade de apariçoes
        int chave = 0;
        boolean have = false;
        for (Map.Entry<Integer, Integer> entry : mapaApostas.entrySet()) {//percorre valores do mapa
            int key = entry.getKey();
            int qtd = entry.getValue();
            for (int i = 0; i < numeros.length; i++) { //verifica se valor já está no vetor
                if (numeros[i] == key) {
                    have = true;
                }
            }
            if (!have) {//não tem o valor
                if (qtd > maior) {//verifica se é o maior
                    maior = qtd;
                    chave = key;
                }
            }
            have = false;// zera o alerta
            if (maior == 0) {
                int cont = 0;
                int aleatorio = 0;
                do {
                    cont = 0;
                    aleatorio = MAIN.getRandomNumberInRange(1, 60);
                    for (int j = 0; j < numeros.length; j++) { //verifica valores iguais no jogo
                        if (numeros[j] == aleatorio) {
                            cont++;
                        }
                    }
                } while (cont != 0);
                return aleatorio;
            }
        }
        return chave;
    }
}

//
//for (Map.Entry<Integer, Integer> entry : mapaApostas.entrySet()) {
//            Integer key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println("Chave:" + key + "Qtd: " + value);
//        }
//        int chave = 0; //numero da aposta com maior aparição
//        int cont = 0; //contador de numeros repetidos
//        int flag = 0;
//        for (Jogo jogo : jogos) {
//            int[] numeros = new int[jogo.getQtd()];
//            for (int i = 0; i < jogo.getQtd(); i++) {
//                chave = geraMaior(flag, mapaApostas, numeros);
//                do {
//                    cont = 0;
//                    for (int j = 0; j < numeros.length; j++) { //verifica valores iguais no jogo
//                        if (numeros[j] == chave) {
//                            cont++;
//                        }
//                    }
//                    if (cont == 0) {
//                        numeros[i] = chave;
//                        int quantidade = mapaApostas.getOrDefault(chave, 0);
//                        mapaApostas.put(chave, quantidade - 1); //remove um na quantidade da chave
//                    } else {
//                        flag++;
//                        chave = geraMaior(flag, mapaApostas, numeros);
//                    }
//                } while (cont != 0);
//                flag = 0;
//                jogo.setNumeros(numeros);
////                int quantidade = mapaApostas.getOrDefault(chave, 0);
////                mapaApostas.put(chave, quantidade - 1);
//            }
//        }
//        return jogos;
