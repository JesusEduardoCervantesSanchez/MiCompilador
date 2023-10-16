package compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author CSjes
 */
public class Interfaz extends javax.swing.JFrame {

    public int cont, conant = 0, c = 0;
    public boolean bandera = true;
    public NoLinea nlinea;
    public String elementos = "";
    Lexer lexico;
    public Stack<String> pilaSintactica = new Stack();
    public Map<String, Produccion> Producciones;
    public ArrayList<String> Estados = new ArrayList<>(Arrays.asList("Q0", "Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7", "Q8", "Q9", "Q10", "Q11", "Q12", "Q13", "Q14", "Q15", "Q16", "Q17", "Q18", "Q19", "Q20", "Q21", "Q22", "Q23", "Q24", "Q25", "Q26", "Q27", "Q28", "Q29", "Q30", "Q31", "Q32", "Q33", "Q34", "Q35", "Q36", "Q37", "Q38", "Q39", "Q40", "Q41", "Q42", "Q43", "Q44"));
    public ArrayList<String> TYNT = new ArrayList<>(Arrays.asList("id", "num", "int", "float", "char", ",", ";", "+", "-", "*", "/", "=", "(", ")", "$", "P", "Tipo", "V", "A", "EXP", "E", "TERM", "T", "F"));
    public String componente;
    public String[][] tabla = {
        {"Q7", "", "Q4", "Q5", "Q6", "", "", "", "", "", "", "", "", "", "", "Q1", "Q2", "", "Q3", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P0", "", "", "", "", "", "", "", "", ""},
        {"Q8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P2", "", "", "", "", "", "", "", "", ""},
        {"P3", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"P4", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"P5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "Q9", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "Q11", "Q12", "", "", "", "", "", "", "", "", "", "", "Q10", "", "", "", "", "", ""},
        {"Q18", "Q19", "", "", "", "", "", "Q14", "Q15", "", "", "", "Q20", "", "", "", "", "", "", "Q13", "", "Q16", "", "Q17"},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P1", "", "", "", "", "", "", "", "", ""},
        {"Q21", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"Q7", "", "Q4", "Q5", "Q6", "", "", "", "", "", "", "", "", "", "", "Q22", "Q2", "", "Q3", "", "", "", "", ""},
        {"", "", "", "", "", "", "Q23", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"Q18", "Q19", "", "", "", "", "", "", "", "", "", "", "Q20", "", "", "", "", "", "", "", "", "Q24", "", "Q17"},
        {"Q18", "Q19", "", "", "", "", "", "", "", "", "", "", "Q20", "", "", "", "", "", "", "", "", "Q25", "", "Q17"},
        {"", "", "", "", "", "", "P14", "Q27", "Q28", "", "", "", "", "P14", "", "", "", "", "", "", "Q26", "", "", ""},
        {"", "", "", "", "", "", "P18", "P18", "P18", "Q30", "Q31", "", "", "P18", "", "", "", "", "", "", "", "", "Q29", ""},
        {"", "", "", "", "", "", "P19", "P19", "P19", "P19", "P19", "", "", "P19", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P20", "P20", "P20", "P20", "P20", "", "", "P20", "", "", "", "", "", "", "", "", "", ""},
        {"Q18", "Q19", "", "", "", "", "", "Q14", "Q15", "", "", "", "Q20", "", "", "", "", "", "", "Q32", "", "Q16", "", "Q17"},
        {"", "", "", "", "", "Q11", "Q12", "", "", "", "", "", "", "", "", "", "", "Q33", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P7", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P8", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P14", "Q27", "Q28", "", "", "", "", "P14", "", "", "", "", "", "", "Q34", "", "", ""},
        {"", "", "", "", "", "", "P14", "Q27", "Q28", "", "", "", "", "P14", "", "", "", "", "", "", "Q35", "", "", ""},
        {"", "", "", "", "", "", "P11", "", "", "", "", "", "", "P11", "", "", "", "", "", "", "", "", "", ""},
        {"Q18", "Q19", "", "", "", "", "", "", "", "", "", "", "Q20", "", "", "", "", "", "", "", "", "Q36", "", "Q17"},
        {"Q18", "Q19", "", "", "", "", "", "", "", "", "", "", "Q20", "", "", "", "", "", "", "", "", "Q37", "", "Q17"},
        {"", "", "", "", "", "", "P15", "P15", "P15", "", "", "", "", "P15", "", "", "", "", "", "", "", "", "", ""},
        {"Q18", "Q19", "", "", "", "", "", "", "", "", "", "", "Q20", "", "", "", "", "", "", "", "", "", "", "Q38"},
        {"Q18", "Q19", "", "", "", "", "", "", "", "", "", "", "Q20", "", "", "", "", "", "", "", "", "", "", "Q39"},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "Q40", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P6", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P9", "", "", "", "", "", "", "P9", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P10", "", "", "", "", "", "", "P10", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P14", "Q27", "Q28", "", "", "", "", "P14", "", "", "", "", "", "", "Q41", "", "", ""},
        {"", "", "", "", "", "", "P14", "Q27", "Q28", "", "", "", "", "P14", "", "", "", "", "", "", "Q42", "", "", ""},
        {"", "", "", "", "", "", "P18", "P18", "P18", "Q30", "Q31", "", "", "P18", "", "", "", "", "", "", "", "", "Q43", ""},
        {"", "", "", "", "", "", "P18", "P18", "P18", "Q30", "Q31", "", "", "P18", "", "", "", "", "", "", "", "", "Q44", ""},
        {"", "", "", "", "", "", "P21", "P21", "P21", "P21", "P21", "", "", "P21", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P12", "", "", "", "", "", "", "P12", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P13", "", "", "", "", "", "", "P13", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P16", "P16", "P16", "", "", "", "", "P16", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P17", "P17", "P17", "", "", "", "", "P17", "", "", "", "", "", "", "", "", "", ""}
    };
    String res, err;

    public Interfaz() {
        initComponents();
        Producciones = new LinkedHashMap<>();
        inicializar();
        setLocationRelativeTo(null);
    }

    private void InicializarPilaSintactica() {
        pilaSintactica.clear();
//      cadena.add("$"); //Añade el terminador de cadena
        pilaSintactica.push("$");
        pilaSintactica.push("Q0");
    }

    private void inicializar() {
        nlinea = new NoLinea(codigoFuente);
        jScrollPane4.setRowHeaderView(nlinea);
    }

    private void Limpiar() {
        Errores.setText("");
        LexicoG.setText("");
        Sintactico.setText("");
    }

    private void AnalisisLexico() {
        DatosToken DToken;
        try {
            File codigo = new File("archivo.txt");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytes = codigoFuente.getText().getBytes();
            output.write(bytes);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF-8"));
            lexico = new Lexer(entrada);
            DToken = new DatosToken();
            String resu = "", resuE = "";
            while (bandera) {
                Tokens token = lexico.yylex();
                if (token == null) {
                    AnalisisSintactico("$", "", (DatosToken.numeroLinea + 1) + "");
                    resu += "";
                    return;
                }
                switch (token) {
                    case ERROR:
                        resuE += "Error lexico en la linea " + (DToken.numeroLinea + 1) + " simbolo: " + lexico.lexeme + " incorecto" + "\n";
                        Errores.setText(resuE);
                        break;
                    default:
                        if (token.getSimbolo() == null) {
                            resu += token + "\n";
                            LexicoG.setText(resu);
                            AnalisisSintactico(token + "", DatosToken.lexema, (DatosToken.numeroLinea + 1) + "");
                        } else {
                            resu += token.getSimbolo() + "\n";
                            AnalisisSintactico(token.getSimbolo(), DatosToken.lexema, (DatosToken.numeroLinea + 1) + "");
                            LexicoG.setText(resu);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AnalisisSintactico(String comp, String lexema, String nlinea) {
        cont = 0;
        String estadoPila, dir;
        int ncol, nfil;
        boolean ban;
        componente = comp;
        ban = true;
        Producciones.put("P0", new Produccion("P'", "P", 1));
        Producciones.put("P1", new Produccion("P", "Tipo id V", 3));
        Producciones.put("P2", new Produccion("P", "A", 1));
        Producciones.put("P3", new Produccion("Tipo", "int", 1));
        Producciones.put("P4", new Produccion("Tipo", "float", 1));
        Producciones.put("P5", new Produccion("Tipo", "char", 1));
        Producciones.put("P6", new Produccion("V", ", id V", 3));
        Producciones.put("P7", new Produccion("V", "; P", 2));
        Producciones.put("P8", new Produccion("A", "id = EXP ;", 4));
        Producciones.put("P9", new Produccion("EXP", "+ TERM E", 3));
        Producciones.put("P10", new Produccion("EXP", "- TERM E", 3));
        Producciones.put("P11", new Produccion("EXP", "TERm E", 2));
        Producciones.put("P12", new Produccion("E", "+ TERM E", 3));
        Producciones.put("P13", new Produccion("E", "- TERM E", 3));
        Producciones.put("P14", new Produccion("E", "Vacia", 0));
        Producciones.put("P15", new Produccion("TERM", "F T", 2));
        Producciones.put("P16", new Produccion("T", "* F T", 3));
        Producciones.put("P17", new Produccion("T", "/ F T", 3));
        Producciones.put("P18", new Produccion("T", "Vacia", 0));
        Producciones.put("P19", new Produccion("F", "id", 1));
        Producciones.put("P20", new Produccion("F", "num", 1));
        Producciones.put("P21", new Produccion("F", "( EXP )", 3));
        while (ban) {
            estadoPila = pilaSintactica.peek();
            ncol = TYNT.indexOf(componente);
            nfil = Estados.indexOf(estadoPila);
            //System.out.println(" " + nfil + " " + ncol);
            String accion = tabla[nfil][ncol];
            //System.out.println(accion);
            if (!accion.equals("")) {
                if (accion.equals("P0")) {
                    for (int i = 0; i < (Producciones.get(accion).getCE()) * 2; i++) {
                        pilaSintactica.pop();
                    }
                    res += Arrays.toString(pilaSintactica.toArray()) + "\n";
                    res += "Analizis finalizado correctamente";
                    Sintactico.setText(res);
                    ban = false;
                } else {
                    switch (accion.charAt(0)) {
                        case 'P' -> {
                            //System.out.println(Producciones.get(accion).getP());
                            if (Producciones.get(accion).getP().equals("Vacia")) {
                                String estadoPilaP = pilaSintactica.peek();
                                //System.out.println(Producciones.get(accion).getNT());
                                int ncolP = TYNT.indexOf(Producciones.get(accion).getNT());
                                int nfilP = Estados.indexOf(estadoPilaP);
                                //System.out.println(" " + nfilP + " " + ncolP);
                                pilaSintactica.push(Producciones.get(accion).getNT());
                                pilaSintactica.push(tabla[nfilP][ncolP]);
                                res += Arrays.toString(pilaSintactica.toArray()) + "\n";
                                Sintactico.setText(res);
                            } else {
                                for (int i = 0; i < (Producciones.get(accion).getCE()) * 2; i++) {
                                    pilaSintactica.pop();
                                }
                                String estadoPilaP = pilaSintactica.peek();
                                int ncolP = TYNT.indexOf(Producciones.get(accion).getNT());
                                int nfilP = Estados.indexOf(estadoPilaP);
                                //System.out.println(" " + nfilP + " " + ncolP);
                                pilaSintactica.push(Producciones.get(accion).getNT());
                                pilaSintactica.push(tabla[nfilP][ncolP]);
                                res += Arrays.toString(pilaSintactica.toArray()) + "\n";
                                Sintactico.setText(res);
                            }
                            break;
                        }
                        case 'Q' -> {
                            pilaSintactica.push(componente);
                            pilaSintactica.push(accion);
                            res += Arrays.toString(pilaSintactica.toArray()) + "\n";
                            Sintactico.setText(res);
                            ban = componente.equals("$");
                            break;
                        }
                    }
                }
            } else {
                elementos = "";
                for (int i = 0; i < 14; i++) {
                    //System.out.println(tabla[nfil][i]);
                    elementos += tabla[nfil][i].equals("") ? "" : TYNT.get(i) + ", ";
                }
                if (!lexema.equals("")) {
                    err += "Error sintactico en la linea " + nlinea + " no se esperaba " + lexema + " se esperaba " + elementos + "\n";
                } else {
                    err += "Error sintactico en la linea " + nlinea + " se esperaba " + elementos + "\n";
                }
                Errores.setText(err);
                ban = false;
                bandera = false;
            }
            //System.out.println("En el while");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        codigoFuente1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        LexicoG = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        codigoFuente = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        Sintactico = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        Errores = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();

        codigoFuente1.setColumns(20);
        codigoFuente1.setRows(5);
        codigoFuente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoFuente1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(codigoFuente1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MiCompilador");
        setLocation(new java.awt.Point(0, 0));

        LexicoG.setEditable(false);
        LexicoG.setColumns(20);
        LexicoG.setRows(5);
        LexicoG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                LexicoGKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(LexicoG);

        codigoFuente.setColumns(20);
        codigoFuente.setRows(5);
        codigoFuente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codigoFuenteKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(codigoFuente);

        Sintactico.setEditable(false);
        Sintactico.setColumns(20);
        Sintactico.setRows(5);
        Sintactico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SintacticoKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(Sintactico);

        Errores.setEditable(false);
        Errores.setColumns(20);
        Errores.setForeground(new java.awt.Color(255, 0, 0));
        Errores.setRows(5);
        Errores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ErroresKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(Errores);

        jLabel1.setText("Analisis Lexico");

        jLabel2.setText("Analisis Sintactico");

        jLabel3.setText("Errores");

        btnAnalizar.setText("Analizar");
        btnAnalizar.setAutoscrolls(true);
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(413, 413, 413))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(354, 354, 354))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(372, 372, 372))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnalizar)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LexicoGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LexicoGKeyReleased

    }//GEN-LAST:event_LexicoGKeyReleased

    private void codigoFuente1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoFuente1KeyReleased

    }//GEN-LAST:event_codigoFuente1KeyReleased

    private void codigoFuenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoFuenteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoFuenteKeyReleased

    private void SintacticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SintacticoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_SintacticoKeyReleased

    private void ErroresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ErroresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ErroresKeyReleased

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        InicializarPilaSintactica();
        Limpiar();
        bandera = true;
        res = "";
        err = "";
        AnalisisLexico();    }//GEN-LAST:event_btnAnalizarActionPerformed

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
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea Errores;
    public javax.swing.JTextArea LexicoG;
    public javax.swing.JTextArea Sintactico;
    private javax.swing.JButton btnAnalizar;
    public javax.swing.JTextArea codigoFuente;
    public javax.swing.JTextArea codigoFuente1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}
