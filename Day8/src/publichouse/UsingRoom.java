package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  

 public class UsingRoom extends JFrame implements ActionListener{
	 	
	 	//JLabel jlb1=new JLabel("������Ҫ��ѯ�Ŀͻ���",JLabel.CENTER);
	 	
	 	//JTextField jf1=new JTextField(10);
	 	
	 	JButton jb1=new JButton("�鿴ȫ��");
	 	JButton jb2=new JButton("��ѯ");
	 	Choice cho=new Choice();
	 	JButton jb3=new JButton("����������");
	 	//JButton jb4=new JButton("�˷�");
	 	
	 	JTable table;
	    Object body[][]=new Object[50][3];
	    String title[]={"����","��������","����״̬"};;
	    Statement stat;
	    ResultSet rs;
	    JTabbedPane tp,ps;
	 public UsingRoom(){
		//jp1=new JPanel(null);
		super("8��Ƶ����ϵͳ"); 
	    
        this.setSize(1000,700);
        this.setLocation(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cho.add("�ػݷ�");
		cho.add("���˴�");
		cho.add("�󴲷�");
		cho.add("˫�˼�");
		cho.add("��Ʒ��");
		 
        JPanel ps=new JPanel();
        ps.add(cho);
        ps.add(jb2);
        ps.add(jb1);
        ps.add(jb3);
        table=new JTable(body,title);
        tp=new JTabbedPane();
        tp.add("��ѯ�ͷ�״̬���",new JScrollPane(table));
        this.getContentPane().add(tp,"Center");
        this.getContentPane().add(ps,"South");
        this.setVisible(true);
        

		jb2.addActionListener(this);
		jb1.addActionListener(this);
		jb3.addActionListener(this);
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
	           // body[x][7]=null;
	            }
	          //  String history=null;
	            ResultSet rs=null;
	            //switch(LoginWindow.jtf.getText()){
	            /*case "ad":*/rs=sql.executeQuery("select * from usecondition");           
	            int i=0;								//from history1
	            while(rs.next()){
	            body[i][0]=rs.getInt(1);
	            body[i][1]=rs.getString(2);
	            body[i][2]=rs.getString(3);
	           // body[i][7]=rs.getString(8);
	            i=i+1;
	            }
	            this.repaint();
	        } catch (SQLException ex) {
	        }

	    }
	 public void selectSort() {
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
	            }
	            ResultSet rs=null;
	            String roomsort=cho.getSelectedItem();
	            rs=sql.executeQuery("select RoomID,Sort,Rcondition from UseCondition where Sort='"+roomsort+"'");           
	            int i=0;
	            while(rs.next()){
	            body[i][0]=rs.getInt(1);
	            body[i][1]=rs.getString(2);
	            body[i][2]=rs.getString(3);
	            i=i+1;
	            }
	            this.repaint();
	        } catch (SQLException ex) {
	        	JOptionPane.showMessageDialog(null, "û���ҵ�ָ�����ͷ�����Ϣ��������ѡ�񷿼����ͣ�");
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
     		selectSort();
     		}
     	}
}
