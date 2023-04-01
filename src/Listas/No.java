package Listas;

public class No {
    private No prox;
    private No ant;
    private int info;

    public No(){
        this.prox = null;
        this.ant = null;
    }
    public No(No ant, No prox){
        this.ant = ant;
        this.prox = prox;
    }
    public No(No prox, No ant, int info){
        this.prox = prox;
        this.ant = ant;
        this.info = info;
    }

    public void setProx(No prox){
       this.prox = prox;
    }
    public No getProx(){
        return prox;
    }
    public void setAnt(No ant){
        this.ant = ant;
    }

    public No getAnt(){
        return ant;
    }

    public void setInfo(int info){
        this.info = info;
    }

    public int getInfo(){
        return info;
    }

}
