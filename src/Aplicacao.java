import Arquivos.Arquivo;
import Arquivos.Entrada;
import Extra.Extra;
import Listas.Lista;

public class Aplicacao {

    public static char telaInicial(){
        System.out.println("Informe a estrutura que deseja: \n");
        System.out.println("[A] Algoritmos de ordenação em ARQUIVO \n");
        System.out.println("[L] Algoritmos de ordenação em LISTA \n");
        String resp = Entrada.leString("[-->] Opção: ");

        if(!resp.isEmpty())
            return Character.toUpperCase(resp.charAt(0));
        else
            return ' ';

    }
    public static char telaArquivo(){
        System.out.println("       [ ALGORITMOS EM ARQUIVO ]        \n");
        System.out.println("Informe o tipo de ordenação: \n");
        System.out.println("[A] - Bubble Sort (bolha) \n");
        System.out.println("[B] - Bucket (bolha) \n");
        System.out.println("[C] - Comb \n");
        System.out.println("[D] - Counting \n");
        System.out.println("[E] - Gnome \n");
        System.out.println("[F] - Heap Sort\n");
        System.out.println("[G] - Inserção Binária \n");
        System.out.println("[H] - Inserção Direta \n");
        System.out.println("[I] - Merge Sort (1) \n");
        System.out.println("[J] - Merge Sort (2) \n");
        System.out.println("[K] - Quick Sort - Com Pivô \n");
        System.out.println("[L] - Quick Sort - Sem Pivô \n");
        System.out.println("[M] - Radix \n");
        System.out.println("[N] - Selection Sort (Seleção direta) \n");
        System.out.println("[O] - Shake \n");
        System.out.println("[P] - Shell \n");
        System.out.println("[Q] - Tim \n\n");
        System.out.println("[X] - Voltar \n");
        String resp = Entrada.leString("[-->] Opção: ");

        if(!resp.isEmpty())
            return Character.toUpperCase(resp.charAt(0));
        else
            return ' ';
    }
    public static char telaLista(){
        System.out.println("       [ ALGORITMOS EM LISTA ]        \n");
        System.out.println("Informe o tipo de ordenação: \n");
        System.out.println("[A] - Bubble Sort (bolha) \n");
        System.out.println("[B] - Bucket (bolha) \n");
        System.out.println("[C] - Comb \n");
        System.out.println("[D] - Counting \n");
        System.out.println("[E] - Gnome \n");
        System.out.println("[F] - Heap Sort\n");
        System.out.println("[G] - Inserção Binária \n");
        System.out.println("[H] - Inserção Direta \n");
        System.out.println("[I] - Merge Sort (1) \n");
        System.out.println("[J] - Merge Sort (2) \n");
        System.out.println("[K] - Quick Sort - Com Pivô \n");
        System.out.println("[L] - Quick Sort - Sem Pivô \n");
        System.out.println("[M] - Radix \n");
        System.out.println("[N] - Selection Sort (Seleção direta) \n");
        System.out.println("[O] - Shake \n");
        System.out.println("[P] - Shell \n");
        System.out.println("[Q] - Tim \n\n");
        System.out.println("[X] - Voltar \n");
        String resp = Entrada.leString("[-->] Opção: ");

        if(!resp.isEmpty())
            return Character.toUpperCase(resp.charAt(0));
        else
            return ' ';
    }
    public static void iniciar(){
        char opcao, algoritmo;
        do{
            Extra.limparTela();
            opcao = telaInicial();
            switch (opcao){
                case 'A':
                    Extra.limparTela();
                    Lista lista = new Lista();
                    algoritmo = telaArquivo();
                    do{
                        switch (algoritmo){
                            case 'A':
                                break;
                            case 'B':
                                break;
                            case 'C':
                                break;
                            case 'D':
                                break;
                            case 'E':
                                break;
                            case 'F':
                                break;
                            case 'G':
                                break;
                            case 'H':
                                break;
                            case 'I':
                                break;
                            case 'J':
                                break;
                            case 'K':
                                break;
                            case 'L':
                                break;
                            case 'M':
                                break;
                            case 'N':
                                break;
                            case 'O':
                                break;
                            case 'P':
                                break;
                            case 'Q':
                                break;
                        }
                    }while(algoritmo!='X');
                    break;

                case 'L':
                    Extra.limparTela();
                    algoritmo = telaLista();
                    Arquivo arquivo = new Arquivo("arquivo.txt");
                    do{
                        switch (algoritmo){
                            case 'A':
                                break;
                            case 'B':
                                break;
                            case 'C':
                                break;
                            case 'D':
                                break;
                            case 'E':
                                break;
                            case 'F':
                                break;
                            case 'G':
                                break;
                            case 'H':
                                break;
                            case 'I':
                                break;
                            case 'J':
                                break;
                            case 'K':
                                break;
                            case 'L':
                                break;
                            case 'M':
                                break;
                            case 'N':
                                break;
                            case 'O':
                                break;
                            case 'P':
                                break;
                            case 'Q':
                                break;
                        }
                    }while(algoritmo!='X');
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;


            }
        }while(opcao!=27);
    }
    public static void main(String args[])
    {


        iniciar();
    }
}
