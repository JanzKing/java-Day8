package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  

 public class ClientQuery extends JFrame implements ActionListener{
	 	
	 	JLabel jlb1=new JLabel("请输入要查询的客户：",JLabel.CENTER);
	 	
	 	JTextField jf1=new JTextField(10);
	 	JTextField jf2=new JTextField(10);
	 	
	 	JButton jb1=new JButton("查看全部");
	 	JButton jb2=new JButton("查询");
	 	JButton jb3=new JButton("返回主界面");
	 	JButton jb4=new JButton("退房");
	 	
	 	JTable table;
	    Object body[][]=new Object[50][7];
	    String title[]={"房号","客户姓名","性别","身份证号码","电话号码","房间类型","入住时间"};;
	    Statement stat;
	    ResultSet rs;
	    JTabbedPane tp,ps;
	 public ClientQuery(){
		//jp1=new JPanel(null);
		super("8天酒店管理系统"); 
	    
        this.setSize(1000,700);
        this.setLocation(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel ps=new JPanel();
        ps.add(jb1);
        ps.add(jlb1);
        ps.add(jf1);
        ps.add(jb2);
        ps.add(jf2);
        ps.add(jb4);
        ps.add(jb3);
        table=new JTable(body,title);
        tp=new JTabbedPane();
        tp.add("查询客房结果",new JScrollPane(table));
        this.getContentPane().add(tp,"Center");
        this.getContentPane().add(ps,"South");
        this.setVisible(true);
        
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
	 }
	 public void select() {
			try{
				   Class.forName("com.mysql.jdbc.Driver");
		    }
		    catch(ClassNotFoundException a){
		   	       System.out.println(""+a.getMessage());
			}
	        try {
	        	Connection con;
	        	con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zhuangziqing","root","root");
	        	Statement sql=con.createStatement();
	            for(int x=1;x<body.length;x++){
	            body[x][0]=null;
	            body[x][1]=null;
	            body[x][2]=null;
	            body[x][3]=null;
	            body[x][4]=null;
	            body[x][5]=null;
	            body[x][6]=null;
	           // body[x][7]=null;
	            }
	          //  String history=null;
	            ResultSet rs=null;
	            //switch(LoginWindow.jtf.getText()){
	            /*case "ad":*/rs=sql.executeQuery("select * from clientinformation");           
	            int i=0;								//from history1
	            while(rs.next()){
	            body[i][0]=rs.getInt(1);
	            body[i][1]=rs.getString(2);
	            body[i][2]=rs.getString(3);
	            body[i][3]=rs.getString(4);
	            body[i][4]=rs.getString(5);
	            body[i][5]=rs.getString(6);
	            body[i][6]=rs.getString(7);
	           // body[i][7]=rs.getString(8);
	            i=i+1;
	            }
	            this.repaint();
	        } catch (SQLException ex) {
	        }

	    }
	 public void selectClient() {
			try{
				   Class.forName("com.mysql.jdbc.Driver");
		    }
		    catch(ClassNotFoundException a){
		   	       System.out.println(""+a.getMessage());
			}
	        try {
	        	Connection con;
	        	con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zhuangziqing","root","root");
	        	Statement sql=con.createStatement();
	            for(int x=1;x<body.length;x++){
	            body[x][0]=null;
	            body[x][1]=null;
	            body[x][2]=null;
	            body[x][3]=null;
	            body[x][4]=null;
	            body[x][5]=null;
	            body[x][6]=null;
	            }
	            ResultSet rs=null;
	            String name=jf1.getText().trim();
	            rs=sql.executeQuery("select RoomNo,ClientName,ClientSex,identityCard,"
	            		+ "PhoneNumber,Sort,BookTime from clientinformation where ClientName='"+name+"'");           
	            int i=0;
	            while(rs.next()){
	            body[i][0]=rs.getInt(1);
	            body[i][1]=rs.getString(2);
	            body[i][2]=rs.getString(3);
	            body[i][3]=rs.getString(4);
	            body[i][4]=rs.getString(5);
	            body[i][5]=rs.getString(6);
	            body[i][6]=rs.getString(7);
	            i=i+1;
	            }
	            this.repaint();
	        } catch (SQLException ex) {
	        	JOptionPane.showMessageDialog(null, "没有找到指定客户信息，请重新输入客户姓名！");
	        }
	        
	        }


     public void actionPerformed(ActionEvent e){
     	if(e.getSource()==jb3){
     		dispose();
     		//StaffQuery a=new StaffQuery();
     		}
     	if(e.getSource()==jb1){
     		select();
     		}
     	if(e.getSource()==jb2){
     		selectClient();
     		}
     	if(e.getSource()==jb4){
     		Function b=new Function();
     		b.DeleteClient(jf2.getText().trim());
     		//selectNumber();
     		}
     	}
}