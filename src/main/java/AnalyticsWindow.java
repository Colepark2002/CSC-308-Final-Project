import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.sql.*;

/**
 * Analytics Window GUI
 *
 * @author Bret Craig
 * @version 1.0
 */
public class AnalyticsWindow extends JFrame {

    public AnalyticsWindow() throws SQLException {
        super("Analytics");
        this.add(createChart());
        setSize(560, 367);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private ChartPanel createChart() throws SQLException {
        String db_url = "jdbc:mysql://us-east.connect.psdb.cloud/tutor_database?sslMode=VERIFY_IDENTITY";
        String db_user = "iclc7hopn03jo4dnefaq";
        String db_password = "pscale_pw_hV1NQ465waoiY2mUeitXKOhSr6XrytNU13LIGiTBiuc";

        try (Connection conn = DriverManager.getConnection(db_url, db_user, db_password)) {

            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery("Select * from users");

            int[] values = new int[4];
            double total = 0.0;
            while (result.next()) {
                String prof = result.getString("Proficiency");
                if (prof.equals("0")) {
                    values[0]++;
                }
                if (prof.equals("1")) {
                    values[1]++;
                }
                if (prof.equals("2")) {
                    values[2]++;
                }
                if (prof.equals("3")) {
                    values[3]++;
                }
                total++;
            }

            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("0", (Math.abs(values[0]/total)*100));
            dataset.setValue("1", (Math.abs(values[1]/total)*100));
            dataset.setValue("2", (Math.abs(values[2]/total)*100));
            dataset.setValue("3", (Math.abs(values[3]/total)*100));

            JFreeChart chart = ChartFactory.createPieChart("Class Proficiency", dataset, true, true, false);
            return new ChartPanel(chart);
        }
    }


}
