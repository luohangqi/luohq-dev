package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoController {

	private Logger logger = LoggerFactory.getLogger(VideoController.class);

	/**
	 * video
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/video")
	public String video() {
		logger.info("video");
		return "video";
	}
	
	
	/**
	 * 视频跳转
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getVideo/{id}")
	public ModelAndView getVideo(@PathVariable("id") String id) {
		logger.info("getVideo" + id);
		ModelAndView mav = new ModelAndView("success");
		mav.addObject("path", "/video.do?id=" + id);
		return mav;
	}

	/**
	 * 视频流读取
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/query/video/{id}")
	@ResponseBody
	public void video(@PathVariable("id") String id, HttpServletResponse response) {
		logger.info("video" + id);
		FileInputStream in = null;
		ServletOutputStream out = null;
		response.setContentType("application/x-shockwave-flash");
		try {
			File file = new File("E:\\haha.mp4");
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] b = null;
			while (in.available() > 0) {
				if (in.available() > 10240) {
					b = new byte[10240];
				} else {
					b = new byte[in.available()];
				}
				in.read(b, 0, b.length);
				out.write(b, 0, b.length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.flush();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
