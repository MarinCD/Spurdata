import javax.swing.*;
import java.awt.*;
import DATA.Statcard;
import collec.StatCardCollec;

public class statsdts extends JDialog {

    // Variable statique pour suivre si la fenêtre est déjà ouverte
    private static statsdts instance = null;

    public static void ouvrirFenetre(JFrame parent, String firstCellValue ,String secondCellValue ,String thirdCellValue ,String fourthCellValue ,String fiveCellValue, String sixCellValue) {
        if (instance == null || !instance.isVisible()) {
            instance = new statsdts(parent, firstCellValue, secondCellValue, thirdCellValue, fourthCellValue, fiveCellValue, sixCellValue);

        } else {
            // Optionnel : Ramener la fenêtre existante au premier plan
            instance.toFront();
        }
    }


    private statsdts(JFrame parent, String firstCellValue, String secondCellValue, String thirdCellValue,
            String fourthCellValue, String fiveCellValue, String sixCellValue) {
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

        JLabel label = new JLabel("Stats de " + fiveCellValue );
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(20, 10, 250, 25);
        panel.add(label);
        
        JLabel label1 = new JLabel("Match ID : " + sixCellValue);
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
        label1.setBounds(20, 40, 250, 25); // Position correcte
        panel.add(label1);

        JLabel labelPoints = new JLabel("Points : " + secondCellValue);
        labelPoints.setFont(new Font("Arial", Font.PLAIN, 16));
        labelPoints.setBounds(20, 100, 400, 25);
        panel.add(labelPoints);

        JLabel labelRebonds = new JLabel("Rebonds : " + thirdCellValue);
        labelRebonds.setFont(new Font("Arial", Font.PLAIN, 16));
        labelRebonds.setBounds(20, 140, 400, 25);
        panel.add(labelRebonds);

        JLabel labelPasses = new JLabel("Passes décisives : " + fourthCellValue);
        labelPasses.setFont(new Font("Arial", Font.PLAIN, 16));
        labelPasses.setBounds(20, 180, 400, 25);
        panel.add(labelPasses);

        // Ajout d'une image optionnelle (si une image pour le joueur existe)
        try {
            ImageIcon playerImage = new ImageIcon("ressource/" + fiveCellValue + ".jpg");
            Image img = playerImage.getImage();
            Image resizedImage = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            playerImage = new ImageIcon(resizedImage);

            JLabel imageLabel = new JLabel(playerImage);
            imageLabel.setBounds(300, 50, 80, 80);
            panel.add(imageLabel);
        } catch (Exception e) {
            System.err.println("Image non trouvée pour " + firstCellValue);
        }

        setVisible(true);
    }


    @Override
    public void dispose() {
        super.dispose();
        instance = null;  // Réinitialiser l'instance lorsque la fenêtre est fermée
    }
}
