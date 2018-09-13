package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;

public class ProjectDao extends BaseDao {

	public List<Project> search() {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			stat = conn.createStatement();

			rs = stat.executeQuery("select * from Project");

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}
	
	public Project search(int id) {
		Project pro = new Project();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			rs = stat.executeQuery("select * from project where id="+id);

			if (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pro;
	}
	
	public int searchCount(Project condition) {
		int count = 0;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();

			String where = " where 1=1";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}

			
			String sql = "select count(*) from project" + where ;

			rs = stat.executeQuery(sql);

			while (rs.next()) {
			
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return count;
	}
	
	public List<Project> search(Project condition,int begin,int size) {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();

			String where = " where 1=1";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}

			String sql = "select * from project" + where + " limit "+begin+","+size;

			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				
				list.add(pro);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

//	public List<Project> searchByCondintion(Project condition) {
//		List<Project> list = new ArrayList<Project>();
//		Connection conn = null;
//		Statement stat = null;
//		ResultSet rs = null;
//		try {
//			conn = getConnection();
//			stat = conn.createStatement();
//
//			String where = " where 1=1";
//			if (!condition.getName().equals("")) {
//				where += " and name='" + condition.getName() + "'";
//			}
//
//			String sql = "select * from Project" + where;
//
//			rs = stat.executeQuery(sql);
//
//			while (rs.next()) {
//				Project pro = new Project();
//				pro.setId(rs.getInt("id"));
//				pro.setName(rs.getString("name"));
//
//				list.add(pro);
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			closeAll(conn, stat, rs);
//		}
//
//		return list;
//	}

	public boolean add(Project pro) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			int rs = stat.executeUpdate("insert into Project(name)values('" + pro.getName() + "')");

			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}

		return flag;
	}

	public boolean update(Project pro) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = getConnection();

			String sql = "update Project set name =? where id=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pro.getName());

			pstat.setInt(2, pro.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}

	public boolean delete(String ids) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		try {

			conn = getConnection();

			conn.setAutoCommit(false);

			String sql = "delete from Project where id in (" + ids + ")";
			stat = conn.createStatement();
			int rs = stat.executeUpdate(sql);

			stat.close();

			// sql = "update employee set d_id=null where d_id in (" + ids + ")";
			sql = "delete from r_dep_pro where p_id in (" + ids + ")";
			stat = conn.prepareStatement(sql);

			rs = stat.executeUpdate(sql);

			conn.commit();
			// conn.setAutoCommit(true);

			if (rs > 0) {
				flag = true;

			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

}
