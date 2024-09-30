package in.com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.bean.MarksheetBean;
import in.com.rays.bean.UserBean;
import in.com.rays.model.MarksheetModel;
import in.com.rays.model.UserModel;

@WebServlet("/MarksheetListCtl.do")
public class MarksheetListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MarksheetBean bean = null;

		int pageNo = 1;

		int pageSize = 5;

		MarksheetModel model = new MarksheetModel();
		
		

		try {
			List list = model.search(bean, pageNo, pageSize);
			
			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("MarksheetListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String op = req.getParameter("operation");

		MarksheetBean bean = null;

		int pageNo = 1;

		int pageSize = 5;

		MarksheetModel model = new MarksheetModel();

		if (op.equals("search")) {
			bean = new MarksheetBean();
			bean.setname(req.getParameter("name"));
		}
		if (op.equals("next")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo++;
		}
		if (op.equals("previous")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo--;
		}
		if (op.equals("add")) {
			resp.sendRedirect("MarksheetCtl.do");
		}
		if (op.equals("delete")) {
			String[] ids = req.getParameterValues("ids");
			if(ids !=null && ids.length > 0) {
				for(String id :ids) {
					try {
						model.delete(Integer.parseInt(id));
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		try {
			List list = model.search(bean, pageNo, pageSize);

			req.setAttribute("list", list);
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = req.getRequestDispatcher("MarksheetListView.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
