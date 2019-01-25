package com.douzon.guestbook.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import java.util.*;
import com.douzon.guestbook.dao.GuestBookDao;
import com.douzon.guestbook.vo.GuestBookVo;
import com.douzon.web.WebUtils;


@WebServlet("/gd")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");

		// 요청분리(식별)
		String action = request.getParameter("a");
		
//		if ("index".contentEquals(action))
//		{
//			
//		}
		if ("deleteform".equals(action))
		{
			WebUtils.forward(request, response, "/WEB-INF/Views/deleteform.jsp");
		}
		else if("add".equals(action))
		{
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");
			
			GuestBookVo vo = new GuestBookVo(name, password, message);
			
			new GuestBookDao().insert(vo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/gd"); // 서블릿에서 얻어올때
		}
		else if("delete".equals(action))
		{
			String password = request.getParameter("password");
			String no = request.getParameter("no");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setPassword(password);
			vo.setNo(Long.parseLong(no));
			
			new GuestBookDao().delete(vo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/gd");
		}
		else
		{
			/* default action : 디폴트 요청 처리   파라미터를 안줄때(아무 액션이없을때)*/
			/* index */
			
			// 1. /gb?pa=babo 	내가적어놓지않은 요청이 들어오면
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();
			
//			getServletContext() // 정보들을 갖고있는 객체
			
			// 데이터를 request 범위에 저장
			request.setAttribute("list", list); // 리퀘스트 안에 데이터를 담아넣음
			
			
			// forwarding
			WebUtils.forward(request, response, "/WEB-INF/Views/index.jsp");
			
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/index.jsp"); // 어디로 분기할지 정해줘야함
//			rd.forward(request, response); // forward는 서블릿에서 jsp로 제어를 넘김
			// HttpServletRequest request 위의 리퀘스트는 ㅇ이거와 같음
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
