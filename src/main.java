import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        arvoreBinaria.inserir(10);
        arvoreBinaria.inserir(15);
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(0);
        arvoreBinaria.inserir(20);
        arvoreBinaria.inserir(35);
        arvoreBinaria.inserir(30);

        System.out.println("----------------");
        System.out.println("Mostrar maior");
        System.out.println(arvoreBinaria.mostrarMaior());

        System.out.println("----------------");
        System.out.println("Mostrar Menor");
        System.out.println(arvoreBinaria.mostrarMenor());

        System.out.println("----------------");
        System.out.println("Mostrar folhas");
        arvoreBinaria.mostrarNodosFolha();

        System.out.println("----------------");
        System.out.println("Mostrar Ancestrais");
        arvoreBinaria.mostrarAncestrais(35);

        System.out.println("----------------");
        System.out.println("Mostrar Descendentes");
        arvoreBinaria.mostrarDescendentes(5);

        System.out.println("----------------");
        System.out.println("Mostrar SubArvore Direita");
        arvoreBinaria.mostrarSubArvoreDir(15);

        System.out.println("----------------");
        System.out.println("Mostrar Subarvore Esquerda");
        arvoreBinaria.mostrarSubArvoreEsq(5);

        System.out.println("----------------");
        System.out.println("Transformar em lista encadeada");
        LinkedList lista = arvoreBinaria.transformaLista();
        System.out.println(lista);

        System.out.println("----------------");
        System.out.println("Mostrar Pares");
        arvoreBinaria.mostrarPares();

        System.out.println("----------------");
        System.out.println("Mostrar Nível");
        arvoreBinaria.mostrarNível(35);

        System.out.println("----------------");
        System.out.println("Mostrar Altura");
        arvoreBinaria.mostrarAltura();

        System.out.println("----------------");
        System.out.println("Mostrar tamanho");
        arvoreBinaria.mostrarTamanho();

        System.out.println("----------------");
        System.out.println("Inserção não-recursiva");
        arvoreBinaria.inserirSemRecursividade(40);

        System.out.println("----------------");
        System.out.println("Mostrar maior");
        System.out.println(arvoreBinaria.mostrarMaior());
    }
}
