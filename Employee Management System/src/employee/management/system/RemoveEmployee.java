
package employee.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class RemoveEmployee extends JFrame implements ActionListener{
    
    Choice empId;
    JButton delete,back;
    
    RemoveEmployee() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50,50,100,30);
        add(labelempId);
        
        empId = new Choice();
        empId.setBounds(200,50,150,30);
        add(empId);
        
        try {
            Conn c = new Conn();
            String query = "select * from employee";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                empId.add(rs.getString("empId"));
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50,100,100,30);
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200,100,100,30);
        add(lblname);
        
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50,150,100,30);
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200,150,100,30);
        add(lblphone);
        
        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50,200,100,30);
        add(labelemail);
        
        JLabel lblemail = new JLabel();
        lblemail.setBounds(200,200,100,30);
        add(lblemail);
        
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId = '" + empId.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
               lblname.setText(rs.getString("name"));
               lblphone.setText(rs.getString("phone"));
               lblemail.setText(rs.getString("email"));
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        empId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie){
                try {
                      Conn c = new Conn();
                      String query = "select * from employee where empId = '" + empId.getSelectedItem() + "'";
                      ResultSet rs = c.s.executeQuery(query);
                      while(rs.next()){
                          lblname.setText(rs.getString("name"));
                          lblphone.setText(rs.getString("phone"));
                          lblemail.setText(rs.getString("email"));
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
                
            }
        });
        
        delete = new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);
        
        back = new JButton("Back");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        ImageIcon il = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = il.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,600,400);
        add(image);
        
        
        setSize(1000,400);
        setLocation(300,150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == delete){
            try {
                Conn c = new Conn();
                String query = "delete from employee where empId = '" + empId.getSelectedItem() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Information Deleted Sucessfully");
                setVisible(false);
                new Home();
            }catch (Exception e) {
                e.printStackTrace();
            }
            
        }else{
            setVisible(false);
            new Home();
        }
        
    }
    
    public static void main(String[] args){
        new RemoveEmployee();
    }
    
}
