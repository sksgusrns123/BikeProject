package ProJect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch21_jdbc.DB;

public class BikeDAO {
	//추가
	public int insertBike(BikeDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbConn();
			String sql="insert into bike values (?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNum());
			pstmt.setString(2, dto.getBranch());
			pstmt.setString(3, dto.getPrname());
			pstmt.setString(4, dto.getCompany());
			pstmt.setString(5, dto.getWhdate());
			pstmt.setInt(6, dto.getMoney());
			pstmt.setInt(7, dto.getAmount());
			pstmt.setInt(8, dto.getTot());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}//insertBike()
	//삭제
	public int deleteBike(String company) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbConn();
			String sql="delete from bike where company=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, company);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}//deleteBike()
	//수정
	public int updateBike(BikeDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbConn();
					String sql="UPDATE bike"
							+ " SET num=?, branch=?, prname=?, company=?, whdate=?, money=?, amount=? "
							+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNum());
			pstmt.setString(2, dto.getBranch());
			pstmt.setString(3, dto.getPrname());
			pstmt.setString(4, dto.getCompany());
			pstmt.setString(5, dto.getWhdate());
			pstmt.setInt(6, dto.getMoney());
			pstmt.setInt(7, dto.getAmount());
			pstmt.setString(8, dto.getNum());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}//updateBike()
	//검색
	public Vector searchBike(String company) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbConn();
			String sql="select num,branch,prname,company,whdate,money,amount,(money*amount) tot"
					+ " from bike where company like ? order by company";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+company+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("branch"));
				row.add(rs.getString("prname"));
				row.add(rs.getString("company"));
				row.add(rs.getString("whdate"));
				row.add(rs.getInt("money"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("tot"));
				items.add(row);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;//백터 리턴	
	}//searchBike()
	//목록 보기
	public Vector listBike() {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbConn();
			String sql="select num,branch,prname,company,whdate,money,amount,(money*amount) tot"
					+ " from bike order by prname";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("branch"));
				row.add(rs.getString("prname"));
				row.add(rs.getString("company"));
				row.add(rs.getString("whdate"));
				row.add(rs.getInt("money"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("tot"));
				items.add(row);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return items;
	}
}
