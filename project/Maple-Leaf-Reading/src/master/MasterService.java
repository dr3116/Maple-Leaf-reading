package master;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Student;

import dbu.DBUtil;

public class MasterService {
	private List<Master> master;
	private DBUtil dbUtil;
	public MasterService() {
		master = new ArrayList<Master>();
		dbUtil = new DBUtil();
	}
	
	/**
	 * 获取所有用户信息
	 * @param str1 用户名 , str2 密码
	 * @return boolean   密码错误为0，密码正确为1
	 */
	
	public int getUser(String str1,String str2){
		int judge=0;
		try {
			//查询所有用户信息
			ResultSet rs = dbUtil.queryDate("select * from master where name='"+str1+"'");
			while(rs.next()) {
				String password = rs.getString("password");
				System.out.println("管理员登录 真实密码"+password);
				if(password!=null && !password.equals("")) {
					if(password.equals(str2)) {
						judge=1;
					}					
				}
			}
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return judge;
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
		String sql = "delete from user where user_id ="+userId;
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
}
