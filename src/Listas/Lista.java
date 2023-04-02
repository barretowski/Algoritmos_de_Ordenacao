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
    //---------------------------------------- Buscas -----------------------------------------------
    public No localizaNo(int pos){
        No ini = inicio;
        for(int i=0; ini!=null && pos>i; i++){
            ini = ini.getProx();
        }
        return ini;
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
            for(pos=i; pos!=inicio && aux.getInfo()>i.getAnt().getInfo(); pos=pos.getAnt())
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
}
