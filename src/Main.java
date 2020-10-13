import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Stream<Path> walk = Files.walk(Paths.get("./entradas/"))) {
            List<File> result = walk
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName().replace("tarefas", "").replace(".txt", ""))))
                    .collect(Collectors.toList());


            var fileIngenua = new File("./saidaIngenua.csv");
            var fileLista = new File("./saidaListaLigada.csv");
            var fileArranjo = new File("./saidaArranjo.csv");

            fileIngenua.createNewFile();
            fileLista.createNewFile();
            fileArranjo.createNewFile();

            var saidaIngenua = new PrintWriter(new FileOutputStream(fileIngenua));
            var saidaListaLigada = new PrintWriter(new FileOutputStream(fileLista));
            var saidaArranjo = new PrintWriter(new FileOutputStream(fileArranjo));

            for (File f : result) {
                System.out.println("Executando instruções do arquivo " + f.getName());

                var reader = new Scanner(new InputStreamReader(new FileInputStream(f)));

                List<String> instrucoes = new ArrayList<>();
                while (reader.hasNextLine()) {
                    instrucoes.add(reader.nextLine());
                }

                processarInstrucoes(instrucoes, new PilhaIngenua(), saidaIngenua);
                processarInstrucoes(instrucoes, new PilhaListaLigada(), saidaListaLigada);
                processarInstrucoes(instrucoes, new PilhaArranjo(), saidaArranjo);

                saidaIngenua.flush();
                saidaArranjo.flush();
                saidaListaLigada.flush();
            }

            saidaListaLigada.close();
            saidaIngenua.close();
            saidaArranjo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void processarInstrucoes(List<String> instrucoes, Pilha pilha, PrintWriter saida) {
        List<Integer> instrucoesProcessadas = new ArrayList<>();
        long tempoInicio = System.currentTimeMillis();

        for (String instrucao : instrucoes) {
            if ("".equals(instrucao)) {
                instrucoesProcessadas.add(pilha.remove());
            } else {
                int id = Integer.parseInt(instrucao);
                pilha.add(id);
            }
        }

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;

        saida.println(tempoTotal + "," + instrucoes.size() + ",");
    }
}
