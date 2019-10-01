package br.com.franca.resource.exception;

public class StandartError {
	private Integer status;
	private String msg;
	private Long timeStamp;

	public StandartError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
