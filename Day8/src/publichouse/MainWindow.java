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
	
	// ����Լ�
	private static MainWindow self = null;
	// �����һ��JFrame
	JFrame lastFrame = null;
	// ��ǰ�Ľ������ݶ���
	Component currentBox = null;
	// ��������һ������ �ж��û��ǲ��ǹ���Ա
	boolean isAdmin = false;
	
	JMenuBar menubar=new JMenuBar();
	JMenu menuRoomInfo = new JMenu("�ͷ���Ϣ����") ;     //����JMenu�˵�����
	JMenu menuRoomDeal = new JMenu("�ͷ���Ӫ����") ;     //����JMenu�˵�����
	JMenu menuUserSearch = new JMenu("�ͻ���Ϣ��ѯ") ;     //����JMenu�˵�����
	JMenu menuStaffInfo = new JMenu("Ա����Ϣ����") ;     //����JMenu�˵�����
	
	JMenu menuSetting = new JMenu("����") ;  

	JMenuItem btnInsertRoomInfo = new JMenuItem("¼��ͷ���Ϣ") ;  //�˵���
	//JMenuItem t2=new JMenuItem("ɾ���ͷ���Ϣ") ;//�˵���
	JMenuItem btnUpdateRoomInfo = new JMenuItem("�޸Ŀͷ���Ϣ") ;//�˵���
	JMenuItem btnSearchRoomInfo = new JMenuItem("��ѯ�ͷ���Ϣ") ;//�˵���

	JMenuItem t3=new JMenuItem("�ͷ�ʹ�����") ;//�˵���
	JMenuItem t4=new JMenuItem("�ͷ�Ԥ��") ;
	JMenuItem t2=new JMenuItem("�����˷�") ;//�˵���

	JMenuItem t5=new JMenuItem("��ӿͻ���Ϣ") ;//�˵���
	JMenuItem t6=new JMenuItem("��ѯ�ͻ���Ϣ") ;//�˵���

	JMenuItem t7=new JMenuItem("��ѯԱ����Ϣ") ;//�˵���
	JMenuItem t8=new JMenuItem("���Ա����Ϣ") ;//�˵���
	
	JMenuItem btnChangePassword = new JMenuItem("�޸�����") ;//�˵���
	JMenuItem btnLogOut = new JMenuItem("�˳���¼");
	
	/*JLabel ��ӿͷ���Ϣ=new JLabel("--��ӿͷ���Ϣ--",JLabel.CENTER);
	��ӿͷ���Ϣ.setFont(new Font("TimesRoman",Font.BOLD,25));*/

	/*  public static void main(String[] args) {
	StaffQuery a=new StaffQuery();
	}*/

	public MainWindow(JFrame frame){
		super("8��Ƶ����ϵͳ");
		// ֻ�е�һ�δ��������ڲŵ���
		if(MainWindow.self == null){
			MainWindow.self = this;
			
			// ��¼��һ������
			this.lastFrame = frame;
			
			// ��ʼ���˵�
			this.initMenuBar();
			
			// ����¼�
			btnLogOut.addActionListener(this);
			btnChangePassword.addActionListener(this);

			btnInsertRoomInfo.addActionListener(this);//���ü���
			t2.addActionListener(this);
			t3.addActionListener(this);
			t4.addActionListener(this);
			t5.addActionListener(this);
			t6.addActionListener(this);
			t7.addActionListener(this);
			t8.addActionListener(this);
			btnUpdateRoomInfo.addActionListener(this);
			btnSearchRoomInfo .addActionListener(this);
			
			// ��ʼ�����ֲ���ʾ
			this.initLayout();
		}
	}
	
	// ��̬���� ��ȡ��ǰ���ڶ���
	public static MainWindow getWindow(){
		// ���û�д����� ����һ��
		if(MainWindow.self == null) 
			return new MainWindow(null);
		else return MainWindow.self;
	}
	
	// �˵�������
	public void initMenuBar(){
		//jp1=new JPanel(null);
		
		
	   // Image image;
	    

		menuRoomInfo.add(btnInsertRoomInfo);   //���˵���Ŀ��ӵ��˵�
		//jm0.add(t2);    //���˵���Ŀ��ӵ��˵�
		menuRoomInfo.add(btnUpdateRoomInfo);   //���˵���Ŀ��ӵ��˵�
		menuRoomInfo.add(btnSearchRoomInfo );    //���˵���Ŀ��ӵ��˵�

		menuRoomDeal.add(t3);    //���˵���Ŀ��ӵ��˵�
		menuRoomDeal.add(t4);    //���˵���Ŀ��ӵ��˵�
		menuRoomDeal.add(t2);
		menuUserSearch.add(t5);    //���˵���Ŀ��ӵ��˵�
		menuUserSearch.add(t6);//���˵���Ŀ��ӵ��˵�

		menuStaffInfo.add(t7);
		menuStaffInfo.add(t8); //���˵���Ŀ��ӵ��˵�
		
		menuSetting.add(btnChangePassword);
		menuSetting.add(btnLogOut);

		//���˵���Ŀ��ӵ��˵�
		//JMenuBar  br=new  JMenuBar() ;  //�����˵�������
		//jp1.add(jm);      //���˵����ӵ��˵�������
		//this.add(jp1) ;  //Ϊ ��������  �˵�������
		menubar.add(menuRoomInfo);
		menubar.add(menuRoomDeal);
		menubar.add(menuUserSearch);

		// �ǹ���Ա
		if(((LoginWindow)this.lastFrame).modeAdmin.isSelected()){
			menubar.add(menuStaffInfo);
			this.isAdmin = true;
		}
			
		
		menubar.add(menuSetting);
		

		this.setJMenuBar(menubar);

	}
	
	// ���²���
	public void initLayout(){

		//���������ñ���  
		this.setTitle("8��Ƶ����ϵͳ");  
		//���ô����С  
		this.setSize(1000,700);  
		//���ô����ʼλ��  
		this.setLocation(200, 150);  
		//���õ��رմ���ʱ����֤JVMҲ�˳�  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//��ʾ����  

		this.setVisible(true);  
		//this.setResizable(false);  
	}
	
	// ����������
	private void updateBox(Component box){
		
		// ɾ��ԭ�е�������
		if(this.currentBox != null) this.remove(this.currentBox);
		// ��¼��ǰ���£�������
		this.currentBox = box;
		
		// ��Ӳ�����
		this.add(box);
		this.setVisible(true);
	}

	// �˵���ť��
	public void actionPerformed(ActionEvent e){
		
		// �˳���¼
		if(e.getSource() == btnLogOut){
			
			this.dispose();
			((LoginWindow) this.lastFrame).initLayout();

		// �޸�����
		} else if(e.getSource()==btnChangePassword){
			
			layoutChangePassword changePassword = new layoutChangePassword(this.lastFrame);
			Box box = changePassword.getBox();
			
			this.updateBox(box);
		
		// �����·�����Ϣ
		} else if(e.getSource() == btnInsertRoomInfo){
			
			layoutAddRoomInfo addRoom = new layoutAddRoomInfo();
			Box box = addRoom.getBox();
			
			this.updateBox(box);
		
		// ���·�����Ϣ
		} else if(e.getSource() == btnUpdateRoomInfo){

			layoutUpdateRoom updateRoom = new layoutUpdateRoom();
			Box box = updateRoom.getBox();
			
			this.updateBox(box);
		
		// ���ҷ���
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
