public class PilhaListaLigada implements Pilha {
    private No topo;

    @Override
    public void add(int newElement) {
        if(topo == null) topo = new No(newElement);
        else {
            No novo = new No(newElement);
            novo.proximo = topo;
            topo = novo;
        }
    }

    @Override
    public int remove() {
        No removido = topo;
        topo = topo.proximo;

        return removido.valor;
    }

    protected class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
        }
    }
}
