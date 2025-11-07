package lab4_juegoahorcado;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuAhorcado extends JFrame {

    public MenuAhorcado() {
        setTitle("Juego de Ahorcado");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Panel de fondo con color similar al de tu imagen
        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(102, 121, 153)); // azul grisáceo
        fondo.setBounds(0, 0, 600, 500);
        fondo.setLayout(null);
        add(fondo);

        // Imagen del ahorcado a la izquierda
        JLabel dibujo = new JLabel();
        dibujo.setBounds(10, 70, 220, 320);
        dibujo.setIcon(new ImageIcon("src/lab4_juegoahorcado/ahorcado (2).png")); // ruta de tu imagen
        fondo.add(dibujo);

        // Título
        JLabel titulo = new JLabel("AHORCADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 34));
        titulo.setForeground(Color.BLACK);
        titulo.setBounds(300, 80, 230, 40);
        fondo.add(titulo);

        // Botón "Jugar"
        JButton btnJugar = new JButton("Jugar");
        btnJugar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnJugar.setBounds(320, 170, 200, 50);
        btnJugar.setBackground(new Color(175, 191, 216));
        btnJugar.setFocusPainted(false);
        btnJugar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fondo.add(btnJugar);

        // Botón "Salir"
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnSalir.setBounds(320,250, 200, 50);
        btnSalir.setBackground(new Color(175, 191, 216));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fondo.add(btnSalir);

        // Acción del botón Jugar → abre el juego
        btnJugar.addActionListener(e -> {
                  
            new JuegoGUI().setVisible(true);
            dispose(); // cerrar el menú
        });

        // Acción del botón Salir → cierra la aplicación
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuAhorcado();
    }
}


