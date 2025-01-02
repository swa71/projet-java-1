package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class BibliothecaireVieww extends JFrame {

    // Bibliothecaire components
    private JLabel idLabel = new JLabel("ID");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel prenomLabel = new JLabel("Prénom");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel telephoneLabel = new JLabel("Téléphone");
    private JLabel motDePasseLabel = new JLabel("Mot de Passe");
    private JTextField idText = new JTextField(30);
    private JTextField nomText = new JTextField(30);
    private JTextField prenomText = new JTextField(30);
    private JTextField emailText = new JTextField(30);
    private JTextField telephoneText = new JTextField(30);
    private JTextField motDePasseText = new JTextField(30);
    private DefaultTableModel bibliothecaireTableModel = new DefaultTableModel(new Object[0][], new Object[]{"ID", "Nom", "Prénom", "Email", "Téléphone"});
    private JTable bibliothecaireList;
    private JButton ajouterButton, modifierButton, saveBibliothecaireButton, supprimerButton;

    // Livre components
    private JLabel idLivreLabel = new JLabel("ID Livre");
    private JLabel titreLabel = new JLabel("Titre");
    private JLabel auteurLabel = new JLabel("Auteur");
    private JLabel anneeLabel = new JLabel("Année");
    private JLabel disponibiliteLabel = new JLabel("Disponibilité");
    private JTextField idLivreText = new JTextField(30);
    private JTextField titreText = new JTextField(30);
    private JTextField auteurText = new JTextField(30);
    private JTextField anneeText = new JTextField(30);
    private JCheckBox disponibiliteCheckBox = new JCheckBox("Disponible");
    private DefaultTableModel livreTableModel = new DefaultTableModel(new Object[0][], new Object[]{"ID", "Titre", "Auteur", "Année", "Disponibilité"});
    private JTable livreList;
    private JButton ajouterLivreButton, modifierLivreButton, emprunterLivreButton, saveLivreButton, supprimerLivreButton;

    // Emprunt components
    private JLabel idEmpruntLabel = new JLabel("ID Emprunt");
    private JLabel dateEmpruntLabel = new JLabel("Date Emprunt");
    private JLabel dateRetourLabel = new JLabel("Date Retour");
    private JLabel membreLabel = new JLabel("Membre");

    private JTextField idEmpruntText = new JTextField(30);
    private JTextField dateEmpruntText = new JTextField(30);
    private JTextField dateRetourText = new JTextField(30);
    private JTextField membreText = new JTextField(30);
    private JTextField bibliothecaireText = new JTextField(30);
    private DefaultTableModel empruntTableModel = new DefaultTableModel(new Object[0][], new Object[]{"ID", "Date Emprunt", "Date Retour", "Membre", "Bibliothécaire"});
    private JTable empruntList;
    private JButton ajouterEmpruntButton, modifierEmpruntButton, saveEmpruntButton, supprimerEmpruntButton;

    // Paths to CSV files
    private static final String CSV_BIBLIOTHECAIRE_FILE = "Bibliothecaire.csv";
    private static final String CSV_LIVRE_FILE = "Livre.csv";
    private static final String CSV_EMPRUNT_FILE = "Emprunt.csv";

    private JButton statsButton;
    public BibliothecaireVieww() {
        bibliothecaireList = new JTable(bibliothecaireTableModel);
        livreList = new JTable(livreTableModel);
        empruntList = new JTable(empruntTableModel);

        // Initialize buttons
        ajouterButton = new JButton("Ajouter Member");
        modifierButton = new JButton("Modifier Member");
        saveBibliothecaireButton = new JButton("Sauvegarder Member");
        supprimerButton = new JButton("Supprimer Member");

        ajouterLivreButton = new JButton("Ajouter Livre");
        modifierLivreButton = new JButton("Modifier Livre");
        emprunterLivreButton = new JButton("Emprunter Livre");
        saveLivreButton = new JButton("Sauvegarder Livre");
        supprimerLivreButton = new JButton("Supprimer Livre");

        ajouterEmpruntButton = new JButton("Ajouter Emprunt");
        modifierEmpruntButton = new JButton("Modifier Emprunt");
        saveEmpruntButton = new JButton("Sauvegarder Emprunt");
        supprimerEmpruntButton = new JButton("Supprimer Emprunt");

        setTitle("Gestion des Bibliothécaires, Livres et Emprunts");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadBibliothecaireData();
        loadLivreData();
        loadEmpruntData();
        setVisible(true);
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();



        // Bibliothecaire Tab
        tabbedPane.addTab("Member", createPanel(bibliothecaireTableModel, bibliothecaireList,
                new JComponent[]{ajouterButton, modifierButton, saveBibliothecaireButton, supprimerButton},
                new JComponent[]{idLabel, idText, nomLabel, nomText, prenomLabel, prenomText, emailLabel, emailText, telephoneLabel, telephoneText, motDePasseLabel, motDePasseText}));

        // Livre Tab
        tabbedPane.addTab("Livre", createPanel(livreTableModel, livreList,
                new JComponent[]{ajouterLivreButton, modifierLivreButton, emprunterLivreButton, saveLivreButton, supprimerLivreButton},
                new JComponent[]{idLivreLabel, idLivreText, titreLabel, titreText, auteurLabel, auteurText, anneeLabel, anneeText, disponibiliteLabel, disponibiliteCheckBox}));

        // Emprunt Tab
        tabbedPane.addTab("Emprunt", createPanel(empruntTableModel, empruntList,
                new JComponent[]{ajouterEmpruntButton, modifierEmpruntButton, saveEmpruntButton, supprimerEmpruntButton},
                new JComponent[]{idEmpruntLabel, idEmpruntText, dateEmpruntLabel, dateEmpruntText, dateRetourLabel, dateRetourText, membreLabel, membreText}));

        statsButton = new JButton("Afficher les Statistiques");
        statsButton.addActionListener(e -> showStatistics());

        // Add this button to the panel or tab where you want it to appear
        JPanel statsButtonPanel = new JPanel();
        statsButtonPanel.add(statsButton);
        tabbedPane.addTab("Statistiques", statsButtonPanel);

        add(tabbedPane, BorderLayout.CENTER);
        addListeners();

        // Bibliothecaire ListSelectionListener
        bibliothecaireList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = bibliothecaireList.getSelectedRow();
                if (selectedRow != -1) {
                    idText.setText((String) bibliothecaireTableModel.getValueAt(selectedRow, 0));
                    nomText.setText((String) bibliothecaireTableModel.getValueAt(selectedRow, 1));
                    prenomText.setText((String) bibliothecaireTableModel.getValueAt(selectedRow, 2));
                    emailText.setText((String) bibliothecaireTableModel.getValueAt(selectedRow, 3));
                    telephoneText.setText((String) bibliothecaireTableModel.getValueAt(selectedRow, 4));
                }
            }

        });

        // Livre ListSelectionListener
        livreList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = livreList.getSelectedRow();
                if (selectedRow != -1) {
                    idLivreText.setText((String) livreTableModel.getValueAt(selectedRow, 0));
                    titreText.setText((String) livreTableModel.getValueAt(selectedRow, 1));
                    auteurText.setText((String) livreTableModel.getValueAt(selectedRow, 2));
                    anneeText.setText((String) livreTableModel.getValueAt(selectedRow, 3));
                    disponibiliteCheckBox.setSelected("Oui".equals(livreTableModel.getValueAt(selectedRow, 4)));
                }
            }
        });

        // Emprunt ListSelectionListener
        empruntList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = empruntList.getSelectedRow();
                if (selectedRow != -1) {
                    idEmpruntText.setText((String) empruntTableModel.getValueAt(selectedRow, 0));
                    dateEmpruntText.setText((String) empruntTableModel.getValueAt(selectedRow, 1));
                    dateRetourText.setText((String) empruntTableModel.getValueAt(selectedRow, 2));
                    membreText.setText((String) empruntTableModel.getValueAt(selectedRow, 3));
                }
            }
        });

        setVisible(true);
    }

    private void showStatistics() {
        // Create a new JFrame or JDialog for displaying statistics
        JFrame statsFrame = new JFrame("Statistiques");
        statsFrame.setSize(400, 300);
        statsFrame.setLocationRelativeTo(this);

        // Create a panel to display the statistics
        JPanel statsPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        // Example statistics
        int totalLibrarians = bibliothecaireTableModel.getRowCount();
        int totalBooks = livreTableModel.getRowCount();
        int totalEmprunts = empruntTableModel.getRowCount();
        int availableBooks = getAvailableBooks();

        statsPanel.add(new JLabel("Total des Members: " + totalLibrarians));
        statsPanel.add(new JLabel("Total des Livres: " + totalBooks));
        statsPanel.add(new JLabel("Total des Livres Empruntés: " + (totalBooks - availableBooks)));
        statsPanel.add(new JLabel("Total des Emprunts: " + totalEmprunts));
        statsPanel.add(new JLabel("Livres Disponibles: " + availableBooks));

        statsFrame.add(statsPanel);
        statsFrame.setVisible(true);
    }

    // Helper method to count available books
    private int getAvailableBooks() {
        int availableCount = 0;
        for (int i = 0; i < livreTableModel.getRowCount(); i++) {
            if ("Oui".equals(livreTableModel.getValueAt(i, 4))) {
                availableCount++;
            }
        }
        return availableCount;
    }


    private JPanel createPanel(DefaultTableModel tableModel, JTable table, JComponent[] buttons, JComponent[] fields) {
        JPanel panel = new JPanel(new BorderLayout());

        // Add search panel at the top
        panel.add(createSearchPanel(tableModel, table), BorderLayout.NORTH);

        // Add table in the center
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Add button panel at the bottom
        JPanel buttonPanel = new JPanel();
        for (JComponent button : buttons) buttonPanel.add(button);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add field panel at the right
        JPanel fieldPanel = new JPanel(new GridLayout(fields.length / 2, 2, 10, 10));
        for (JComponent field : fields) fieldPanel.add(field);
        panel.add(fieldPanel, BorderLayout.EAST);

        return panel;
    }
    private JPanel createSearchPanel(DefaultTableModel model, JTable table) {
        JPanel searchPanel = new JPanel();
        JTextField searchTextField = new JTextField(20);


        searchPanel.add(new JLabel("Rechercher:"));
        searchPanel.add(searchTextField);


        // Ajout d'un KeyListener pour écouter les frappes de touche et appliquer la recherche en temps réel
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchTextField.getText().trim();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                table.setRowSorter(sorter);

                if (query.isEmpty()) {
                    // Si la recherche est vide, afficher toutes les lignes
                    sorter.setRowFilter(null);
                } else {
                    // Sinon, appliquer un filtre avec la recherche
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query)); // Filtre insensible à la casse
                }
            }
        });

        return searchPanel;
    }





    private void addListeners() {
        // Ajouter les action listeners pour chaque bouton
        ajouterButton.addActionListener(e -> addRow(bibliothecaireTableModel, new JTextField[]{idText, nomText, prenomText, emailText, telephoneText}, null));
        modifierButton.addActionListener(e -> modifyRowBibliothecaire(bibliothecaireTableModel, bibliothecaireList));  // Modifier Bibliothecaire
        saveBibliothecaireButton.addActionListener(e -> saveData(CSV_BIBLIOTHECAIRE_FILE, bibliothecaireTableModel));
        supprimerButton.addActionListener(e -> deleteRow(bibliothecaireTableModel, bibliothecaireList));

        ajouterLivreButton.addActionListener(e -> addRow(livreTableModel, new JTextField[]{idLivreText, titreText, auteurText, anneeText}, disponibiliteCheckBox));
        modifierLivreButton.addActionListener(e -> modifyRowLivre(livreTableModel, livreList));  // Modifier Livre
        saveLivreButton.addActionListener(e -> saveData(CSV_LIVRE_FILE, livreTableModel));
        supprimerLivreButton.addActionListener(e -> deleteRow(livreTableModel, livreList));

        ajouterEmpruntButton.addActionListener(e -> addRow(empruntTableModel, new JTextField[]{idEmpruntText, dateEmpruntText, dateRetourText, membreText}, null));
        modifierEmpruntButton.addActionListener(e -> modifyRowEmprunt(empruntTableModel, empruntList));  // Modifier Emprunt
        saveEmpruntButton.addActionListener(e -> saveData(CSV_EMPRUNT_FILE, empruntTableModel));
        supprimerEmpruntButton.addActionListener(e -> deleteRow(empruntTableModel, empruntList));

        emprunterLivreButton.addActionListener(e -> emprunterLivre());
    }


    private void clearInputs() {
        // Pour la table Bibliothécaire
        idText.setText("");
        nomText.setText("");
        prenomText.setText("");
        emailText.setText("");
        telephoneText.setText("");
        motDePasseText.setText("");

        // Pour la table Livre
        idLivreText.setText("");
        titreText.setText("");
        auteurText.setText("");
        anneeText.setText("");
        disponibiliteCheckBox.setSelected(false);

        // Pour la table Emprunt
        idEmpruntText.setText("");
        dateEmpruntText.setText("");
        dateRetourText.setText("");
        membreText.setText("");
    }


    private void addRow(DefaultTableModel model, JTextField[] fields, JCheckBox checkBox) {
        String[] rowData = new String[fields.length + (checkBox != null ? 1 : 0)];
        for (int i = 0; i < fields.length; i++) {
            rowData[i] = fields[i].getText();
        }
        if (checkBox != null) {
            rowData[fields.length] = checkBox.isSelected() ? "Oui" : "Non";
        }
        model.addRow(rowData);
        model.fireTableDataChanged();  // Refresh the table
    }
    private void modifyRowBibliothecaire(DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Récupérer les valeurs des champs
            String id = idText.getText();
            String nom = nomText.getText();
            String prenom = prenomText.getText();
            String email = emailText.getText();
            String telephone = telephoneText.getText();

            // Vérifier que tous les champs sont remplis
            if (id.isEmpty() || nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mettre à jour la ligne dans la table
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(nom, selectedRow, 1);
            model.setValueAt(prenom, selectedRow, 2);
            model.setValueAt(email, selectedRow, 3);
            model.setValueAt(telephone, selectedRow, 4);

            // Rafraîchir la table après modification
            model.fireTableRowsUpdated(selectedRow, selectedRow);

            // Vider les champs après la modification
            idText.setText("");
            nomText.setText("");
            prenomText.setText("");
            emailText.setText("");
            telephoneText.setText("");
        }
    }


    private void modifyRowLivre(DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Récupérer les valeurs des champs
            String idLivre = idLivreText.getText();
            String titre = titreText.getText();
            String auteur = auteurText.getText();
            String annee = anneeText.getText();
            String disponibilite = disponibiliteCheckBox.isSelected() ? "Oui" : "Non";

            // Vérifier que tous les champs sont remplis
            if (idLivre.isEmpty() || titre.isEmpty() || auteur.isEmpty() || annee.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mettre à jour la ligne dans la table
            model.setValueAt(idLivre, selectedRow, 0);
            model.setValueAt(titre, selectedRow, 1);
            model.setValueAt(auteur, selectedRow, 2);
            model.setValueAt(annee, selectedRow, 3);
            model.setValueAt(disponibilite, selectedRow, 4);

            // Rafraîchir la table après modification
            model.fireTableRowsUpdated(selectedRow, selectedRow);

            // Vider les champs après la modification
            idLivreText.setText("");
            titreText.setText("");
            auteurText.setText("");
            anneeText.setText("");
            disponibiliteCheckBox.setSelected(false);
        }
    }

    private void modifyRowEmprunt(DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Récupérer les valeurs des champs
            String idEmprunt = idEmpruntText.getText();
            String dateEmprunt = dateEmpruntText.getText();
            String dateRetour = dateRetourText.getText();
            String membre = membreText.getText();

            // Vérifier que tous les champs sont remplis
            if (idEmprunt.isEmpty() || dateEmprunt.isEmpty() || dateRetour.isEmpty() || membre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mettre à jour la ligne dans la table
            model.setValueAt(idEmprunt, selectedRow, 0);
            model.setValueAt(dateEmprunt, selectedRow, 1);
            model.setValueAt(dateRetour, selectedRow, 2);
            model.setValueAt(membre, selectedRow, 3);

            // Rafraîchir la table après modification
            model.fireTableRowsUpdated(selectedRow, selectedRow);

            // Vider les champs après la modification
            idEmpruntText.setText("");
            dateEmpruntText.setText("");
            dateRetourText.setText("");
            membreText.setText("");
        }
    }



    private void deleteRow(DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            model.fireTableRowsDeleted(selectedRow, selectedRow);  // Refresh after deletion
        }
    }

    private void saveData(String filePath, DefaultTableModel model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i) + (i < model.getColumnCount() - 1 ? "," : "\n"));
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j) + (j < model.getColumnCount() - 1 ? "," : "\n"));
                }
            }
            model.fireTableDataChanged();  // Refresh the table after saving data
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement des données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadBibliothecaireData() {
        loadData(CSV_BIBLIOTHECAIRE_FILE, bibliothecaireTableModel);
    }

    private void loadLivreData() {
        loadData(CSV_LIVRE_FILE, livreTableModel);
    }

    private void loadEmpruntData() {
        loadData(CSV_EMPRUNT_FILE, empruntTableModel);
    }

    private void loadData(String filePath, DefaultTableModel model) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split(","));
            }
            model.fireTableDataChanged();  // Refresh the table after loading data
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void emprunterLivre() {
        // Récupérer la ligne sélectionnée dans la table des livres
        int selectedRow = livreList.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre à emprunter.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifier si le livre est disponible
        String disponibilite = (String) livreTableModel.getValueAt(selectedRow, 4); // La disponibilité est dans la 5ème colonne (index 4)

        if ("Non".equals(disponibilite)) {
            JOptionPane.showMessageDialog(this, "Ce livre n'est pas disponible pour l'emprunt.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Demander l'ID du bibliothécaire
        String bibliothecaireID = JOptionPane.showInputDialog(this, "Entrez l'ID du bibliothécaire:");
        if (bibliothecaireID == null || bibliothecaireID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "L'ID du bibliothécaire est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifier si l'ID du bibliothécaire existe dans la table des bibliothécaires
        boolean bibliothecaireExiste = false;
        for (int i = 0; i < bibliothecaireTableModel.getRowCount(); i++) {
            String id = (String) bibliothecaireTableModel.getValueAt(i, 0); // Récupérer l'ID du bibliothécaire (dans la première colonne)
            if (id.equals(bibliothecaireID)) {
                bibliothecaireExiste = true;
                break;
            }
        }

        if (!bibliothecaireExiste) {
            JOptionPane.showMessageDialog(this, "L'ID du bibliothécaire n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Demander les informations de l'emprunt après avoir vérifié l'ID du bibliothécaire


        // Récupérer la date actuelle pour l'emprunt
        String dateEmprunt = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        String dateRetour = JOptionPane.showInputDialog(this, "Entrez la date de retour (jj/mm/aaaa):");
        if (dateRetour == null || dateRetour.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La date de retour est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer une nouvelle ligne pour l'emprunt
        String[] empruntData = new String[] {
                String.valueOf(empruntTableModel.getRowCount() + 1),  // ID de l'emprunt (simple incrémentation)
                dateEmprunt, // Date d'emprunt
                dateRetour,  // Date de retour
                bibliothecaireID // ID du bibliothécaire
        };

        // Ajouter l'emprunt dans la table des emprunts
        empruntTableModel.addRow(empruntData);
        empruntTableModel.fireTableDataChanged();  // Rafraîchir la table des emprunts

        // Mettre à jour l'état de disponibilité du livre
        livreTableModel.setValueAt("Non", selectedRow, 4);  // Mettre la disponibilité à "Non"
        livreTableModel.fireTableDataChanged();  // Rafraîchir la table des livres

        // Confirmer l'emprunt
        JOptionPane.showMessageDialog(this, "Le livre a été emprunté avec succès.", "Emprunt Réussi", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliothecaireVieww::new);
    }
}