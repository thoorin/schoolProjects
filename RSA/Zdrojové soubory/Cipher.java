package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Random;

public class Cipher extends Frame {
    //vytvoření komponentů okna
    private TextField tFPrime1 = new TextField();
    private TextField tFPrime2 = new TextField();
    private TextField tFBitLength = new TextField();
    private TextField tFPublicKey = new TextField();
    private TextField tFPrivateKey = new TextField();
    private Button generateBtn = new Button("Generovat");
    private Button cipherBtn = new Button("Zašifrovat");
    private TextArea areaMessage = new TextArea();
    private TextField tFCiphered = new TextField();
    private Label lPrime1 = new Label("Prvočíslo 1:");
    private Label lPrime2 = new Label("Prvočíslo 2:");
    private Label lBitLength = new Label("Bitová délka:");
    private Label lMessage = new Label("Zpráva na zašifrování:");
    private Label lPublic = new Label("Veřejný klíč:");
    private Label lPrivate = new Label("Tajný klíč:");
    private Label lCiphered = new Label("Zašifrovaná zpráva:");
    private final FileDialog fileDialog = new FileDialog(this,"Vyberte kam se má soubor uložit");
    private Button showFileDialogButton = new Button("Uložit");
    private Component[] elements = new Component[] { tFPrime1, tFPrime2, tFBitLength, tFPublicKey, tFPrivateKey,
            generateBtn, cipherBtn, areaMessage, tFCiphered, lPrime1, lPrime2, lBitLength, lMessage, lPublic, lPrivate, lCiphered, showFileDialogButton };

    //konstruktor šifrovacího okna
    Cipher(int x, int y) {
        setAllBounds();
        tFPrivateKey.setEditable(false);
        tFPublicKey.setEditable(false);
        tFCiphered.setEditable(false);
        createListeners();
        setWindow( x, y );
    }

    //přidání jednotlivých komponent do okna a nastavení samotného okna
    private void setWindow(int x, int y){
        for (int i = 0; i < elements.length; i++) add(elements[i]);
        setResizable(false);
        setLocation(x,y);
        setSize(520, 520);
        setTitle("RSA - Zašifrování");
        setLayout(null);
        setVisible(true);
    }

    //vytvoření jednotlivých posluchačů
    private void createListeners(){
        //chování po stisknutí šifrovacího tlačítko
        cipherBtn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                BigInteger prime1 = null;
                BigInteger prime2 = null;
                //kontrola vstupu prvního prvočísla, zachytává nepovolené symboly
                try {
                    prime1 = new BigInteger(tFPrime1.getText());
                    tFPrime1.setBackground(Color.WHITE);
                } catch (Exception ex){
                    tFPrime1.setText("Špatně zadané prvočíslo");
                    tFPrime1.setBackground(Color.PINK);
                }
                //kontrola vstupu prvního prvočísla, zachytává nepovolené symboly
                try {
                    prime2 = new BigInteger(tFPrime2.getText());
                    tFPrime2.setBackground(Color.WHITE);
                } catch (Exception ex){
                    tFPrime2.setText("Špatně zadané prvočíslo");
                    tFPrime2.setBackground(Color.PINK);
                }
                if (prime1 != null && prime2 != null) {
                    try {
                        //spuštění šifrování
                        Object[] results = RSA.cipher(areaMessage.getText(), prime1, prime2);

                        //uložení výsledků šifrování do jednotlivých textových polí
                        tFPublicKey.setText(String.valueOf(results[0]));
                        tFPrivateKey.setText(String.valueOf(results[1]));
                        tFCiphered.setText(String.valueOf(results[2]));
                        areaMessage.setBackground(Color.WHITE);
                        tFBitLength.setBackground(Color.WHITE);
                    } catch (IsNotPrimeException ex){
                        //pokud některé ze zadaných čísel není prvočíslo, vypíše se do textové oblasti varování
                        areaMessage.setText("Některé ze zadaných čísel není prvočíslo");
                        areaMessage.setBackground(Color.PINK);
                    } catch (NumberFormatException ex){
                        //pokud je text pro zašifrování prázdný vypíše se do textové oblasti varování
                        areaMessage.setText("Text pro zašifrování je prázdný");
                        areaMessage.setBackground(Color.PINK);
                    } catch (ArrayIndexOutOfBoundsException ex){
                        //pokud není možné zprávu zašifrovat pomocí daných prvočísel, vypíše se do textové oblasti varování
                        areaMessage.setText("Text pro zašifrování je příliš dlouhý nebo zadaná prvočísla příliš krátká");
                        areaMessage.setBackground(Color.PINK);
                    }
                }
            }
        });

        //chování po stisknutí generujícího tlačítka
        generateBtn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                try {
                    //získání hodnoty bitové délky z textového pole
                    int BIT_LENGTH = Integer.valueOf(tFBitLength.getText());
                    Random rand = new Random();
                    //náhodné vygenerování prvočísel dané bitové délky
                    BigInteger prime1 = BigInteger.probablePrime(BIT_LENGTH / 2, rand);
                    BigInteger prime2 = BigInteger.probablePrime(BIT_LENGTH / 2, rand);

                    //zobrazení vygenerovaných prvočísel v odpovídajících textových polích
                    tFPrime1.setText(String.valueOf(prime1));
                    tFPrime2.setText(String.valueOf(prime2));
                    tFBitLength.setBackground(Color.white);
                    tFPrime1.setBackground(Color.WHITE);
                    tFPrime2.setBackground(Color.WHITE);
                } catch (Exception ex){
                    //pokud je bitová délka špatně zadaná (je prázdná nebo obsahuje neplatné symboly - jakýkoliv nečíselný
                    //symbol-) zobrazí se varování
                    tFBitLength.setText("Špatně zadaná bitová délka");
                    tFBitLength.setBackground(Color.PINK);
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
                    //zapsání šifrované zprávu do určeného souboru
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileDialog.getDirectory()+fileDialog.getFile()));
                    writer.write(tFCiphered.getText());

                    writer.close();
                } catch (Exception ex){

                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void setAllBounds(){
        //nastavení parametrů jednotlivých komponent
        int[] bounds = new int[] { 50, 60, 270, 60, 50, 120, 50, 360, 260, 360, 270, 120, 380, 120, 50, 200, 50, 420, 50, 30, 270, 30, 50, 90, 50, 170, 50, 330, 260, 330, 50, 390, 50, 460 };
        int[] widths = new int[] { 200, 200, 200, 210, 210, 80, 80, 420, 420, 70, 70, 70, 120, 70, 70, 120, 70 };

        for (int i = 0; i < elements.length; i++){
            int height = ( elements[i].getClass().getName() == "java.awt.TextArea" )? 110 : 30;
            elements[i].setBounds(bounds[2*i], bounds[2*i+1], widths[i], height);
        }
    }
}
