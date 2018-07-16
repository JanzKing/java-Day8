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
	
	JButton btnLogin,btnExit=null;  //按钮
    JPanel jpUser,jpPass,jpMode,jpBtn=null;  //面板
    JTextField vUser=null;     //文本
    JLabel lbUser,lbPass=null;  //标签
    JPasswordField vPass=null;  //密码
    ButtonGroup bg = new ButtonGroup(); ;
    JRadioButton modeAdmin = new JRadioButton("管理员");
    JRadioButton modeNormal = new JRadioButton("普通用户");
    
    int userMode = 0;
    
 	
  public static void main(String[] args) {  
	   
	   
	   try{
		 
			 // 设置外观
			 UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			 JFrame.setDefaultLookAndFeelDecorated(true);
			 JDialog.setDefaultLookAndFeelDecorated(true);
			 // 设置主题
			 SubstanceLookAndFeel.setSkin(new EbonyHighContrastSkin());
			 // 设置按钮外观
			 SubstanceLookAndFeel
			       .setCurrentButtonShaper(new StandardButtonShaper());
			 // 设置水印
			 SubstanceLookAndFeel
		       .setCurrentWatermark(new SubstanceBubblesWatermark());
			 // 设置边框
			 SubstanceLookAndFeel
			       .setCurrentBorderPainter(new StandardBorderPainter());
			 //设置渐变渲染
			 //SubstanceLookAndFeel
			//       .setCurrentGradientPainter(new StandardGradientPainter());
			 // 设置标题
			 //SubstanceLookAndFeel
			   //           .setCurrentTitlePainter(new MatteHeaderPainter());
			 
		 } catch (UnsupportedLookAndFeelException ex) {
		       ex.printStackTrace();
		 }
	   
	   
	   //System.out.println("创建登录窗口");
	   		
	   // 创建登录窗口
	   LoginWindow loginWindow = new LoginWindow();
		 
		 // 每次都要登录太麻烦了！
//		 loginWindow.userMode = 1;
//		 loginWindow.dispose();
		 
//		 new MainWindow(loginWindow); 
    }
    
    //构造函数  
    public LoginWindow()  
    {  
    	
        this.initPanel();
        this.initLayout();
    }
    
    // 初始化面板内容
    private void initPanel(){
        //创建组件  
       btnLogin=new JButton("登录");  
       btnExit=new JButton("退出系统");   
       //设置监听  
       btnLogin.addActionListener(this);  
       btnExit.addActionListener(this);  
                
       jpUser=new JPanel();  
       jpPass=new JPanel();  
       jpMode=new JPanel();  
       jpBtn=new JPanel();                 
         
       lbUser=new JLabel("帐号：");  
       lbPass=new JLabel("密码：");  
       vUser=new JTextField(10);  
       vPass=new JPasswordField(10);  
       //加入到JPanel中
       
       bg.add(modeAdmin);
       bg.add(modeNormal);
       
       jpUser.add(lbUser);  
       jpUser.add(vUser);  
         
       jpPass.add(lbPass);  
       jpPass.add(vPass);  
       
       // 初始化默认普通用户
       modeNormal.setSelected(true);
       jpMode.add(modeAdmin);  
       jpMode.add(modeNormal); 
         
       jpBtn.add(btnLogin);  
       jpBtn.add(btnExit);  
       //jp4.add(jb3);  
         
       //加入JFrame中  
       //this.setJMenuBar(jmb);  
       this.add(jpUser);  
       this.add(jpPass);  
       this.add(jpMode);  
       this.add(jpBtn);
       
       // 添加键盘监听
       vUser.addKeyListener(this);
       vPass.addKeyListener(this);
    }
    
    public void initLayout(){
    	
    	vUser.setText("");
    	this.vPass.setText("");
    	
        //设置布局管理器  
        this.setLayout(new GridLayout(4,1));  
        //给窗口设置标题  
        this.setTitle("8天酒店管理系统");  
        //设置窗体大小  
        this.setSize(400,200);  
        //设置窗体初始位置  
        this.setLocation(200, 150);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(false);  
    }
    
    // 登录
    private void login(){
       
        // 判定输入是否完整
        if(vUser.getText().length() == 0){
        	vUser.requestFocus();
        	JOptionPane.showMessageDialog(null,"你还没有输入账号呢！","致命错误！",JOptionPane.WARNING_MESSAGE); 
        	return;
        }
        
        if(vPass.getPassword().length == 0){
        	vPass.requestFocus();
        	JOptionPane.showMessageDialog(null,"你的密码！","你是傻掉了吗？",JOptionPane.WARNING_MESSAGE); 
        	return;
        }
        
        // 获取数据库对象
        Function func = Function.getSelf();
        
        if(func == null){
        	System.out.println("数据库连接失败");
        	return;
        }
        
        // 读取文本框内容
        String userName = vUser.getText();
        String userPass = String.valueOf(vPass.getPassword());
        userMode = 0;
        boolean isLogin = false;
        
        // 登录验证
        if(modeAdmin.isSelected()){
        	// 管理员模式
        	userMode = 1;
        	isLogin = func.userLogin(userName, userPass, userMode);
        } else {
        	// 普通模式
        	userMode = 2;
        	isLogin = func.userLogin(userName, userPass, userMode);
        }
        
        // 登录失败
        if(!isLogin){
        	vPass.requestFocus();
        	JOptionPane.showMessageDialog(null,"检查一下账号密码有没有错吧 ","登录失败了 ",JOptionPane.WARNING_MESSAGE); 
        	return;
        }
        
        // 关闭窗口 开启主窗口
        dispose();
        new MainWindow(this);
    }
    
    // 事件响应
	public void actionPerformed(ActionEvent e){
    	
        if(e.getSource() == btnLogin){
        	// 登录事件
        	this.login();
        } else if(e.getSource() == btnExit){
        	// 退出事件
        	dispose();
        }
    }
	
    public void keyPressed(KeyEvent e) {  
        //System.out.print("键盘" + KeyEvent.getKeyText(e.getKeyCode()) + "键向下\n");  
    	if(e.getKeyCode() == KeyEvent.VK_ENTER){
    		this.login();
    	}
    }  
  
    public void keyReleased(KeyEvent e) {  
    	//System.out.print("键盘" + KeyEvent.getKeyText(e.getKeyCode()) + "键松开\n");  
    }  
  
    public void keyTyped(KeyEvent e) {  
    	//System.out.print("输入的内容是" + e.getKeyChar() + "\n");  
    }  

}