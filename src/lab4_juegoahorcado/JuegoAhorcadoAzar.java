/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_juegoahorcado;

/**
 *
 * @author Gabriel
 */

import java.util.ArrayList;
import java.util.Random;


public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    
    private AdminPalabrasSecretas adminPalabras;
    private Random random;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas adminPalabras) {
        this.adminPalabras = adminPalabras;
        this.random = new Random();
        this.intentos = limiteIntentos;
        this.letrasUsadas = new ArrayList<>();
        
    }
    
    @Override
public void inicializarPalabraSecreta() {
    try {
        this.palabraSecreta = adminPalabras.obtenerRandom().toUpperCase();
    } catch (AdminException.sinPalabras e) {
        this.palabraSecreta = "JAVA";
    }
    this.palabraActual = "_".repeat(palabraSecreta.length());
    this.intentos = limiteIntentos;
    this.letrasUsadas.clear();
}


   
    @Override
    public void jugar(char letra) {
        try {
            intentarLetra(letra);
        } catch (LetraRepetidaException | JuegoTerminadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        char[] actual = palabraActual.toCharArray();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (Character.toLowerCase(palabraSecreta.charAt(i)) == Character.toLowerCase(letra)) {
                actual[i] = palabraSecreta.charAt(i);
            }
        }
        palabraActual = new String(actual);
    }

    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.toLowerCase().contains(String.valueOf(Character.toLowerCase(letra)));
    }

    @Override
    public boolean hasGanado() {
        return !palabraActual.contains("_");
    }


    public boolean intentarLetra(char letra) throws LetraRepetidaException, JuegoTerminadoException {
        if (isJuegoTerminado()) {
            throw new JuegoTerminadoException("El juego ya ha terminado");
        }
        
        char letraLower = Character.toLowerCase(letra);
        if (letrasUsadas.contains(letraLower)) {
            throw new LetraRepetidaException("La letra '" + letra + "' ya fue utilizada");
        }

        letrasUsadas.add(letraLower);
        boolean acierto = verificarLetra(letra);

        if (acierto) {
            actualizarPalabraActual(letra);
        } else {
            intentos--;
        }

        return acierto;
    }


    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    
    public String getPalabraActual() {
        return palabraActual;
    }


    public int getIntentosRestantes() {
        return intentos;
    }

    
    public int getLimiteIntentos() {
        return limiteIntentos;
    }

    
    public boolean isJuegoTerminado() {
        return hasGanado() || intentos <= 0;
    }

    public String getFiguraAhorcadoActual() {
        int errores = limiteIntentos - intentos;
        if (errores >= 0 && errores < figuraAhorcado.size()) {
            return figuraAhorcado.get(errores);
        }
        return "";
    }
}
