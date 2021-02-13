
/*
 * Author Of Code : Priyanshu Lanjewar
 * Registration Number : 1941012603
 * Branch / Sec / Sem : CSE - F - 3rd Sem
 * Institute : Institute of Technical Education and Research
 * University : Siksha O Anusandhana Deemed To Be University.
 * Class Description :
   Execution of GUI part of programme(Server Side) begins here. User will get an option either to continue with default server settings like ip, port and location or to change.
 */

import javax.swing.*;

public class ConfigSetting extends JFrame {

    private JPanel configSettingsPanel;
    private JButton continueWithDefaultsButton;
    private JButton updateConfigurationSettingsButton;

    public ConfigSetting() {
        add(configSettingsPanel);
        setSize(700, 150);
        ImageIcon icn = new ImageIcon(this.getClass().getResource("confSet.png"));
        setIconImage(icn.getImage());
        setTitle("Remote File Content Display System");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        continueWithDefaultsButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            Server f = new Server();
            ConfigSetting.this.setVisible(false);
            f.setVisible(true);
        }));
        updateConfigurationSettingsButton.addActionListener(e -> {
            ServerConfiguration sc = new ServerConfiguration();
            ConfigSetting.this.setVisible(false);
            sc.setVisible(true);
        });
    }

}
