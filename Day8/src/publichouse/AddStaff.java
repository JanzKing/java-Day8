package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  

 public class AddStaff extends JFrame implements ActionListener{
	 	JMenuBar menubar=new JMenuBar();
	    JMenu jm0=new JMenu("客房信息管理") ;     //创建JMenu菜单对象
	    JMenu jm1=new JMenu("客房经营管理") ;     //创建JMenu菜单对象
	    JMenu jm2=new JMenu("客户信息查询") ;     //创建JMenu菜单对象
	    JMenu jm3=new JMenu("员工信息管理") ;     //创建JMenu菜单对象
	    
	    JMenuItem t1=new JMenuItem("录入客房信息") ;  //菜单项
	    //JMenuItem t2=new JMenuItem("删除客房信息") ;//菜单项
	    JMenuItem t9=new JMenuItem("修改客房信息") ;//菜单项
	    JMenuItem t10=new JMenuItem("查询客房信息") ;//菜单项
	    
	    JMenuItem t3=new JMenuItem("客房使用情况") ;//菜单项
	    JMenuItem t4=new JMenuItem("客房预订") ;
	    JMenuItem t2=new JMenuItem("客人退房") ;//菜
	    
	    JMenuItem t5=new JMenuItem("添加客户信息") ;//菜单项
	    JMenuItem t6=new JMenuItem("查询客户信息") ;//菜单项
	    
	    JMenuItem t7=new JMenuItem("查询员工信息") ;//菜单项
	    JMenuItem t8=new JMenuItem("添加员工信息") ;//菜单项
	  //  JMenuItem t9=new JMenuItem("删除员工信息") ;//菜单项
	 
	 	JLabel jlb1=new JLabel("员工编号:",JLabel.CENTER);
	 	JLabel jlb2=new JLabel("员工姓名:",JLabel.CENTER);
	 	JLabel jlb8=new JLabel("性别:",JLabel.CENTER);
	 	JLabel jlb3=new JLabel("邮箱:",JLabel.CENTER);
	 	JLabel jlb4=new JLabel("通信地址:",JLabel.CENTER);
	 	JLabel jlb5=new JLabel("联系电话:",JLabel.CENTER);
	 	JLabel jlb6=new JLabel("所在部门:",JLabel.CENTER);
	 	JLabel jlb7=new JLabel("员工类型:",JLabel.CENTER);
	 	
	 	JTextField jf1=new JTextField(10);
	 	JTextField jf2=new JTextField(10);
	 	JTextField jf3=new JTextField(10);
	 	JTextField jf4=new JTextField(10);
	 	JTextField jf5=new JTextField(10);
	 	
	 	Choice cho=new Choice();
	 	Choice cho1=new Choice();
	 	
	 	JRadioButton jrb1 = new JRadioButton("男");
	 	JRadioButton jrb2 = new JRadioButton("女");
	 	ButtonGroup group = new ButtonGroup();  
	 	
	 	JButton jb1=new JButton("录入");
	 	/*JPanel jp1=new JPanel(null);
	 	JPanel jp2=new JPanel(null);
	 	JPanel jp3=new JPanel(null);*/
	 	
	 	Box box0=Box.createHorizontalBox();
	 	Box box1=Box.createHorizontalBox();
	 	Box box2=Box.createHorizontalBox();
	 	Box box3=Box.createHorizontalBox();
	 	Box box4=Box.createHorizontalBox();
	 	Box box5=Box.createHorizontalBox();
	 	Box box6=Box.createHorizontalBox();
	 	Box box7=Box.createHorizontalBox();
	 	Box box8=Box.createHorizontalBox();
	 	Box boxTotal=Box.createVerticalBox();
	 	
	 	//Box boxa=Box.createHorizontalStrut(10);
	 	//Box boxb=Box.createHorizontalStrut(10);
	    /*public static void main(String[] args) {
	    	AddRoom a=new AddRoom();
	 }*/
	 public AddStaff(){
	 }
		//jp1=new JPanel(null);
	 public Box getBox(){	
	    jm0.add(t1);   //将菜单项目添加到菜单
	    //jm0.add(t2);    //将菜单项目添加到菜单
	    jm0.add(t9);   //将菜单项目添加到菜单
	    jm0.add(t10);    //将菜单项目添加到菜单
	    
	    jm1.add(t3);    //将菜单项目添加到菜单
	    jm1.add(t4);    //将菜单项目添加到菜单
	    jm1.add(t2);
	    jm2.add(t5);    //将菜单项目添加到菜单
	    jm2.add(t6);    //将菜单项目添加到菜单
	    jm3.add(t7);    //将菜单项目添加到菜单
	    jm3.add(t8);    //将菜单项目添加到菜单
	   //JMenuBar  br=new  JMenuBar() ;  //创建菜单工具栏
	    //jp1.add(jm);      //将菜单增加到菜单工具栏
	    //this.add(jp1) ;  //为 窗体设置  菜单工具栏
	    menubar.add(jm0);
	    menubar.add(jm1);
	    menubar.add(jm2);
	    menubar.add(jm3);
	    

		 cho.add("前台部");
		 cho.add("后勤部");
		 cho.add("保卫部");
		 cho.add("财政部");
		 
		 cho1.add("普通员工");
		 cho1.add("大堂经理");
		 cho1.add("部门部长");
		 
		 //默认选择为男
		 jrb1.setSelected(true);
		 group.add(jrb1);
		 group.add(jrb2);
		 
		 box0.add(Box.createHorizontalStrut(400));
		 box0.add(jlb1);
		 box0.add(jf1);
		 box0.add(Box.createHorizontalStrut(400));
		 
		 box1.add(Box.createHorizontalStrut(400));
		 box1.add(jlb2);
		 box1.add(jf2);
		 box1.add(Box.createHorizontalStrut(400));
		 
		 box2.add(Box.createHorizontalStrut(400));
		 box2.add(jlb8);
		 box2.add(jrb1);
		 box2.add(jrb2);
		 box2.add(Box.createHorizontalStrut(400));
		 
		 box3.add(Box.createHorizontalStrut(400));
		 box3.add(jlb3);
		 box3.add(jf3);
		 box3.add(Box.createHorizontalStrut(400));
		 
		 box4.add(Box.createHorizontalStrut(400));
		 box4.add(jlb4);
		 box4.add(jf4);
		 box4.add(Box.createHorizontalStrut(400));
		 
		 box5.add(Box.createHorizontalStrut(400));
		 box5.add(jlb5);
		 box5.add(jf5);
		 box5.add(Box.createHorizontalStrut(400));
		 
		 box6.add(Box.createHorizontalStrut(400));
		 box6.add(jlb6);
		 box6.add(cho);
		 box6.add(Box.createHorizontalStrut(400));
		 
		 box7.add(Box.createHorizontalStrut(400));
		 box7.add(jlb7);
		 box7.add(cho1);
		 box7.add(Box.createHorizontalStrut(400));
		 
		 box8.add(Box.createHorizontalStrut(400));
		 box8.add(jb1);
		 box8.add(Box.createHorizontalStrut(400));
		 
		 boxTotal.add(Box.createVerticalStrut(100));
		 boxTotal.add(box0);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box1);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box2);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box3);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box4);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box5);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box6);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box7);
		 boxTotal.add(Box.createVerticalStrut(30));
		 boxTotal.add(box8);
		 boxTotal.add(Box.createVerticalStrut(200));
		 
		    t1.addActionListener(this);//设置监听
		    t2.addActionListener(this);
		    t3.addActionListener(this);
		    t4.addActionListener(this);
		    t5.addActionListener(this);
		    t6.addActionListener(this);
		    t7.addActionListener(this);
		    //t8.addActionListener(this);
		    t9.addActionListener(this);
		    t10.addActionListener(this);
		    jb1.addActionListener(this);
		    
		    return boxTotal;
//		 this.add(boxTotal);
//		 
//	    //this.setLayout(new GridLayout(4,1));  
//		 this.setJMenuBar(menubar);
//        //给窗口设置标题  
//        this.setTitle("8天酒店管理系统");  
//        //设置窗体大小  
//        this.setSize(1000,700);  
//        //设置窗体初始位置  
//        this.setLocation(200, 150);  
//        //设置当关闭窗口时，保证JVM也退出  
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        //显示窗体  
//        this.setVisible(true);  
//        this.setResizable(true);  
	 }
	 public void actionPerformed(ActionEvent e){
     	if(e.getSource()==t1){
     		dispose();
     		layoutAddRoomInfo g=new layoutAddRoomInfo();
     		}
     	if(e.getSource()==t9){
     		dispose();
     		layoutUpdateRoom x=new layoutUpdateRoom();
     		}
     	if(e.getSource()==t10){
     		dispose();
     		QueryRoom b=new QueryRoom();
     		}
     	if(e.getSource()==t3){
     		dispose();
     		UsingRoom c=new UsingRoom();
     		}
     	if(e.getSource()==t4){
     		dispose();
     		RoomBook d=new RoomBook();
     		}
     	if(e.getSource()==t2){
     		dispose();
     		ReturnRoom f=new ReturnRoom();
     		}
     	if(e.getSource()==t6){
     		dispose();
     		ClientQuery h=new ClientQuery();
     		}
     	if(e.getSource()==t5){
     		dispose();
     		AddClient i=new AddClient();
     		}
     	if(e.getSource()==t7){
     		dispose();
     		//RecomposeDelete o=new RecomposeDelete();
     		}
     	if(e.getSource()==jb1){
     		String sex = new String();
            if (jrb1.isSelected()) {
                sex = jrb1.getText().toString();
            } else {
                sex = jrb2.getText().toString();
            }
     		Function p=new Function();
     		p.insertStaff(jf1.getText().trim(),jf2.getText().trim(),sex,jf3.getText().trim(),jf5.getText().trim(),
     				cho.getSelectedItem(),cho1.getSelectedItem(),jf4.getText().trim());
     		}
     	}
}
