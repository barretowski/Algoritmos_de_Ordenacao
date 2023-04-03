package Listas;

public class Lista {
    private No inicio;
    private No fim;

    public void setInicio(No inicio){
        this.inicio = inicio;
    }

    public No getInicio(){
        return inicio;
    }

    public void setFim(No fim){
        this.fim = fim;
    }

    public No getFim(){
        return fim;
    }
    //---------------------------------------- Buscas e Auxiliares  -----------------------------------------------
    //retorna o endereco do No a partir de uma posicao numerica informada
    public No localizaNo(int pos){
        No ini = inicio;
        for(int i=0; ini!=null && pos>i; i++){
            ini = ini.getProx();
        }
        return ini;
    }
    //retorna a posição numerica de um determinado No
    public int localizaPosNumerico(No pos){
        No i = inicio;
        int cont = 0;
        while(i.getInfo()!=pos.getInfo()){
          cont++;
          i = i.getProx();
        }
        return cont;
    }
    public No buscaBinaria(int info, int fim){
        int ini=0;
        int meio = (ini+fim)/2;
        No no = localizaNo(meio);
        while(ini<fim && info!=no.getInfo()){
            if(info>no.getInfo())
                ini = meio+1;
            else
                fim = meio-1;

            meio = (ini+fim)/2;
            no = localizaNo(meio);
        }
        if(info>no.getInfo())
            return no.getProx();
        else
            return no;
    }

    //--------------------------------------- Algoritmos de Ordenação  ------------------------------
    public void insercaoDireta(){
        No i = inicio, pos, aux;

        for(i = i.getProx(); i!=null; i=i.getProx()){
            aux = i;
            for(pos=i; pos!=inicio && aux.getInfo()<i.getAnt().getInfo(); pos=pos.getAnt())
                pos.setInfo(pos.getAnt().getInfo());
            pos.setInfo(aux.getInfo());
        }
    }

    public void insercaoBinaria(){
        No pos, posAux, pi;
        int aux, i=1;

        for(pi=inicio;pi!=null;pi = pi.getProx()){
            aux = pi.getInfo();
            pos = buscaBinaria(aux, i);
            for(posAux = pi;posAux!=pos;posAux=posAux.getAnt())
                posAux.setInfo(posAux.getAnt().getInfo());

            pos.setInfo(aux);
            i++;
        }
    }

    public void selecaoDireta(){
        No posMenor, aux;
        int menor;

        for(No ini = inicio; ini !=null; ini=ini.getProx()){
            menor = ini.getInfo();
            posMenor = ini;

            for(aux=ini.getProx(); aux!=null; aux=aux.getProx()){
                if(aux.getInfo()<menor){
                    menor = aux.getInfo();
                    posMenor = aux;
                }
            }

            posMenor.setInfo(ini.getInfo());
            ini.setInfo(menor);
        }

    }

    public void bolha(){
        No tl;
        No ini = inicio;
        int aux;
        for(tl=fim; ini!=fim; tl = tl.getAnt()){
            for(ini = inicio;ini.getInfo() > ini.getProx().getInfo(); ini=ini.getProx()){
                aux = ini.getInfo();
                ini.setInfo(ini.getProx().getInfo());
                ini.getProx().setInfo(aux);
            }
        }
    }

    public void heap(){
        int pai, aux;
        No noPai, FE, FD, maiorF;
        int tl = localizaPosNumerico(fim);
        No end = fim;
        while(tl>1 && end!=null){
            pai = tl/2-1;
            noPai = localizaNo(pai);
            while (pai >=0 && noPai!=null){
                FE = localizaNo(pai*2+1);
                FD = null;
                if(FE.getProx()!=null)
                    FD = FE.getProx();

                if(FD!=null && FD.getInfo()>FE.getInfo())
                    maiorF = FD;
                else
                    maiorF = FE;

                if(maiorF.getInfo()> noPai.getInfo()){
                    aux = noPai.getInfo();
                    noPai.setInfo(maiorF.getInfo());
                    maiorF.setInfo(aux);
                }

                pai--;
                noPai = noPai.getAnt();
            }
            aux = inicio.getInfo();
            inicio.setInfo(end.getInfo());
            end.setInfo(aux);
            tl--;
            end = end.getAnt();
        }
    }

    public void shell(){
        int tl = localizaPosNumerico(fim);
        No pAtual, pProx;
        No pAnt, pAux;
        int aux;
        for(int dist=4; dist>=0; dist=dist/2){
            for(int i=0; i<tl;i++){
                for(int j=i; j<tl/dist; j=j+dist){
                    pAtual = localizaNo(i);
                    pProx = localizaNo(j+dist);
                    if(pAtual.getInfo()>pProx.getInfo()){
                        aux = pProx.getInfo();
                        pProx.setInfo(pAtual.getInfo());
                        pAtual.setInfo(aux);

                        int k=j;
                        if(k>i){
                            pAnt = localizaNo(k-dist);
                            pAtual = localizaNo(k);
                            while(k>i &&pAtual.getInfo()> pAnt.getInfo()){
                                aux = pAnt.getInfo();
                                pAnt.setInfo(pAtual.getInfo());
                                pAtual.setInfo(aux);

                                k=k-dist;
                                pAnt = localizaNo(k-dist);
                                pAtual = localizaNo(k);
                            }
                        }
                    }
                }
            }
        }
    }

    public void callQuickSemPivo(){
        int tl=localizaPosNumerico(fim);
        quickSemPivo(0,tl-1);
    }
    public void callQuickComPivo(){
        int tl=localizaPosNumerico(fim);
        quickComPivo(0,tl-1);
    }
    public void quickSemPivo(int posIni, int posFim){
        int i = posIni;
        int j = posFim;
        int aux;

        No noIni = localizaNo(i);
        No noFim = localizaNo(j);

        while(i<j && noIni!=null && noFim!=null){//verifica se os ponteiros se encontraram ou não chegaram ao fim

            while(i<j && noIni.getInfo()<=noFim.getInfo()){//percorre da esquerda pra direita enquanto for menor
                noIni = noIni.getProx();
                i++;
            }
            //realiza a permutação
            aux = noIni.getInfo();
            noIni.setInfo(noFim.getInfo());
            noFim.setInfo(aux);

            while(i<j && noFim.getInfo()>noIni.getInfo()){//percorre da direita pra esquerda enquanto for maior
                noFim = noFim.getAnt();
                j--;
            }

            aux = noIni.getInfo();
            noIni.setInfo(noFim.getInfo());
            noFim.setInfo(aux);
        }

        if(posIni <i-1)//passa o bloco da esquerda -> [5,3,4]|6|[9,4,7]
            quickSemPivo(posIni,i-1);
        if(j+1 < posFim)//passa o bloco da direita [5,3,4]|6|[9,4,7] <-
            quickSemPivo(j+1, posFim);
    }

    public void quickComPivo(int posIni, int posFim){
        int i = posIni, j=posFim, aux;
        No noIni = localizaNo(posIni);
        No noFim = localizaNo(posFim);
        No noPivo = localizaNo((i+j)/2);
        int pivo = localizaPosNumerico(noPivo);

        while(noIni.getInfo()<pivo){
            i++;
            noIni = noIni.getProx();
        }
        while(noFim.getInfo()>pivo){
            j--;
            noFim = noFim.getAnt();
        }
        if(i<=j){
            aux = noIni.getInfo();
            noIni.setInfo(noFim.getInfo());
            noFim.setInfo(aux);
            j--;
            i++;
        }
        if(posIni<j)
            quickComPivo(posIni,j);
        if(i<posFim)
            quickComPivo(i, posFim);
    }

}
