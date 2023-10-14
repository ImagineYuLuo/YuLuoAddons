import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class YuLuoAddonsInstallerFrame extends JFrame implements ActionListener, MouseListener {

    private JLabel logo = null;
    private JLabel versionInfo = null;

    private JPanel panelCenter = null;
    private JPanel panelBottom = null;
    private JPanel totalContentPane = null;

    private JTextArea descriptionText = null;
    private JTextArea forgeDescriptionText = null;

    private JButton buttonInstall = null;
    private JButton buttonClose = null;

    private static final int TOTAL_HEIGHT = 435;
    private static final int TOTAL_WIDTH = 404;

    private int x = 0;
    private int y = 0;

    private int w = TOTAL_WIDTH;
    private int h;
    private int margin;

    public YuLuoAddonsInstallerFrame(){
        setName("YuLuoAddonsInstallerFrame");
        setTitle("YuLuoAddons Installer");
        setResizable(false);
        setSize(TOTAL_WIDTH, TOTAL_HEIGHT);
        setContentPane(getPanelContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getForgeTextArea().addMouseListener(this);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            YuLuoAddonsInstallerFrame frame = new YuLuoAddonsInstallerFrame();
            frame.centerFrame(frame);
            frame.setVisible(true);
        } catch (Exception ex) {
            showErrorPopup(ex);
        }
    }

    private JPanel getPanelContentPane(){
        if(totalContentPane == null){
            try{
                totalContentPane = new JPanel();
                totalContentPane.setName("PanelContentPane");
                totalContentPane.setLayout(new BorderLayout(5, 5));
                totalContentPane.setPreferredSize(new Dimension(TOTAL_WIDTH, TOTAL_HEIGHT));
                totalContentPane.add(getPanelCenter(), "Center");
                totalContentPane.add(getPanelBottom(), "South");
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return totalContentPane;
    }

    //Center
    private JPanel getPanelCenter(){
        if(panelCenter == null){
            try{
                panelCenter = new JPanel();
                panelCenter.setName("PanelCenter");
                panelCenter.add(getPictureLabel(), getPictureLabel().getName());
                panelCenter.add(getVersionInfo(), getVersionInfo().getName());
                panelCenter.add(getTextArea(), getTextArea().getName());
                panelCenter.add(getForgeTextArea(), getForgeTextArea().getName());
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return panelCenter;
    }

    private JLabel getPictureLabel(){
        if(logo == null){
            try{
                h = w/2;
                margin = 5;

                BufferedImage myPicture = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("images/logo.png"), "Logo not found."));
                Image scaled = myPicture.getScaledInstance(w-margin*4, h-margin*8, Image.SCALE_SMOOTH);
                logo = new JLabel(new ImageIcon(scaled));
                logo.setName("Logo");
                logo.setBounds(x-margin, y-margin, w-margin*4, h-margin*8);
                logo.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
                logo.setHorizontalAlignment(SwingConstants.CENTER);
                logo.setPreferredSize(new Dimension(w, h-margin*8));

                y+=h;
            }catch (Throwable ex){
                showErrorPopup(ex);
            }
        }
        return logo;
    }

    private JLabel getVersionInfo(){
        if(versionInfo == null){
            try{
                h = 20;
                versionInfo = new JLabel();
                versionInfo.setName("LabelMcVersion");
                versionInfo.setBounds(x, y, w, h);
                versionInfo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
                versionInfo.setHorizontalAlignment(SwingConstants.CENTER);
                versionInfo.setPreferredSize(new Dimension(w, h));
                versionInfo.setText("Version : 1.0 by YuLuo - for Minecraft 1.8.9");

                y += h;
            }catch (Throwable ex){
                showErrorPopup(ex);
            }
        }
        return versionInfo;
    }

    private JTextArea getTextArea(){
        if(descriptionText == null){
            try{
                h = 60;
                margin = 10;

                descriptionText = new JTextArea();
                descriptionText.setName("TextArea");
                setTextAreaProperties(descriptionText);
                descriptionText.setText("This installer will copy YuLuoAddons into your forge folder. " +
                        "Close this if you want to do this yourself.");
                descriptionText.setWrapStyleWord(true);
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return descriptionText;
    }

    private void setTextAreaProperties(JTextArea textArea){
        textArea.setBounds(x+margin, y+margin, w-margin*2, h-margin);
        textArea.setEditable(false);
        textArea.setHighlighter(null);
        textArea.setEnabled(true);
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setPreferredSize(new Dimension(w-margin*3, h-margin-10));
    }

    private JTextArea getForgeTextArea(){
        if(forgeDescriptionText == null){
            try{
                h = 40;
                margin = 10;

                forgeDescriptionText = new JTextArea();
                forgeDescriptionText.setName("ForgeTextArea");
                setTextAreaProperties(forgeDescriptionText);
                forgeDescriptionText.setText("If you don't have forge, click here to the download page.");
                forgeDescriptionText.setForeground(Color.BLUE.darker());
                forgeDescriptionText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                forgeDescriptionText.setWrapStyleWord(true);

                y += h;
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return forgeDescriptionText;
    }

    //Bottom
    private JPanel getPanelBottom(){
        if(panelBottom == null){
            try{
                panelBottom = new JPanel();
                panelBottom.setName("PanelBottom");
                panelBottom.add(getButtonInstall(), getButtonInstall().getName());
                panelBottom.add(getButtonClose(), getButtonClose().getName());
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return panelBottom;
    }

    private JButton getButtonInstall(){
        if(buttonInstall == null){
            try{
                buttonInstall = new JButton();
                buttonInstall.setName("InstallButton");
                buttonInstall.setText("Click here to copy the jar to your mod folder.");
                buttonInstall.setFocusable(false);
                buttonInstall.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        copyJarToFolder();
                    }
                });
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return buttonInstall;
    }

    private JButton getButtonClose(){
        if(buttonClose == null){
            try{
                buttonClose = new JButton();
                buttonClose.setName("CloseButton");
                buttonClose.setText("Close");
                buttonClose.setFocusable(false);
                getButtonClose().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
            }catch(Throwable ex){
                showErrorPopup(ex);
            }
        }
        return buttonClose;
    }

    private void copyJarToFolder(){
        try{
            String jarPath = YuLuoAddonsInstallerFrame.class.getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath();
            File jarFile = new File(jarPath);

            if(jarFile.isFile()){
                String userName = System.getProperty("user.name");
                File destinationFolder = new File("C:" + File.separator + "Users" + File.separator + userName +
                        File.separator + "AppData" + File.separator + "Roaming" + File.separator + ".minecraft" +
                        File.separator + "mods");
                if(!destinationFolder.exists()){
                    destinationFolder.mkdirs();
                    showMessage("Helping you to create the folder!");
                }
                showMessage("Success to copy this file to your mod folder.");
                File destinationFile = new File(destinationFolder, jarFile.getName());
                Files.copy(jarFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }else{
                showErrorMessage("Something got error, check your file folder would be like : " +
                        "C:\\Users\\your_username\\AppData\\Roaming\\.minecraft\\mods");
            }
        }catch (Throwable ex){
            showErrorPopup(ex);
        }
    }


    public void centerFrame(JFrame frame){
        frame.setLocationRelativeTo(null);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message, "YuLuoAddons", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "YuLuoAddons", JOptionPane.ERROR_MESSAGE);
    }


    private static String getStackTraceText(Throwable ex){
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replace("\t", " ");
    }

    private static void showErrorPopup(Throwable ex){
        JTextArea textArea = new JTextArea(getStackTraceText(ex));
        textArea.setEditable(false);
        Font currentFont = textArea.getFont();
        Font newFont = new Font(Font.MONOSPACED, currentFont.getStyle(), currentFont.getStyle());
        textArea.setFont(newFont);

        JScrollPane errorScrollPane = new JScrollPane(textArea);
        errorScrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(null, errorScrollPane, "Error", JOptionPane.ERROR_MESSAGE);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == getForgeTextArea()){
            try{
                Desktop.getDesktop().browse(new URI("https://files.minecraftforge.net/net/minecraftforge/forge/index_1.8.9.html"));
            }catch(URISyntaxException ex){
                showErrorPopup(ex);
            }catch (IOException ex){
                showErrorPopup(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}