package lab4_juegoahorcado;
public class AdminException extends Exception{
    
    //validar que no se agreguen duplicados
    public static class palabraDuplicada extends Exception{
        public palabraDuplicada (String mensaje){
            super(mensaje);
        }
    }
    //validar que haya palabras disponibles
    public static class sinPalabras extends Exception{
        public sinPalabras (String mensaje){
            super(mensaje);
        }
    }
    
}
