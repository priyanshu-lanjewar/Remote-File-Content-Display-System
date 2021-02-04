import javax.swing.*;
import java.awt.*;

public class RemoteFileContentDisplaySystem
{
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ignored) {

            }
            ConfigSetting m = new ConfigSetting();
            m.setVisible(true);
        });

    }
}
