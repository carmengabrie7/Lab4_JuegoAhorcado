package lab4_juegoahorcado;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JuegoGUI extends JFrame {
    private JuegoAhorcadoBase juego;

    public JuegoGUI(String modo) {
        setTitle("Juego del Ahorcado");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        ImageIcon logo = new ImageIcon("src/lab4_juegoahorcado/logo.png"); 
        setIconImage(logo.getImage()); 

        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 600, 500);
        fondo.setOpaque(true);
        fondo.setBackground(new Color(30, 30, 30));
        fondo.setLayout(null);
        add(fondo);

        JLabel titulo = new JLabel("🎯 JUEGO DEL AHORCADO", SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(0, 30, 600, 30);
        fondo.add(titulo);

        JLabel lblPalabra = new JLabel("_ _ _ _ _", SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Consolas", Font.BOLD, 28));
        lblPalabra.setForeground(Color.WHITE);
        lblPalabra.setBounds(0, 100, 600, 40);
        fondo.add(lblPalabra);

        JLabel lblFigura = new JLabel("", SwingConstants.CENTER);
        lblFigura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblFigura.setForeground(Color.WHITE);
        lblFigura.setBounds(0, 150, 600, 120);
        fondo.add(lblFigura);

        JLabel lblIntentos = new JLabel("Intentos restantes: 6", SwingConstants.CENTER);
        lblIntentos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblIntentos.setForeground(Color.WHITE);
        lblIntentos.setBounds(0, 280, 600, 30);
        fondo.add(lblIntentos);

        JLabel lblMensaje = new JLabel("Seleccione modo de juego", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.LIGHT_GRAY);
        lblMensaje.setBounds(0, 320, 600, 30);
        fondo.add(lblMensaje);

        JTextField txtLetra = new JTextField();
        txtLetra.setBounds(230, 360, 50, 30);
        txtLetra.setFont(new Font("Arial", Font.BOLD, 20));
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        fondo.add(txtLetra);

        JButton btnAdivinar = new JButton("Adivinar");
        btnAdivinar.setBounds(300, 360, 100, 30);
        fondo.add(btnAdivinar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(420, 360, 100, 30);
        fondo.add(btnVolver);

        
        try {
            if ("fijo".equalsIgnoreCase(modo)) {
                // Asegúrate que JuegoAhorcadoFijo NO sea abstracta
                juego = new JuegoAhorcadoFijo("JAVA");
            } else {
                AdminPalabrasSecretas admin = new AdminPalabrasSecretas();
                
                juego = new JuegoAhorcadoAzar(admin);
            }
            juego.inicializarPalabraSecreta();
        } catch (Throwable t) {
            
            Logger.getLogger(JuegoGUI.class.getName()).log(Level.SEVERE, "Error iniciando juego", t);
            JOptionPane.showMessageDialog(this,
                    "No se pudo iniciar el juego:\n" + t.getClass().getSimpleName() + ": " + t.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            new MenuAhorcado();
            return;
        }

        lblPalabra.setText(espaciada(juego.palabraActual));
        lblIntentos.setText("Intentos restantes: " + juego.intentos);
        lblFigura.setText("<html>" + figuraNivel(juego, 0) + "</html>");
        lblMensaje.setText("Ingrese una letra:");

        btnAdivinar.addActionListener(e -> {
            String entrada = txtLetra.getText().trim().toUpperCase();
            txtLetra.setText("");

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                lblMensaje.setText("Ingrese una sola letra (A-Z)");
                return;
            }

            char letra = entrada.charAt(0);

            boolean acierto;
            try {
                acierto = juego.verificarLetra(letra);
                if (acierto) juego.actualizarPalabraActual(letra);
            } catch (Throwable t) {
                lblMensaje.setText("Error: " + t.getMessage());
                return;
            }

            lblPalabra.setText(espaciada(juego.palabraActual));
            lblIntentos.setText("Intentos restantes: " + juego.intentos);

            int nivel = Math.min(juego.limiteIntentos - juego.intentos,
                                 Math.max(0, juego.figuraAhorcado.size() - 1));
            lblFigura.setText("<html>" + figuraNivel(juego, nivel) + "</html>");
            lblMensaje.setText(acierto ? "¡Letra correcta!" : "Letra incorrecta");

            if (juego.hasGanado()) {
                lblMensaje.setText("🎉 ¡Ganaste! La palabra era " + juego.palabraSecreta);
                btnAdivinar.setEnabled(false);
            } else if (juego.intentos <= 0) {
                lblMensaje.setText("😢 Perdiste. La palabra era " + juego.palabraSecreta);
                btnAdivinar.setEnabled(false);
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new MenuAhorcado();
        });

        setVisible(true);
    }

    private String espaciada(String s) {
        return s == null ? "" : s.replace("", " ").trim();
    }

    private String figuraNivel(JuegoAhorcadoBase j, int n) {
        if (j == null || j.figuraAhorcado == null || j.figuraAhorcado.isEmpty()) return "";
        n = Math.max(0, Math.min(n, j.figuraAhorcado.size() - 1));
        return j.figuraAhorcado.get(n).replace("\n", "<br>");
    }

    public JuegoGUI() { this("fijo"); }

    public static void main(String[] args) { new JuegoGUI("azar"); }
}
