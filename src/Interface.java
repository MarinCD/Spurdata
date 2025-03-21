import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;

public class Interface {

    private JFrame frame;
    private JButton btnDireMatchs;
    private JButton btnStats;
    private JButton btnJoueurs;


    public Interface() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        // Initialisation de la fenêtre d'accueil
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);

        JPanel panelMessage = new JPanel();
        JPanel panelTitre = new JPanel();
        panelMessage.add(panelTitre);

        JLabel lblTitre = new JLabel("Spurdata");
        lblTitre.setFont(new Font("Serif", Font.BOLD, 18));
        panelTitre.add(lblTitre);

        frame.getContentPane().add(panelMessage);

        JPanel panelBoutons = new JPanel();
        btnDireMatchs = new JButton("Matchs");
        btnStats = new JButton("Stats");
        btnJoueurs = new JButton("Joueurs");


        // Action pour le bouton "Matchs"
        btnDireMatchs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre d'accueil
                new match(); // Ouvrir la nouvelle fenêtre "Match"
            }
        });

        // Action pour le bouton "Stats"
        btnStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre d'accueil
                new stats(); // Ouvrir la nouvelle fenêtre "Stats"
            }
        });

        // Action pour le bouton "Joueurs"
        btnJoueurs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre d'accueil
                new joueur(); // Ouvrir la nouvelle fenêtre "Joueurs"
            }
        });

        panelBoutons.add(btnDireMatchs);
        panelBoutons.add(btnStats);
        panelBoutons.add(btnJoueurs);

        frame.getContentPane().add(panelBoutons);
    }
}
