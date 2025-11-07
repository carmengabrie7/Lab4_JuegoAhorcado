package lab4_juegoahorcado;

import java.awt.*;
import javax.swing.*;
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
        fondo.setBackground(new Color(30, 30, 30)); // gris oscuro
        fondo.setLayout(null);
        add(fondo);

        // TÍTULO
        JLabel titulo = new JLabel("JUEGO DEL AHORCADO", SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(0, 30, 600, 30);
        fondo.add(titulo);

        // PALABRA
        JLabel lblPalabra = new JLabel("_ _ _ _ _", SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Consolas", Font.BOLD, 28));
        lblPalabra.setForeground(Color.WHITE);
        lblPalabra.setBounds(0, 100, 600, 40);
        fondo.add(lblPalabra);

        // FIGURA DEL AHORCADO
        JLabel lblFigura = new JLabel("", SwingConstants.CENTER);
        lblFigura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblFigura.setForeground(Color.WHITE);
        lblFigura.setBounds(0, 150, 600, 120);
        fondo.add(lblFigura);

        // INTENTOS
        JLabel lblIntentos = new JLabel("Intentos restantes: 6", SwingConstants.CENTER);
        lblIntentos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblIntentos.setForeground(Color.WHITE);
        lblIntentos.setBounds(0, 280, 600, 30);
        fondo.add(lblIntentos);

        // MENSAJE
        JLabel lblMensaje = new JLabel("Seleccione modo de juego", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.LIGHT_GRAY);
        lblMensaje.setBounds(0, 320, 600, 30);
        fondo.add(lblMensaje);

        // CAMPO DE TEXTO
        JTextField txtLetra = new JTextField();
        txtLetra.setBounds(230, 360, 50, 30);
        txtLetra.setFont(new Font("Arial", Font.BOLD, 20));
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        fondo.add(txtLetra);

        // BOTONES
        JButton btnAdivinar = new JButton("Adivinar");
        btnAdivinar.setBounds(300, 360, 100, 30);
        btnAdivinar.setEnabled(false);
        fondo.add(btnAdivinar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(420, 360, 100, 30);
        fondo.add(btnVolver);

        // --- INICIALIZAR EL JUEGO SEGÚN EL MODO ---
        if (modo.equalsIgnoreCase("fijo")) {
            juego = new JuegoAhorcadoFijo("JAVA");
            juego.inicializarPalabraSecreta();
        } else {
            AdminPalabrasSecretas admin = new AdminPalabrasSecretas();
            try {
                admin.agregarPalabras("PROGRAMAR");
                admin.agregarPalabras("AHORCADO");
                admin.agregarPalabras("COMPUTADORA");
                juego = new JuegoAhorcadoAzar(admin.obtenerRandom());
                juego.inicializarPalabraSecreta();
            } catch (AdminException.palabraDuplicada | AdminException.sinPalabras ex) {
                Logger.getLogger(JuegoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // MOSTRAR ESTADO INICIAL
        lblPalabra.setText(juego.palabraActual.replace("", " ").trim());
        lblIntentos.setText("Intentos restantes: " + juego.intentos);
        lblMensaje.setText("Ingrese una letra:");
        lblFigura.setText("<html>" + juego.figuraAhorcado.get(0).replace("\n", "<br>") + "</html>");
        btnAdivinar.setEnabled(true);

        // --- BOTÓN ADIVINAR ---
        btnAdivinar.addActionListener(e -> {
            String entrada = txtLetra.getText().toUpperCase();
            if (entrada.length() == 1) {
                char letra = entrada.charAt(0);
                boolean acierto = juego.verificarLetra(letra);

                lblPalabra.setText(juego.palabraActual.replace("", " ").trim());
                lblIntentos.setText("Intentos restantes: " + juego.intentos);

                int nivel = juego.limiteIntentos - juego.intentos;
                if (nivel >= juego.figuraAhorcado.size()) nivel = juego.figuraAhorcado.size() - 1;
                lblFigura.setText("<html>" + juego.figuraAhorcado.get(nivel).replace("\n", "<br>") + "</html>");

                if (acierto) {
                    lblMensaje.setText("¡Letra correcta!");
                } else {
                    lblMensaje.setText("Letra incorrecta.");
                }

                if (juego.hasGanado()) {
                    lblMensaje.setText("🎉 ¡Ganaste! La palabra era " + juego.palabraSecreta);
                    btnAdivinar.setEnabled(false);
                } else if (juego.intentos == 0) {
                    lblMensaje.setText("😢 Perdiste. La palabra era " + juego.palabraSecreta);
                    btnAdivinar.setEnabled(false);
                }

            } else {
                lblMensaje.setText("Ingrese solo una letra");
            }
            txtLetra.setText("");
        });

        // --- BOTÓN VOLVER ---
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuAhorcado(); // volver al menú principal
        });

        setVisible(true);
    }

    // constructor vacío (para pruebas directas)
    public JuegoGUI() {
        this("fijo");
    }

    public static void main(String[] args) {
        new JuegoGUI("azar");
    }
}
