package ar.edu.unlp.bbdd2.heracles.bo.impl;

import ar.edu.unlp.bbdd2.heracles.bo.ActivityBO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ActivityDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;

public class ActivityBOImpl implements ActivityBO{
	
	private ActivityDAOImpl activityDAO;

	@Override
	public Integer activityProggress(Activity activity) {
		activityDAO.excerciseStateCount(activity, ExerciseState.STOP);
		Integer result= 0;
		Integer exercisesStopCount = activityDAO.excerciseStateCount(activity, ExerciseState.STOP);
		if (exercisesStopCount > 0 ){
			result = (activity.getExercises().size() * 100 / exercisesStopCount);
		}
		return result;
	}

	@Override
	public Integer exercisesCount(Activity activity) {
		return activity.getExercises().size();
	}

	public ActivityDAOImpl getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(ActivityDAOImpl activityDAO) {
		this.activityDAO = activityDAO;
	}

}
