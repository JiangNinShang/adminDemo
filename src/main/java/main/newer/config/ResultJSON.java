package main.newer.config;

import java.util.Map;
import com.google.gson.Gson;
/**
 * 前端数据响应类
 * @author 007
 *
 */
public class ResultJSON {
	private boolean success; /* 操作成功标志 */
	private String result; /* 操作结果 */
	private String info; /* 操作结果提示信息 */
	private int status; /* 操作结果状态码 */
	private Map<String, Object> data; /* 操作结果数据 */

	public ResultJSON() {

	}

	public ResultJSON(boolean success, String info, int status, Map<String, Object> data) {
		this.success = success;
		this.result = info;
		this.info = info;
		this.status = status;
		this.data = data;
	}
	public ResultJSON(boolean success, String info, int status) {
		this.success = success;
		this.result = info;
		this.info = info;
		this.status = status;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public static ResultJSON ok() {
		return ok("操作成功");
	}

	/* 简洁方法,404 */
	public ResultJSON error404(String info) {
		this.setStatus(404);
		this.setSuccess(false);
		this.setInfo(info);
		this.setResult(info);
		return this;
	}

	public ResultJSON error400(String info) {
		return error400(info, data);
	}

	public ResultJSON error400(String info, Map<String, Object> data) {
		this.setStatus(400);
		this.setSuccess(false);
		this.setInfo(info);
		this.setResult(info);
		this.data = data;
		return this;
	}

	/* 简洁方法,操作成功,200,有返回值 */
	public static ResultJSON ok(Map<String, Object> data) {
		return new ResultJSON(true, "操作成功", 200, data);
	}
	/* 简洁方法,操作成功,200,无返回值 */
	public static ResultJSON ok(Boolean success) {
		if(success) {
			return new ResultJSON(success, "操作成功", 200);
		}else {
			return new ResultJSON(success, "操作失败", 400);
		}
	}
	/* 返回值结果直接处理成JSON字符串 */
	public String toJson() {
		return new Gson().toJson(this);
	}

	/* 简洁方法,操作成功,200,无返回值,有提示信息 */
	public static ResultJSON ok(String info) {
		return new ResultJSON(true, info, 200, null);
	}

	public static ResultJSON error(String info) {
		return new ResultJSON().error400(info);
	}
}
