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
    public void bubble_sort()
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

    }
}
