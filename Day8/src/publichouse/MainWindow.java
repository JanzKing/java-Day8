package publichouse;

import javax.swing.*;  
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener; 


public class MainWindow extends JFrame implements ActionListener{
	//JPanel jp1=null;
	public final static int INSERT = 0;
	public final static int UPDATE = 0;
	
	// 存放自己
	private static MainWindow self = null;
	// 存放上一个JFrame
	JFrame lastFrame = null;
	// 当前的界面内容对象
	Component currentBox = null;
	// 我们设置一个变量 判定用户是不是管理员
	boolean isAdmin = false;
	
	JMenuBar menubar=new JMenuBar();
	JMenu menuRoomInfo = new JMenu("客房信息管理") ;     //创建JMenu菜单对象
	JMenu menuRoomDeal = new JMenu("客房经营管理") ;     //创建JMenu菜单对象
	JMenu menuUserSearch = new JMenu("客户信息查询") ;     //创建JMenu菜单对象
	JMenu menuStaffInfo = new JMenu("员工信息管理") ;     //创建JMenu菜单对象
	
	JMenu menuSetting = new JMenu("设置") ;  

	JMenuItem btnInsertRoomInfo = new JMenuItem("录入客房信息") ;  //菜单项
	//JMenuItem t2=new JMenuItem("删除客房信息") ;//菜单项
	JMenuItem btnUpdateRoomInfo = new JMenuItem("修改客房信息") ;//菜单项
	JMenuItem btnSearchRoomInfo = new JMenuItem("查询客房信息") ;//菜单项

	JMenuItem t3=new JMenuItem("客房使用情况") ;//菜单项
	JMenuItem t4=new JMenuItem("客房预订") ;
	JMenuItem t2=new JMenuItem("客人退房") ;//菜单项

	JMenuItem t5=new JMenuItem("添加客户信息") ;//菜单项
	JMenuItem t6=new JMenuItem("查询客户信息") ;//菜单项

	JMenuItem t7=new JMenuItem("查询员工信息") ;//菜单项
	JMenuItem t8=new JMenuItem("添加员工信息") ;//菜单项
	
	JMenuItem btnChangePassword = new JMenuItem("修改密码") ;//菜单项
	JMenuItem btnLogOut = new JMenuItem("退出登录");
	
	/*JLabel 添加客房信息=new JLabel("--添加客房信息--",JLabel.CENTER);
	添加客房信息.setFont(new Font("TimesRoman",Font.BOLD,25));*/

	/*  public static void main(String[] args) {
	StaffQuery a=new StaffQuery();
	}*/

	public MainWindow(JFrame frame){
		super("8天酒店管理系统");
		// 只有第一次创建主窗口才调用
		if(MainWindow.self == null){
			MainWindow.self = this;
			
			// 记录上一个窗口
			this.lastFrame = frame;
			
			// 初始化菜单
			this.initMenuBar();
			
			// 添加事件
			btnLogOut.addActionListener(this);
			btnChangePassword.addActionListener(this);

			btnInsertRoomInfo.addActionListener(this);//设置监听
			t2.addActionListener(this);
			t3.addActionListener(this);
			t4.addActionListener(this);
			t5.addActionListener(this);
			t6.addActionListener(this);
			t7.addActionListener(this);
			t8.addActionListener(this);
			btnUpdateRoomInfo.addActionListener(this);
			btnSearchRoomInfo .addActionListener(this);
			
			// 初始化布局并显示
			this.initLayout();
		}
	}
	
	// 静态方法 获取当前窗口对象
	public static MainWindow getWindow(){
		// 如果没有创建过 创建一个
		if(MainWindow.self == null) 
			return new MainWindow(null);
		else return MainWindow.self;
	}
	
	// 菜单栏布局
	public void initMenuBar(){
		//jp1=new JPanel(null);
		
		
	   // Image image;
	    

		menuRoomInfo.add(btnInsertRoomInfo);   //将菜单项目添加到菜单
		//jm0.add(t2);    //将菜单项目添加到菜单
		menuRoomInfo.add(btnUpdateRoomInfo);   //将菜单项目添加到菜单
		menuRoomInfo.add(btnSearchRoomInfo );    //将菜单项目添加到菜单

		menuRoomDeal.add(t3);    //将菜单项目添加到菜单
		menuRoomDeal.add(t4);    //将菜单项目添加到菜单
		menuRoomDeal.add(t2);
		menuUserSearch.add(t5);    //将菜单项目添加到菜单
		menuUserSearch.add(t6);//将菜单项目添加到菜单

		menuStaffInfo.add(t7);
		menuStaffInfo.add(t8); //将菜单项目添加到菜单
		
		menuSetting.add(btnChangePassword);
		menuSetting.add(btnLogOut);

		//将菜单项目添加到菜单
		//JMenuBar  br=new  JMenuBar() ;  //创建菜单工具栏
		//jp1.add(jm);      //将菜单增加到菜单工具栏
		//this.add(jp1) ;  //为 窗体设置  菜单工具栏
		menubar.add(menuRoomInfo);
		menubar.add(menuRoomDeal);
		menubar.add(menuUserSearch);

		// 是管理员
		if(((LoginWindow)this.lastFrame).modeAdmin.isSelected()){
			menubar.add(menuStaffInfo);
			this.isAdmin = true;
		}
			
		
		menubar.add(menuSetting);
		

		this.setJMenuBar(menubar);

	}
	
	// 更新布局
	public void initLayout(){

		//给窗口设置标题  
		this.setTitle("8天酒店管理系统");  
		//设置窗体大小  
		this.setSize(1000,700);  
		//设置窗体初始位置  
		this.setLocation(200, 150);  
		//设置当关闭窗口时，保证JVM也退出  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//显示窗体  

		this.setVisible(true);  
		//this.setResizable(false);  
	}
	
	// 更新主界面
	private void updateBox(Component box){
		
		// 删除原有的主界面
		if(this.currentBox != null) this.remove(this.currentBox);
		// 记录当前（新）主界面
		this.currentBox = box;
		
		// 添加并更新
		this.add(box);
		this.setVisible(true);
	}

	// 菜单按钮响
	public void actionPerformed(ActionEvent e){
		
		// 退出登录
		if(e.getSource() == btnLogOut){
			
			this.dispose();
			((LoginWindow) this.lastFrame).initLayout();

		// 修改密码
		} else if(e.getSource()==btnChangePassword){
			
			layoutChangePassword changePassword = new layoutChangePassword(this.lastFrame);
			Box box = changePassword.getBox();
			
			this.updateBox(box);
		
		// 插入新房间信息
		} else if(e.getSource() == btnInsertRoomInfo){
			
			layoutAddRoomInfo addRoom = new layoutAddRoomInfo();
			Box box = addRoom.getBox();
			
			this.updateBox(box);
		
		// 更新房间信息
		} else if(e.getSource() == btnUpdateRoomInfo){

			layoutUpdateRoom updateRoom = new layoutUpdateRoom();
			Box box = updateRoom.getBox();
			
			this.updateBox(box);
		
		// 查找房间
		} else if(e.getSource()==btnSearchRoomInfo ){
			
			QueryRoom queryroom=new QueryRoom();


		} else if(e.getSource()==t3){

			UsingRoom usingroom=new UsingRoom();

		} else if(e.getSource()==t4){
			RoomBook roombook=new RoomBook();
			Box box = roombook.getBox();
			
			this.updateBox(box);
			

		} else if(e.getSource()==t2){

			ReturnRoom returnroom=new ReturnRoom();			

		} else if(e.getSource()==t6){

			ClientQuery h=new ClientQuery();
			

		} else if(e.getSource()==t5){

			AddClient addclient=new AddClient();
			Box box = addclient.getBox();
			
			this.updateBox(box);

		} else if(e.getSource()==t8){

			AddStaff addstaff=new AddStaff();
			Box box = addstaff.getBox();
			
			this.updateBox(box);

		} else if(e.getSource()==t7){

			QueryStaff o=new QueryStaff();
		
		}
	}

}
