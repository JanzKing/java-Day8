package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  
import java.util.*; 

 public class layoutAddRoomInfo extends JFrame implements ActionListener{


	private int functionFlag;

 	JLabel jlb1=new JLabel("房间号",JLabel.CENTER);
 	JLabel jlb2=new JLabel("房间位置",JLabel.CENTER);
 	JLabel jlb3=new JLabel("价格",JLabel.CENTER);
 	JLabel jlb4=new JLabel("房间类型",JLabel.CENTER);
 	
 	JTextField jf1=new JTextField(10);
 	static JTextField jf2=new JTextField(5);
 	static JTextField jf3=new JTextField(5);
 	static Choice cho=new Choice();
 	
 	JButton jb1=new JButton("录入");
 	JButton jb2=new JButton("重置");

 	Box box0=Box.createHorizontalBox();
 	Box box1=Box.createHorizontalBox();
 	Box box2=Box.createHorizontalBox();
 	Box box3=Box.createHorizontalBox();
 	Box box4=Box.createHorizontalBox();
 	Box boxTotal=Box.createVerticalBox();

	 public layoutAddRoomInfo(){

	 }
	 
	 public Box getBox(){
		 cho.add("特惠房");
		 cho.add("单人床");
		 cho.add("大床房");
		 cho.add("双人间");
		 cho.add("精品房");
		 
		 box0.add(Box.createHorizontalStrut(400));
		 box0.add(jlb1);
		 box0.add(jf1);
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
		 box4.add(jb2);
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
		 jb2.addActionListener(this);
		 
		 return boxTotal;
	 }
	 
	 public void setUpdateRoomInfo(String updateRoomInfo){
		 if(updateRoomInfo==null)
			 return;
		 String str[]=new String[3];
		 int index=-1;
		 for(int i=0;i<10;i++){
		 index=updateRoomInfo.indexOf(',');
		 str[i]=updateRoomInfo.substring(0,index);
		 updateRoomInfo=updateRoomInfo.substring(index+1);
	 }
		str[3]=updateRoomInfo;
		//updateRoomID=Integer.parseInt(str[0]);
		jf1.setText(str[1]);
		jf2.setText(str[2]);
		jf3.setText(str[3]);
	 }
		
	 public void actionPerformed(ActionEvent e){

	     	if(e.getSource()==jb1){
	     		String RoomId = jf1.getText().trim();
	     		String Raddress = jf2.getText().trim();
	     		String Rsort = cho.getSelectedItem();
	     		String Rprice = jf3.getText().trim();
	     		Vector<String>RoomInfo=new Vector<String>();
	     		RoomInfo.add(RoomId);
	     		RoomInfo.add(Raddress);
	     		RoomInfo.add(Rsort);
	     		RoomInfo.add(Rprice);
				/*if(number<8000||number>9000){
					JOptionPane.showMessageDialog(null, "输入房号有误，请重新输入！");
					if(RoomId==null||Raddress==null||Rprice==null){
						JOptionPane.showMessageDialog(null, "输入栏内不能为空！");*/
						if(functionFlag == MainWindow.INSERT&&Service.addrooms(RoomInfo)==0){
							JOptionPane.showMessageDialog(null, "录入成功！");
						}
	
						}
	         }

 }
     	
	 
 
	 
