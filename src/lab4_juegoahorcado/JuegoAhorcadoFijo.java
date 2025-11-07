package lab4_juegoahorcado;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

public JuegoAhorcadoFijo(String palabra){
    super();
    palabraSecreta=palabra.toUpperCase();
      this.palabraActual = "_".repeat(this.palabraSecreta.length());
}

public void inicializarPalabraSecreta(){
    intentos=limiteIntentos;
    letrasUsadas.clear();
    palabraActual = "_".repeat(this.palabraSecreta.length());
}

public boolean verificarLetra(char letra){
    letra=Character.toUpperCase(letra);
    
    if(letrasUsadas.contains(letra)){
        System.out.println("Letra Repetida");
        return false;
    }
    letrasUsadas.add(letra);
    
    if(palabraSecreta.indexOf(letra) >= 0){
        System.out.println("Letra correcta: ");
        return true;
    }else{
        intentos--;
        System.out.println("Letra Incorrecta");
        System.out.println("Intentos restantes: "+intentos);
        return false;
    }  
}

@Override
public void actualizarPalabraActual(char letra){
    letra=Character.toUpperCase(letra);
    String nuevaPalabra= "";
    
    for(int i=0; i< palabraSecreta.length(); i++){
         char letraSecreta = palabraSecreta.charAt(i);
         char letraActual = palabraActual.charAt(i);
         
         if(letraSecreta== letra){
             nuevaPalabra+= letra;
         }else{
             nuevaPalabra+= letraActual;
         }
    }
    palabraActual=nuevaPalabra;
}
@Override
public boolean hasGanado(){
   return palabraActual.equals(palabraSecreta);
}

 public String getFiguraAhorcadoActual() {
        int errores = limiteIntentos - intentos;
        if (errores >= 0 && errores < figuraAhorcado.size()) {
            return figuraAhorcado.get(errores);
        }
        return "";
    }

@Override
public void jugar(char letra){
    boolean esCorrecta = verificarLetra(letra);
    
    if(esCorrecta){
        actualizarPalabraActual(letra);
        System.out.println("Palabra Actrual: "+palabraActual);
    }
    
    if(hasGanado()){
        System.out.println("Has Ganado! La palabra era: "+palabraSecreta);
        return;
    }
    
    if(intentos <= 0){
        System.out.println("Has perdido, suerte a la proxima! La palabra era: "+palabraSecreta);
    }
}
    
}
