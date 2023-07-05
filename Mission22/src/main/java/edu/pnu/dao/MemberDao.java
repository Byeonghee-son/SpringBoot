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
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "scott";
	private String password = "tiger";

	private Connection con;

	public MemberDao() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));

			if(rs.next()) {
				Member m = Member.builder()
					.id(rs.getLong("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
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
	public List<Member> getMembers(){
		List<Member> list = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("Select * from member order by id"));
			
			while(rs.next()) {
				list.add(Member.builder()
						.id(rs.getLong("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build());
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int insertMember(Member member) {
		try {
			PreparedStatement psmt = con.prepareStatement("Insert into member(pass,name) values (?,?)");
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			
			int result = psmt.executeUpdate();
			psmt.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateMember(Member member) {
		try {
			PreparedStatement psmt = con.prepareStatement("Update member set pass =? , name = ? where id = ?");
			
			psmt.setString(1,member.getPass());
			psmt.setString(2,member.getName());
			psmt.setLong(3,member.getId());
			
			int result = psmt.executeUpdate();
			psmt.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteMember(Long id) {
		try {
			PreparedStatement psmt = con.prepareStatement("Delete from member where id = ?");
			psmt.setLong(1, id);
			
			int result = psmt.executeUpdate();
			psmt.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
