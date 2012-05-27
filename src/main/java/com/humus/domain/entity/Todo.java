package com.humus.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private TodoList todoList;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	private Boolean isComplete;
	
	private String title;
	
	
	@ManyToMany(cascade = { CascadeType.ALL },mappedBy="todos" )
	private List<Tag> tags;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String content;


	public Todo(String title) {
		super();
		this.title = title;
		
		tags = new ArrayList<Tag>();
	}


	/**
	 * @return the todoList
	 */
	public TodoList getTodoList() {
		return todoList;
	}


	/**
	 * @param todoList the todoList to set
	 */
	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}


	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}


	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	/**
	 * @return the isComplete
	 */
	public Boolean getIsComplete() {
		return isComplete;
	}


	/**
	 * @param isComplete the isComplete to set
	 */
	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}


	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Todo [id=").append(id).append(", createdDate=")
				.append(createdDate).append(", dueDate=").append(dueDate)
				.append(", isComplete=").append(isComplete).append(", title=")
				.append(title).append(", tags=").append(tags).append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
