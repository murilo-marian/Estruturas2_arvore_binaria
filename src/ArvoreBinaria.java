import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArvoreBinaria {
    private class Nodo {
        private int chave;
        private Nodo dir, esq;

        public Nodo(int item) {
            this.chave = item;
            dir = esq = null;
        }

        public int getChave() {
            return chave;
        }

        public void setChave(int chave) {
            this.chave = chave;
        }

        public Nodo getDir() {
            return dir;
        }

        public void setDir(Nodo dir) {
            this.dir = dir;
        }

        public Nodo getEsq() {
            return esq;
        }

        public void setEsq(Nodo esq) {
            this.esq = esq;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("");
            sb.append("chave=").append(chave);
            return sb.toString();
        }
    }

    Nodo raiz = null;

    public void inserir(int chave) {
        raiz = inserirDado(raiz, chave);
    }

    private Nodo inserirDado(Nodo raiz, int chave) {
        if (raiz == null) {
            raiz = new Nodo(chave);
            return raiz;
        } else if (chave < raiz.chave) {
            raiz.esq = inserirDado(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            raiz.dir = inserirDado(raiz.dir, chave);
        }
        return raiz;
    }

    //codigo que mostra a árvore tá faltando

    public void remover(int chave) {
        raiz = removerItem(raiz, chave);
    }

    private Nodo removerItem(Nodo raiz, int chave) {
        if (raiz == null) {
            //nó 404
            return null;
        }
        if (chave < raiz.chave) {
            //chave a ser removida está a esquerda
            raiz.esq = removerItem(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            //chave a ser removida está a direita
            raiz.dir = removerItem(raiz.dir, chave);
        } else {
            //nó encontrado
            if (raiz.esq == null) {
                //caso em que o nó não possui filho a esquerda
                return raiz.dir;
            } else if (raiz.dir == null) {
                //casoo em que o nó não possui filha a direita
                return raiz.esq;
            } else {
                //caso em que o nó possui ambos os filhos
                //nó sucessor sereá o menor doa subárvoreda direita
                Nodo sucessor = encontrarSucessor(raiz.dir);
                raiz.chave = sucessor.chave;
                raiz.dir = removerItem(raiz.dir, sucessor.chave);
            }
        }
        return raiz;
    }

    private Nodo encontrarSucessor(Nodo nodo) {
        while (nodo.esq != null) {
            nodo = nodo.esq;
        }
        return nodo;
    }

    public void mostrarEmOrdem() {
        mostrarOrdenado(raiz);
    }

    private void mostrarOrdenado(Nodo raiz) {
        if (raiz != null) {
            mostrarOrdenado(raiz.esq);
            System.out.println(raiz.getChave());
            mostrarOrdenado(raiz.dir);
        }
    }

    //1 - mostra o maior número da árvore
    public Nodo mostrarMaior() {
        return pegarMaior(this.raiz);
    }

    //método recursivo que descobre o maior número
    private Nodo pegarMaior(Nodo raiz) {
        if (raiz.dir != null) {
            raiz = pegarMaior(raiz.dir);
        }
        return raiz;
    }

    //2 - mostra o menor número da árvore
    public Nodo mostrarMenor() {
        return pegarMenor(this.raiz);
    }

    //método recursivo que descobre o menor número
    private Nodo pegarMenor(Nodo raiz) {
        if (raiz.esq != null) {
            raiz = pegarMenor(raiz.esq);
        }
        return raiz;
    }

    //3 - método que mostra os nodos folha
    public void mostrarNodosFolha() {
        ArrayList<Nodo> nodoList = new ArrayList<>();
        pegarNodosFolha(nodoList, this.raiz);
        for (Nodo nodo : nodoList) {
            System.out.println(nodo);
        }
    }

    //método recursivo que adiciona os nodos sem filhos (folhas) para um array e retorna ele
    private void pegarNodosFolha(ArrayList<Nodo> nodoList, Nodo raiz) {
        if (raiz != null) {
            if (raiz.esq == null && raiz.dir == null) {
                nodoList.add(raiz);
            } else {
                pegarNodosFolha(nodoList, raiz.dir);
                pegarNodosFolha(nodoList, raiz.esq);
            }
        }
    }

    //4 - método que retorna os ancestrais de um valor específico
    public void mostrarAncestrais(int valor) {
        ArrayList<Nodo> nodoList = new ArrayList<>();
        puxarAncestrais(nodoList, valor, this.raiz);
        for (Nodo nodo : nodoList) {
            System.out.println(nodo);
        }
    }

    //método que adiciona os ancestrais a um arraylist
    private void puxarAncestrais(ArrayList<Nodo> nodoList, int valor, Nodo raiz) {
        if (raiz != null) {
            if (raiz.getChave() == valor) {
                return;
            }
            if (raiz.getChave() > valor) {
                nodoList.add(raiz);
                puxarAncestrais(nodoList, valor, raiz.getEsq());
            } else if (raiz.getChave() < valor) {
                nodoList.add(raiz);
                puxarAncestrais(nodoList, valor, raiz.getDir());
            }
        }
    }

    //5 - método que mostra os descendentes
    public void mostrarDescendentes(int valor) {
        ArrayList<Nodo> nodolist = new ArrayList<>();
        Nodo nodoPai = encontrarNodo(valor, this.raiz);
        if (nodoPai == null) {
            System.out.println("erro");
        } else {
            if (nodoPai.getEsq() != null) {
                puxarDescendentes(nodolist, nodoPai.getEsq());
            }
            if (nodoPai.getDir() != null) {
                puxarDescendentes(nodolist, nodoPai.getDir());
            }
            for (Nodo nodo : nodolist) {
                System.out.println(nodo);
            }
        }
    }

    //método que descobre os descendentes e os adiciona a um arraylist
    private void puxarDescendentes(ArrayList<Nodo> nodoList, Nodo raiz) {
        if (raiz != null) {
            nodoList.add(raiz);
            puxarDescendentes(nodoList, raiz.esq);
            puxarDescendentes(nodoList, raiz.dir);
        }
    }

    //método utilizado por outros métodos que encontra a posição de um nodo com valor específico
    private Nodo encontrarNodo(int valor, Nodo raiz) {
        if (raiz != null) {
            if (raiz.getChave() == valor) {
                return raiz;
            } else if (raiz.getChave() < valor) {
                return encontrarNodo(valor, raiz.getDir());
            } else if (raiz.getChave() > valor) {
                return encontrarNodo(valor, raiz.getEsq());
            }
        }
        return null;
    }

    //6 - mostra a subarvore direita
    public void mostrarSubArvoreDir(int valor) {
        ArrayList<Nodo> nodoList = new ArrayList<>();
        Nodo nodoPai = encontrarNodo(valor, this.raiz);
        if (nodoPai == null) {
            System.out.println("Erro");
        } else {
            puxarDescendentes(nodoList, nodoPai.getDir());
            for (Nodo nodo : nodoList) {
                System.out.println(nodo);
            }
        }
    }

    //7 - mesmo do anterior porém a esquerda
    public void mostrarSubArvoreEsq(int valor) {
        ArrayList<Nodo> nodoList = new ArrayList<>();
        Nodo nodoPai = encontrarNodo(valor, this.raiz);
        if (nodoPai == null) {
            System.out.println("Erro");
        } else {
            puxarDescendentes(nodoList, nodoPai.getEsq());
            for (Nodo nodo : nodoList) {
                System.out.println(nodo);
            }
        }
    }

    //transforma em lista encadeada
    public LinkedList transformaLista(){
        LinkedList lista = new LinkedList();
        transformaLista(lista, raiz);
        return lista;
    }

    private void transformaLista(LinkedList lista, Nodo raiz){
        if (raiz!=null){
            transformaLista(lista,raiz.getEsq());
            lista.add(raiz.chave);
            transformaLista(lista,raiz.getDir());
        }
    }

    //9 - mostra os nodos com valor par
    public void mostrarPares() {
        ArrayList<Nodo> nodoList = new ArrayList<>();
        nodoList = pegarPares(nodoList, this.getRaiz());
        for (Nodo nodo : nodoList) {
            System.out.println(nodo);
        }
    }

    //pega os nodos com valores pares e coloca num arraylist
    private ArrayList<Nodo> pegarPares(ArrayList<Nodo> nodoList, Nodo raiz) {
        if (raiz == null) {
            return null;
        }
        if (raiz.getChave() % 2 == 0) {
            nodoList.add(raiz);
        }
        pegarPares(nodoList, raiz.getEsq());
        pegarPares(nodoList, raiz.getDir());
        return nodoList;
    }

    //10 - mostra o nível de algum nodo
    public void mostrarNível(int valor) {
        int nivel = 0;
        nivel = calculaNivel(valor, nivel, this.raiz);
        System.out.println("Nível: " + nivel);
    }

    //calcula o nível de um nodo específico
    private int calculaNivel(int valor, int nivel, Nodo raiz) {
        if (raiz != null) {
            if (raiz.getChave() == valor) {
                return nivel;
            } else {
                nivel++;
                if (raiz.getChave() < valor) {
                    nivel = calculaNivel(valor, nivel, raiz.getDir());
                } else if (raiz.getChave() > valor) {
                    nivel = calculaNivel(valor, nivel, raiz.getEsq());
                }
            }
        }
        return nivel;
    }

    //11 - mostra a altura
    public void mostrarAltura() {
        int altura = 0;
        altura = calcularAltura(altura, this.raiz);
        System.out.println("Altura: " + altura);
    }

    //calcula a altura
    private int calcularAltura(int altura, Nodo raiz) {
        int alturaDir = altura;
        int alturaEsq = altura;
        if (raiz != null) {
            if (raiz.getEsq() != null) {
                alturaEsq++;
                alturaEsq = calcularAltura(alturaEsq, raiz.getEsq());
            }
            if (raiz.getDir() != null) {
                alturaDir++;
                alturaDir = calcularAltura(alturaDir, raiz.getDir());
            }
        }
        if (alturaDir > altura) {
            altura = alturaDir;
        }
        if (alturaEsq > altura) {
            altura = alturaEsq;
        }
        return altura;
    }

    //12 - mostra o tamanho
    public void mostrarTamanho() {
        if (this.raiz != null) {
            int tamanho = calcularTamanho(this.raiz);
            System.out.println("tamanho: " + tamanho);
        } else {
            System.out.println("ERRO");
        }
    }

    //calcula o tamanho
    private int calcularTamanho(Nodo raiz) {
        int tamanho = 0;
        if (raiz != null) {
            tamanho++;
            tamanho += calcularTamanho(raiz.esq);
            tamanho += calcularTamanho(raiz.dir);
        }
        return tamanho;
    }

    //13 - insere sem usar recursividade
    public void inserirSemRecursividade(int valor) {
        Nodo atual = this.raiz;
        while (atual != null) {
            if (atual.getChave() < valor) {
                if (atual.getDir() != null) {
                    atual = atual.getDir();
                } else {
                    atual.setDir(new Nodo(valor));
                }
            } else if (atual.getChave() > valor) {
                if (atual.getEsq() != null) {
                    atual = atual.getEsq();
                } else {
                    atual.setEsq(new Nodo(valor));
                }
            } else {
                System.out.println("Já adicionado");
                break;
            }
        }
    }

    private int calcularAltura(int altura) {
        return 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
}
