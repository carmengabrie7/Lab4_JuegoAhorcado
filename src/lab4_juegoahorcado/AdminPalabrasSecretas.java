package lab4_juegoahorcado;

import java.util.ArrayList;
import java.util.Random;


public class AdminPalabrasSecretas {
    //Gestiona dinamicamente las palabras del juego
    ArrayList<String> palabrasDisponibles;
    
    
    public AdminPalabrasSecretas (){
        palabrasDisponibles = new ArrayList<String>();
    }
    
    public boolean agregarPalabras (String palabra) 
            throws AdminException.palabraDuplicada{
        if (palabrasDisponibles.contains(palabra)){
            throw new AdminException.palabraDuplicada("La palabra ya existe.");
        }  
        palabrasDisponibles.add(palabra);
        return true;
    }
    
    public String obtenerRandom ()
    throws AdminException.sinPalabras{
        if (palabrasDisponibles.isEmpty()){
            throw new AdminException.sinPalabras("No hay palabras disponibles.");
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
}
