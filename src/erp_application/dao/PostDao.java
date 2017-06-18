package erp_application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_application.dto.Address;
import erp_application.jdbc.DBCon;

public class PostDao {
	private static final PostDao instance = new PostDao();
	
	private PostDao(){}

	public static PostDao getInstance() {
		return instance;
	}
	
	public List<Address> selectZipCodeByDoro(String doro){
		List<Address> addresses = new ArrayList<>();
		String sql = "select zipcode, sido, sigungu, doro, building1, building2 from post where doro =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DBCon.getConnection().prepareStatement(sql);
			pstmt.setString(1, doro);
			rs = pstmt.executeQuery();
			while(rs.next()){
				addresses.add(getAddress(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addresses;
	}

	private Address getAddress(ResultSet rs) throws SQLException {
		String zipCode = rs.getString(1);
		String sido = rs.getString(2);
		String sigungu = rs.getString(3);
		String doro = rs.getString(4);
		String build1 = rs.getString(5);
		String build2 = rs.getString(6);
		return new Address(zipCode, sido, sigungu, doro, build1, build2);
	}
}
