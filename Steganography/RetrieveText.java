package com.company;

import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;

public class RetrieveText extends JFrame implements ActionListener
{
    JButton openBtn = new JButton("Najít obrázek"), encodeBtn = new JButton("Zakódovat");
    JTextArea text = new JTextArea(3,6);
    BufferedImage image = null;
    JScrollPane imagePane = new JScrollPane();

    public RetrieveText() {
        super("Získání zašifrovaného textu z obrázku");
        createInterface();
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    //vytvoření rozhraní pro uživatele
    private void createInterface() {
        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.add(openBtn);
        this.getContentPane().add(jPanel, BorderLayout.NORTH);
        openBtn.addActionListener(this);
        openBtn.setBackground(Color.WHITE);

        JPanel jPanel0 = new JPanel(new FlowLayout());
        jPanel0.add(encodeBtn);
        this.getContentPane().add(jPanel0, BorderLayout.SOUTH);
        encodeBtn.addActionListener(this);
        encodeBtn.setBackground(Color.BLACK);
        encodeBtn.setForeground(Color.WHITE);

        openImage();
    }
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if(o == openBtn)
            openImage();
        else if(o == encodeBtn) {
            new PasteText();
            this.dispose();
        }
    }
    //dialogové okno pro vybrání zašifrovaného obrázku
    private java.io.File showFileDialog(boolean open) {
        JFileChooser jFileChooser = new JFileChooser("Vybrat obrázek");
        javax.swing.filechooser.FileFilter fileFilter = new javax.swing.filechooser.FileFilter() {
            public boolean accept(java.io.File file) {
                String name = file.getName().toLowerCase();
                return file.isDirectory() ||   name.endsWith(".png") || name.endsWith(".bmp");
            }
            public String getDescription() {
                return "Image (*.png, *.bmp)";
            }
        };
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.addChoosableFileFilter(fileFilter);

        java.io.File file = null;
        if(open && jFileChooser.showOpenDialog(this) == jFileChooser.APPROVE_OPTION)
            file = jFileChooser.getSelectedFile();
        else if(!open && jFileChooser.showSaveDialog(this) == jFileChooser.APPROVE_OPTION)
            file = jFileChooser.getSelectedFile();
        return file;
    }

    //načtení zašifrovaného obrázku
    private void openImage() {
        java.io.File f = showFileDialog(true);
        try {
            image = ImageIO.read(f);
            JLabel l = new JLabel(new ImageIcon(image));
            imagePane.getViewport().add(l);

            JPanel jPanel = new JPanel(new GridLayout(1,1));
            jPanel.add(new JScrollPane(text));
            text.setFont(new Font("Arial",Font.BOLD,20));
            jPanel.setBorder(BorderFactory.createTitledBorder("Dekódovaný text"));
            text.setEditable(false);
            this.getContentPane().add(jPanel, BorderLayout.EAST);

            imagePane.setBorder(BorderFactory.createTitledBorder("Obrázek se zašifrovaným textem"));
            this.getContentPane().add(imagePane, BorderLayout.CENTER);
            this.validate();

            retrieveText();
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    //dekodování zprávy
    private void retrieveText() {
        //rozšifrování velikosti
        int len = extractInteger(image, 0, 0);

        byte b[] = new byte[len];
        for(int i=0; i<len; i++)
            //rozšifrování daného znaku
            b[i] = extractByte(image, i*8+32, 0);
        text.setText(new String(b));
    }

    // metoda pro rozšifrování velikosti zprávy
    private int extractInteger(BufferedImage img, int start, int storageBit) {
        int maxX = img.getWidth(), maxY = img.getHeight();
        int startX = start/maxY;
        int startY = start - startX*maxY;
        int count=0;
        int length = 0;

        //projíždění jednotlivých pixelů obrázku
        for(int i=startX; i<maxX && count<32; i++) {
            for(int j=startY; j<maxY && count<32; j++) {
                //získání pixelu a bitové hodnoty pro zjištění velikosti zprávy
                int rgb = img.getRGB(i, j), bit = getBitValue(rgb, storageBit);
                //současná velikost zprávy
                length = setBitValue(length, count, bit);
                count++;
            }
        }
        return length;
    }

    //rozšifrování znaku
    private byte extractByte(BufferedImage img, int start, int storageBit) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start/maxY;
        int startY = start - startX*maxY;
        int count=0;
        byte b = 0;
        for(int i=startX; i<maxX && count<8; i++) {
            for(int j=startY; j<maxY && count<8; j++) {
                //získání pixelu a bitové hodnoty reprezentující daný bit znaku
                int rgb = img.getRGB(i, j), bit = getBitValue(rgb, storageBit);
                //hodnota znaku
                b = (byte)setBitValue(b, count, bit);
                count++;
            }
        }
        return b;
    }
    //získání bitu
    private int getBitValue(int n, int location) {
        int v = n & (int) Math.round(Math.pow(2, location));
        return v==0?0:1;
    }
    // nastavení bitu
    private int setBitValue(int n, int location, int bit) {
        int toggle = (int) Math.pow(2, location), bitValue = getBitValue(n, location);
        if(bitValue == bit)
            return n;
        if(bitValue == 0 && bit == 1)
            n |= toggle;
        else if(bitValue == 1 && bit == 0)
            n ^= toggle;
        return n;
    }

    public static void main(String arg[]) {
        new RetrieveText();
    }
}
