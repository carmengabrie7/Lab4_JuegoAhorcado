package lab4_juegoahorcado;

import java.util.ArrayList;
import java.util.Random;


public class AdminPalabrasSecretas {
    //Gestiona dinamicamente las palabras del juego
    ArrayList<String> palabrasDisponibles = new ArrayList<String>();
    
    public AdminPalabrasSecretas (ArrayList<String> palabrasDisponibles){
        this.palabrasDisponibles=palabrasDisponibles;
    }
    
    public boolean agregarPalabras (String palabra){
        palabrasDisponibles.add(palabra);
        return true;
    }
    
    public String obtenerRandom (){
        Random random = new Random();
        int indice = random.nextInt(palabrasDisponibles.size());
        return palabrasDisponibles.get(indice);
    }
    
    public void mostrarPalabras(){
        System.out.println("Palabras Disponibles");
        for (String p : palabrasDisponibles){
            System.out.println("- "+p);
        }
    }
}
