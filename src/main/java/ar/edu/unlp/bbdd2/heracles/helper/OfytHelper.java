package ar.edu.unlp.bbdd2.heracles.helper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.entities.User;

import com.googlecode.objectify.ObjectifyService;

public class OfytHelper implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Client.class);
		ObjectifyService.register(Trainer.class);
		ObjectifyService.register(Activity.class);
		ObjectifyService.register(Exercise.class);
		ObjectifyService.register(ExerciseConfiguration.class);
		ObjectifyService.register(ExerciseSnapshot.class);
		ObjectifyService.register(Routine.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// App Engine does not currently invoke this method.
	}

}
