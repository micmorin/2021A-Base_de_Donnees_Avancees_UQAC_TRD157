/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.ui;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Michael
 */
public class UI extends javax.swing.JFrame {

    private Vector filmData = new Vector();
    private tp3.entity.Client client;
    /**
     * Creates new form UI
     */
    public UI() {
        initComponents();
    }
    
    private void displayResultConnection(List resultList) {    
       if(!resultList.isEmpty()) {
            tp3.entity.Client c = (tp3.entity.Client) resultList.get(0);
            client = c;
            welcomeLabel.setText("Welcome " + c.getPrenom().trim() + " " + c.getNom().trim()+"!");
            resutlPanel.setVisible(true);
        }
       else
       {erreurConnection.setVisible(true);}
    }
    
    private void displayResultSearch(List resultList) { 
        Vector<String> data = new Vector<String>();
        if(!resultList.isEmpty()) {        
            for (Object o : resultList )
            {
                tp3.entity.Film f = (tp3.entity.Film) o;
                data.add(f.getTitre()+" ("+f.getAnneedesortie()+")");
                filmData.add(f);
            }
            
            LinkedHashSet<String> lhSet = new LinkedHashSet<String>(data);
            data.clear();
            data.addAll(lhSet);
            resultJList.setListData(data);
            
            LinkedHashSet<tp3.entity.Film> lhSet2 = new LinkedHashSet<tp3.entity.Film>(filmData);
            filmData.clear();
            filmData.addAll(lhSet2);
            
        }
    }
    
    private void executeHQLQuery(String hql, int option) {
    try {
        SessionFactory s = tp3.util.HibernateUtil.getSessionFactory();
        Session session = s.openSession();
        session.beginTransaction();
        Query q = session.createQuery(hql);
        List resultList = q.list();
        if (option == 0 )
        {displayResultConnection(resultList);}
        else if (option == 1)
        {displayResultSearch(resultList);}
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
    }
}
    
    private void runQueryConnection() {
            executeHQLQuery("from Client c where c.email like '"+ emailField.getText() +"' and c.motdepasse ='"+ passwordField.getText()+"'",0);
}
    
    private void runQuerySearch() {
            String orString = "";
            String anneeMin = "";
            String anneeMax = "";
            
            
            if(!anneeMinField.getText().equals("")) {anneeMin = "and f.anneedesortie > '" + anneeMinField.getText() + "' ";}
            if(!anneeMaxField.getText().equals("")) {anneeMax = "and f.anneedesortie < '" + anneeMaxField.getText() + "' and ( ";}
            else {anneeMax = "and ";}
            if(!titreField.getText().equals("")) {
                if(orString.isEmpty()){orString += "(f.titre like '%" + titreField.getText() + "%' ";}
                else{}
            }
            if(!paysField.getText().equals("")) {
                if(orString.isEmpty()){orString += "(pays.nompays like '%" + paysField.getText() + "%' ";}
                else{orString += "or pays.nompays like '%" + paysField.getText() + "%' ";}
            }
            if(!langueField.getText().equals("")) {
                if(orString.isEmpty()){orString += "(f.langue.nomlangue like '%" + langueField.getText() + "%' ";}
                else{orString += "or f.langue.nomlangue like '%" + langueField.getText() + "%' ";}
            }
            if(!genreField.getText().equals("")) {
                if(orString.isEmpty()){orString += "(genre.nomgenre like '%" + genreField.getText() + "%' ";}
                else{orString += "or genre.nomgenre like '%" + genreField.getText() + "%' ";}
            }
            if(!realisateurField.getText().equals("")) {
                if(orString.isEmpty()){orString += "((personneltechniques.prenom Like '%" + realisateurField.getText() + "%' " +" or personneltechniques.nom Like '%" + realisateurField.getText() + "%') ";}
                else{orString += "or (personneltechniques.prenom Like '%" + realisateurField.getText() + "%' " +" or personneltechniques.nom Like '%" + realisateurField.getText() + "%') ";}
            }
            if(!acteurField.getText().equals("")) {
                if(orString.isEmpty()){orString += "((a.prenom like '%" + acteurField.getText() + "%' " +"or  a.nom like '%" + acteurField.getText() + "%') ";}
                else{orString += "or (a.prenom like '%" + acteurField.getText() + "%' " +"or  a.nom like '%" + acteurField.getText() + "%') ";}
            }
            if(!orString.isEmpty()){orString += ")";}
            
            executeHQLQuery(
                    "select f " +
                    "from Film as f " +
                    "from Acteur as a " +
                    "join f.genres as genre " +
                    "join f.payses as pays " +
                    "join f.personnages as personnages " +
                    "join f.examplaires as examplaires " +
                    "join f.personneltechniques as personneltechniques " +
                    "where personnages.acteur = a " +
                    anneeMin +
                    anneeMax +
                    orString
                    ,1);
}
   
        private boolean verifyInput(String in){
            for(Character c : in.toCharArray())
            {
                if(!Character.isLetterOrDigit(c) && (c.equals((Character)'%') | c.equals((Character)"'".charAt(0))| c.equals((Character)'"')))
                {
                    inputErreurLabel.setText("Vous avez entre un charactere invalide (%, ' , \"). Veuillez reessayer");
                    inputErreur.setVisible(true);
                    return false;
                }  
            }
            return true;
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        erreurConnection = new javax.swing.JDialog();
        msgErreur1 = new javax.swing.JLabel();
        msgErreur2 = new javax.swing.JLabel();
        OKButtonConnection = new javax.swing.JButton();
        ficheFilmDialog = new javax.swing.JDialog();
        fLabelTitre = new javax.swing.JLabel();
        fLabelAnnee = new javax.swing.JLabel();
        fLabelLangue = new javax.swing.JLabel();
        fLabelPays = new javax.swing.JLabel();
        fLabelPersonnel = new javax.swing.JLabel();
        fLabelPersonnage = new javax.swing.JLabel();
        fLabelDuree = new javax.swing.JLabel();
        fLabelGenres = new javax.swing.JLabel();
        fLabelResume = new javax.swing.JLabel();
        fFieldTitre = new javax.swing.JTextField();
        fFieldAnnee = new javax.swing.JTextField();
        fFieldPays = new javax.swing.JTextField();
        fFieldLangue = new javax.swing.JTextField();
        fFieldDuree = new javax.swing.JTextField();
        fFieldGenres = new javax.swing.JTextField();
        fFieldPersonnel = new javax.swing.JComboBox();
        fFieldPersonnage = new javax.swing.JComboBox();
        persFicheButton = new javax.swing.JButton();
        actFicheButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        fFieldResume = new javax.swing.JTextArea();
        louerButton = new javax.swing.JButton();
        erreurSearch = new javax.swing.JDialog();
        err1 = new javax.swing.JLabel();
        err2 = new javax.swing.JLabel();
        OKButtonSearch = new javax.swing.JButton();
        fichePersDialog = new javax.swing.JDialog();
        pLabelNom = new javax.swing.JLabel();
        fLabelAnnee1 = new javax.swing.JLabel();
        fLabelPays1 = new javax.swing.JLabel();
        pLabelBio = new javax.swing.JLabel();
        pFieldNom = new javax.swing.JTextField();
        pFieldAnnee = new javax.swing.JTextField();
        pFieldLieu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pFieldBio = new javax.swing.JTextArea();
        louerDialog = new javax.swing.JDialog();
        msgLouer1 = new javax.swing.JLabel();
        msgLouer2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        inputErreur = new javax.swing.JDialog();
        okInputButton = new javax.swing.JButton();
        inputErreurLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        resutlPanel = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        titreLabel = new javax.swing.JLabel();
        titreField = new javax.swing.JTextField();
        paysLabel = new javax.swing.JLabel();
        paysField = new javax.swing.JTextField();
        anneeMinLabel = new javax.swing.JLabel();
        anneeMinField = new javax.swing.JTextField();
        anneeMaxLabel = new javax.swing.JLabel();
        anneeMaxField = new javax.swing.JTextField();
        realisateurLabel = new javax.swing.JLabel();
        realisateurField = new javax.swing.JTextField();
        acteurLabel = new javax.swing.JLabel();
        acteurField = new javax.swing.JTextField();
        genreLabel = new javax.swing.JLabel();
        genreField = new javax.swing.JTextField();
        langueLabel = new javax.swing.JLabel();
        langueField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultJList = new javax.swing.JList();
        filmFicheButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();

        erreurConnection.setTitle("Erreur");
        erreurConnection.setAlwaysOnTop(true);
        erreurConnection.setBackground(java.awt.Color.lightGray);
        erreurConnection.setMinimumSize(new java.awt.Dimension(550, 200));
        erreurConnection.setResizable(false);

        msgErreur1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        msgErreur1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgErreur1.setText("Le email ou le mot de passe n'est pas valide.");
        msgErreur1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        msgErreur2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        msgErreur2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgErreur2.setText(" Veuillez ressayer.");

        OKButtonConnection.setText("OK");
        OKButtonConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout erreurConnectionLayout = new javax.swing.GroupLayout(erreurConnection.getContentPane());
        erreurConnection.getContentPane().setLayout(erreurConnectionLayout);
        erreurConnectionLayout.setHorizontalGroup(
            erreurConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(msgErreur1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
            .addComponent(msgErreur2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(erreurConnectionLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(OKButtonConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        erreurConnectionLayout.setVerticalGroup(
            erreurConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(erreurConnectionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(msgErreur1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msgErreur2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OKButtonConnection)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        erreurConnection.setLocationRelativeTo(null);

        ficheFilmDialog.setMinimumSize(new java.awt.Dimension(650, 500));

        fLabelTitre.setText("Titre");

        fLabelAnnee.setText("Annee");

        fLabelLangue.setText("Langue");

        fLabelPays.setText("Pays");

        fLabelPersonnel.setText("Personnel technique");

        fLabelPersonnage.setText("Personnages (acteurs)");

        fLabelDuree.setText("Duree");

        fLabelGenres.setText("Genres");

        fLabelResume.setText("Resume");

        persFicheButton.setText("Fiche Personnel");
        persFicheButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                persFicheButtonActionPerformed(evt);
            }
        });

        actFicheButton.setText("Fiche Acteur");
        actFicheButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actFicheButtonActionPerformed(evt);
            }
        });

        fFieldResume.setColumns(20);
        fFieldResume.setRows(5);
        jScrollPane3.setViewportView(fFieldResume);

        louerButton.setText("Louer");
        louerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                louerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ficheFilmDialogLayout = new javax.swing.GroupLayout(ficheFilmDialog.getContentPane());
        ficheFilmDialog.getContentPane().setLayout(ficheFilmDialogLayout);
        ficheFilmDialogLayout.setHorizontalGroup(
            ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fLabelTitre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                                    .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                                            .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(fLabelAnnee)
                                                .addComponent(fLabelLangue)
                                                .addComponent(fLabelPays)
                                                .addComponent(fLabelDuree)
                                                .addComponent(fLabelGenres))
                                            .addGap(74, 74, 74))
                                        .addComponent(fLabelPersonnel, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(19, 19, 19)
                                    .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fFieldAnnee)
                                        .addComponent(fFieldTitre)
                                        .addComponent(fFieldPays)
                                        .addComponent(fFieldLangue)
                                        .addComponent(fFieldDuree)
                                        .addComponent(fFieldGenres)
                                        .addComponent(fFieldPersonnel, 0, 354, Short.MAX_VALUE))))
                            .addComponent(fLabelResume)
                            .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                                .addComponent(fLabelPersonnage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fFieldPersonnage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(persFicheButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(actFicheButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ficheFilmDialogLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(louerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        ficheFilmDialogLayout.setVerticalGroup(
            ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ficheFilmDialogLayout.createSequentialGroup()
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fLabelTitre)
                            .addComponent(fFieldTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fLabelAnnee)
                            .addComponent(fFieldAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fLabelPays)
                            .addComponent(fFieldPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fLabelLangue)
                            .addComponent(fFieldLangue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fLabelDuree)
                            .addComponent(fFieldDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fLabelGenres)
                            .addComponent(fFieldGenres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ficheFilmDialogLayout.createSequentialGroup()
                        .addComponent(louerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fFieldPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fLabelPersonnel))
                    .addComponent(persFicheButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ficheFilmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fFieldPersonnage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fLabelPersonnage)
                    .addComponent(actFicheButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fLabelResume)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ficheFilmDialog.setLocationRelativeTo(null);

        erreurSearch.setTitle("Erreur");
        erreurSearch.setAlwaysOnTop(true);
        erreurSearch.setBackground(java.awt.Color.lightGray);
        erreurSearch.setMinimumSize(new java.awt.Dimension(550, 200));
        erreurSearch.setResizable(false);

        err1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        err1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        err1.setText("Les criteres n'ont rien retourner.");
        err1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        err2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        err2.setText(" Veuillez ressayer.");

        OKButtonSearch.setText("OK");
        OKButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout erreurSearchLayout = new javax.swing.GroupLayout(erreurSearch.getContentPane());
        erreurSearch.getContentPane().setLayout(erreurSearchLayout);
        erreurSearchLayout.setHorizontalGroup(
            erreurSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(erreurSearchLayout.createSequentialGroup()
                .addGroup(erreurSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(erreurSearchLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(err2))
                    .addGroup(erreurSearchLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(OKButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(erreurSearchLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(err1)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        erreurSearchLayout.setVerticalGroup(
            erreurSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(erreurSearchLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(err2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OKButtonSearch)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        erreurConnection.setLocationRelativeTo(null);

        fichePersDialog.setMinimumSize(new java.awt.Dimension(550, 350));

        pLabelNom.setText("Nom");

        fLabelAnnee1.setText("Annee de Naissance");

        fLabelPays1.setText("Lieu de Naissance");

        pLabelBio.setText("Biographie");

        pFieldBio.setColumns(20);
        pFieldBio.setRows(5);
        jScrollPane1.setViewportView(pFieldBio);

        javax.swing.GroupLayout fichePersDialogLayout = new javax.swing.GroupLayout(fichePersDialog.getContentPane());
        fichePersDialog.getContentPane().setLayout(fichePersDialogLayout);
        fichePersDialogLayout.setHorizontalGroup(
            fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fichePersDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fichePersDialogLayout.createSequentialGroup()
                        .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(fichePersDialogLayout.createSequentialGroup()
                                .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fLabelAnnee1)
                                    .addComponent(fLabelPays1))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fichePersDialogLayout.createSequentialGroup()
                                .addComponent(pLabelNom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pFieldNom)
                            .addComponent(pFieldAnnee, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                            .addComponent(pFieldLieu)))
                    .addComponent(pLabelBio)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        fichePersDialogLayout.setVerticalGroup(
            fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fichePersDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pLabelNom)
                    .addComponent(pFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLabelAnnee1)
                    .addComponent(pFieldAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fichePersDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fLabelPays1)
                    .addComponent(pFieldLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pLabelBio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        fichePersDialog.setLocationRelativeTo(null);

        louerDialog.setMinimumSize(new java.awt.Dimension(875, 225));

        msgLouer1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        msgLouer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgLouer1.setText("jLabel1");

        msgLouer2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        msgLouer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgLouer2.setText("jLabel2");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout louerDialogLayout = new javax.swing.GroupLayout(louerDialog.getContentPane());
        louerDialog.getContentPane().setLayout(louerDialogLayout);
        louerDialogLayout.setHorizontalGroup(
            louerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(louerDialogLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(louerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(msgLouer1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                    .addComponent(msgLouer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, louerDialogLayout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );
        louerDialogLayout.setVerticalGroup(
            louerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(louerDialogLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(msgLouer1)
                .addGap(18, 18, 18)
                .addComponent(msgLouer2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        louerDialog.setLocationRelativeTo(null);

        inputErreur.setMinimumSize(new java.awt.Dimension(625, 150));

        okInputButton.setText("OK");
        okInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okInputButtonActionPerformed(evt);
            }
        });

        inputErreurLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputErreurLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputErreurLabel.setText("jLabel1");

        javax.swing.GroupLayout inputErreurLayout = new javax.swing.GroupLayout(inputErreur.getContentPane());
        inputErreur.getContentPane().setLayout(inputErreurLayout);
        inputErreurLayout.setHorizontalGroup(
            inputErreurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputErreurLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(inputErreurLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(okInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );
        inputErreurLayout.setVerticalGroup(
            inputErreurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputErreurLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(inputErreurLabel)
                .addGap(33, 33, 33)
                .addComponent(okInputButton)
                .addGap(21, 21, 21))
        );

        inputErreur.setLocationRelativeTo(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password");

        emailLabel.setText("Email");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(emailLabel)
                .addGap(18, 18, 18)
                .addComponent(emailField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(passwordLabel)))
        );

        resutlPanel.setVisible(false);

        welcomeLabel.setText("Welcome");

        titreLabel.setText("Titre");

        paysLabel.setText("Pays");

        anneeMinLabel.setText("Annee Min");

        anneeMaxLabel.setText("Annee Max");

        realisateurLabel.setText("Realisateur");

        acteurLabel.setText("Acteur");

        genreLabel.setText("Genre");

        langueLabel.setText("Langue");

        searchButton.setText("Chercher");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(resultJList);

        filmFicheButton.setText("Voir Fiche");
        filmFicheButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filmFicheButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resutlPanelLayout = new javax.swing.GroupLayout(resutlPanel);
        resutlPanel.setLayout(resutlPanelLayout);
        resutlPanelLayout.setHorizontalGroup(
            resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resutlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, resutlPanelLayout.createSequentialGroup()
                        .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addComponent(realisateurLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(realisateurField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addComponent(titreLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titreField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addComponent(paysLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paysField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addComponent(acteurLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(acteurField)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addComponent(genreLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(genreField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addComponent(anneeMinLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anneeMinField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(langueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(langueField))
                            .addGroup(resutlPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anneeMaxLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anneeMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(resutlPanelLayout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, resutlPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filmFicheButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        resutlPanelLayout.setVerticalGroup(
            resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resutlPanelLayout.createSequentialGroup()
                .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(welcomeLabel))
                .addGap(18, 18, 18)
                .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titreLabel)
                    .addComponent(paysLabel)
                    .addComponent(anneeMinLabel)
                    .addComponent(anneeMaxLabel)
                    .addComponent(paysField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anneeMinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anneeMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realisateurLabel)
                    .addComponent(acteurLabel)
                    .addComponent(genreLabel)
                    .addComponent(langueLabel)
                    .addComponent(realisateurField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acteurField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(langueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(resutlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(filmFicheButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(705, Short.MAX_VALUE))
        );

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton)
                .addGap(30, 30, 30))
            .addComponent(resutlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resutlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-662)/2, (screenSize.height-508)/2, 662, 508);
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
       if(!emailField.getText().equals("") && !passwordField.getText().equals("")) {
           if(verifyInput(emailField.getText())&& verifyInput(passwordField.getText()))
           {runQueryConnection();}
        } 
    }//GEN-LAST:event_connectButtonActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if(!(   
                acteurField.getText().equals("") && anneeMinField.getText().equals("") &&
                anneeMaxField.getText().equals("") && genreField.getText().equals("") &&
                langueField.getText().equals("") && paysField.getText().equals("") &&
                realisateurField.getText().equals("") && titreField.getText().equals("")
             )
           ) 
        {
            if(verifyInput(acteurField.getText())&& verifyInput(anneeMinField.getText()) &&
               verifyInput(anneeMaxField.getText())&& verifyInput(genreField.getText()) &&     
               verifyInput(langueField.getText())&& verifyInput(paysField.getText()) &&
               verifyInput(realisateurField.getText())&& verifyInput(titreField.getText())
              )    
            {runQuerySearch();}
        } 
        
        else
        {
            erreurSearch.setVisible(true);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void OKButtonConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonConnectionActionPerformed
        erreurConnection.setVisible(false);
    }//GEN-LAST:event_OKButtonConnectionActionPerformed

    private void filmFicheButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filmFicheButtonActionPerformed
        int selection = resultJList.getSelectedIndex();
        if(selection != -1)
        {
            tp3.entity.Film f = (tp3.entity.Film) filmData.get(selection);
            String genres = "";
            String pays = "";
            
            for(Object o : f.getPayses())
            {tp3.entity.Pays p = (tp3.entity.Pays) o; pays += p.getNompays() + "; ";}
            for(Object o : f.getGenres())
            {tp3.entity.Genre g = (tp3.entity.Genre) o; genres += g.getNomgenre() + "; ";}       
            
            fFieldTitre.setText(f.getTitre());
            fFieldAnnee.setText(f.getAnneedesortie().toString()) ;
            fFieldPays.setText(pays);
            fFieldLangue.setText(f.getLangue().getNomlangue());
            fFieldDuree.setText(f.getDuree().toString());
            fFieldGenres.setText(genres);
            for (Object o : f.getPersonneltechniques() )
            {
                tp3.entity.Personneltechnique p = (tp3.entity.Personneltechnique) o;
                fFieldPersonnel.addItem(p.getPrenom() + " " + p.getNom() +" ("+ p.getRoletechnique().getNomrole() +")");
            }
            for (Object o : f.getPersonnages() )
            {
                tp3.entity.Personnage p = (tp3.entity.Personnage) o;
                fFieldPersonnage.addItem(p.getPrenom() + " " + p.getNom() +" ("+ p.getActeur().getPrenom() + " " + p.getActeur().getNom() +")");
            }
            try {
                fFieldResume.setText(f.getResume().getSubString(1, (int) f.getResume().length()));
            } catch (SQLException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            ficheFilmDialog.setVisible(true);
        }
    }//GEN-LAST:event_filmFicheButtonActionPerformed

    private void OKButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonSearchActionPerformed
        erreurSearch.setVisible(false);
    }//GEN-LAST:event_OKButtonSearchActionPerformed

    private void persFicheButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_persFicheButtonActionPerformed
        int selection = fFieldPersonnel.getSelectedIndex();
        if(selection != -1)
        {
            tp3.entity.Film f = (tp3.entity.Film) filmData.get(resultJList.getSelectedIndex());
            Object[] pArr = f.getPersonneltechniques().toArray();
            tp3.entity.Personneltechnique p = (tp3.entity.Personneltechnique) pArr[selection]; 
            
            String bio;
            if(!p.getFilms().isEmpty()) {bio = "Cette personne a participer aux films suivants:" + System.lineSeparator();}
            else {bio = "Cette personne n'a participer a aucun films.";}
            for(Object o : p.getFilms() )
            {tp3.entity.Film fTemp = (tp3.entity.Film) o; bio += fTemp.getTitre()+" ("+fTemp.getAnneedesortie()+")"+System.lineSeparator();}
            bio += "Son role principal est " + p.getRoletechnique().getNomrole() +".";
            
            pFieldNom.setText(p.getPrenom()+" "+p.getNom());
            pFieldAnnee.setText(p.getDatedenaissance().toString());
            pFieldBio.setText(bio);
      
            fichePersDialog.setVisible(true);
        }
    }//GEN-LAST:event_persFicheButtonActionPerformed

    private void actFicheButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actFicheButtonActionPerformed
        int selection = fFieldPersonnage.getSelectedIndex();
        if(selection != -1)
        {
            tp3.entity.Film f = (tp3.entity.Film) filmData.get(resultJList.getSelectedIndex());
            Object[] pSet =  f.getPersonnages().toArray();
            tp3.entity.Personnage p = (tp3.entity.Personnage) pSet[selection]; 
            tp3.entity.Acteur a = (tp3.entity.Acteur) p.getActeur();
                       
            pFieldNom.setText(a.getPrenom()+" "+a.getNom());
            pFieldAnnee.setText(a.getDatedenaissance().toString());
            pFieldLieu.setText(a.getLieudenaissance());
            try {
                pFieldBio.setText(a.getBiographie().getSubString(1, (int) a.getBiographie().length()));
            } catch (SQLException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            fichePersDialog.setVisible(true);
        }
    }//GEN-LAST:event_actFicheButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        louerDialog.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void louerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_louerButtonActionPerformed
        int max = client.getForfait().getMaxlocation().intValue();
        int courrant = 0;
        for(Object o : client.getLocations().toArray())
        {
           tp3.entity.Location l = (tp3.entity.Location) o;
           try{ l.getConfirmationretour().toString();}
           catch(NullPointerException e){courrant++;}
        }
        
        if(max > courrant)
        {
            tp3.entity.Film f = (tp3.entity.Film) filmData.get(resultJList.getSelectedIndex());
            tp3.entity.Examplaire examplairePourClient = null;
            for(Object o : f.getExamplaires().toArray())
            {
                tp3.entity.Examplaire e = (tp3.entity.Examplaire) o;
                if(e.getLocations().isEmpty())
                {
                    examplairePourClient = e;
                    break;
                }
                else
                {
                    for(Object ol : e.getLocations().toArray())
                    {
                        tp3.entity.Location l = (tp3.entity.Location) ol;
                        try{ l.getConfirmationretour().toString();}
                        catch(NullPointerException ex){examplairePourClient = e; break;}       
                    }
                }
            }
            
            try
            {
                examplairePourClient.getFilm();
                
                try {
                    SessionFactory s = tp3.util.HibernateUtil.getSessionFactory();
                    Session session = s.openSession();
                    session.beginTransaction();
                    Query q = session.createSQLQuery("select ISEQ$$_76777.nextval as num from dual");
                    List resultList = q.list();
                    
                    tp3.entity.Location l = new tp3.entity.Location();
                    int futureDate = 1000*60*60*24;
                    futureDate = futureDate * client.getForfait().getMaxduree().intValue();
                    l.setClient(client);
                    l.setDatelocation(new Date());
                    l.setDateretour( new Date( new Date().getTime()+(futureDate) ) );
                    l.setExamplaire(examplairePourClient);
                    l.setIdlocation((BigDecimal) resultList.get(0));
                    
                    client.getLocations().add(l);
                    examplairePourClient.getLocations().add(l);
                    
                    session.save(l);
                    session.getTransaction().commit();
                    msgLouer1.setText("Location effectuer");
                    msgLouer2.setText("Il vous reste "+(max-courrant-1)+" locations disponibles.");
                    louerDialog.setVisible(true);
                    
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
            catch(NullPointerException ex)
            { 
                msgLouer1.setText("Aucun Examplaires Disponibles");
                msgLouer2.setText("Veuillez choisir un autre film.");
                louerDialog.setVisible(true);
            }
        }
        else
        {
            msgLouer1.setText("Vous avez atteint votre limite de Location.");
            msgLouer2.setText("Veuillez retourner un examplaire avant de Louer.");
            louerDialog.setVisible(true);
        }
    }//GEN-LAST:event_louerButtonActionPerformed

    private void okInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okInputButtonActionPerformed
        inputErreur.setVisible(false);
    }//GEN-LAST:event_okInputButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButtonConnection;
    private javax.swing.JButton OKButtonSearch;
    private javax.swing.JButton actFicheButton;
    private javax.swing.JTextField acteurField;
    private javax.swing.JLabel acteurLabel;
    private javax.swing.JTextField anneeMaxField;
    private javax.swing.JLabel anneeMaxLabel;
    private javax.swing.JTextField anneeMinField;
    private javax.swing.JLabel anneeMinLabel;
    private javax.swing.JButton connectButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel err1;
    private javax.swing.JLabel err2;
    private javax.swing.JDialog erreurConnection;
    private javax.swing.JDialog erreurSearch;
    private javax.swing.JTextField fFieldAnnee;
    private javax.swing.JTextField fFieldDuree;
    private javax.swing.JTextField fFieldGenres;
    private javax.swing.JTextField fFieldLangue;
    private javax.swing.JTextField fFieldPays;
    private javax.swing.JComboBox fFieldPersonnage;
    private javax.swing.JComboBox fFieldPersonnel;
    private javax.swing.JTextArea fFieldResume;
    private javax.swing.JTextField fFieldTitre;
    private javax.swing.JLabel fLabelAnnee;
    private javax.swing.JLabel fLabelAnnee1;
    private javax.swing.JLabel fLabelDuree;
    private javax.swing.JLabel fLabelGenres;
    private javax.swing.JLabel fLabelLangue;
    private javax.swing.JLabel fLabelPays;
    private javax.swing.JLabel fLabelPays1;
    private javax.swing.JLabel fLabelPersonnage;
    private javax.swing.JLabel fLabelPersonnel;
    private javax.swing.JLabel fLabelResume;
    private javax.swing.JLabel fLabelTitre;
    private javax.swing.JDialog ficheFilmDialog;
    private javax.swing.JDialog fichePersDialog;
    private javax.swing.JButton filmFicheButton;
    private javax.swing.JTextField genreField;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JDialog inputErreur;
    private javax.swing.JLabel inputErreurLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField langueField;
    private javax.swing.JLabel langueLabel;
    private javax.swing.JButton louerButton;
    private javax.swing.JDialog louerDialog;
    private javax.swing.JLabel msgErreur1;
    private javax.swing.JLabel msgErreur2;
    private javax.swing.JLabel msgLouer1;
    private javax.swing.JLabel msgLouer2;
    private javax.swing.JButton okInputButton;
    private javax.swing.JTextField pFieldAnnee;
    private javax.swing.JTextArea pFieldBio;
    private javax.swing.JTextField pFieldLieu;
    private javax.swing.JTextField pFieldNom;
    private javax.swing.JLabel pLabelBio;
    private javax.swing.JLabel pLabelNom;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField paysField;
    private javax.swing.JLabel paysLabel;
    private javax.swing.JButton persFicheButton;
    private javax.swing.JTextField realisateurField;
    private javax.swing.JLabel realisateurLabel;
    private javax.swing.JList resultJList;
    private javax.swing.JPanel resutlPanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField titreField;
    private javax.swing.JLabel titreLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
