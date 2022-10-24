public class Produto {
    private int id;
    private int quantidade;
    private boolean disponivel;

    public Produto(int id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
        this.disponivel = quantidade > 0;
    }

    public synchronized void produzir(int quantidade, String threadId) {
        this.quantidade += quantidade;
        this.disponivel = true;
        System.out.println(
                "Produtor: " + threadId + " | Produziu: " + quantidade + " | " + this);
        notifyAll();
    }

    public synchronized void consumir(int quantidade, String threadId) {
        while (disponivel == false || (this.quantidade - quantidade) < 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        this.quantidade -= quantidade;
        this.disponivel = this.quantidade > 0;

        System.out.println(
                "consumidor: " + threadId + " | consumiu: " + quantidade + " | " + this);

        notifyAll();
    }

    public synchronized int getQuantidade() {
        return quantidade;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", quantidade=" + quantidade + "]";
    }
}
