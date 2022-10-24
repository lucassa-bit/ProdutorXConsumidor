import java.util.List;

public class Produtor extends Thread {
    int delay;
    int producao;
    int idProduto;
    private Produto produto;

    public Produtor(int delay, int producao, int idProduto, List<Produto> produto) {
        this.delay = delay;
        this.producao = producao;
        this.idProduto = idProduto;
        this.produto = produto.parallelStream()
                .filter(customer -> idProduto == customer.getId())
                .findAny()
                .orElse(null);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            produto.produzir(producao, this.getName());
        }
    }
}
