package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;

public class RoutineDTO {
	
	private Long id;
	private String name;
	private Date createDate;
	private Date endDate;
	private TrainerDTO trainer;

	private Client client;
	private ActivityDTO runActivity;
	private List<ActivityDTO> activities;
	
	public RoutineDTO (){}
	
	public RoutineDTO (Routine routine){
		this();
		this.id = routine.getId();
		this.name = routine.getName();
		this.createDate = routine.getCreateDate();
		this.endDate = routine.getEndDate();
		this.trainer = new TrainerDTO(routine.getTrainer());
		
//		this.runActivity = new ActivityDTO(routine.getRunActivity());
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

	public TrainerDTO getTrainer() {
		return trainer;
	}

	public void setTrainer(TrainerDTO trainer) {
		this.trainer = trainer;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ActivityDTO getRunActivity() {
		return runActivity;
	}

	public void setRunActivity(ActivityDTO runActivity) {
		this.runActivity = runActivity;
	}

	public List<ActivityDTO> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityDTO> activities) {
		this.activities = activities;
	}
	
}
