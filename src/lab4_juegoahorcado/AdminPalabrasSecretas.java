package lab4_juegoahorcado;

import java.util.ArrayList;
import java.util.Random;

public class AdminPalabrasSecretas {
    
    private ArrayList<String> palabrasDisponibles;

    
    public AdminPalabrasSecretas() {
        palabrasDisponibles = new ArrayList<>();
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
        palabrasDisponibles.add("ABSTRACTO");
    }

    
    public boolean agregarPalabras(String palabra)
            throws AdminException.palabraDuplicada {
        if (palabra == null || palabra.trim().isEmpty()) {
            return false; 
        }

        palabra = palabra.toUpperCase();

        if (palabrasDisponibles.contains(palabra)) {
            throw new AdminException.palabraDuplicada("La palabra ya existe.");
        }

        palabrasDisponibles.add(palabra);
        return true;
    }

   
    public String obtenerRandom()
            throws AdminException.sinPalabras {
        if (palabrasDisponibles.isEmpty()) {
            throw new AdminException.sinPalabras("No hay palabras disponibles.");
        }

        Random random = new Random();
        int indice = random.nextInt(palabrasDisponibles.size());
        return palabrasDisponibles.get(indice);
    }

   
    public void mostrarPalabras() {
        System.out.println("Palabras Disponibles:");
        for (String p : palabrasDisponibles) {
            System.out.println("- " + p);
        }
    }

    
    public ArrayList<String> getPalabrasDisponibles() {
        return new ArrayList<>(palabrasDisponibles);
    }
}
