package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.glass.ui.Size;

import entity.Department;
import entity.Employee;

public class DepartmentDao2 extends BaseDao {

	public List<Department> search() {
		List<Department> list = new ArrayList<Department>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			String sql = "select e.*,d.name as d_name,emp_count from employee as e left join "
					+ " department as d on e.d_id=d.id";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Department dep = new Department();
				//emp.setId(rs.getInt("id"));
				//emp.setName(rs.getString("name"));
				//emp.setSex(rs.getString("sex"));
				//emp.setAge(rs.getInt("age"));
				
				 dep.setId(rs.getInt("d_id"));
				 dep.setName(rs.getString("d_name"));
				 dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
				list.add(dep);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public int searchCount() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();

			stat = conn.createStatement();

			String sql = "select count(*) from department";
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	public int searchCount(Department condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();

			stat = conn.createStatement();

			String where = " where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			
			if (condition.getEmpCount() != -1) {
				where += " and emp_count=" + condition.getEmpCount();
			}
			// if (condition.getDep().getId() != -1) {
			// where += " and d_id=" + condition.getDep().getId();
			// }
			// String sql = "select e.*,d.name as d_name,emp_count from employee as e left
			// join department as d on e.d_id=d.id"
			// + where;

			String sql = "select count(*) from department" + where;
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	public List<Department> search(int begin, int size) {
		List<Department> list = new ArrayList<Department>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			String sql = "select * from Department limit " + begin + "," + size;

			rs = stat.executeQuery(sql);

			while (rs.next()) {
				//Employee emp = new Employee();
				//emp.setId(rs.getInt("id"));
				//emp.setName(rs.getString("name"));
				//emp.setSex(rs.getString("sex"));
				//emp.setAge(rs.getInt("age"));
				 Department dep = new Department();
				 dep.setId(rs.getInt("id"));
				 dep.setName(rs.getString("name"));
				 dep.setEmpCount(rs.getInt("emp_count"));
				// emp.setDep(dep);
				list.add(dep);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public List<Department> search(Department condition, int begin, int size) {
		List<Department> list = new ArrayList<Department>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();

			String where = " where 1=1 ";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			
			if (condition.getEmpCount() != -1) {
				where += " and emp_count=" + condition.getEmpCount();
			}
			// if (condition.getDep().getId() != -1) {
			// where += " and d_id=" + condition.getDep().getId();
			// }
			// String sql = "select e.*,d.name as d_name,emp_count from employee as e left
			// join department as d on e.d_id=d.id"
			// + where;
			String sql = "select * from department" + where + " limit " + begin + "," + size;
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
//				Employee emp = new Employee();
//				emp.setId(rs.getInt("id"));
//				emp.setName(rs.getString("name"));
//				emp.setSex(rs.getString("sex"));
//				emp.setAge(rs.getInt("age"));
				 Department dep = new Department();
				 dep.setId(rs.getInt("id"));
				 dep.setName(rs.getString("name"));
				 dep.setEmpCount(rs.getInt("emp_count"));
				 //emp.setDep(dep);

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

	public Department searchById(int id) {
		Department dep = new Department();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id)values('"
			// + emp.getName() + "','"
			// + emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");
			stat = conn.createStatement();
			String sql = "select * from department where id=" + id;

			rs = stat.executeQuery(sql);
			while (rs.next()) {

				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				
				dep.setEmpCount(rs.getInt("emp_count"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return dep;
	}

	public List<Department> searchById(String ids) {
		List<Department> list = new ArrayList<>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from department where id in(" + ids + ")";

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Department dep) {

		Connection conn = null;
		PreparedStatement pstat = null;
		int rs = 0;

		try {
			conn = getConnection();

			// int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id)values('"
			// + emp.getName() + "','"
			// + emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");

			String sql = "insert into department(name)values(?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());
			

			rs = pstat.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return rs > 0;
	}

	public boolean update(Department dep) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = getConnection();

			String sql = "update department set name = ? where id=?";
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
		PreparedStatement pstat = null;
		try {

			conn = getConnection();

			String sql = "delete from department where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setInt(1, id);
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

	// public boolean delete(String ids) {
	// boolean flag = false;
	// Connection conn = null;
	// Statement stat = null;
	// try {
	//
	// conn = getConnection();
	//
	// String sql = "delete from employee where id in (" + ids + ")";
	// stat = conn.createStatement();
	//
	// int rs = stat.executeUpdate(sql);
	// if (rs > 0) {
	// flag = true;
	//
	// }
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	//
	// } finally {
	// closeAll(conn, stat, null);
	// }
	// return flag;
	// }

	public boolean deleteBatch(String ids) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		try {

			conn = getConnection();

			String sql = "delete from department where id in (" + ids + ")";
			stat = conn.createStatement();
			

			int rs = stat.executeUpdate(sql);
			if (rs > 0) {
				flag = true;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

	

}
