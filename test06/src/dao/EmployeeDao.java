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

public class EmployeeDao extends BaseDao {

	public List<Employee> search() {
		List<Employee> list = new ArrayList<Employee>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			String sql = "select * from employee";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				 Department dep = new Department();
				 dep.setId(rs.getInt("d_id"));
				 dep.setName(rs.getString("d_name"));
				 dep.setEmpCount(rs.getInt("emp_count"));
				 emp.setDep(dep);
				list.add(emp);
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

			String sql = "select count(*) from employee";
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

	public int searchCount(Employee condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();

			stat = conn.createStatement();

			String where = " where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and e.name='" + condition.getName() + "'";
			}
			if (condition.getSex() != null && !condition.getSex().equals("")) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge();
			}
			 if (condition.getDep()!=null&&condition.getDep().getId() != -1) {
			 where += " and d_id=" + condition.getDep().getId();
			 }
			// String sql = "select e.*,d.name as d_name,emp_count from employee as e left
			// join department as d on e.d_id=d.id"
			// + where;

			String sql = "select count(*) from employee as e left join department as d on e.d_id=d.id" + where;
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

	public List<Employee> search(int begin, int size) {
		List<Employee> list = new ArrayList<Employee>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			stat = conn.createStatement();

			String sql = "select * from employee limit " + begin + "," + size;

			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				 Department dep = new Department();
				 dep.setId(rs.getInt("d_id"));
				 dep.setName(rs.getString("d_name"));
				 dep.setEmpCount(rs.getInt("emp_count"));
				 emp.setDep(dep);
				list.add(emp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public List<Employee> search(Employee condition, int begin, int size) {
		List<Employee> list = new ArrayList<Employee>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();

			String where = " where 1=1 ";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and e.name='" + condition.getName() + "'";
			}
			if (condition.getSex() != null && !condition.getSex().equals("")) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge();
			}
			 if (condition.getDep()!=null&&condition.getDep().getId() != -1) {
				 where += " and d_id=" + condition.getDep().getId();
				 }
			// String sql = "select e.*,d.name as d_name,emp_count from employee as e left
			// join department as d on e.d_id=d.id"
			// + where;
			String sql = "select e.*,d.name as d_name from employee as e left join department as d on e.d_id=d.id" + where + " limit " + begin + "," + size;
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoto(rs.getString("photo"));
				 Department dep = new Department();
				 dep.setId(rs.getInt("d_id"));
				 dep.setName(rs.getString("d_name"));
				 
				 emp.setDep(dep);

				list.add(emp);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public Employee searchById(int id) {
		Employee emp = new Employee();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id)values('"
			// + emp.getName() + "','"
			// + emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");
			stat = conn.createStatement();
			String sql = "select e.*,d.name as d_name from employee as e left join department as d on d.id=e.d_id  where e.id=" + id;

			rs = stat.executeQuery(sql);
			while (rs.next()) {

				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));

				Department dep = new Department();
				 dep.setId(rs.getInt("d_id"));
				 dep.setName(rs.getString("d_name"));
				 emp.setDep(dep);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return emp;
	}

	public List<Employee> searchById(String ids) {
		List<Employee> list = new ArrayList<>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from employee where id in(" + ids + ")";

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Employee emp) {

		Connection conn = null;
		PreparedStatement pstat = null;
		int rs = 0;

		try {
			conn = getConnection();

			// int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id)values('"
			// + emp.getName() + "','"
			// + emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");

			String sql = "insert into employee(name,sex,age,d_id,photo)values(?,?,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setObject(4, emp.getDep().getId());
			pstat.setString(5, emp.getPhoto());

			rs = pstat.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return rs > 0;
	}

	public boolean update(Employee emp) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = getConnection();

			String sql = "update employee set name = ?,sex=?,age=?,d_id=? where id=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setObject(4, emp.getDep().getId());
			pstat.setInt(5, emp.getId());
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

	public boolean updateBatch1(String ids, Employee emp) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = getConnection();

			String sql = "update employee set name = ?,sex=?,age=? where id in(" + ids + ")";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			// pstat.setInt(4, emp.getDep().getId());

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

			String sql = "delete from employee where id=?";
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

			String sql = "delete from employee where id in (" + ids + ")";
			stat = conn.createStatement();
			System.out.println(sql);

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

	public boolean updateBatch2(List<Employee> list) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {

			conn = getConnection();
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				String sql = "update employee set name = ?,sex=?,age=? where id =?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, emp.getName());
				pstat.setString(2, emp.getSex());
				pstat.setInt(3, emp.getAge());
				pstat.setInt(4, emp.getId());
				// pstat.setInt(4, emp.getDep().getId());

				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;

	}

}
