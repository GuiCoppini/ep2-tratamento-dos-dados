package code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Linha> linhas = new ArrayList<>();
    static List<Double> medias = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // le o CSV e popula eles
        populaLinhas();

        // media atual
        double media = 0;

        // ultima posicao que foi utilizada para pegar a media
        int lastCut = 0;

        // passa pelas linhas e para pra calcular a media de 5 em 5 posicoes ou no ultimo numero
        for(int i = 0; i<linhas.size(); i++) {
            boolean isAtLastLine = i == linhas.size() - 1;

            // se entrar no if eh pq passou 5 numeros ou eh o ultimo
            if ((i % 5 == 0 && i != 0) || isAtLastLine) {
                // i - lastCut normalmente eh 5, mas no ultimo numero pode valer menos
                // se eu utilizasse 5 direto, o ultimo valor seria muito menor pq faria a soma de 3 numeros / 5
                media /= i-lastCut;

                lastCut = i;
                medias.add(media);
                media = 0;
            }
            media += linhas.get(i).close;
        }
        System.out.println("Tamanho do dataset original = " + linhas.size());
        System.out.println("Tamanho do medias tratado = " + medias.size());

        System.out.println("Salvando resultado no arquivo \"resultado.csv\"");
        File resultado = new File("resultado.csv");
        if(resultado.exists()) {
            resultado.delete();
        }
        FileWriter writer = new FileWriter(resultado, true);
        writer.append("Close\n");
        for (Double valor : medias) {
            writer.append(valor.toString() + ";\n");
        }


    }

    public static void populaLinhas() throws FileNotFoundException {

        File csv = new File(Main.class.getClassLoader().getResource("ep.csv").getFile());
        FileReader arq = new FileReader(csv);

        Scanner bufferedScanner = new Scanner(new BufferedReader(arq));
        // pula linha de header do csv
        bufferedScanner.nextLine();
        try {
            while (bufferedScanner.hasNextLine()) {
                String linhaCurrente = bufferedScanner.nextLine();

                Scanner linhaScanner = new Scanner(linhaCurrente);
                linhaScanner.useDelimiter(";");

                linhas.add(
                        new Linha(
                                linhaScanner.next(),
                                Double.valueOf(formatToDouble(linhaScanner.next())),
                                Double.valueOf(formatToDouble(linhaScanner.next())),
                                Double.valueOf(formatToDouble(linhaScanner.next())),
                                Double.valueOf(formatToDouble(linhaScanner.next())),
                                Double.valueOf(formatToDouble(linhaScanner.next())),
                                Double.valueOf(formatToDouble(linhaScanner.next()))
                                ));
                linhaScanner.close();
            }
        } finally {
            try {
                bufferedScanner.close();
            } catch (Exception anException) {
                System.out.println("Error: " + anException);
            }
        }
    }

    // usando "," ele da pau... tem que usar "."
    private static String formatToDouble(String in) {
        return in.replace(",", ".");
    }
}
