package publichouse;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;
import java.sql.*;  

 public class AddStaff extends JFrame implements ActionListener{
	 	JMenuBar menubar=new JMenuBar();
	    JMenu jm0=new JMenu("�ͷ���Ϣ����") ;     //����JMenu�˵�����
	    JMenu jm1=new JMenu("�ͷ���Ӫ����") ;     //����JMenu�˵�����
	    JMenu jm2=new JMenu("�ͻ���Ϣ��ѯ") ;     //����JMenu�˵�����
	    JMenu jm3=new JMenu("Ա����Ϣ����") ;     //����JMenu�˵�����
	    
	    JMenuItem t1=new JMenuItem("¼��ͷ���Ϣ") ;  //�˵���
	    //JMenuItem t2=new JMenuItem("ɾ���ͷ���Ϣ") ;//�˵���
	    JMenuItem t9=new JMenuItem("�޸Ŀͷ���Ϣ") ;//�˵���
	    JMenuItem t10=new JMenuItem("��ѯ�ͷ���Ϣ") ;//�˵���
	    
	    JMenuItem t3=new JMenuItem("�ͷ�ʹ�����") ;//�˵���
	    JMenuItem t4=new JMenuItem("�ͷ�Ԥ��") ;
	    JMenuItem t2=new JMenuItem("�����˷�") ;//��
	    
	    JMenuItem t5=new JMenuItem("��ӿͻ���Ϣ") ;//�˵���
	    JMenuItem t6=new JMenuItem("��ѯ�ͻ���Ϣ") ;//�˵���
	    
	    JMenuItem t7=new JMenuItem("��ѯԱ����Ϣ") ;//�˵���
	    JMenuItem t8=new JMenuItem("���Ա����Ϣ") ;//�˵���
	  //  JMenuItem t9=new JMenuItem("ɾ��Ա����Ϣ") ;//�˵���
	 
	 	JLabel jlb1=new JLabel("Ա�����:",JLabel.CENTER);
	 	JLabel jlb2=new JLabel("Ա������:",JLabel.CENTER);
	 	JLabel jlb8=new JLabel("�Ա�:",JLabel.CENTER);
	 	JLabel jlb3=new JLabel("����:",JLabel.CENTER);
	 	JLabel jlb4=new JLabel("ͨ�ŵ�ַ:",JLabel.CENTER);
	 	JLabel jlb5=new JLabel("��ϵ�绰:",JLabel.CENTER);
	 	JLabel jlb6=new JLabel("���ڲ���:",JLabel.CENTER);
	 	JLabel jlb7=new JLabel("Ա������:",JLabel.CENTER);
	 	
	 	JTextField jf1=new JTextField(10);
	 	JTextField jf2=new JTextField(10);
	 	JTextField jf3=new JTextField(10);
	 	JTextField jf4=new JTextField(10);
	 	JTextField jf5=new JTextField(10);
	 	
	 	Choice cho=new Choice();
	 	Choice cho1=new Choice();
	 	
	 	JRadioButton jrb1 = new JRadioButton("��");
	 	JRadioButton jrb2 = new JRadioButton("Ů");
	 	ButtonGroup group = new ButtonGroup();  
	 	
	 	JButton jb1=new JButton("¼��");
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
	    jm0.add(t1);   //���˵���Ŀ��ӵ��˵�
	    //jm0.add(t2);    //���˵���Ŀ��ӵ��˵�
	    jm0.add(t9);   //���˵���Ŀ��ӵ��˵�
	    jm0.add(t10);    //���˵���Ŀ��ӵ��˵�
	    
	    jm1.add(t3);    //���˵���Ŀ��ӵ��˵�
	    jm1.add(t4);    //���˵���Ŀ��ӵ��˵�
	    jm1.add(t2);
	    jm2.add(t5);    //���˵���Ŀ��ӵ��˵�
	    jm2.add(t6);    //���˵���Ŀ��ӵ��˵�
	    jm3.add(t7);    //���˵���Ŀ��ӵ��˵�
	    jm3.add(t8);    //���˵���Ŀ��ӵ��˵�
	   //JMenuBar  br=new  JMenuBar() ;  //�����˵�������
	    //jp1.add(jm);      //���˵����ӵ��˵�������
	    //this.add(jp1) ;  //Ϊ ��������  �˵�������
	    menubar.add(jm0);
	    menubar.add(jm1);
	    menubar.add(jm2);
	    menubar.add(jm3);
	    

		 cho.add("ǰ̨��");
		 cho.add("���ڲ�");
		 cho.add("������");
		 cho.add("������");
		 
		 cho1.add("��ͨԱ��");
		 cho1.add("���þ���");
		 cho1.add("���Ų���");
		 
		 //Ĭ��ѡ��Ϊ��
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
		 
		    t1.addActionListener(this);//���ü���
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
//        //���������ñ���  
//        this.setTitle("8��Ƶ����ϵͳ");  
//        //���ô����С  
//        this.setSize(1000,700);  
//        //���ô����ʼλ��  
//        this.setLocation(200, 150);  
//        //���õ��رմ���ʱ����֤JVMҲ�˳�  
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        //��ʾ����  
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
