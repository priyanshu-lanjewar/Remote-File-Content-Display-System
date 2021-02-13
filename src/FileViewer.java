/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 * Class Description :
   Graphical User Interface to View Content of Requested File.
 * */

import javax.swing.*;
import java.awt.*;

public class FileViewer extends JFrame{
    private JTabbedPane viewer;
    private JTextField fileName;
    private JPanel panel;
    private JButton closeFile;
    JButton b;
    ImageIcon icon = new ImageIcon(this.getClass().getResource("fw.png"));


    public FileViewer() {

        add(panel);
        setSize(900, 700);
        setTitle("RFCDS - File Viewer");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        viewer.removeTabAt(0);
        fileName.setDisabledTextColor(Color.black);
        setIconImage(icon.getImage());


        closeFile.addActionListener(e -> viewer.removeTabAt(viewer.getSelectedIndex()));
    }

    public void  openFile(String title,String data){
        JComponent panel = new JTextArea(data);
        panel.setOpaque(false);
        JScrollPane pn = new JScrollPane(panel);
        viewer.add(title,pn);
        b=new JButton("X");
        b.setBackground(Color.RED);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel titleLbl = new JLabel(title);
        titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        p.add(titleLbl);
        p.add(b);
        viewer.setTabComponentAt(this.viewer.indexOfTab(title),p);
        b.addActionListener(e -> viewer.removeTabAt(this.viewer.indexOfTab(title)));
        fileName.setText(title+".txt");
    }
}

