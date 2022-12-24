package codigocesar;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.AbcEnum;
import enums.EncryptionType;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JonathanPortatil
 */
public class cesar extends javax.swing.JFrame implements ActionListener{

    final int abcLength = AbcEnum.MINUSC.getValueEnum().length();
    final int abcIndxLength = AbcEnum.MINUSC.getValueEnum().length()-1;
    /**
     * Creates new form cesar
     */
    public cesar() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        cifrarBoton.addActionListener(this);
        descifrarBoton.addActionListener(this);
    }
    
    
    
    public Boolean validations(String inputValue,String corrimientoValue, EncryptionType encryptionType){
        
        if(corrimientoValue.equals("0")){
            System.out.println("Valor invalido de corrimiento no puede ser 0");
            JOptionPane.showMessageDialog(rootPane, "Valor invalido de corrimiento no puede ser 0");
            return false;
        }
        
        if (corrimientoValue.isEmpty() || corrimientoValue.equals("")){
            System.out.println("Valor invalido de corrimiento");
            JOptionPane.showMessageDialog(rootPane, "Valor invalido de corrimiento");
            return false;
        }
        
        if((inputValue.isEmpty() || inputValue.equals("")) && encryptionType == EncryptionType.CIFRAR){
            System.out.println("Valor invalido para cifrar");
            JOptionPane.showMessageDialog(rootPane, "Valor invalido para cifrar");
            return false;
        }else if((inputValue.isEmpty() || inputValue.equals("")) && encryptionType == EncryptionType.DESCIFRAR){
            System.out.println("Valor invalido para descifrar");
            JOptionPane.showMessageDialog(rootPane, "Valor invalido para descifrar");
            return false;
        }else if(inputValue.isEmpty() || inputValue.equals("")){
            System.out.println("Valor invalido para cifrar/descifrar");
            JOptionPane.showMessageDialog(rootPane, "Valor invalido para cifrar/descifrar");
            return false;            
        }
        
        return true;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==cifrarBoton) cifrar();
        if (e.getSource()==descifrarBoton) descifrar();
        
    }
    
    
    
    private void cifrar(){
        
        String inputValue = textInput.getText();
        String corrimientoValue = corrimientoInput.getText();
        
        Boolean isValidated = validations(inputValue, corrimientoValue, EncryptionType.CIFRAR);
        if(isValidated == false) return;
        
        int corrimiento;
        try{
            corrimiento = Integer.valueOf(corrimientoValue);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Ocurrrio un problema, no se puedo cifrar");
            return;
        }
        
        String finalResult = "";
        char[] charTextArray = inputValue.toCharArray();
        
        for(char i : charTextArray){
            
            
            if(AbcEnum.MINUSC.getValueEnum().contains(String.valueOf(i))){
                System.out.println("Min :: "+ i);
                if(AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) + corrimiento
                        > AbcEnum.MINUSC.getValueEnum().length()-1){
                    finalResult += AbcEnum.MINUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    abcLength + corrimiento % abcIndxLength,
                                    (AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    abcLength + corrimiento % abcIndxLength)+1
                            );
                }else{                  
                    finalResult += AbcEnum.MINUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    corrimiento % abcIndxLength,
                                    (AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    corrimiento % abcIndxLength)+1
                            );
                }
                
            }else if(AbcEnum.MAYUSC.getValueEnum().contains(String.valueOf(i))){
                System.out.println("May :: "+ i);
                if(AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) + corrimiento
                        > AbcEnum.MAYUSC.getValueEnum().length()-1){
                    finalResult += AbcEnum.MAYUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    abcLength + corrimiento % abcIndxLength,
                                    (AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    abcLength + corrimiento % abcIndxLength)+1
                            );
                }else{                  
                    finalResult += AbcEnum.MAYUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    corrimiento % abcIndxLength,
                                    (AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    corrimiento % abcIndxLength)+1
                            );
                }
            }else{
                System.out.println("nn :: "+ i);
                finalResult += i;
            }
            
        }
        
        textAreaResult.setText(finalResult);
    
    }
    
    private void descifrar(){
     
        String inputValue = textInput.getText();
        String corrimientoValue = corrimientoInput.getText();
        
        int corrimiento;
        try{
            corrimiento = Integer.valueOf(corrimientoValue);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Ocurrrio un problema, no se puedo descifrar");
            return;
        }
        
        
        
        
        Boolean isValidated = validations(inputValue, corrimientoValue, EncryptionType.DESCIFRAR);
        
        if(isValidated == false) return;
        
        String finalResult = "";
        char[] charTextArray = inputValue.toCharArray();
        
        for(char i : charTextArray){
            
            
            if(AbcEnum.MINUSC.getValueEnum().contains(String.valueOf(i))){
                System.out.println("Min :: "+ i);
                if(AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) - corrimiento < 0){
                    finalResult += AbcEnum.MINUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    abcLength - corrimiento % abcIndxLength,
                                    (AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    abcLength - corrimiento % abcIndxLength)+1
                            );
                }else{                  
                    finalResult += AbcEnum.MINUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    corrimiento % abcIndxLength,
                                    (AbcEnum.MINUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    corrimiento % abcIndxLength)+1
                            );
                }
                
            }else if(AbcEnum.MAYUSC.getValueEnum().contains(String.valueOf(i))){
                System.out.println("May :: "+ i);
                if(AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) - corrimiento < 0){
                    finalResult += AbcEnum.MAYUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    abcLength - corrimiento % abcIndxLength,
                                    (AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) +
                                    abcLength - corrimiento % abcIndxLength)+1
                            );
                }else{                  
                    finalResult += AbcEnum.MAYUSC.getValueEnum()
                            .substring(
                                    AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    corrimiento % abcIndxLength,
                                    (AbcEnum.MAYUSC.getValueEnum().indexOf(String.valueOf(i)) -
                                    corrimiento % abcIndxLength)+1
                            );
                }
            }else{
                System.out.println("nn :: "+ i);
                finalResult += i;
            }
            
        }
        
        textAreaResult.setText(finalResult);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textInput = new javax.swing.JTextField();
        corrimientoInput = new javax.swing.JTextField();
        cifrarBoton = new javax.swing.JButton();
        descifrarBoton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaResult = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("CÓDIGO CESAR");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Ingrese la palabra a cifrar/descifrar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Ingrese el valor de corrimiento");

        textInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        corrimientoInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cifrarBoton.setText("Cifrar");

        descifrarBoton.setText("Descifrar");

        textAreaResult.setColumns(20);
        textAreaResult.setRows(5);
        jScrollPane1.setViewportView(textAreaResult);

        jLabel4.setText("Resultado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(corrimientoInput))
                            .addComponent(jLabel2)
                            .addComponent(textInput, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(cifrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(descifrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(corrimientoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cifrarBoton)
                    .addComponent(descifrarBoton))
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(cesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cesar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cifrarBoton;
    private javax.swing.JTextField corrimientoInput;
    private javax.swing.JButton descifrarBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textAreaResult;
    private javax.swing.JTextField textInput;
    // End of variables declaration//GEN-END:variables
}
