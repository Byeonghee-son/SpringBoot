package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {
	
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/telephone";
	private String username = "soctt";
	private String password = "tiger";
	private Connection con;
	
	//Database Connection 설정
	public MemberDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Member m) {
		try {
			PreparedStatement psmt = con.prepareStatement("Insert into member (name,age,nickname) values(?,?,?)");
			psmt.setString(1, m.getName());
			psmt.setInt(2, m.getAge());
			psmt.setString(3, m.getNickname());
			
			int result = psmt.executeUpdate();
			psmt.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public List<Member> getMembers(){
		List<Member> list = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from member order by id"));
			
			while(rs.next()) {
				list.add(Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname"))
						.build());
					}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d",id));
			
			if(rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.nickname(rs.getString("nickname"))
						.build();
				rs.close();
				stmt.close();
				return m;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
