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
}
