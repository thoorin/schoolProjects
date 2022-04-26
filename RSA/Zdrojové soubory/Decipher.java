package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class Decipher extends Frame {
    //vytvoření komponentů okna
    private TextField tFPublicKey = new TextField();
    private TextField tFCiphered = new TextField();
    private TextArea area = new TextArea();
    private Label lPublic = new Label("Veřejný klíč:");
    private Label lCiphered = new Label("Zašifrovaná zpráva:");
    private Label lMessage = new Label("Dešifrovaná zpráva:");
    private Button decipherBtn = new Button("Dešifrovat");
    private final FileDialog fileDialog = new FileDialog(this,"Vyberte soubor se zašifrovanou zprávou");
    private Button showFileDialogButton = new Button("Nahrát");

    //konstruktor dešifrovacího okna
    Decipher(int x, int y) {
        //nastavení jednotlivých komponent okna
        decipherBtn.setBounds(360, 60, 80, 30);
        showFileDialogButton.setBounds(360,130,80,30);
        tFPublicKey.setBounds(50,60, 100, 30);
        area.setBounds(50, 130, 300, 110);
        tFCiphered.setBounds( 150, 60, 200, 30);
        lPublic.setBounds( 50, 30, 70, 30);
        lCiphered.setBounds( 150, 30, 120, 30);
        lMessage.setBounds( 50, 100, 120, 30);

        area.setEditable(false);

        add(decipherBtn);
        add(tFPublicKey);
        add(area);
        add(tFCiphered);
        add(lPublic);
        add(lCiphered);
        add(lMessage);
        add(showFileDialogButton);

        //nastavení samotného okna
        createListeners();
        setResizable(false);
        setLocation(x,y);
        setSize(500, 260);
        setTitle("RSA - Dešifrování");
        setLayout(null);
        setVisible(true);
    }

    //vytvoření jednotlivých posluchačů
    private void createListeners(){
        //chování po stisknutí dešifrovacího tlačítko
        decipherBtn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                try {
                    //spuštění dešifrování
                    String decipheredText = RSA.decipher(tFPublicKey.getText(), tFCiphered.getText());
                    //zapsání výsledk dešifrování
                    area.setText(decipheredText);
                    area.setBackground(Color.WHITE);
                    tFCiphered.setBackground(Color.WHITE);
                } catch (NumberFormatException ex){
                    //pokud je špatně zadaný klíč nebo zpráva zobrazí se varování
                    area.setText("Špatně zadaný klíč nebo zpráva");
                    area.setBackground(Color.PINK);
                }
            }
        });

        //chování po stisknutí ukládacího tlačítka
        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //zobrazení dialogu
                fileDialog.setVisible(true);

                try {
                    //přečtení šifrované zprávu z určeného souboru
                    BufferedReader reader = new BufferedReader(new FileReader(fileDialog.getFile()));
                    String currentLine = reader.readLine();
                    tFCiphered.setText(currentLine);
                    reader.close();
                    tFCiphered.setBackground(Color.WHITE);
                } catch (Exception ex){
                    //pokud uživatel zadá špatný soubor, zobrazí se varování
                    tFCiphered.setText("Špatný typ souboru");
                    tFCiphered.setBackground(Color.PINK);
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
