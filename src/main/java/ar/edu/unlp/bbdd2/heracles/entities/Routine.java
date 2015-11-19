/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Entity
public class Routine {

	@Id
	private Long id;
	private String name;
	private Date createDate;
	private Date endDate;
	@Index
	private @Load(Trainer.class) Ref<Trainer> trainer;

	@Load
	private Ref<Client> client;
	private Activity runActivity;
	private List<Ref<Activity>> activities;

	/**
	 * Contructor default
	 */
	public Routine() {
		super();
	}

	public Routine(Routine routine, Client client) {
		this();
		this.name = routine.getName();
		this.createDate = new Date();
		this.setTrainer(routine.getTrainer());
		this.client = Ref.create(client);
		this.activities = new ArrayList<Ref<Activity>>();
	}

	public Routine(String name, Trainer trainer, Client client) {
		this();
		this.name = name;
		this.setTrainer(trainer);
		this.setClient(client);
		this.setActivities(new ArrayList<Activity>());
		this.createDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return trainer.get();
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = Ref.create(trainer);
	}

	public Client getClient() {
		return client.get();
	}

	public void setClient(Client client) {
		this.client = Ref.create(client);
	}

	public Activity getRunActivity() {
		return runActivity;
	}

	public void setRunActivity(Activity runActivity) {
		this.runActivity = runActivity;
	}

	public List<Activity> getActivities() {
		return RefHelper.deref(this.activities);
	}

	public void setActivities(List<Activity> activities) {
		this.activities = RefHelper.ref(activities);
	}

}
