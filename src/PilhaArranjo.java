public class PilhaArranjo implements Pilha {
    protected final int MAX = 1000000;
    protected Integer[] pilha;

    protected int topo = -1;

    PilhaArranjo() {
        pilha = new Integer[MAX];
    }

    @Override
    public void add(int newElement) {
        pilha[++topo] = newElement;
    }

    @Override
    public int remove() {
        return pilha[topo--];
    }
}
