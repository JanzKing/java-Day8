package publichouse;

import java.sql.*;  

//import java.sql.Date;
import javax.swing.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector; 
import java.util.Date; 
  
public class Function{
	
	//����ύ���û���������  
	Connection conn;
	private PreparedStatement pstmt;
	private String sql;
	float rest;
	static String zh;
	static String ad;
	static String fanghao;
	
	private String dbAddress;
	private String dbName;
	private String dbUser;
	private String dbPass;
	
	private static Function self = null;


	// ���ǰѹ��캯�����ó�˽�У�Ȼ��ͳһ��getSelf��ȡ����
	// �����ܱ�֤�˶����Ψһ��
	// ����Ϊ�˼��ݻ�û�޸Ĺ��Ĵ��룬������ʱ���ĳ�˽��
	// TODO ֮��Ҫ�ĳ�˽��
	@Deprecated
	public Function(){
		self = this;
		
		this.dbAddress = "127.0.0.1:3306";
		this.dbName = "zhuangziqing";
		this.dbUser = "root";
		this.dbPass = "root";
	}

	public static Function getSelf(){
		if(self == null){
			new Function();
			
			// ֱ������
			if(!self.loadDatabaseDriver()){
				
				// ��������ʧ��
				self = null;
				
			} else if(!self.connect()){
				// ����ʧ��
				self = null;
			}
		}
		
		return self;
	}
	
	//�������ݿ�
	private boolean loadDatabaseDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("�������ݿ�����ʧ�ܣ�");
			System.err.println(e);
			return false;
		}
	}
	private boolean connect(){
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://"+this.dbAddress+"/"+this.dbName+"",
					this.dbUser,
					this.dbPass);
			
			this.conn = conn;
			return true;
		} catch (SQLException e) {
			System.err.println("���ݿ����ӳ���");
			System.err.println(e);
			return false;
		}
	}
	
	
	// Ӧ�ðѼ��غ����Ӷ������ڲ���ɱȽϺ�
	@Deprecated
	public boolean loadDataBaseDriver_Old() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("�������ݿ�����ʧ�ܣ�");
			System.err.println(e);
			return false;
		}
	}
	@Deprecated
	public boolean connect_Old(){
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://"+this.dbAddress+"/"+this.dbName+"",this.dbUser,this.dbPass);
			this.conn = con;
			return true;
		} catch (SQLException e) {
			System.err.println("���ݿ����ӳ���");
			System.err.println(e);
			return false;
		}
	}
	
	// �û���¼
	public boolean userLogin(String userName, String password, int mode){
		
		// Admin
		if(mode == 1){
			sql = "SELECT id from admin WHERE userName = ? AND password = ?";
		} else {
			sql = "SELECT id from user WHERE userName = ? AND password = ?";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			zh=userName;
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// int id = rs.getInt("id");
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return false;
		}
	
		return false;
	}

	//¼�뷿����Ϣ
	public int insertRoom(Vector<String>RoomInfo){
		try{
			this.connect_Old();
			sql="INSERT into rooms VALUES(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			for(int i=1;i<=RoomInfo.size();i++){
				if(i==4)
					pstmt.setInt(i, Integer.parseInt(RoomInfo.elementAt(i-1)));
				else
					pstmt.setString(i,RoomInfo.elementAt(i-1));
			}
			pstmt.executeUpdate();
			sql="INSERT into UseCondition VALUES(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(RoomInfo.elementAt(0)));		
			pstmt.setString(2,RoomInfo.elementAt(2));		
			pstmt.setString(3,"�շ�");	
			pstmt.executeUpdate();
		}catch(SQLException se){
			System.err.println("���ݿ�¼�����ݳ���");
			System.err.println(se);
			JOptionPane.showMessageDialog(null, "�����ظ������������뷿�ţ�");
			return -1;
		}
		return 0; 
	}

	//�޸ķ�����Ϣ
	public boolean updateRoom(String RoomNo,String Raddress,String Rsort,String Rprice){
	try{
		this.connect_Old();
		sql="UPDATE rooms SET Address='"+Raddress+"'";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		
		sql="UPDATE rooms SET Sort='"+Rsort+"'";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		
		sql="UPDATE rooms SET price='"+Rprice+"'";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		//}
	}catch(SQLException se){
		System.err.println("���ݿ��޸����ݳ���!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}

//ɾ��������Ϣ
public boolean DeleteRoom(String RoomNo){
	try{
		this.connect_Old();
		sql="DELETE FROM rooms";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		sql="DELETE FROM usecondition";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
	}catch(SQLException se){
		System.err.println("���ݿ�ɾ�����ݳ���!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//��ȡ��ǰʱ��
public String getTime(){
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ 

	String s = dateFormat.format(now); 
	//System.out.println(s); 
	return s;
 }
//��ӿͻ�
public boolean insertClient(String RoomNo,String Name,String Sex,String phone,String sort,String time,String identity){
	try{
		this.connect_Old();
		sql="insert into ClientInformation(RoomNo,ClientName,ClientSex,PhoneNumber,Sort,BookTime,IdentityCard)"
				+"values(?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,RoomNo);		
		pstmt.setString(2,Name);		
		pstmt.setString(3,Sex);		
		pstmt.setString(4,phone);		
		pstmt.setString(5,sort);		
		pstmt.setString(6,time);		
		pstmt.setString(7,identity);		
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "��ӿͻ���Ϣ�ɹ���");
	}catch(SQLException se){
		System.err.println("���ݿ�������ݳ���!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//ɾ���ͻ���Ϣ���˷���
public boolean DeleteClient(String Number){
	try{
		this.connect_Old();
		sql="DELETE FROM clientinformation";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomNo= '"+Number+"'");
		
		sql="UPDATE usecondition SET Rcondition='"+"�շ�"+"'";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+Number+"'");
		JOptionPane.showMessageDialog(null, "�˷��ɹ���");
	}catch(SQLException se){
		System.err.println("���ݿ�ɾ�����ݳ���!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}

	//���� �ͻ����� �������� �绰 ��ע����
	public boolean BookRoom(String clientname,String sort,String phone,String remark){
		try{
			// ֻ��Ҫ����һ�ξͿ�����
			this.connect_Old();
			String roomcondition=null;
			String Rsort=null;
			ResultSet rs=null;
	
			//¼����� �޸ķ���״̬��Ĺ���  �շ���ΪԤ��
			Statement sql1=conn.createStatement();		
			rs=sql1.executeQuery("SELECT * FROM usecondition WHERE Rcondition LIKE '%�շ�%' ");
			while(rs.next()){
				roomcondition=rs.getString(1);
			}		
			// �пշ������
			if(roomcondition!=null){   
				// ָ������
				String kongfang="�շ�";
				rs=sql1.executeQuery("SELECT * FROM usecondition WHERE Sort LIKE '%"+sort+"%' And Rcondition='"+kongfang+"'");
				while(rs.next()){
					Rsort=rs.getString(1);
				}			
				if(Rsort!=null){      //����Ҫ�����ͷ�
					sql="insert into Booked(ClientName,Sort,PhoneNumber,remark) values(?,?,?,?)";      //���붩����Ϣ
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,clientname);		
					pstmt.setString(2,sort);		
					pstmt.setString(3,phone);
					pstmt.setString(4,remark);
					pstmt.executeUpdate();
	
					String yuding="Ԥ��";
					Statement sql2=conn.createStatement();
					rs=sql2.executeQuery("select RoomID from usecondition where Sort='"+sort+"' And Rcondition='"+kongfang+"'");                               //ѡ����շ��ķ����ȡ����
					int []num=new int[50];//���շ�����һ������
					
					for(int i = 0; i < num.length; i++){
						while(rs.next()){
							num[i] = rs.getInt(1);
							//System.out.println(num[i]);
						} 
					}
					
					sql="UPDATE usecondition SET Rcondition='"+yuding+"'";
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate(sql+" WHERE RoomID= '"+num[0]+"'");//����һ���շ���״̬��ΪԤ��
					JOptionPane.showMessageDialog(null, "Ԥ������ɹ���");	
//					pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
				}
				else{
					JOptionPane.showMessageDialog(null, "�����Ϳͷ�������");	
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "����������");			
			}	
		}catch(SQLException se){
			System.err.println("���ݿ�������ݳ���!");
			System.err.println(se);
//			return -1;
		}
		return false;	
	}

public boolean insertStaff(String staffno,String staffName,String staffSex,String staffemail,
		String phonenumber,String staffsection,String sort,String staffAddress){
	try{
		this.connect_Old();
		sql="insert into staff(staffno,name,sex,email,phone,section,staffsort,address)"
				+"values(?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,staffno);		
		pstmt.setString(2,staffName);		
		pstmt.setString(3,staffSex);	
		pstmt.setString(4,staffemail);		
		pstmt.setString(5,phonenumber);		
		pstmt.setString(6,staffsection);		
		pstmt.setString(7,sort);	
		pstmt.setString(8,staffAddress);
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "���Ա����Ϣ�ɹ���");
	}catch(SQLException se){
		System.err.println("���ݿ�������ݳ���!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//ɾ��Ա����Ϣ����ְ��
public boolean DeleteStaff(String staffname){
	try{
		this.connect_Old();
		sql="DELETE FROM staff";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE name= '"+staffname+"'");
		JOptionPane.showMessageDialog(null, "ɾ��Ա����Ϣ��");
	}catch(SQLException se){
		System.err.println("���ݿ�ɾ�����ݳ���!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//�޸���ͨ�û�����
public boolean changePassword(String oldpass,String newpass,String new2pass){
	this.connect_Old();
	try{
		sql="SELECT * FROM user";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql+" WHERE userName = '"+zh+"'");
		if(rs.next()){	
			String n1=rs.getString("password");
			if(oldpass.equals(n1) && newpass.equals(new2pass)){
		//		System.out.println("sssssssss");
				sql="UPDATE user SET password=";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate(sql+newpass+" WHERE userName = '"+zh+"'");
				JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "��Ϣ�����������������룡");
			}	
		}
		return true;
	}catch(SQLException se){
		System.out.println("���ݿ��޸��������");
		System.out.println(se);
		return false;
	}	
  }
//�޸Ĺ���Ա����
public boolean changePassword2(String oldpass,String newpass,String new2pass){
	this.connect_Old();
	try{
		sql="SELECT * FROM admin";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql+" WHERE username = '"+zh+"'");
		if(rs.next()){	
			String n1=rs.getString("password");
			if(oldpass.equals(n1) && newpass.equals(new2pass)){
		//		System.out.println("sssssssss");
				sql="UPDATE admin SET password=";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate(sql+newpass+" WHERE username = '"+zh+"'");
				JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "��Ϣ�����������������룡");
			}	
		}
		return true;
	}catch(SQLException se){
		System.out.println("���ݿ��޸��������");
		System.out.println(se);
		return false;
	}	
}
}


	
	
