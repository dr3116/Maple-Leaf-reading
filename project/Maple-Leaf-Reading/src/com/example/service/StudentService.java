package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Student;

import dbu.DBUtil;

public class StudentService {
	private List<Student> users;
	private DBUtil dbUtil;
	public StudentService() {
		users = new ArrayList<Student>();
		dbUtil = new DBUtil();
	}
	/**
	 * 获取所有用户信息
	 * @param sql 查询用户信息的sql语句
	 * @return 用户信息集合
	 */
	public List<Student> gerUsers(String sql){
		try {
			//查询所有用户信息
			ResultSet rs = dbUtil.queryDate(sql);
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String userPassword = rs.getString("user_password");
				String userPhoto = rs.getString("user_photo");
				Date accumulatedReadingTime = rs.getDate("accumulated_reading_time");
				int continuousSignInDays = rs.getInt("continuous_sign_in_days");
				int cumulativeSignInDays = rs.getInt("cumulative_sign_in_days");
				Date lastCheckInTime = rs.getDate("last_check_in_time");
				Date timeOfJoiningTheOrganization = rs.getDate("time_of_joining_the_organization");
				Date readingTimeOfThisMonth = rs.getDate("reading_time_of_this_month");
				Date readingTimeToday = rs.getDate("reading_time_today");
				Date lastReadingTime = rs.getDate("last_reading_time");
				Student user = createUser(userId,userName,userPassword,userPhoto,accumulatedReadingTime,continuousSignInDays,cumulativeSignInDays,lastCheckInTime,timeOfJoiningTheOrganization,readingTimeOfThisMonth,readingTimeToday,lastReadingTime);
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
	private Student createUser(int userId,String userName,String userPassword,String userPhoto,Date accumulatedReadingTime,int continuousSignInDays,int cumulativeSignInDays,Date lastCheckInTime,Date timeOfJoiningTheOrganization,Date readingTimeOfThisMonth,Date readingTimeToday,Date lastReadingTime) {
		Student user = new Student();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserPhoto(userPhoto);
		user.setAccumulatedReadingTime(accumulatedReadingTime);
		user.setContinuousSignInDays(continuousSignInDays);
		user.setCumulativeSignInDays(cumulativeSignInDays);
		user.setLastCheckInTime(lastCheckInTime);
		user.setTimeOfJoiningTheOrganization(timeOfJoiningTheOrganization);
		user.setReadingTimeOfThisMonth(readingTimeOfThisMonth);
		user.setReadingTimeToday(readingTimeToday);
		user.setLastReadingTime(lastReadingTime);
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
		String sql = "insert into user(user_id,user_name,user_password,user_photo,accumulated_reading_time,continuous_sign_in_days,cumulative_sign_in_days,last_check_in_time,time_of_joining_the_organization,reading_time_of_this_month,reading_time_today,last_reading_time)values("+
				user.getUserId()+","+"'"+user.getUserName()+"'"+","+"'"+user.getUserPassword()+"'"+","+"'"+user.getUserPhoto()+"'"+","+"'"+user.getAccumulatedReadingTime()+"'"+","+user.getContinuousSignInDays()+","+user.getCumulativeSignInDays()+","+"'"+user.getLastCheckInTime()+"'"+
				","+"'"+user.getTimeOfJoiningTheOrganization()+"'"+","+"'"+user.getReadingTimeOfThisMonth()+"'"+","+"'"+user.getReadingTimeToday()+"'"+","+"'"+user.getLastReadingTime()+"'"+")";
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
		//删除评论
		sql = "delete from comments where user_id ="+userId;
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
	public boolean reviseUser(int userId,String userName,String userPassword,String userPhoto,Date accumulatedReadingTime,int continuousSignInDays,int cumulativeSignInDays,Date lastCheckInTime,Date timeOfJoiningTheOrganization,Date readingTimeOfThisMonth,Date readingTimeToday,Date lastReadingTime) throws ClassNotFoundException, SQLException {
		String sql = "update user set user_id="+userId+",user_name="+"'"+userName+"'"+",user_password="+"'"+userPassword+"'"+",user_photo="+"'"+userPhoto+"'"+",accumulated_reading_time="+"'"+accumulatedReadingTime+"'"+",continuous_sign_in_days="+continuousSignInDays+",cumulative_sign_in_days="+cumulativeSignInDays+
				",last_check_in_time="+"'"+lastCheckInTime+"'"+",time_of_joining_the_organization="+"'"+timeOfJoiningTheOrganization+"'"+",reading_time_of_this_month="+"'"+readingTimeOfThisMonth+"'"+",reading_time_today="+"'"+readingTimeToday+"'"+",last_reading_time="+"'"+lastReadingTime+"'";
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
//		System.out.println("图书总数为"+booksNumber);
		return  studentsNumber;
	}
	
	
	
	
	
	
	
	
	
	
}
