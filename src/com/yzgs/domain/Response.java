package com.yzgs.domain;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Response {
	
	public static void reSponseJson(HttpServletResponse response,String string) {
	
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(string);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}


}
