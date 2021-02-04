import javax.swing.*;

public class ConfigSetting extends JFrame {

    private JPanel configSettingsPanel;
    private JButton continueWithDefaultsButton;
    private JButton updateConfigurationSettingsButton;

    public ConfigSetting(){
        add(configSettingsPanel);
        setSize(700,150);
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
