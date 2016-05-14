package ar.edu.unlp.bbdd2.heracles.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import ar.edu.unlp.bbdd2.heracles.dao.ActivityDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;


public class ActivityDAOImpl extends BaseDAOImpl<Activity> implements ActivityDAO{
	
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public ActivityDAOImpl (Class<Activity> classType){
		super();
		this.type = classType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer excerciseStateCount(Activity activity, ExerciseState state) {
		List<ExerciseConfiguration> exConfs = new ArrayList<ExerciseConfiguration>();
		exConfs.addAll(activity.getExercises());

		return ofy().load().type(ExerciseSnapshot.class).filter("exerciseConfiguration IN", exConfs).filter("state =", state).count();
	}

	public DatastoreService getDatastore() {
		return datastore;
	}

	public void setDatastore(DatastoreService datastore) {
		this.datastore = datastore;
	}

	
}
