package publichouse;

import java.sql.*;  

//import java.sql.Date;
import javax.swing.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector; 
import java.util.Date; 
  
public class Function{
	
	//获得提交的用户名和密码  
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


	// 我们把构造函数设置成私有，然后统一从getSelf获取对象
	// 这样能保证此对象的唯一性
	// 但是为了兼容还没修改过的代码，所以暂时不改成私有
	// TODO 之后要改成私有
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
			
			// 直接连接
			if(!self.loadDatabaseDriver()){
				
				// 加载驱动失败
				self = null;
				
			} else if(!self.connect()){
				// 连接失败
				self = null;
			}
		}
		
		return self;
	}
	
	//连接数据库
	private boolean loadDatabaseDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("加载数据库驱动失败！");
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
			System.err.println("数据库连接出错！");
			System.err.println(e);
			return false;
		}
	}
	
	
	// 应该把加载和连接都放在内部完成比较好
	@Deprecated
	public boolean loadDataBaseDriver_Old() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("加载数据库驱动失败！");
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
			System.err.println("数据库连接出错！");
			System.err.println(e);
			return false;
		}
	}
	
	// 用户登录
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
	
		return false;
	}

	//录入房间信息
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
			pstmt.setString(3,"空房");	
			pstmt.executeUpdate();
		}catch(SQLException se){
			System.err.println("数据库录入数据出错！");
			System.err.println(se);
			JOptionPane.showMessageDialog(null, "房号重复！请重新输入房号！");
			return -1;
		}
		return 0; 
	}

	//修改房间信息
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
		System.err.println("数据库修改数据出错!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}

//删除房间信息
public boolean DeleteRoom(String RoomNo){
	try{
		this.connect_Old();
		sql="DELETE FROM rooms";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		sql="DELETE FROM usecondition";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
		JOptionPane.showMessageDialog(null, "删除成功！");
	}catch(SQLException se){
		System.err.println("数据库删除数据出错!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//获取当前时间
public String getTime(){
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式 

	String s = dateFormat.format(now); 
	//System.out.println(s); 
	return s;
 }
//添加客户
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
		JOptionPane.showMessageDialog(null, "添加客户信息成功！");
	}catch(SQLException se){
		System.err.println("数据库添加数据出错!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//删除客户信息（退房）
public boolean DeleteClient(String Number){
	try{
		this.connect_Old();
		sql="DELETE FROM clientinformation";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomNo= '"+Number+"'");
		
		sql="UPDATE usecondition SET Rcondition='"+"空房"+"'";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE RoomID= '"+Number+"'");
		JOptionPane.showMessageDialog(null, "退房成功！");
	}catch(SQLException se){
		System.err.println("数据库删除数据出错!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}

	//订房 客户名称 房间类型 电话 备注？？
	public boolean BookRoom(String clientname,String sort,String phone,String remark){
		try{
			// 只需要连接一次就可以了
			this.connect_Old();
			String roomcondition=null;
			String Rsort=null;
			ResultSet rs=null;
	
			//录入过程 修改房间状态表的过程  空房变为预订
			Statement sql1=conn.createStatement();		
			rs=sql1.executeQuery("SELECT * FROM usecondition WHERE Rcondition LIKE '%空房%' ");
			while(rs.next()){
				roomcondition=rs.getString(1);
			}		
			// 有空房的情况
			if(roomcondition!=null){   
				// 指定类型
				String kongfang="空房";
				rs=sql1.executeQuery("SELECT * FROM usecondition WHERE Sort LIKE '%"+sort+"%' And Rcondition='"+kongfang+"'");
				while(rs.next()){
					Rsort=rs.getString(1);
				}			
				if(Rsort!=null){      //有想要的类型房
					sql="insert into Booked(ClientName,Sort,PhoneNumber,remark) values(?,?,?,?)";      //插入订房信息
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,clientname);		
					pstmt.setString(2,sort);		
					pstmt.setString(3,phone);
					pstmt.setString(4,remark);
					pstmt.executeUpdate();
	
					String yuding="预订";
					Statement sql2=conn.createStatement();
					rs=sql2.executeQuery("select RoomID from usecondition where Sort='"+sort+"' And Rcondition='"+kongfang+"'");                               //选择出空房的房间获取房号
					int []num=new int[50];//将空房放入一个数组
					
					for(int i = 0; i < num.length; i++){
						while(rs.next()){
							num[i] = rs.getInt(1);
							//System.out.println(num[i]);
						} 
					}
					
					sql="UPDATE usecondition SET Rcondition='"+yuding+"'";
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate(sql+" WHERE RoomID= '"+num[0]+"'");//将第一个空房的状态置为预订
					JOptionPane.showMessageDialog(null, "预订房间成功！");	
//					pstmt.executeUpdate(sql+" WHERE RoomID= '"+RoomNo+"'");
				}
				else{
					JOptionPane.showMessageDialog(null, "该类型客房已满！");	
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "房间已满！");			
			}	
		}catch(SQLException se){
			System.err.println("数据库添加数据出错!");
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
		JOptionPane.showMessageDialog(null, "添加员工信息成功！");
	}catch(SQLException se){
		System.err.println("数据库添加数据出错!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//删除员工信息（离职）
public boolean DeleteStaff(String staffname){
	try{
		this.connect_Old();
		sql="DELETE FROM staff";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate(sql+" WHERE name= '"+staffname+"'");
		JOptionPane.showMessageDialog(null, "删除员工信息！");
	}catch(SQLException se){
		System.err.println("数据库删除数据出错!");
		System.err.println(se);
		//return -1;
	}
	return false;
	}
//修改普通用户密码
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
				JOptionPane.showMessageDialog(null, "密码修改成功！");
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "信息输入有误，请重新输入！");
			}	
		}
		return true;
	}catch(SQLException se){
		System.out.println("数据库修改密码出错！");
		System.out.println(se);
		return false;
	}	
  }
//修改管理员密码
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
				JOptionPane.showMessageDialog(null, "密码修改成功！");
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "信息输入有误，请重新输入！");
			}	
		}
		return true;
	}catch(SQLException se){
		System.out.println("数据库修改密码出错！");
		System.out.println(se);
		return false;
	}	
}
}


	
	
