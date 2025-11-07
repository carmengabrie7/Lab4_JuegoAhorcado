/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_juegoahorcado;

/**
 *
 * @author eduar
 */
import java.util.ArrayList;

public abstract class JuegoAhorcadoBase {
    
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos=6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;

    public JuegoAhorcadoBase() {
        this.intentos= limiteIntentos;
        this.letrasUsadas= new ArrayList<>();
        this.figuraAhorcado= new ArrayList<>();
        inicializarFigura();    
    }
    
    protected void inicializarFigura(){
        figuraAhorcado.add("");
        figuraAhorcado.add(" O ");
        figuraAhorcado.add(" O\n | ");
        figuraAhorcado.add(" O\n/| ");
        figuraAhorcado.add(" O\n/|\\");
        figuraAhorcado.add(" O\n/|\\\n/ ");
        figuraAhorcado.add(" O\n/|\\\n/ \\");
    }
    
    public abstract void actualizarPalabraActual(char letra);
    public abstract boolean verifivarLetra(char letra);
    public abstract boolean hasGanado();
    
    
    
    
    
}
