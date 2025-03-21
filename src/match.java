import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DATA.DBConnection;
import DATA.Match;
import collec.MatchCollec;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class match {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel buttonPanel; // Panneau pour les boutons
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton InsererBDD;
    private JLabel TextError;
    private JButton SelectXMLButton;
    private JMenuBar menuBar;


    public match() {
        // Création de la fenêtre
        frame = new JFrame("Fenêtre Matchs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); // Utilisation de BorderLayout pour la disposition

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"Match ID", "Adversaire", "Date", "Lieu", "Résultat", "Diffscore"};
        tableModel = new NonEditableTableModel(columnNames, 0);

        // Remplissage de la table
        List<Match> matchs = MatchCollec.getMatchs();
        for (Match match : matchs) {
            Object[] rowData = {
                match.getMatchID(),
                match.getAdversaire(),
                match.getDate(),
                match.getLieu(),
                match.getResultat(),
                match.getDifScore()
            };
            tableModel.addRow(rowData);
        }

        // Création de la table
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER); // Ajouter le tableau au centre

        // Panneau des boutons (placé en bas)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Layout pour centrer les boutons
        frame.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le panneau des boutons en bas

        // Bouton Retour
        btnNewButton = new JButton("Retour");
        buttonPanel.add(btnNewButton);

        // Bouton Insérer XML dans BDD
        InsererBDD = new JButton("Insérer XML dans BDD");
        buttonPanel.add(InsererBDD);

        // Bouton Sélectionner un fichier XML
        SelectXMLButton = new JButton("Sélectionner un fichier XML");
        buttonPanel.add(SelectXMLButton);
        
        
     // Créer une barre de menu (JMenuBar)
        menuBar = new JMenuBar();

        // Créer un menu (JMenu)
        JMenu fileMenu = new JMenu("Menu");

        // Créer des éléments de menu (JMenuItem)
        JMenuItem matchItem = new JMenuItem("Match");
        JMenuItem statItem = new JMenuItem("Stats");
        JMenuItem joueurItem = new JMenuItem("Joueurs");
        JMenuItem exitItem = new JMenuItem("Quitter");

        // Ajouter les éléments au menu "Fichier"
        fileMenu.add(matchItem);
        fileMenu.add(statItem);
        fileMenu.add(joueurItem);
        fileMenu.addSeparator();  // Séparateur entre les éléments de menu
        fileMenu.add(exitItem);

        // Ajouter le menu "Fichier" à la barre de menu
        menuBar.add(fileMenu);

        // Assigner la barre de menu à la fenêtre
        frame.setJMenuBar(menuBar);


        // Action pour le bouton "Matchs"
        matchItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre d'accueil
                new match(); // Ouvrir la nouvelle fenêtre "Match"
            }
        });

        // Action pour le bouton "Stats"
        statItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre d'accueil
                new stats(); // Ouvrir la nouvelle fenêtre "Stats"
            }
        });

        // Action pour le bouton "Joueurs"
        joueurItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre d'accueil
                new joueur(); // Ouvrir la nouvelle fenêtre "Joueurs"
            }
        });
        
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Quitter l'application
            }
        });
        

        // Action pour le bouton Retour
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Interface();
            }
        });

        // Action pour le bouton Insérer XML dans BDD
        InsererBDD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent el) {
                if (TextError != null) {
                    buttonPanel.remove(TextError);
                }

                try (Connection connection = DBConnection.connectionBDD()) {
                    Usine usine = new Usine();
                    boolean insertionReussie = usine.MatchtoBase(connection);

                    if (insertionReussie) {
                        TextError = new JLabel("Insertion réussie");
                    } else {
                        TextError = new JLabel("Erreur d'insertion");
                    }

                    buttonPanel.add(TextError);

                } catch (Exception e) {
                    TextError = new JLabel("Erreur d'insertion");
                    buttonPanel.add(TextError);
                }

                buttonPanel.revalidate();
                buttonPanel.repaint();
            }
        });

        // Action pour le bouton Sélectionner un fichier XML
        SelectXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Sélectionner un fichier XML");

                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        Usine.XMLTableMatch(filePath);

                        JOptionPane.showMessageDialog(frame,
                                "Fichier XML traité avec succès : " + filePath,
                                "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Erreur lors du traitement du fichier XML : " + ex.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }
}
