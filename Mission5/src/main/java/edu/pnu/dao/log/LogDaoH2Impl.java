package edu.pnu.dao.log;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl implements LogDao {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void addLog(String method, String sqlstring, boolean success) {
		
		PreparedStatement psmt = null;
		try {
			psmt = dataSource.getConnection().prepareStatement("insert into dblog (method,sqlstring,success) values (?,?,?)");
			psmt.setString(1,  method);
			psmt.setString(2, sqlstring);
			psmt.setBoolean(3,  success);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
