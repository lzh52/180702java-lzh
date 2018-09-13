package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import net.sf.json.JSONArray;
import util.Pagination;
import util.Constant;;

public class projectServlet extends HttpServlet {
	public static final String path="WEB-INF/project/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");

			if (type == null) {
				search(request, response);		
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			Project condition = new Project();
			String name = request.getParameter("name");
			
			condition.setName(name);
			
			ProjectDao proDao = new ProjectDao();
			
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}

			int count = proDao.searchCount(condition);

			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

			List<Project> list = proDao.search(condition,p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			
			request.setAttribute("pros", list);
			request.getRequestDispatcher(path + "pros.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao proDao = new ProjectDao();
			Project pro = proDao.search(id);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDao proDao = new ProjectDao();
			Project pro = new Project();
			String name = request.getParameter("name");
			
			
			pro.setName(name);
			
			
			boolean flag = proDao.add(pro);
			// show(request, response);

			response.sendRedirect("project");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDao proDao = new ProjectDao();
			Project pro = new Project();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			
			
			pro.setId(id);
			pro.setName(name);
			
			
			boolean flag = proDao.update(pro);
			// show(request, response);

			response.sendRedirect("project");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("id");
			ProjectDao proDao = new ProjectDao();

			boolean flag = proDao.delete(ids);
			// show(request, response);

			response.sendRedirect("project");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
