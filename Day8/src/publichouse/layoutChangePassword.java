package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  
import java.util.*; 

 public class layoutChangePassword extends JFrame implements ActionListener{

 	JLabel jlb1=new JLabel("原密码",JLabel.CENTER);
 	JLabel jlb2=new JLabel("新密码",JLabel.CENTER);
 	JLabel jlb3=new JLabel("确认密码",JLabel.CENTER);
 	
    JPasswordField jpf1=new JPasswordField(10); //密码
    JPasswordField jpf2=new JPasswordField(10); //密码
    JPasswordField jpf3=new JPasswordField(10); //密码
 	
 	JButton jb1=new JButton("修改");
 	JButton jb2=new JButton("重置");

 	Box box0=Box.createHorizontalBox();
 	Box box1=Box.createHorizontalBox();
 	Box box2=Box.createHorizontalBox();
 	//Box box3=Box.createHorizontalBox();
 	Box box4=Box.createHorizontalBox();
 	Box boxTotal=Box.createVerticalBox();

	 public layoutChangePassword(Frame frame){

	 }
	 
	 public Box getBox(){
		 box0.add(Box.createHorizontalStrut(400));
		 box0.add(jlb1);
		 box0.add(jpf1);
		 box0.add(Box.createHorizontalStrut(400));
		 
		 box1.add(Box.createHorizontalStrut(400));
		 box1.add(jlb2);
		 box1.add(jpf2);
		 box1.add(Box.createHorizontalStrut(400));
		 
		 box2.add(Box.createHorizontalStrut(400));
		 box2.add(jlb3);
		 box2.add(jpf3);
		 box2.add(Box.createHorizontalStrut(400));
		 
		 box4.add(Box.createHorizontalStrut(400));
		 box4.add(jb1);
		 box4.add(jb2);
		 box4.add(Box.createHorizontalStrut(400));
		 
		 boxTotal.add(Box.createVerticalStrut(200));
		 boxTotal.add(box0);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box1);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box2);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box4);
		 boxTotal.add(Box.createVerticalStrut(200));
		 
		 jb1.addActionListener(this);
		 jb2.addActionListener(this);
		 
		 return boxTotal;
	 }
	 public void actionPerformed(ActionEvent e){		 
     	if(e.getSource()==jb1){
  
     		System.out.println("zzzzzzz");
     		// 是管理员
     		Function a=new Function();
     		if(MainWindow.getWindow().isAdmin){
         		a.changePassword2(jpf1.getText().trim(), jpf2.getText().trim(), jpf3.getText().trim());
     		} else {
         		a.changePassword(jpf1.getText().trim(), jpf2.getText().trim(), jpf3.getText().trim());
     		}

		}
	 }

 }