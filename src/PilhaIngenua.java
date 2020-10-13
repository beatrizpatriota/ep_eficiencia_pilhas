public class PilhaIngenua implements Pilha {
    protected final int MAX = 1000000;
    protected Integer[] pilha;

    PilhaIngenua() {
        pilha = new Integer[MAX];
    }

    public void add(int newElement) {
        int i;
        for (i = 0; pilha[i] != null; i++) ;
        pilha[i] = newElement;
    }

    public int remove() {
        int i;
        for (i = 0; pilha[i] != null; i++) ;
        int tmp = pilha[i - 1];
        pilha[i - 1] = null;
        return tmp;
    }
}
