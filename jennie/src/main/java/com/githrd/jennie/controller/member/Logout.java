package com.githrd.jennie.controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;

public class Logout implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/whistle/main.blp";
		req.setAttribute("isRedirect", true);
		req.getSession().removeAttribute("SID");
		return view;
	}

}
