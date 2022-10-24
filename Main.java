import java.util.ArrayList;
import java.util.List;

/**
 * Atividade de thread - Produtor X consumidor = > Sistemas operacionais UEPB
 */
public class Main {
    public static List<Produto> produtos;

    public static void main(String[] args) {
        produtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            produtos.add(new Produto(i, 0));
        }

        Produtor produtor = new Produtor(10, 100, 1, produtos);
        Produtor produtor1 = new Produtor(10, 30, 1, produtos);
        Consumidor consumidor = new Consumidor(10, 150, 1, produtos);

        consumidor.start();
        produtor1.start();
        produtor.start();

        while (produtor1.isAlive() || produtor.isAlive() || consumidor.isAlive()) {
            if(!produtor1.isAlive() && !produtor.isAlive()) {
                consumidor.interrupt();
            }
        }

        produtos.stream().forEach(System.out::println);
    }
}