package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import servlet.projectServlet;
import util.Grade;

public class ScoreDao extends BaseDao {

	public List<Score> search() {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			stat = conn.createStatement();
			// String sql = "select e.name as e_name,d.name as d_name,p.name as
			// p_name,s.id,s.value,s.grade from employee as e LEFT JOIN department as d on
			// d.id=e.d_id LEFT JOIN score as s on e.id=s.e_id LEFT JOIN project as p on
			// p.id=s.p_id";
			String sql = "select * from v_emp_score";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getInt("sc_id"));

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));

				emp.setDep(dep);
				score.setEmp(emp);

				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				score.setPro(pro);

				score.setValue((Integer) rs.getObject("value"));
				 Grade g = Grade.getGrade(rs.getString("grade"));
				 score.setGrade(g);
				list.add(score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
	public Score search(int id) {
		Score score = new Score();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			stat = conn.createStatement();
			
			String sql = "select * from v_emp_score where sc_id="+id;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				
				score.setId(rs.getInt("sc_id"));

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));

				emp.setDep(dep);
				score.setEmp(emp);

				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				score.setPro(pro);

				score.setValue((Integer) rs.getObject("value"));
				 Grade g = Grade.getGrade(rs.getString("grade"));
				 score.setGrade(g);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, stat, rs);
		}
		return score;

	}

//	public void save(Set<Score> set) {
//		for (Score sc : set) {
//			if (sc.getId() == 0) {
//				add(sc);
//			} else {
//				update(sc);
//			}
//		}
//	}

	public int add(Score sc) {

		int rs = 0;
		int id = 0;
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = getConnection();
			String sql = "insert into score(e_id,p_id,value) values(?,?,?)";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getEmp().getId());
			pstat.setInt(2, sc.getPro().getId());
			pstat.setInt(3, sc.getValue());
			rs = pstat.executeUpdate();
			pstat.close();
			 sql = "select last_insert_id()";
			 pstat = conn.prepareStatement(sql);
			ResultSet r = pstat.executeQuery();
			if (r.next()) {
				id=r.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return id;
	}

	public boolean update(Score sc) {

		int rs = 0;
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = getConnection();
			String sql = "update score set value=? where id=?";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getValue());
			pstat.setInt(2, sc.getId());
			rs = pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return rs > 0;
	}
	
	public int searchCount(Score condition) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();

			stat = conn.createStatement();

			String where = " where 1=1";
			if (condition.getEmp().getName() != null && !condition.getEmp().getName().equals("")) {
				where += " and e_name='" + condition.getEmp().getName() + "'";
			}
			
			 if (condition.getEmp().getDep()!=null&&condition.getEmp().getDep().getId() != -1) {
			 where += " and d_id=" + condition.getEmp().getDep().getId();
			 }
			 if (condition.getPro()!=null&&condition.getPro().getId() != -1) {
				 where += " and p_id=" + condition.getPro().getId();
				 }
			

			String sql = "select count(*) from v_emp_score " + where;
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
	
	public List<Score> search(Score condition, int begin, int size) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();

			String where = " where 1=1 ";
			if (condition.getEmp().getName() != null && !condition.getEmp().getName().equals("")) {
				where += " and e_name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getEmp().getDep()!=null&&condition.getEmp().getDep().getId() != -1) {
				 where += " and d_id=" + condition.getEmp().getDep().getId();
				 }
				 if (condition.getPro()!=null&&condition.getPro().getId() != -1) {
					 where += " and p_id=" + condition.getPro().getId();
					 }
			
			// String sql = "select e.*,d.name as d_name,emp_count from employee as e left
			// join department as d on e.d_id=d.id"
			// + where;
			String sql = "select * from v_emp_score" + where + " limit " + begin + "," + size;
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				
				 Department dep = new Department();
				 dep.setId(rs.getInt("d_id"));
				 dep.setName(rs.getString("d_name"));
				 
				 Project pro = new Project();
				 pro.setId(rs.getInt("p_id"));
				 pro.setName(rs.getString("p_name"));
				 
				 Score sc = new Score();
				 sc.setValue(rs.getInt("value"));
				 
				 Grade g = Grade.getGrade(rs.getString("grade"));
				 sc.setGrade(g);
				
				 emp.setDep(dep);
				 sc.setEmp(emp);
				 sc.setPro(pro);
				 

				list.add(sc);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}

		return list;
	}

	public List<Score> search(String e_name, String d_name, String p_name) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			stat = conn.createStatement();
			// 5执行sql语句并得到结果
			rs = stat.executeQuery("select * from v_emp_score");
			// 对结果集进行处理
			while (rs.next()) {
				String eName = "";
				if (rs.getString("e_name") != null) {
					eName = rs.getString("e_name");
				}
				String pName = "";
				if (rs.getString("p_name") != null) {
					pName = rs.getString("p_name");
				}
				String dName = "";
				if (rs.getString("d_name") != null) {
					dName = rs.getString("d_name");
				}

				if ((eName.indexOf(e_name) != -1 || e_name.equals("")) && (pName.equals(p_name) || p_name.equals(""))
						&& (dName.equals(d_name) || d_name.equals(""))) {
					Score sc = new Score();
					Employee emp = new Employee();
					emp.setId(rs.getInt("e_id"));
					emp.setName(rs.getString("e_name"));

					Department dep = new Department();
					dep.setId(rs.getInt("d_id"));
					dep.setName(rs.getString("d_name"));

					emp.setDep(dep);
					sc.setEmp(emp);

					Project pro = new Project();
					pro.setId(rs.getInt("p_id"));
					pro.setName(rs.getString("p_name"));
					sc.setValue((Integer) rs.getObject("value"));
					 Grade g = Grade.getGrade(rs.getString("grade"));
					 sc.setGrade(g);
					list.add(sc);
				}

			}
			// 7关闭
			closeAll(conn, stat, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

}
