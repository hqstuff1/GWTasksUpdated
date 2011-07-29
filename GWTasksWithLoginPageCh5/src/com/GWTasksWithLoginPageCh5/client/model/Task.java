

package com.GWTasksWithLoginPageCh5.client.model;

/**
 * A Task
 *
 * @author
 */
public class Task {

	public enum Priority { HIGH, NORMAL, LOW }

	private Long id;
	private String title;
	private String description;
	private Priority priority;
	
	/**
	 * Default empty constructor.
	 */
	public Task() {
		this(null, null);
	}
	
	/**
	 * Constructs a new Task with a given id and title.
	 *
	 * @param id The id of the task.
	 * @param title The title of the task.
	 */
	public Task(Long id, String title) {
		this(id, title, "");
	}
	
	/**
	 * Constructs a new Task with given id, title, and description.
	 *
	 * @param id The id of the task.
	 * @param title The title of the task.
	 * @param description The description of the task.
	 */
	public Task(Long id, String title, String description) {
		this(id, title, Priority.NORMAL, description);
	}
	
	/**
	 * Constructs a new task with given id, title, and priority.
	 *
	 * @param id The id of the task.
	 * @param title The title of the task.
	 * @param priority The priority of the task.
	 */
	public Task(Long id, String title, Priority priority) {
		this(id, title, priority, "");
	}
	
	/**
	 * Constructs a new task with all its details.
	 *
	 * @param id The id of the task.
	 * @param title The title of the task.
	 * @param priority The priority of the task.
	 * @param description The description of the task.
	 */
	public Task(Long id, String title, Priority priority, String description) {
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.description = description;
	}
	
	/**
	 * Returns the id of this task.
	 *
	 * @return The id of this task.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id of this task.
	 *
	 * @param id The id of this task.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Returns the title of this task.
	 *
	 * @return The title of this task.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of this task.
	 *
	 * @param title The title of this task.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns the description of this task.
	 *
	 * @return The description of this task.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of this task.
	 *
	 * @param description The description of this task.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the priority of this task.
	 *
	 * @return The priority of this task.
	 */
	public Priority getPriority() {
		return priority;
	}
	
	/**
	 * Sets the priority of this task.
	 *
	 * @param priority The priority of this task.
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
}