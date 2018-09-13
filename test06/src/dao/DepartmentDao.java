package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao extends BaseDao{
	public List<Department> search() {
		List<Department> list = new ArrayList<Department>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			rs = stat.executeQuery("select * from Department");

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public Department search(int id) {
		Department dep = new Department();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			rs = stat.executeQuery("select * from department where id="+id);

			if (rs.next()) {
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				dep.setEmpCount(rs.getInt("emp_count"));
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dep;
	}
	
	public int searchCount(Department condition) {
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

			if (condition.getEmpCount() != -1) {
				where += " and ifnull(emp_count,0)=" + condition.getEmpCount();
			}
			String sql = "select count(*) from Department" + where ;

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


	public List<Department> search(Department condition,int begin,int size) {
		List<Department> list = new ArrayList<Department>();
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

			if (condition.getEmpCount() != -1) {
				where += " and ifnull(emp_count,0)=" + condition.getEmpCount();
			}
			String sql = "select * from Department" + where + " limit "+begin+","+size;

			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public boolean add(Department dep) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			int rs = stat.executeUpdate("insert into Department(name)values('" + dep.getName() + "')");

			if (rs > 0) {
				flag = true;
			}
			stat.close();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		} /*
			 * finally { closeAll(conn, stat, null); }
			 */
		return flag;
	}

	public boolean update(Department dep) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = getConnection();

			String sql = "update Department set name =? where id=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());

			pstat.setInt(2, dep.getId());
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

	 public boolean delete(int id) {
	 boolean flag = false;
	 Connection conn = null;
	 conn = getConnection();
	 PreparedStatement pstat = null;
	 try {
	
	 conn = getConnection();
	 // 事务
	 conn.setAutoCommit(false);
	
	 String sql = "delete from Department where id=?";
	 pstat = conn.prepareStatement(sql);
	 pstat.setInt(1, id);
	 int rs = pstat.executeUpdate();
	
	 pstat.close();
	
	 sql = "update employee set d_id=null where d_id=?";
	 pstat = conn.prepareStatement(sql);
	 pstat.setInt(1, id);
	 rs = pstat.executeUpdate();
	
	 pstat.close();
	
	 sql = "delete from r_dep_pro where d_id=?";
	 pstat = conn.prepareStatement(sql);
	 pstat.setInt(1, id);
	 rs = pstat.executeUpdate();
	
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

			String sql = "delete from Department where id in (" + ids + ")";
			stat = conn.createStatement();
			int rs = stat.executeUpdate(sql);

			stat.close();

			sql = "update employee set d_id=null where d_id in (" + ids + ")";
			stat = conn.prepareStatement(sql);

			rs = stat.executeUpdate(sql);

			stat.close();

			sql = "delete from r_dep_pro where d_id in (" + ids + ")";
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
