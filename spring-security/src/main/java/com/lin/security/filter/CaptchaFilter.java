package com.lin.security.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.github.cage.Cage;
import com.github.cage.IGenerator;
import com.lin.security.common.Constants;

/**
 * 
 * desc: 验证码生成
 * 
 * @author xuelin
 * @date Dec 22, 2015
 */
public class CaptchaFilter extends OncePerRequestFilter {
	
	private static final Cage cage = new Cage(null, null, null, null, null, new TokenGenerator(), null);
	
	public static void generateToken(HttpSession session) {
		String token = cage.getTokenGenerator().next();
		session.setAttribute(Constants.CAPTCHA_VALUE_ATTR, token);
		markTokenUsed(session, false);
	}
	
	public static String getToken(HttpSession session) {
		Object val = session.getAttribute(Constants.CAPTCHA_VALUE_ATTR);
		return val != null ? val.toString() : null;
	}
	
	protected static boolean isTokenUsed(HttpSession session) {
		return !Boolean.FALSE.equals(session.getAttribute(Constants.CAPTCHA_STATUS_ATTR));
	}
	
	protected static void markTokenUsed(HttpSession session, boolean used) {
		session.setAttribute(Constants.CAPTCHA_STATUS_ATTR, used);
	}
	
	protected void setResponseHeaders(HttpServletResponse resp) {
		resp.setContentType("image/" + cage.getFormat());
		resp.setHeader("Cache-Control", "no-cache, no-store");
		resp.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		resp.setDateHeader("Last-Modified", time);
		resp.setDateHeader("Date", time);
		resp.setDateHeader("Expires", time);
	}
	
	static class TokenGenerator implements IGenerator<String> {
		static final short DEFAULT_LENGTH = 4;
		static final Random rnd = new Random();
		private short length;
		
		static final char[] CHARACTERS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'i', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'I', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z' };
		
		public TokenGenerator() {
			this(null);
		}
		
		public TokenGenerator(Short length) {
			super();
			if (null != length) {
				this.length = length;
			} else {
				this.length = DEFAULT_LENGTH;
			}
		}
		
		@Override
		public String next() {
			StringBuilder rndChars = new StringBuilder();
			int len = CHARACTERS.length;
			for (int i = 0; i < this.length; i++) {
				rndChars.append(CHARACTERS[rnd.nextInt(len - 1)]);
			}
			return rndChars.toString();
		}
		
		public short getLength() {
			return length;
		}
		
		public void setLength(short length) {
			this.length = length;
		}
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);
		generateToken(session);
		String token = session != null ? getToken(session) : null;
		if (token == null || isTokenUsed(session)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Captcha not found.");
			return;
		}
		setResponseHeaders(resp);
		cage.draw(token, resp.getOutputStream());
	}
	
}
