package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion.EPR;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Pagination;
import util.Constant;
import util.Grade;;

public class scoreServlet extends HttpServlet {
	public static final String path = "WEB-INF/score/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 防止相应乱码
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");

			if (type == null) {
				search(request, response);
			} else if ("manage".equals(type)) {
				manage(request, response);
			} else if ("input".equals(type)) {
				input(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void input(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();

			int id = Integer.parseInt(request.getParameter("id"));
			int value = Integer.parseInt(request.getParameter("value"));

			Score score = new Score();
			score.setValue(value);

			ScoreDao scDao = new ScoreDao();
			boolean flag = false;
			if (id == 0) {
				int empId = Integer.parseInt(request.getParameter("empId"));
				int proId = Integer.parseInt(request.getParameter("proId"));

				Employee emp = new Employee();
				emp.setId(empId);
				Project pro = new Project();
				pro.setId(proId);

				score.setEmp(emp);
				score.setPro(pro);

				id = scDao.add(score);
				if (id > 0) {
					flag = true;
				}
				score.setId(id);
			} else {
				score.setId(id);
				flag = scDao.update(score);
			}
			Score sc = scDao.search(id);
			if (flag) {
				JSON json = JSONObject.fromObject(sc);
				out.print(json);
			} else {
				out.print(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void basic(HttpServletRequest request, HttpServletResponse response) {
		Employee emp = new Employee();
		Department dep = new Department();
		Project pro = new Project();
		Score condition = new Score();
		String name = request.getParameter("name");

		int depId = -1;
		if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		int proId = -1;
		if (request.getParameter("proId") != null && !"".equals(request.getParameter("proId"))) {
			proId = Integer.parseInt(request.getParameter("proId"));
		}

		emp.setName(name);
		dep.setId(depId);
		emp.setDep(dep);
		pro.setId(proId);
		condition.setEmp(emp);
		condition.setPro(pro);

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		ScoreDao scDao = new ScoreDao();
		int count = scDao.searchCount(condition);

		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Score> list = scDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		DepartmentDao depDao = new DepartmentDao();
		List<Department> depList = depDao.search();

		ProjectDao proDao = new ProjectDao();
		List<Project> proList = proDao.search();

		Grade[] grades = Grade.values();

		request.setAttribute("p", p);
		request.setAttribute("c", condition);
		request.setAttribute("deps", depList);
		request.setAttribute("pros", proList);
		request.setAttribute("grades", Grade.values());
		request.setAttribute("scs", list);
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {

			basic(request, response);
			request.getRequestDispatcher(path + "scs.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void manage(HttpServletRequest request, HttpServletResponse response) {
		try {

			basic(request, response);
			request.getRequestDispatcher(path + "manage.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
