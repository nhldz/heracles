/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class Routine {
	
	private String name;
	private Date createDate;
	private Date endDate;
	private Trainer trainer;
	private Client client;
	private Activity runActivity;
	private List<Activity> activities;
	
	/**
	 * Contructor default
	 */
	public Routine() {
		super();
	}
	
	public Routine (Routine routine, Client client){
		this();
		this.name = routine.getName();
		this.createDate = new Date();
		this.trainer = routine.getTrainer();
		this.activities = routine.getActivities();
		this.client = client;
		this.activities = new ArrayList<Activity>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Activity getRunActivity() {
		return runActivity;
	}
	public void setRunActivity(Activity runActivity) {
		this.runActivity = runActivity;
	}

	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}
