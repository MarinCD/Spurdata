import javax.swing.*;
import java.awt.*;
import DATA.Statcard;
import collec.StatCardCollec;

public class NouvelleFenetre extends JDialog {

    // Variable statique pour suivre si la fenêtre est déjà ouverte
    private static NouvelleFenetre instance = null;

    public static void ouvrirFenetre(JFrame parent, String firstCellValue) {
        if (instance == null || !instance.isVisible()) {
            instance = new NouvelleFenetre(parent, firstCellValue);
        } else {
            // Optionnel : Ramener la fenêtre existante au premier plan
            instance.toFront();
        }
    }


    private NouvelleFenetre(JFrame parent, String firstCellValue) {
        super(parent, "Nouvelle Fenêtre", true); // Le troisième paramètre 'true' active la modalité
        setSize(450, 300);
        setLocationRelativeTo(parent); // Centrer par rapport au parent
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Contenu de la fenêtre (inchangé)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 300);
        add(panel);

        JLabel label = new JLabel("Stats totales pour : " + firstCellValue);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(20, 10, 250, 25);
        panel.add(label);

        Statcard statsTotales = StatCardCollec.additionnerStatsJoueur(firstCellValue);

        if (statsTotales != null) {
            JLabel labelPoints = new JLabel("Points : " + statsTotales.getPoints());
            JLabel labelRebonds = new JLabel("Rebonds : " + statsTotales.getRebonds());
            JLabel labelPasses = new JLabel("Passes décisives : " + statsTotales.getPasse_D());

            labelPoints.setBounds(50, 50, 200, 25);
            labelRebonds.setBounds(50, 90, 200, 25);
            labelPasses.setBounds(50, 130, 200, 25);

            panel.add(labelPoints);
            panel.add(labelRebonds);
            panel.add(labelPasses);

            ImageIcon playerImage = new ImageIcon("ressource/" + firstCellValue + ".jpg");
            Image img = playerImage.getImage();
            Image resizedImage = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            playerImage = new ImageIcon(resizedImage);

            JLabel imageLabel = new JLabel(playerImage);
            imageLabel.setBounds(270, 50, 80, 80);
            panel.add(imageLabel);
        } else {
            JLabel labelError = new JLabel("Aucune statistique trouvée pour le joueur.");
            labelError.setFont(new Font("Arial", Font.PLAIN, 14));
            labelError.setForeground(Color.RED);
            labelError.setBounds(150, 180, 200, 30);
            panel.add(labelError);
        }

        setVisible(true);
    }


    @Override
    public void dispose() {
        super.dispose();
        instance = null;  // Réinitialiser l'instance lorsque la fenêtre est fermée
    }
}
