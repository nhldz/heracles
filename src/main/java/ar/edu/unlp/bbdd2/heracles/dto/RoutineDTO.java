package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.util.Utilities;

public class RoutineDTO {

	private Long id;
	private String name;
	private String createDate;
	private String endDate;
	private Long trainerid;
	private Long clientid;
	private ClientDTO client;
	private TrainerDTO trainer;
	//private ActivityDTO runActivity;
	private List<ActivityDTO> activities;

	public RoutineDTO() {
		activities = new ArrayList<ActivityDTO>();
	}

	public RoutineDTO(Routine routine) {
		this();
		this.id = routine.getId();
		this.name = routine.getName();
		this.createDate = Utilities.formatDateToString(routine.getCreateDate());
		if (routine.getEndDate() != null){
			this.endDate = Utilities.formatDateToString(routine.getEndDate());
		}
		this.trainerid = routine.getTrainer().getId();
		this.clientid = routine.getClient().getId();
		this.client = new ClientDTO(routine.getClient());
		this.trainer = new TrainerDTO(routine.getTrainer());
		this.activities =  createListActivityDTO(routine.getActivities());
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(Long trainerid) {
		this.trainerid = trainerid;
	}

	public Long getClientid() {
		return clientid;
	}

	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public TrainerDTO getTrainer() {
		return trainer;
	}

	public void setTrainer(TrainerDTO trainer) {
		this.trainer = trainer;
	}
	
	/**
	 * Carga la informacion necesaria para visualizar la rutina en la lista
	 * @param routine
	 */
	public void loadDataForTheList(Routine routine) {
		this.id = routine.getId();
		this.name = routine.getName();
		this.createDate = Utilities.formatDateToString(routine.getCreateDate());
		if (routine.getEndDate() != null){
			this.endDate = Utilities.formatDateToString(routine.getEndDate());
		}
		this.client = new ClientDTO();
		this.client.loadDataForTheList(routine.getClient());
	}
	
	public List<ActivityDTO> createListActivityDTO(List<Activity> activities) {
		List<ActivityDTO> listActivity = new ArrayList<ActivityDTO>();
		for (Activity activity : activities) {
			ActivityDTO activityDTO = new ActivityDTO(activity);
			listActivity.add(activityDTO);
		}
		return listActivity;
	}

	
	public List<ActivityDTO> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityDTO> activities) {
		this.activities = activities;
	}
	
	// public ActivityDTO getRunActivity() {
	// return runActivity;
	// }
	//
	// public void setRunActivity(ActivityDTO runActivity) {
	// this.runActivity = runActivity;
	// }
	//
	// public List<ActivityDTO> getActivities() {
	// return activities;
	// }
	//
	// public void setActivities(List<ActivityDTO> activities) {
	// this.activities = activities;
	// }

}
