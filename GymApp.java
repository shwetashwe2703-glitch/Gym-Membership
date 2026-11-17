
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class GymApp extends JFrame {

    JTextField id, name, age, phone;
    JComboBox<String> plan;
    Connection con;

    GymApp() {
        setTitle("Gym Membership Management System");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Database connection
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gymdb",
                "root", "Shweta@0411");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        // Menu bar
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem home = new JMenuItem("Home");
        JMenuItem members = new JMenuItem("Members");
        JMenuItem exit = new JMenuItem("Exit");

        menu.add(home);
        menu.add(members);
        menu.add(exit);
        mb.add(menu);
        setJMenuBar(mb);

        home.addActionListener(e -> clearFields());
        members.addActionListener(e -> showMembers());
        exit.addActionListener(e -> System.exit(0));

        // Form
        JPanel p = new JPanel(new GridLayout(7, 2, 10, 10));

        p.add(new JLabel("Member ID"));
        id = new JTextField();
        p.add(id);

        p.add(new JLabel("Member Name"));
        name = new JTextField();
        p.add(name);

        p.add(new JLabel("Age"));
        age = new JTextField();
        p.add(age);

        p.add(new JLabel("Phone Number"));
        phone = new JTextField();
        p.add(phone);

        p.add(new JLabel("Membership Plan"));
        plan = new JComboBox<>(
            new String[]{"Monthly", "Quarterly", "Yearly"});
        p.add(plan);

        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton clear = new JButton("Clear");

        p.add(add);
        p.add(update);
        p.add(delete);
        p.add(clear);

        add(new JScrollPane(p));

        add.addActionListener(e -> save("add"));
        update.addActionListener(e -> save("update"));
        delete.addActionListener(e -> save("delete"));
        clear.addActionListener(e -> clearFields());

        setVisible(true);
    }

    // Add, Update and Delete
    void save(String action) {
        try {
            if (con == null || con.isClosed()) {
                JOptionPane.showMessageDialog(this,
                    "Database not connected!");
                return;
            }

            String sql;

            if (action.equals("add"))
                sql = "INSERT INTO members VALUES (?, ?, ?, ?, ?)";
            else if (action.equals("update"))
                sql = "UPDATE members SET member_name=?, age=?, phone=?, membership_plan=? WHERE member_id=?";
            else
                sql = "DELETE FROM members WHERE member_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            if (action.equals("add")) {
                ps.setInt(1, Integer.parseInt(id.getText()));
                ps.setString(2, name.getText());
                ps.setInt(3, Integer.parseInt(age.getText()));
                ps.setString(4, phone.getText());
                ps.setString(5, plan.getSelectedItem().toString());
            } else if (action.equals("update")) {
                ps.setString(1, name.getText());
                ps.setInt(2, Integer.parseInt(age.getText()));
                ps.setString(3, phone.getText());
                ps.setString(4, plan.getSelectedItem().toString());
                ps.setInt(5, Integer.parseInt(id.getText()));
            } else {
                ps.setInt(1, Integer.parseInt(id.getText()));
            }

            int result = ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                result > 0 ? "Operation Successful!" :
                "Member Not Found!");

            ps.close();

            if (action.equals("add") || action.equals("delete"))
                clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Display all members
    void showMembers() {
        try {
            if (con == null || con.isClosed()) {
                JOptionPane.showMessageDialog(this,
                    "Database not connected!");
                return;
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM members");

            DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Age", "Phone", "Plan"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("member_id"),
                    rs.getString("member_name"),
                    rs.getInt("age"),
                    rs.getString("phone"),
                    rs.getString("membership_plan")
                });
            }

            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(this,
                new JScrollPane(table), "Gym Members",
                JOptionPane.INFORMATION_MESSAGE);

            rs.close();
            st.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Clear fields
    void clearFields() {
        id.setText("");
        name.setText("");
        age.setText("");
        phone.setText("");
        plan.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new GymApp();
    }
}
