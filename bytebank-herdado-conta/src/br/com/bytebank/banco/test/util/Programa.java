package br.com.bytebank.banco.test.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.bytebank.banco.modelo.Conta;

class Programa {
    public static void main(String[] args) {
        Aula aulaIntro = new Aula("Introdu��o �s Cole��es", 20);
        Aula aulaModelando = new Aula("Modelando a Classe Aula", 18);
        Aula aulaSets = new Aula("Trabalhando com Conjuntos", 16);

        List<Aula> aulas = new ArrayList<Aula>();
        aulas.add(aulaIntro);
        aulas.add(aulaModelando);
        aulas.add(aulaSets);

        //Collections.sort(aulas); 
        //A interface Comparable exige uma implementa��o do m�todo compareTo, que precisa ser chamado pelo algoritmo interno do m�todo sort() da lista.

        for (Aula aula : aulas) {
            System.out.println(aula);
        }
    }
}

class Aula {

    private String titulo;
    private int tempo;

    public Aula(String titulo, int tempo) {
        this.titulo = titulo;
        this.tempo = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String toString() {
        return "[t�tulo: " + titulo + ", tempo: " + tempo + " minutos]";
    }
    
    
}