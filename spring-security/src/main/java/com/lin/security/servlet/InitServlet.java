package com.lin.security.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lin.security.common.LogUtils;

/**
 * 
 * desc:   初始化servlet
 * @author xuelin
 * @date   Dec 17, 2015
 */
public class InitServlet extends HttpServlet {
	
	static Logger logger = LoggerFactory.getLogger(InitServlet.class);

	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = 7809567959118728484L;
	
	/**
	 * 
	 * desc: 初始化配置	
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		LogUtils.info(logger, "上下文启动成功...");
		super.init();
	}
	
}
