package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  
import java.util.Vector;

 public class layoutUpdateRoom extends JFrame implements ActionListener{
	 
	 	private int functionFlag;
	 	private int updateRoomID;

	 	JLabel jlb1=new JLabel("�����",JLabel.CENTER);
	 	JLabel jlb2=new JLabel("����λ��",JLabel.CENTER);
	 	JLabel jlb3=new JLabel("�۸�",JLabel.CENTER);
	 	JLabel jlb4=new JLabel("��������",JLabel.CENTER);
	 	
	 	JTextField jf1=new JTextField(10);
	 	JTextField jf2=new JTextField(5);
	 	JTextField jf3=new JTextField(5);
	 	Choice cho=new Choice();
	 	
	 	JButton jb1=new JButton("�޸�");
	 	//JButton jb2=new JButton("����");
	 	/*JPanel jp1=new JPanel(null);
	 	JPanel jp2=new JPanel(null);
	 	JPanel jp3=new JPanel(null);*/
	 	
	 	Box box0=Box.createHorizontalBox();
	 	Box box1=Box.createHorizontalBox();
	 	Box box2=Box.createHorizontalBox();
	 	Box box3=Box.createHorizontalBox();
	 	Box box4=Box.createHorizontalBox();
	 	Box boxTotal=Box.createVerticalBox();
	 	
	 	//Box boxa=Box.createHorizontalStrut(10);
	 	//Box boxb=Box.createHorizontalStrut(10);
	    /*public static void main(String[] args) {
	    	AddRoom a=new AddRoom();
	 }*/
	 	
	 public layoutUpdateRoom(){
	
	 }
	 
	 public Box getBox(){
		 cho.add("�ػݷ�");
		 cho.add("���˴�");
		 cho.add("�󴲷�");
		 cho.add("˫�˼�");
		 cho.add("��Ʒ��");
		 
		 box0.add(Box.createHorizontalStrut(400));
		 box0.add(jlb1);
		 box0.add(jf1);
		 //box0.add(jb2);
		 box0.add(Box.createHorizontalStrut(400));
		 
		 box1.add(Box.createHorizontalStrut(400));
		 box1.add(jlb2);
		 box1.add(jf2);
		 box1.add(Box.createHorizontalStrut(400));
		 
		 box2.add(Box.createHorizontalStrut(400));
		 box2.add(jlb4);
		 box2.add(cho);
		 box2.add(Box.createHorizontalStrut(400));
		 
		 box3.add(Box.createHorizontalStrut(400));
		 box3.add(jlb3);
		 box3.add(jf3);
		 box3.add(Box.createHorizontalStrut(400));
		 
		 box4.add(Box.createHorizontalStrut(400));
		 box4.add(jb1);
		 box4.add(Box.createHorizontalStrut(400));
		 
		 boxTotal.add(Box.createVerticalStrut(200));
		 boxTotal.add(box0);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box1);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box2);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box3);
		 boxTotal.add(Box.createVerticalStrut(50));
		 boxTotal.add(box4);
		 boxTotal.add(Box.createVerticalStrut(200));
		 
		 jb1.addActionListener(this);
		 
		 return boxTotal;
	 }
	 
     public void actionPerformed(ActionEvent e){

     	if(e.getSource()==jb1){
     		Function hh=new Function();
     		hh.updateRoom(jf1.getText().trim(),jf2.getText().trim(),cho.getSelectedItem(),jf3.getText().trim());
     		JOptionPane.showMessageDialog(null, "�޸ĳɹ���");

     	}
     }
	 
}