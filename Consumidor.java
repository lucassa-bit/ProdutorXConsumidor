import java.util.List;

public class Consumidor extends Thread {
    int delay;
    int consumo;
    int idProduto;
    private Produto produto;

    public Consumidor(int delay, int consumo, int idProduto, List<Produto> produto) {
        this.delay = delay;
        this.consumo = consumo;
        this.idProduto = idProduto;
        this.produto = produto.parallelStream()
                .filter(customer -> idProduto == customer.getId())
                .findAny()
                .orElse(null);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            produto.consumir(consumo, this.getName());
        }
    }
}
