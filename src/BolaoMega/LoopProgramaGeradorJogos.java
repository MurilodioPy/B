package BolaoMega;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Murilo
 */
public class LoopProgramaGeradorJogos {

    Scanner scanner = new Scanner(System.in);

    public LoopProgramaGeradorJogos() throws IOException {

        CarregaDoArquivo novaAposta = new CarregaDoArquivo(); //cria o objeto aposta aposta
        List<Aposta> apostas = new ArrayList();
        double valorArrecadado = 0; //montante do bolão
        int flag = 0; //sinaliza carga dos dados do arquivo

        int opcaoUsuario = 10;

        while (opcaoUsuario != 0) {
            opcaoUsuario = this.recebeOpcaoUsuario();
            switch (opcaoUsuario) {
                case 0://SAIR
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Inserindo números manualmente.\n\n");
                    apostas.add(novaAposta.carregaApostasManuais());
                    break;
                case 2:
                    if (flag == 0) {
                        System.out.println("Importando números de arquivo.\n\n");
                        String arquivo = "arquivoLido.txt";
                        apostas.addAll(novaAposta.carregaApostas(arquivo));
                    } else {
                        System.out.println("Arquivo ja foi importado.\n\n");
                    }
                    flag++;
                    break;
                case 3:
                    System.out.println("Números já importados.\n\n");
                    for (Aposta aposta : apostas) {
                        System.out.println(Arrays.toString(aposta.getNumeros()));
                    }
                    
                    break;
                case 4:
                    System.out.println("Valor total disponível.\n\n");
                    System.out.println("R$ " + valorArrecadado);
                    break;
                case 5:
                    System.out.println("Gerando jogos.\n\n");
                    System.out.println("Digite o valor arrecadado: ");
                    Scanner sc = new Scanner(System.in);
                    valorArrecadado = sc.nextDouble();
                    sc.nextLine();

                    List<Jogo> jogos = MAIN.criaListaDeJogos(valorArrecadado);

                    Map<Integer, Integer> mapaNomes = novaAposta.criaMapa(apostas);

                    jogos = novaAposta.montaJogo(jogos, mapaNomes);

                    String outFile = "src/jogos/jogo" + valorArrecadado + ".txt";
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
                        for (Jogo jogo : jogos) {
                            System.out.println(jogo);
                            bw.write(jogo.toString());
                            bw.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("escola uma opcao valida");
                    break;
            }
        }
        System.out.println("Saí do menu");
    }

    public static void main(String[] args) throws IOException {
        new LoopProgramaGeradorJogos();
    }

    private int recebeOpcaoUsuario() {
        StringBuilder builder = new StringBuilder("");
        builder.append("\n\nSEJA BEM VINDO AO GERADOR DE JOGOS\n");
        builder.append("\n0 - Sair");
        builder.append("\n1 - Inser números manualmente");
        builder.append("\n2 - Importar números de arquivo");
        builder.append("\n3 - Visualizar números já importados");
        builder.append("\n4 - Valor total disponível");
        builder.append("\n5 - Gerar jogos.\n");
        builder.append("\nQual sua opção ? R: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
