package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;  

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;
import org.jvnet.substance.skin.*;

public class LoginWindow extends JFrame implements ActionListener, KeyListener{
	
	JButton btnLogin,btnExit=null;  //��ť
    JPanel jpUser,jpPass,jpMode,jpBtn=null;  //���
    JTextField vUser=null;     //�ı�
    JLabel lbUser,lbPass=null;  //��ǩ
    JPasswordField vPass=null;  //����
    ButtonGroup bg = new ButtonGroup(); ;
    JRadioButton modeAdmin = new JRadioButton("����Ա");
    JRadioButton modeNormal = new JRadioButton("��ͨ�û�");
    
    int userMode = 0;
    
 	
  public static void main(String[] args) {  
	   
	   
	   try{
		 
			 // �������
			 UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			 JFrame.setDefaultLookAndFeelDecorated(true);
			 JDialog.setDefaultLookAndFeelDecorated(true);
			 // ��������
			 SubstanceLookAndFeel.setSkin(new EbonyHighContrastSkin());
			 // ���ð�ť���
			 SubstanceLookAndFeel
			       .setCurrentButtonShaper(new StandardButtonShaper());
			 // ����ˮӡ
			 SubstanceLookAndFeel
		       .setCurrentWatermark(new SubstanceBubblesWatermark());
			 // ���ñ߿�
			 SubstanceLookAndFeel
			       .setCurrentBorderPainter(new StandardBorderPainter());
			 //���ý�����Ⱦ
			 //SubstanceLookAndFeel
			//       .setCurrentGradientPainter(new StandardGradientPainter());
			 // ���ñ���
			 //SubstanceLookAndFeel
			   //           .setCurrentTitlePainter(new MatteHeaderPainter());
			 
		 } catch (UnsupportedLookAndFeelException ex) {
		       ex.printStackTrace();
		 }
	   
	   
	   //System.out.println("������¼����");
	   		
	   // ������¼����
	   LoginWindow loginWindow = new LoginWindow();
		 
		 // ÿ�ζ�Ҫ��¼̫�鷳�ˣ�
//		 loginWindow.userMode = 1;
//		 loginWindow.dispose();
		 
//		 new MainWindow(loginWindow); 
    }
    
    //���캯��  
    public LoginWindow()  
    {  
    	
        this.initPanel();
        this.initLayout();
    }
    
    // ��ʼ���������
    private void initPanel(){
        //�������  
       btnLogin=new JButton("��¼");  
       btnExit=new JButton("�˳�ϵͳ");   
       //���ü���  
       btnLogin.addActionListener(this);  
       btnExit.addActionListener(this);  
                
       jpUser=new JPanel();  
       jpPass=new JPanel();  
       jpMode=new JPanel();  
       jpBtn=new JPanel();                 
         
       lbUser=new JLabel("�ʺţ�");  
       lbPass=new JLabel("���룺");  
       vUser=new JTextField(10);  
       vPass=new JPasswordField(10);  
       //���뵽JPanel��
       
       bg.add(modeAdmin);
       bg.add(modeNormal);
       
       jpUser.add(lbUser);  
       jpUser.add(vUser);  
         
       jpPass.add(lbPass);  
       jpPass.add(vPass);  
       
       // ��ʼ��Ĭ����ͨ�û�
       modeNormal.setSelected(true);
       jpMode.add(modeAdmin);  
       jpMode.add(modeNormal); 
         
       jpBtn.add(btnLogin);  
       jpBtn.add(btnExit);  
       //jp4.add(jb3);  
         
       //����JFrame��  
       //this.setJMenuBar(jmb);  
       this.add(jpUser);  
       this.add(jpPass);  
       this.add(jpMode);  
       this.add(jpBtn);
       
       // ��Ӽ��̼���
       vUser.addKeyListener(this);
       vPass.addKeyListener(this);
    }
    
    public void initLayout(){
    	
    	vUser.setText("");
    	this.vPass.setText("");
    	
        //���ò��ֹ�����  
        this.setLayout(new GridLayout(4,1));  
        //���������ñ���  
        this.setTitle("8��Ƶ����ϵͳ");  
        //���ô����С  
        this.setSize(400,200);  
        //���ô����ʼλ��  
        this.setLocation(200, 150);  
        //���õ��رմ���ʱ����֤JVMҲ�˳�  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //��ʾ����  
        this.setVisible(true);  
        this.setResizable(false);  
    }
    
    // ��¼
    private void login(){
       
        // �ж������Ƿ�����
        if(vUser.getText().length() == 0){
        	vUser.requestFocus();
        	JOptionPane.showMessageDialog(null,"�㻹û�������˺��أ�","��������",JOptionPane.WARNING_MESSAGE); 
        	return;
        }
        
        if(vPass.getPassword().length == 0){
        	vPass.requestFocus();
        	JOptionPane.showMessageDialog(null,"������룡","����ɵ������",JOptionPane.WARNING_MESSAGE); 
        	return;
        }
        
        // ��ȡ���ݿ����
        Function func = Function.getSelf();
        
        if(func == null){
        	System.out.println("���ݿ�����ʧ��");
        	return;
        }
        
        // ��ȡ�ı�������
        String userName = vUser.getText();
        String userPass = String.valueOf(vPass.getPassword());
        userMode = 0;
        boolean isLogin = false;
        
        // ��¼��֤
        if(modeAdmin.isSelected()){
        	// ����Աģʽ
        	userMode = 1;
        	isLogin = func.userLogin(userName, userPass, userMode);
        } else {
        	// ��ͨģʽ
        	userMode = 2;
        	isLogin = func.userLogin(userName, userPass, userMode);
        }
        
        // ��¼ʧ��
        if(!isLogin){
        	vPass.requestFocus();
        	JOptionPane.showMessageDialog(null,"���һ���˺�������û�д�� ","��¼ʧ���� ",JOptionPane.WARNING_MESSAGE); 
        	return;
        }
        
        // �رմ��� ����������
        dispose();
        new MainWindow(this);
    }
    
    // �¼���Ӧ
	public void actionPerformed(ActionEvent e){
    	
        if(e.getSource() == btnLogin){
        	// ��¼�¼�
        	this.login();
        } else if(e.getSource() == btnExit){
        	// �˳��¼�
        	dispose();
        }
    }
	
    public void keyPressed(KeyEvent e) {  
        //System.out.print("����" + KeyEvent.getKeyText(e.getKeyCode()) + "������\n");  
    	if(e.getKeyCode() == KeyEvent.VK_ENTER){
    		this.login();
    	}
    }  
  
    public void keyReleased(KeyEvent e) {  
    	//System.out.print("����" + KeyEvent.getKeyText(e.getKeyCode()) + "���ɿ�\n");  
    }  
  
    public void keyTyped(KeyEvent e) {  
    	//System.out.print("�����������" + e.getKeyChar() + "\n");  
    }  

}