package lab4_juegoahorcado;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class JuegoGUI extends JFrame {

    private JuegoAhorcadoBase juego;
   public JuegoGUI() {
      setTitle("Juego Ahorcado");
      setSize(600,500);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setLayout(null);
      
      JLabel fondo = new JLabel();
      fondo.setBounds(0, 0, 600, 500);
        fondo.setLayout(null);
        fondo.setOpaque(true);
        fondo.setForeground(Color.white);
        add(fondo);
      
      
      JLabel titulo = new JLabel ("JUEGO DEL AHORCADO", SwingConstants.CENTER);
      titulo.setFont(new Font("Times New Roman", Font.BOLD, 22));
      titulo.setBounds(0, 30, 600, 20);
      fondo.add(titulo);
      
      JLabel lblPalabra = new JLabel("_ _ _ _ _", SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Consolas", Font.BOLD, 28));
        lblPalabra.setBounds(0, 100, 600, 40);
        fondo.add(lblPalabra);

        JLabel lblFigura = new JLabel();
        lblFigura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblFigura.setBounds(0, 150, 600, 120);
        fondo.add(lblFigura);

        JLabel lblIntentos = new JLabel("Intentos restantes: 6", SwingConstants.CENTER);
        lblIntentos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblIntentos.setBounds(0, 280, 600, 30);
        fondo.add(lblIntentos);

        JLabel lblMensaje = new JLabel("Seleccione modo de juego", SwingConstants.CENTER);
             lblMensaje.setBounds(0, 320, 600, 30);
        fondo.add(lblMensaje);

        JTextField txtLetra = new JTextField();
        txtLetra.setBounds(230, 360, 50, 30);
        txtLetra.setFont(new Font("Arial", Font.BOLD, 20));
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        fondo.add(txtLetra);

        JButton btnAdivinar = new JButton("Adivinar");
        btnAdivinar.setBounds(300, 360, 100, 30);
        btnAdivinar.setEnabled(false);
        fondo.add(btnAdivinar);

        JButton btnReiniciar = new JButton("Reiniciar");
       btnReiniciar.setBounds(410, 360, 100, 30);
        btnReiniciar.setEnabled(false);
        fondo.add(btnReiniciar);

        JButton btnFijo = new JButton("Palabra Fija");
        btnFijo.setBounds(180, 410, 120, 30);
        fondo.add(btnFijo);
        
        JButton btnAzar = new JButton("Palabra al Azar");
        btnAzar.setBounds(320, 410, 150, 30);
        fondo.add(btnAzar);
        
        btnFijo.addActionListener(e -> {
            
        });

        // Acción: modo palabra aleatoria
        btnAzar.addActionListener(e -> {
            AdminPalabrasSecretas admin = new AdminPalabrasSecretas();
            admin.agregarPalabras("PROGRAMAR");
            admin.agregarPalabras("AHORCADO");
            admin.agregarPalabras("COMPUTADORA");
            
        });

        // Acción: adivinar letra
        btnAdivinar.addActionListener(e -> {
            String entrada = txtLetra.getText().toUpperCase();
            if (entrada.length() == 1) {
                char letra = entrada.charAt(0);
                lblMensaje.setText(juego.verificarLetra(letra));
                lblPalabra.setText(juego.getPalabraActual());
                lblIntentos.setText("Intentos restantes: " + juego.getIntentos());
                lblFigura.setText("<html>" + juego.getFiguraHTML() + "</html>");

                if (juego.hasGanado()) {
                    lblMensaje.setText("🎉 ¡Ganaste! La palabra era " + juego.getPalabraSecreta());
                    btnAdivinar.setEnabled(false);
                } else if (juego.getIntentos() == 0) {
                    lblMensaje.setText("😢 Perdiste. La palabra era " + juego.getPalabraSecreta());
                    btnAdivinar.setEnabled(false);
                }
            } else {
                lblMensaje.setText("Ingrese una sola letra");
            }
            txtLetra.setText("");
        });

        btnReiniciar.addActionListener(e -> {
            lblFigura.setText("");
            lblMensaje.setText("Seleccione modo de juego");
            lblPalabra.setText("_ _ _ _ _");
            lblIntentos.setText("Intentos restantes: 6");
            txtLetra.setText("");
            btnAdivinar.setEnabled(false);
            btnReiniciar.setEnabled(false);
        });
        
                fondo.setVisible(true);


        
} 
   
   public static void main (String [] args){
       JuegoGUI juego = new JuegoGUI();
       juego.setVisible(true);
       
   }
}
