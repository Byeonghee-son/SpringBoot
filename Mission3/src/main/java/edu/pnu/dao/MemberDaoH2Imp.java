package edu.pnu.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Imp implements MemberInterface {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission3";
	private String username = "scott";
	private String password = "tiger";
	
	private Connection con;
	
	public MemberDaoH2Imp() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("Select * from member"));
			
			while(rs.next()) {
				list.add(MemberVO.builder()
								.id(rs.getInt("id"))
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

	@Override
	public MemberVO getMember(Integer id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("Select * from member where id = %d", id));
			if(rs.next()) {
				MemberVO m = MemberVO.builder()
								.id(rs.getInt("id"))
								.pass(rs.getString("pass"))
								.name(rs.getString("name"))
								.regidate(rs.getDate("regidate"))
								.build();
				rs.close();
				stmt.close();
				return m;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addMember(MemberVO member) {
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

	@Override
	public int updateMember(MemberVO member) {
		try{
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

	@Override
	public int deleteMember(Integer id) {
		try {
			PreparedStatement psmt = con.prepareStatement("Delete from member where id = ?");
			psmt.setInt(1, id);
			
			int result = psmt.executeUpdate();
			psmt.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
