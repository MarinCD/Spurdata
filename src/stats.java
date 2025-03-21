import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DATA.DBConnection;
import DATA.Statcard;
import collec.StatCardCollec;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Scrollbar;
import javax.swing.JMenu;

public class stats {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel buttonPanel; // Panneau pour les boutons
    private JButton Retour;
    private JButton InsererBDD;
    private JButton SelectXMLButton; // Bouton pour sélectionner un fichier XML
    private JLabel TextError;
    private JMenuBar menuBar;

    public stats() {
        // Création de la fenêtre
        frame = new JFrame("Fenêtre Stats");
        frame.setResizable(false);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout()); // Utilisation de BorderLayout

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"TD jeu", "Points", "Rebonds", "Passe D", "Nom", "Match ID"};
        tableModel = new NonEditableTableModel(columnNames, 0); // 0 indique le nombre de lignes initiales

        List<Statcard> statcards = StatCardCollec.getStats();

        // Parcourir chaque match et ajouter une ligne dans le tableau
        for (Statcard stat : statcards) {
            Object[] rowData = {
                stat.getTDJeu(),
                stat.getPoints(),
                stat.getRebonds(),
                stat.getPasse_D(),
                stat.getNom(),
                stat.getMatchID()
            };
            tableModel.addRow(rowData);
        }

        // Création de la table
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30); // Hauteur des lignes de la table
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Police pour le texte de la table
        table.setGridColor(Color.LIGHT_GRAY); // Couleur de la grille
        table.getTableHeader().setReorderingAllowed(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permet une seule ligne sélectionnée à la fois

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajout du JScrollPane à la fenêtre
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Création du panneau pour les boutons (placé en bas)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout pour centrer les boutons
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Création et ajout des boutons au panneau
        Retour = new JButton("Retour");
        buttonPanel.add(Retour);
        buttonPanel.add(Box.createVerticalStrut(10)); // Espacer de 10 pixels

        InsererBDD = new JButton("Insérer XML dans BDD");
        buttonPanel.add(InsererBDD);

        buttonPanel.add(Box.createVerticalStrut(10)); // Espacer de 10 pixels

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
        Retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre actuelle
                new Interface(); // Ouvrir la fenêtre Interface
            }
        });

        // Action pour le bouton Insérer XML dans BDD
        InsererBDD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (TextError != null) {
                    buttonPanel.remove(TextError); // Supprimer l'ancien message d'erreur, s'il existe
                }

                try (Connection connection = DBConnection.connectionBDD()) {
                    Usine usine = new Usine();
                    boolean insertionReussie = usine.StatcardtoBase(connection);

                    if (insertionReussie) {
                        TextError = new JLabel("Insertion réussie");
                    } else {
                        TextError = new JLabel("Erreur d'insertion");
                    }

                    buttonPanel.add(TextError); // Ajouter le message de succès ou erreur

                } catch (Exception ex) {
                    TextError = new JLabel("Erreur d'insertion : " + ex.getMessage());
                    buttonPanel.add(TextError);
                }

                buttonPanel.revalidate();
                buttonPanel.repaint();
            }
        });

        SelectXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Sélectionner un fichier XML");

                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        // Appel de la méthode pour traiter le fichier XML
                        Usine.XMLTableStats(filePath); // Traiter le fichier XML

                        // Message de succès
                        JOptionPane.showMessageDialog(frame, 
                            "Fichier XML traité avec succès : " + filePath, 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);

                        // Recharger les données et mettre à jour le tableau
                        List<Statcard> statcards = StatCardCollec.getStats(); // Récupérer les nouvelles données

                        // Vider les anciennes lignes de la table
                        tableModel.setRowCount(0);

                        // Ajouter les nouvelles lignes de données à la table
                        for (Statcard stat : statcards) {
                            Object[] rowData = {
                                stat.getTDJeu(),
                                stat.getPoints(),
                                stat.getRebonds(),
                                stat.getPasse_D(),
                                stat.getNom(),
                                stat.getMatchID()
                            };
                            tableModel.addRow(rowData);
                        }

                        // Rafraîchir l'affichage de la table
                        table.revalidate();
                        table.repaint();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, 
                            "Erreur lors du traitement du fichier XML : " + ex.getMessage(), 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Ajout d'un listener pour détecter la sélection dans la table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object firstCellValue = table.getValueAt(selectedRow, 0);
                        Object secondCellValue = table.getValueAt(selectedRow, 1);
                        Object thirdCellValue = table.getValueAt(selectedRow, 2);
                        Object fourthCellValue = table.getValueAt(selectedRow, 3);
                        Object fiveCellValue = table.getValueAt(selectedRow, 4);
                        Object sixCellValue = table.getValueAt(selectedRow, 5);

                        if (firstCellValue != null) {
                            statsdts.ouvrirFenetre(frame, firstCellValue.toString(), secondCellValue.toString(),
                                thirdCellValue.toString(), fourthCellValue.toString(),
                                fiveCellValue.toString(), sixCellValue.toString());
                        }
                    }
                }
            }
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }
}
