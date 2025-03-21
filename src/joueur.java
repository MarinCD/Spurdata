import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DATA.DBConnection;
import DATA.Joueurs;
import collec.JoueursCollec;

public class joueur {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel buttonPanel; // Panneau pour les boutons
    private JButton InsererBDD;
    private JButton btnNewButton;
    private JLabel TextError;
    private JButton SelectXMLButton;
    private JMenuBar menuBar;


    public joueur() {
        // Création de la fenêtre
        frame = new JFrame("Fenêtre Joueurs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); // Utilisation de BorderLayout pour la disposition

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"Nom", "Prénom", "Age", "Taille", "Poid"};
        tableModel = new NonEditableTableModel(columnNames, 0);

        // Remplissage de la table avec les données
        List<Joueurs> joueurs = JoueursCollec.getJoueurs();
        for (Joueurs joueur : joueurs) {
            Object[] rowData = {
                joueur.getNom(),
                joueur.getPrenom(),
                joueur.getAge(),
                joueur.getTaille(),
                joueur.getPoid(),
            };
            tableModel.addRow(rowData);
        }

        // Création de la table
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Permet une seule ligne sélectionnée à la fois

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER); // Ajouter la table au centre de la fenêtre

        // Création du panneau pour les boutons (placé en bas)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout pour centrer les boutons
        frame.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le panneau des boutons en bas

        // Ajout des boutons dans le panneau
        btnNewButton = new JButton("Retour");
        buttonPanel.add(btnNewButton);

        InsererBDD = new JButton("Insérer XML dans BDD");
        buttonPanel.add(InsererBDD);

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
                frame.setVisible(false); // Masquer la fenêtre actuelle
                new Interface(); // Ouvrir la fenêtre suivante "Interface"
            }
        });

        // Action pour le bouton Insérer XML dans BDD
        InsererBDD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent el) {
                if (TextError != null) {
                    buttonPanel.remove(TextError); // Supprimer l'ancien message d'erreur
                }

                try (Connection connection = DBConnection.connectionBDD()) {
                    Usine usine = new Usine();
                    boolean insertionReussie = usine.JoueurtoBase(connection);

                    if (insertionReussie) {
                        TextError = new JLabel("Insertion réussie");
                    } else {
                        TextError = new JLabel("Erreur d'insertion");
                    }

                    buttonPanel.add(TextError); // Ajouter le message de succès ou erreur

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
                        Usine.XMLTableJoueurs(filePath); // Traiter le fichier XML

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
        
        // Ajout d'un listener pour détecter la sélection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object firstCellValue = table.getValueAt(selectedRow, 0);
                        if (firstCellValue != null) {
                            // Passez 'frame' comme parent ici
                            NouvelleFenetre.ouvrirFenetre(frame, firstCellValue.toString());
                        }
                    }
                }
            }
        });

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
}
