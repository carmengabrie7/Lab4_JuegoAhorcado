package lab4_juegoahorcado;

import java.util.ArrayList;
import java.util.Random;


public class AdminPalabrasSecretas {
    //Gestiona dinamicamente las palabras del juego
    ArrayList<String> palabrasDisponibles = new ArrayList<String>();
    
    public AdminPalabrasSecretas (ArrayList<String> palabrasDisponibles){
        this.palabrasDisponibles=palabrasDisponibles;
    }
    
    public AdminPalabrasSecretas(){
        this.palabrasDisponibles = new ArrayList<>();
        agregarPalabrasIniciales();
    }
    
    private void agregarPalabrasIniciales() {
        palabrasDisponibles.add("JAVA");
        palabrasDisponibles.add("PROGRAMACION");
        palabrasDisponibles.add("COMPUTADORA");
        palabrasDisponibles.add("AHORCADO");
        palabrasDisponibles.add("INTERFAZ");
        palabrasDisponibles.add("HERENCIA");
        palabrasDisponibles.add("POLIMORFISMO");
        palabrasDisponibles.add("ENCAPSULAMIENTO");
    }
    
    public boolean agregarPalabras (String palabra){
        if (palabra != null && !palabra.trim().isEmpty()) {
            palabrasDisponibles.add(palabra.toUpperCase());
            return true;
        }
        return false;
    }
    
    public String obtenerRandom (){
       if (palabrasDisponibles.isEmpty()) {
            return "JAVA"; // Palabra por defecto si no hay palabras
        }
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
    
    public ArrayList<String> getPalabrasDisponibles() {
        return new ArrayList<>(palabrasDisponibles);
    }
}
