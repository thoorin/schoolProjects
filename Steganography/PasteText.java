package com.company;

import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;

public class PasteText extends JFrame implements ActionListener
{
    boolean imgLoaded = false, wordLoaded = false;
    JButton openBtn = new JButton("Najít obrázek"),
            saveBtn = new JButton("Uložit obrázek"),
            decodeBtn = new JButton("Dekódovat");
    JTextArea text = new JTextArea(2,12);
    BufferedImage originalImage = null, modifiedImage = null;
    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    JScrollPane originalPane = new JScrollPane(),
            modifiedPane = new JScrollPane();

    public PasteText() {
        super("Zašifrování textu do obrázku");
        createInterface();
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.validate();
    }

    //vytvoření rozhraní pro uživatele
    private void createInterface() {
        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.add(openBtn);
        this.getContentPane().add(jPanel, BorderLayout.WEST);
        openBtn.addActionListener(this);
        openBtn.setBackground(Color.WHITE);

        JPanel jPanel0 = new JPanel(new FlowLayout());
        jPanel0.add(decodeBtn);
        this.getContentPane().add(jPanel0, BorderLayout.SOUTH);
        decodeBtn.addActionListener(this);
        decodeBtn.setBackground(Color.BLACK);
        decodeBtn.setForeground(Color.WHITE);

        JPanel jPanel1 = new JPanel(new FlowLayout());
        jPanel1.add(saveBtn);
        saveBtn.setVisible(false);
        this.getContentPane().add(jPanel1, BorderLayout.EAST);
        saveBtn.addActionListener(this);
        saveBtn.setBackground(Color.WHITE);

        text.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    wordLoaded = true;
                }
                if (checkInput()) pasteText();
            }
        });

        jPanel = new JPanel(new GridLayout(1,1));
        jPanel.add(new JScrollPane(text));
        text.setFont(new Font("Arial",Font.BOLD,20));
        jPanel.setBorder(BorderFactory.createTitledBorder("Text pro zašifrování"));
        this.getContentPane().add(jPanel, BorderLayout.CENTER);
    }

    //kontrola jestli bylo něco zadáno do textové oblasti a zda byl nahrán obrázek
    public boolean checkInput(){
        return (wordLoaded && imgLoaded)?true:false;
    }

    //listener na tlačítka
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();
        if(o == openBtn)
            openImage();
        else if(o == saveBtn)
            saveImage();
        else if(o == decodeBtn) {
            new RetrieveText();
            this.dispose();
        }
    }

    //dialogové okno pro výběr obrázku
    private java.io.File showFileDialog(final boolean open) {
        JFileChooser jFileChooser = new JFileChooser("Otevřít obrázek");
        javax.swing.filechooser.FileFilter fileFilter = new javax.swing.filechooser.FileFilter() {
            public boolean accept(java.io.File file) {
                String name = file.getName().toLowerCase();
                if(open)
                    return file.isDirectory() || name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".tiff") ||
                            name.endsWith(".bmp") || name.endsWith(".dib");
                return file.isDirectory() || name.endsWith(".png") ||    name.endsWith(".bmp");
            }
            public String getDescription() {
                if(open)
                    return "Image ( *.png, *.gif, *.tiff, *.bmp, *.dib)";
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

    //načtení obrázku pro zašifrování
    private void openImage() {
        java.io.File file = showFileDialog(true);
        try {
            originalImage = ImageIO.read(file);
            JLabel jLabel = new JLabel(new ImageIcon(originalImage));
            originalPane.getViewport().add(jLabel);

            split.setLeftComponent(originalPane);
            split.setRightComponent(modifiedPane);
            originalPane.setBorder(BorderFactory.createTitledBorder("Původní obrázek"));
            modifiedPane.setBorder(BorderFactory.createTitledBorder("Obrázek se zašifrovaným textem"));
            this.getContentPane().add(split, BorderLayout.NORTH);
            this.validate();
            split.setPreferredSize( new Dimension(  50, 200) );

            split.setDividerLocation(0.5);

            saveBtn.setVisible(true);

            imgLoaded = true;
            if (text.getText() != "") wordLoaded = true;
            if (checkInput()) pasteText();
            this.setSize(500,500);
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    // příprava na zakodování
    private void pasteText() {
        String message = this.text.getText();
        modifiedImage = originalImage.getSubimage(0,0,
                originalImage.getWidth(), originalImage.getHeight());
        pasteText(modifiedImage, message);
        JLabel jLabel = new JLabel(new ImageIcon(modifiedImage));
        modifiedPane.getViewport().add(jLabel);
        this.validate();
    }

    //vložení textu do obrázku
    private void pasteText(BufferedImage image, String message) {
        int textLength = message.length();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int imageSize = imageWidth * imageHeight;

        // kontrola, zda jde text zašifrovat do obrázku
        if(textLength * 8 + 32 > imageSize) {
            JOptionPane.showMessageDialog(this, "Text je příliš dlouhý.",
                    "Nelze zašifrovat", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pasteInteger(image, textLength, 0, 0);

        //pole bytů reprezentující znaky zprávy
        byte b[] = message.getBytes();
        for(int i=0; i<b.length; i++)
            pasteByte(image, b[i], i*8+32, 0);
    }

    //zašifrování velikosti zprávy
    private void pasteInteger(BufferedImage img, int messageLneght, int start, int storageBit) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start/maxY;
        int startY = start - startX*maxY;
        int count=0; // kolik bitů je zabráno

        //projíždění jednotlivých pixelů obrázku
        for(int i=startX; i<maxX && count<32; i++) {
            for(int j=startY; j<maxY && count<32; j++) {
                //přepočítání rgb hodnoty a změna na barvy pixelu na nově vypočítanou hodnotu
                int rgb = img.getRGB(i, j);
                int bit = getBitValue(messageLneght, count);
                rgb = setBitValue(rgb, storageBit, bit);
                img.setRGB(i, j, rgb);
                count++;
            }
        }
    }

    // zašifrování znaku
    private void pasteByte(BufferedImage img, byte b, int start, int storageBit) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start/maxY;
        int startY = start - startX*maxY;
        int count=0;

        //projíždění jednotlivých pixelů obrázku
        for(int i=startX; i<maxX && count<8; i++) {
            for(int j=startY; j<maxY && count<8; j++) {
                //přepočítání rgb hodnoty a změna na barvy pixelu na nově vypočítanou hodnotu
                int rgb = img.getRGB(i, j);
                int bit = getBitValue(b, count);
                rgb = setBitValue(rgb, storageBit, bit);
                img.setRGB(i, j, rgb);
                count++;
            }
        }
    }

    // uložení obrázku se zašifrovaným textem
    private void saveImage() {
        if(modifiedImage == null) {
            JOptionPane.showMessageDialog(this, "Nebyla vložena žádná zpráva!",
                    "Nic k uložení", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.io.File f = showFileDialog(false);
        String name = f.getName();
        String ext = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(!ext.equals("png") && !ext.equals("bmp") &&   !ext.equals("dib")) {
            ext = "png";
            f = new java.io.File(f.getAbsolutePath()+".png");
        }
        try {
            if(f.exists()) f.delete();
            ImageIO.write(modifiedImage, ext.toUpperCase(), f);
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    //získání bitu
    private int getBitValue(int messageLenght, int location) {
        int v = messageLenght & (int) Math.round(Math.pow(2, location));
        return v==0?0:1;
    }
    //nastavení bitu
    private int setBitValue(int rgb, int location, int bit) {
        int toggle = (int) Math.pow(2, location), bitValue = getBitValue(rgb, location);
        if(bitValue == bit)
            return rgb;
        if(bitValue == 0 && bit == 1)
            rgb = rgb | toggle;
        else if(bitValue == 1 && bit == 0)
            rgb ^= toggle;
        return rgb;
    }

    public static void main(String arg[]) {
        new PasteText();
    }
}
