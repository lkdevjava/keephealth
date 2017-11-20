package com.kh.common.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.common.model.ResultModel;

public abstract class BaseController {

	private Log logger = LogFactory.getLog(BaseController.class);

	@Autowired
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 实现: 创建返回信息
	 * 
	 * @param code
	 *            返回编码
	 * @param success
	 *            是否成功
	 * @param obj
	 *            返回参数对象
	 * @return 信息对象
	 */
	public ResultModel bulidResult(String code, boolean success, Object obj) {
		ResultModel model = new ResultModel();
		model.setResult(obj);
		model.setSuccess(success);
		model.setMsg("");
		return model;
	}

	/**
	 * 实现: 创建返回信息
	 * 
	 * @param code
	 *            返回编码
	 * @param success
	 *            是否成功
	 * @return 信息对象
	 */
	public ResultModel convertResult(String code, boolean success) {
		return bulidResult(code, success, null);
	}

	/**
	 * 实现: 创建返回信息
	 * 
	 * @param code
	 *            返回编码
	 * @param success
	 *            是否成功
	 * @param obj
	 *            返回参数对象
	 * @return 信息对象
	 */
	public ResultModel convertResult(String code, boolean success, Object obj) {
		return bulidResult(code, success, obj);
	}

	/**
	 * 实现: 输入文件信息
	 * 
	 * @param resp
	 * @param fileName
	 *            文件路径
	 */
	public void writeFile(HttpServletResponse resp, String fileName) {
		OutputStream os = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(fileName);
			bis = new BufferedInputStream(fis);
			os = resp.getOutputStream();
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = bis.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
			os.flush();
		} catch (IOException e) {
			logger.error("读取文件失败", e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (fis != null) {
					fis.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (Exception e2) {
				logger.error("关闭流失败", e2);
			}
		}
	}

}
