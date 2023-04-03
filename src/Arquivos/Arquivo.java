package Arquivos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {
    private String nomearquivo;
    private RandomAccessFile arquivo;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
    }

    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e)
        { }
        return (retorno);
    }

    //insere um Arquivos.Registro no final do arquivo, passado por parâmetro
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    private int filesize() {
        try {
            return (int)arquivo.length()/ Registro.length();
        }catch(IOException e){return 0;}
    }

    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
        }
    }

    public void exibirUmRegistro(int pos)
    {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public void leArq()
    {
        int codigo, idade;
        String nome;
        codigo = Entrada.leInteger("Digite o código");
        while (codigo != 0)
        {
            nome = Entrada.leString("Digite o nome");
            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo, nome, idade));
            codigo = Entrada.leInteger("Digite o código");
        }
    }

    public void executa()
    {
        leArq();
        exibirArq();
    }

    //------------------------------------ Algoritmos ----------------------------------------------
    public void  insercaoDireta(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int tl = filesize(), i,pos;
        for(i=1; i<tl; i++){
            seekArq(i-1);
            reg2.leDoArq(arquivo);
            reg1.leDoArq(arquivo);
            for(pos=i-1;pos<=0 && reg1.getCodigo()<reg2.getCodigo(); pos--){
                seekArq(pos+1);
                reg2.gravaNoArq(arquivo);

                seekArq(pos-1);
                reg2.leDoArq(arquivo);
            }
            seekArq(pos+1);
            reg1.gravaNoArq(arquivo);
        }
    }
    public int buscaBinaria(int chave, int fim){
        int ini=0; int meio=(ini+fim)/2;
        Registro reg = new Registro();
        seekArq(meio);
        reg.leDoArq(arquivo);

        while(ini<fim && reg.getCodigo()!=chave){
            if(chave<reg.getCodigo())
                fim = meio-1;
            else
                ini = meio+1;

            meio = (ini+fim)/2;
            seekArq(meio);
            reg.leDoArq(arquivo);
        }
        if(chave>reg.getCodigo())
            return meio+1;

        return meio;
    }
    public void insercaoBinaria(){
        Registro regIni = new Registro();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int tl = filesize();
        int pos, posAux;

        for(int i=1; i<tl; i++){
            seekArq(i);
            regIni.leDoArq(arquivo);

            pos = buscaBinaria(regIni.getCodigo(),i);
            for(posAux=i; posAux!=pos; posAux--){
                seekArq(posAux-1);
                reg1.leDoArq(arquivo);
                reg1.gravaNoArq(arquivo);
            }
            seekArq(pos);
            regIni.gravaNoArq(arquivo);
        }

    }
    public void SelecaoDireta(){
        int tl = filesize(), menor,aux, posMenor;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        for(int i=0; i<tl; i++){
            seekArq(i);
            reg1.leDoArq(arquivo);
            menor = reg1.getCodigo();
            posMenor = i;
            for(aux=i+1; aux<tl;aux++){
                seekArq(aux);
                reg2.leDoArq(arquivo);
                if(reg2.getCodigo()<menor){
                    menor = reg2.getCodigo();
                    posMenor = aux;
                }
            }
            seekArq(posMenor);
            reg2.leDoArq(arquivo);
            seekArq(i);
            reg2.gravaNoArq(arquivo);
            seekArq(posMenor);
            reg1.gravaNoArq(arquivo);
        }
    }
    public void bolha(){
        int tl;
        int ini=0;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        for(tl= filesize(); ini<tl; tl--){
            for(ini=0;ini<tl-1; ini++){
                seekArq(ini);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                if(reg1.getCodigo()>reg2.getCodigo()){
                     seekArq(ini);
                     reg2.gravaNoArq(arquivo);
                     reg1.gravaNoArq(arquivo);
                }
            }
        }
    }

    public void heap(){
        int posPai, posMaior;
        Registro regPai = new Registro();
        Registro regFe= new Registro();
        Registro regFd= new Registro();
        Registro regFim = new Registro();
        Registro regInicio = new Registro();
        Registro maiorF;
        int tl;

        for(tl=filesize(); tl>1; tl--){

            for(posPai = tl/2-1; posPai>=0; posPai--){
                seekArq(posPai);
                regPai.leDoArq(arquivo);
                seekArq(posPai*2+1);
                regFe.leDoArq(arquivo);
                regFd.leDoArq(arquivo);

                if(regFd!=null && regFd.getCodigo()>regFe.getCodigo()){
                    posMaior = posPai*2+1;
                    maiorF = regFd;
                }
                else {
                    posMaior = posPai*2+1;
                    maiorF = regFe;
                }


                if(maiorF.getCodigo()>regPai.getCodigo()){
                    seekArq(posMaior);
                    regPai.gravaNoArq(arquivo);
                    seekArq(posPai);
                    maiorF.gravaNoArq(arquivo);
                }
            }
            seekArq(tl);
            regFim.leDoArq(arquivo);
            seekArq(0);
            regInicio.leDoArq(arquivo);

            seekArq(tl);
            regInicio.gravaNoArq(arquivo);
            seekArq(0);
            regFim.gravaNoArq(arquivo);

        }
    }

    public void shell(){
        int tl = filesize();
        Registro regAnt = new Registro();
        Registro regAtual = new Registro();
        Registro regProx = new Registro();
        int k, posAnt;

        for(int dist=4; dist>0; dist=dist/2){

            for(int i=0; i<tl; i++){

                for(int j=i; j<tl/dist; j=j+dist){
                    seekArq(j);
                    regAtual.leDoArq(arquivo);
                    seekArq(j+dist);
                    regProx.leDoArq(arquivo);

                    if(regAtual.getCodigo()>regProx.getCodigo()){
                        seekArq(i);
                        regProx.gravaNoArq(arquivo);
                        seekArq(j);
                        regAtual.gravaNoArq(arquivo);

                        k=j;
                        if(k>i){
                            seekArq(k-dist);
                            regAnt.leDoArq(arquivo);
                            seekArq(j);
                            regAtual.leDoArq(arquivo);

                            while (k>i && regAnt.getCodigo()>regAtual.getCodigo()){
                                seekArq(k-dist);
                                regAtual.gravaNoArq(arquivo);
                                seekArq(k);
                                regAnt.gravaNoArq(arquivo);

                                k=k-dist;
                                seekArq(k-dist);
                                regAnt.leDoArq(arquivo);
                                seekArq(k);
                                regAtual.leDoArq(arquivo);
                            }

                        }
                    }

                }
            }
        }
    }

    public void callQuickSemPivo(){
        quickSemPivo(0,filesize());
    }
    public void callQuickComPivo(){
        quickComPivo(0, filesize());
    }
    public void quickSemPivo(int posIni, int posFim){
        int i = posIni, j=posFim;
        Registro regIni = new Registro();
        Registro regFim = new Registro();

        while(i<j){
            seekArq(i);
            regIni.leDoArq(arquivo);
            seekArq(j);
            regFim.leDoArq(arquivo);

            while(i<j && regIni.getCodigo()<=regFim.getCodigo()){
                i++;
                seekArq(i);
                regIni.leDoArq(arquivo);
            }
            seekArq(i);
            regFim.gravaNoArq(arquivo);
            seekArq(j);
            regFim.gravaNoArq(arquivo);

            while(i<j && regFim.getCodigo() >= regIni.getCodigo()){
                j++;
                seekArq(j);
                regFim.leDoArq(arquivo);
            }
            seekArq(i);
            regFim.gravaNoArq(arquivo);
            seekArq(j);
            regIni.gravaNoArq(arquivo);
        }

        if(posIni < i-1)
            quickSemPivo(posIni, i-1);
        if(j+1 < posFim)
            quickSemPivo(j+1, posFim);
    }

    public void quickComPivo(int ini, int fim){
        int i=ini, j=fim;
        Registro regIni = new Registro();
        Registro regFim = new Registro();
        Registro regPivo = new Registro();
        int pivo = (ini+fim)/2;
        seekArq(pivo);
        regPivo.leDoArq(arquivo);

        while(i<j){
            seekArq(i);
            regIni.leDoArq(arquivo);
            seekArq(j);
            regFim.leDoArq(arquivo);

            while(regIni.getCodigo()<regPivo.getCodigo()){
                i++;
                seekArq(i);
                regIni.leDoArq(arquivo);
            }
            while(regFim.getCodigo()>regPivo.getCodigo()){
                j--;
                seekArq(j);
                regFim.leDoArq(arquivo);
            }

            if(i<=j){
                seekArq(i);
                regFim.gravaNoArq(arquivo);
                seekArq(j);
                regIni.gravaNoArq(arquivo);
                i++;
                j--;
            }

            if(ini<j)
                quickComPivo(ini, j);
            if(i<fim)
                quickComPivo(i, fim);
        }
    }

    /*public void bubble_sort()
    {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        seekArq(0);
        int TL = filesize();

        while(TL>1)
        {
            for(int i=0; i<TL-1; i++)
            {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                if(reg1.getCodigo() > reg2.getCodigo())
                {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                }
            }
            TL--;
        }

    }*/
}
