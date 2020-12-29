package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.example.entity.PingLun;
import com.example.entity.Student;

import dbu.DBUtil;

public class StudentService {
	private List<Student> users;
	//private List<PingLun> pingluns;
	private DBUtil dbUtil;
	public StudentService() {
		//pingluns=new ArrayList<>();
		users = new ArrayList<Student>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有用户信息
	 * @param sql 查询用户信息的sql语句
	 * @return 用户信息集合
	 */
	public List<Student> getUsers(String sql){
		try {
			//查询所有用户信息
			ResultSet rs = dbUtil.queryDate(sql);
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String userPassword = rs.getString("user_password");
				String userPhoto = rs.getString("user_photo");
				int userNum=rs.getInt("sign_num");
				Student user = createUser(userId,userName,userPassword,userPhoto,userNum);
				users.add(user);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	/**
	 * 根据用户信息构造用户对象
	 */
	private Student createUser(int userId,String userName,String userPassword,String userPhoto,int userNum) {
		Student user = new Student();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserPhoto(userPhoto);
		user.setNum(userNum);
		return user;
	}
	/**
	 * 添加用户
	 * @param user 待添加的用户对象
	 * @return 添加是否成功，成功返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean addUser(Student user) throws ClassNotFoundException, SQLException {
		String sql = "insert into user(user_id,user_name,user_password,user_photo,sign_num)values("+
				user.getUserId()+","+"'"+user.getUserName()+"'"+","+"'"+user.getUserPassword()+"'"+","+"'"+user.getUserPhoto()+"'"+",'"+user.getNum()+"')";
		System.out.println("用户增加的sql="+sql);
		dbUtil.addDataToTable(sql);
		if(dbUtil.isExist("select * from user where user_id = "+user.getUserId())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 根据用户编号删除指定用户
	 * @param userId 用户编号
	 * @return 是否删除成功，成功删除返回true，否则返回false
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean deleteUserById(int userId) throws ClassNotFoundException, SQLException {
		//删除user表
		String sql = "delete from user where user_id ="+userId;
		dbUtil.updateData(sql);
		//删除书评表
		sql = "delete from book_review where user_id ="+userId;
		dbUtil.updateData(sql);
		//删除书架
		sql = "delete from bookshelf where user_id ="+userId;
		dbUtil.updateData(sql);
		//删除粉丝
		sql = "delete from fans where user_id ="+userId;
		dbUtil.updateData(sql);
		sql = "delete from fans where people_concerned_id ="+userId;
		dbUtil.updateData(sql);
		//删除搜索记录
		sql = "delete from recent_reading where user_id ="+userId;
		dbUtil.updateData(sql);
		//删除最近阅读
		sql = "delete from searchrecords where user_id ="+userId;
		dbUtil.updateData(sql);		
		
		if(dbUtil.isExist("select * from user where user_id ="+userId)) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 *  修改用户数据信息
	 *  @param Student
	 *  @return 是否修改成功，成功修改返回true，否则返回false
	 *  @throws SQLException 
	 *  @throws ClassNotFoundException 
	 * revise
	 */
	public boolean reviseUser(int userId,String userName,String userPassword,String userPhoto,int userNum) throws ClassNotFoundException, SQLException {
		String sql = "update user set user_id="+userId+",user_name="+"'"+userName+"'"+",user_password="+"'"+userPassword+"'"+",user_photo="+"'"+userPhoto+"',sign_num='"+userNum+"'";
		dbUtil.updateData(sql);
		if(dbUtil.isExist("select * from user where user_id ="+userId)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	//用于booklist1，查询所有图书数量
	int studentsNumber=1;	
	public int getStudentsNumber(){
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate("select count(*) from user");
			while(rs.next()) {
				studentsNumber = rs.getInt(1);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return  studentsNumber;
	}
	
	
	public int findUser(String userName) throws ClassNotFoundException, SQLException {
		ResultSet rs = dbUtil.queryDate("select count(*) from user where user_name='"+userName+"'");
		if(rs.next()) {
			return rs.getInt(1);
		}		
		return 0;
	}
	
	
	public int changeImageByName(String image,String userName) throws ClassNotFoundException, SQLException {
		String sql="update user set user_photo='"+image+"' where user_name='"+userName+"'";
		//update user set user_photo='88.png' where user_name='88'
		System.out.println(sql);
		Boolean rs = dbUtil.updateData(sql);

		return 0;
	}
	
	public int changeMessage(String id,String name,String password) throws ClassNotFoundException, SQLException {
		String sql="update user set user_name='"+name+"',user_password='"+password+"' where user_id='"+id+"'";
		System.out.println("changeMessage的sql"+sql);
		Boolean rs = dbUtil.updateData(sql);
		if(rs==true) {
			return 0;
		}
		return 1;
	} 
	
	
	public int getPingLunsNumber(){
		try {
			//查询所有图书
			ResultSet rs = dbUtil.queryDate("select count(*) from book_review");
			while(rs.next()) {
				studentsNumber = rs.getInt(1);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return  studentsNumber;
	}
	
	
	
	public boolean deletePingLunById(int userId) throws ClassNotFoundException, SQLException {
		String sql = "delete from book_review where book_review_id ="+userId;
		dbUtil.updateData(sql);		
		if(dbUtil.isExist("select * from book_review where book_review_id ="+userId)) {
			return false;
		}else {
			return true;
		}
	}
	
/**	
	
	public List<PingLun> getPingLuns(String sql){
		try {
			//查询所有用户信息
			ResultSet rs = dbUtil.queryDate(sql);
			while(rs.next()) {
				String bookName=rs.getString("book_name");
				String bookImage=rs.getString("book_photo");
				String userName=rs.getString("user_name");
				String userImage=rs.getString("user_photo");
				String content=rs.getString("content");
				String score=rs.getDouble("score")+"";
				String id=rs.getString("book_review_id")+"";
				//System.out.println(bookName+"+"+bookImage+"+"+userName+"+"+userImage+"+"+content+"+"+score+"+"+id);
				PingLun pinglun=createPingLun(bookName,bookImage,userName,userImage,content,score,id);
				pingluns.add(pinglun);
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return pingluns;
	}
	
	
	
	private PingLun createPingLun(String bookName,String bookImage,String userName,String userImage,String content,String score,String id) {
		PingLun pinglun=new PingLun();
		pinglun.setBookImage(bookImage);
		pinglun.setBookName(bookName);
		pinglun.setContent(content);
		pinglun.setScore(score);
		pinglun.setUserImage(userImage);
		pinglun.setUserName(userName);
		pinglun.setId(id);
		
		return pinglun;
	}
	
	
**/
	
	
}
