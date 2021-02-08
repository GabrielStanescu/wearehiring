package tema;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame implements ListSelectionListener, ActionListener {
    private JPanel panel1;
    private JScrollPane companiesPane;
    private JScrollPane userPane;
    private JScrollPane depPane;
    private JScrollPane empPane;
    private JLabel companyLabel;
    private JLabel userLabel;
    private JLabel depLabel;
    private JLabel empLabel;
    private JScrollPane jobsPane;
    private JLabel jobsLabel;
    private JTextField text;
    private JButton button;
    private JList companies;
    private JList users;
    private JList departments;
    private JList employees;
    private JList jobs;

    public AdminPage() {
        super("Admin");
        setContentPane(panel1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 400));

        panel1 = new JPanel();

        Application app = Application.getInstance();
        DefaultListModel<String> compList = new DefaultListModel<>();
        for (Company comp : app.getCompanies())
            compList.addElement(comp.name);

        companies = new JList(compList);
        companies.addListSelectionListener(this);
        companiesPane.setViewportView(companies);

        DefaultListModel<String> userList = new DefaultListModel<>();
        for (User user : app.users)
            userList.addElement(user.r.getInfo().getName());
        users = new JList(userList);
        userPane.setViewportView(users);

        button.addActionListener(this);

        pack();
    }

    // afisarea departamentelor in panel
    public void showDep() {
        Application app = Application.getInstance();
        DefaultListModel<String> depList = new DefaultListModel<>();
        for (Department dep : app.getCompany(companies.getSelectedValue().toString()).departments)
            depList.addElement(dep.getClass().getSimpleName());
        departments = new JList(depList);
        // daca se selecteaza un departament, atunci se afiseaza lista cu employees si joburi
        departments.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (departments.isSelectionEmpty())
                    return;
                else {
                    showEmp();
                    showJob();
                }
            }
        });
        depPane.setViewportView(departments);
        pack();
    }

    // afisarea employees in panel
    public void showEmp() {
        Application app = Application.getInstance();
        DefaultListModel<String> empList = new DefaultListModel<>();
        for (Employee emp : app.getCompany(companies.getSelectedValue().toString()).departments.get(departments.getSelectedIndex()).getEmployees())
            empList.addElement(emp.r.getInfo().getName());
        employees = new JList(empList);
        empPane.setViewportView(employees);
    }

    // afisarea joburilor in panel
    public void showJob() {
        Application app = Application.getInstance();
        DefaultListModel<String> jobList = new DefaultListModel<>();
        for (Job job : app.getCompany(companies.getSelectedValue().toString()).departments.get(departments.getSelectedIndex()).getJobs())
            jobList.addElement(job.jobName);
        jobs = new JList(jobList);
        jobsPane.setViewportView(jobs);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (!companies.isSelectionEmpty())
            showDep();
        return;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (departments.isSelectionEmpty())
            return;
        else {
            // daca se apasa butonul, se calculeaza bugetul total pentru departament
            Application app = Application.getInstance();
            Company comp = app.getCompany(companies.getSelectedValue().toString());
            for (Department d : comp.departments) {
                if (d.getClass().getSimpleName().equals(departments.getSelectedValue().toString()))
                    text.setText(String.valueOf(d.getTotalSalaryBudget()));
            }
        }
    }
}
