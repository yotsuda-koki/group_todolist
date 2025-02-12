package model;

import java.sql.Date;
import java.sql.Timestamp;

public class UserTodo {
	private int id;
	private int userId;
	private Date registrationAt;
	private Date expirationAt;
	private Date finishedAt;
	private String todoItem;
	private int isDeleted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getRegistrationAt() {
		return registrationAt;
	}
	public void setRegistrationAt(Date registrationAt) {
		this.registrationAt = registrationAt;
	}
	public Date getExpirationAt() {
		return expirationAt;
	}
	public void setExpirationAt(Date expirationAt) {
		this.expirationAt = expirationAt;
	}
	public Date getFinishedAt() {
		return finishedAt;
	}
	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	public String getTodoItem() {
		return todoItem;
	}
	public void setTodoItem(String todoItem) {
		this.todoItem = todoItem;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
