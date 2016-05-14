package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlp.bbdd2.heracles.bo.ActivityBO;
import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.bo.ExerciseConfigurationBO;
import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ActivityDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoutineDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dto.ActivityDTO;
import ar.edu.unlp.bbdd2.heracles.dto.ExerciseConfigurationDTO;
import ar.edu.unlp.bbdd2.heracles.dto.RoutineDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;
import ar.edu.unlp.bbdd2.heracles.security.UserPrincipal;

/**
 * Controlador que permite administrar la acciones en la rutinas para un cliente
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Controller
@RequestMapping("/client/{name}/routine")
public class ClientRoutineControler {
	
	@Autowired
	private ClientBO clientBO;
	@Autowired
	private RoutineBO routineBO;
	@Autowired
	private ActivityBO activityBO;
	@Autowired
	private ExerciseConfigurationBO exerciseConfigurationBO;
	@Autowired
	private ActivityDAOImpl activityDAO;
	@Autowired
	private RoutineDAOImpl routineDAO;
	
	/**
	 * Vista con el listado de rutinas de un cliente
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getClientRoutines(HttpServletResponse response, @PathVariable("name") String name) {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = null;
		if (up.getName().equals(name)){
			mv = new ModelAndView("/client/routines");
			Client client = this.getClientBO().getClientById(up.getId());
			Routine actualRoutine = client.getActualRoutine();
			mv.addObject("actualRoutine",actualRoutine);
			mv.addObject("actualRoutineProgress",this.getRoutineBO().progress(actualRoutine));
		}
		return mv;
	}
	
	/**
	 * Retorna un json con todos las rutinas de un cliente
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listRoutines(HttpServletResponse response) throws IOException {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = this.getClientBO().getClientById(up.getId());
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Routine> routines = new ArrayList<Routine>();
		routines.addAll(this.getRoutineBO().getClientRoutines(client));
		Routine actualRoutine = client.getActualRoutine();
		if (routines.contains(actualRoutine)){
			routines.remove(actualRoutine);
		}
		List<RoutineDTO> routinesDTO = new ArrayList<RoutineDTO>();
		for (Routine routine : routines) {
			routinesDTO.add(new RoutineDTO(routine));
		}
		String json = JsonTransform.listToJson(routinesDTO);
		out.print(json);
	}
	
	@RequestMapping(value="/{routine}")
	public ModelAndView getClientRoutine (HttpServletResponse response, @PathVariable("name") String name, @PathVariable("routine") String routine){
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = null;
		if (up.getName().equals(name)){
			mv = new ModelAndView("/client/routine");
			Routine rt = this.getRoutineBO().getRoutineById(Long.valueOf(routine));
			mv.addObject("actualRoutine", rt);
			mv.addObject("actualRoutineProgress",this.getRoutineBO().progress(rt));
			Activity activity = null;
			try {
				activity = rt.getRunActivity();
			} catch (Exception e) {
				List<Activity> activities = rt.getActivities();
				activity = activities.get(0);
				rt.setRunActivity(activity);
				activity.setRunExercise(activity.getExercises().get(0));
				this.activityDAO.saveOrUpdate(activity);
				this.routineDAO.saveOrUpdate(rt);
			}
			mv.addObject("runActivity", activity);
			mv.addObject("runActivityProgress",this.getActivityBO().activityProggress(activity));
		}
		return mv;
	}
	
	/**
	 * Retorna las actividadesd de una Rutina
	 * @param response
	 * 		JSON con las actividades
	 * @param routine
	 * 		id de la rutina para la que se requieren las actividades
	 * @throws IOException
	 */
	@RequestMapping(value = "/{routine}/activities", method = RequestMethod.GET)
	public void listRoutineActivities(HttpServletResponse response, @PathVariable("routine") String routine) throws IOException {
		
		Routine rt = this.getRoutineBO().getRoutineById(Long.valueOf(routine));
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Activity> activities = new ArrayList<Activity>();
		activities.addAll(rt.getActivities());
		Activity runActivity = null;
		
		//Sacar y solucionar activando una rutina con un actividad
		try {
			runActivity = rt.getRunActivity();
		} catch (Exception e) {
			runActivity = activities.get(0);
		}
		if (activities.contains(runActivity)){
			activities.remove(runActivity);
		}
		List<ActivityDTO> activitiesDTO = new ArrayList<ActivityDTO>();
		for (Activity activity : activities) {
			activitiesDTO.add(new ActivityDTO(activity));
		}
		String json = JsonTransform.listToJson(activitiesDTO);
		out.print(json);
	}
	
	
	/**
	 * Obtiene la actividad para una rutina dada
	 * 
	 * @param response
	 * 		
	 * @param name
	 * 
	 * @param routine
	 * @return
	 */
	@RequestMapping(value="/{routine}/activity/{activity}")
	public ModelAndView getRoutineActivity (HttpServletResponse response, @PathVariable("name") String name, @PathVariable("routine") String routine,
			@PathVariable("activity") String activity){
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = null;
		if (up.getName().equals(name)){
			mv = new ModelAndView("/client/activity");
			Activity act = this.getActivityBO().getActivityById(Long.valueOf(activity));
			mv.addObject("userName", up.getName());
			mv.addObject("routineId", routine);
			mv.addObject("activity", act);
			mv.addObject("exerciesCount",this.getActivityBO().exercisesCount(act));
			mv.addObject("exercisesEndCount", this.getActivityBO().exerciseStateCount(act, ExerciseState.STOP));
			mv.addObject("exercisesCancelCount", this.getActivityBO().exerciseStateCount(act, ExerciseState.CANCEL));
			Client client = this.getClientBO().getClientByName(name);
			ExerciseConfiguration exercise = null;
			ExerciseConfigurationDTO runExerciseDTO = null;
			try {
				exercise = act.getRunExercise();
				runExerciseDTO = new ExerciseConfigurationDTO(exercise);
				runExerciseDTO.setLastState(this.getClientBO().lastExerciseState(client, exercise));
				if (runExerciseDTO.getLastState() == ExerciseState.RUN){
					mv.addObject("runExercise",runExerciseDTO);
				}else {
					mv.addObject("runExercise", null);
				}
				
			} catch (NullPointerException e) {
				mv.addObject("runExercise", null);
			}
			
			
			
		}
		return mv;
	}
	
	/**
	 * 
	 * @param response
	 * @param routine
	 * @throws IOException
	 */
	@RequestMapping(value = "/{routine}/activity/{activity}/exercises", method = RequestMethod.GET)
	public void listRoutineActivitiesExercies(HttpServletResponse response, @PathVariable("name") String name, @PathVariable("routine") String routine, 
			@PathVariable("activity") String activity) throws IOException {
		
		Activity act = this.getActivityBO().getActivityById(Long.valueOf(activity));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<ExerciseConfiguration> exercises = act.getExercises();
		Client client = this.getClientBO().getClientByName(name);
		try {
			ExerciseConfiguration runExercise = act.getRunExercise();
			if ((runExercise != null) && (ExerciseState.RUN == this.getClientBO().lastExerciseState(client, runExercise))){
				exercises.remove(runExercise);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		

		List<ExerciseConfigurationDTO> exerciseDTOs = new ArrayList<>();
		for (ExerciseConfiguration exerciseConfiguration : exercises) {
			exerciseDTOs.add(new ExerciseConfigurationDTO(exerciseConfiguration));
		}
		String json = JsonTransform.listToJson(exerciseDTOs);
		out.print(json);
	}
	
	
	/**
	 * 
	 * @param response
	 * @param routine
	 * @throws IOException
	 */
	@RequestMapping(value = "/{routine}/activity/{activity}/exercise/{exercise}/run", method = RequestMethod.POST)
	public void runExercise(HttpServletResponse response, @PathVariable("name") String name, @PathVariable("routine") String routine, 
			@PathVariable("activity") String activity, @PathVariable("exercise") String exercise) throws IOException {
		
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (up.getName().equals(name)){
			Client client = this.getClientBO().getClientByName(name);
			ExerciseConfiguration exerciseConf = this.getExerciseConfigurationBO().getExerciseConfiguration(Long.valueOf(exercise));
			try {
				this.getClientBO().startExercise(client, exerciseConf);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param routine
	 * @throws IOException
	 */
	@RequestMapping(value = "/{routine}/activity/{activity}/exercise/{exercise}/stop", method = RequestMethod.POST)
	public void stopExercise(HttpServletResponse response, @PathVariable("name") String name, @PathVariable("routine") String routine, 
			@PathVariable("activity") String activity, @PathVariable("exercise") String exercise) throws IOException {
		
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (up.getName().equals(name)){
			Client client = this.getClientBO().getClientByName(name);
			try {
				this.getClientBO().stopExercise(client);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ClientBO getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBO clientBO) {
		this.clientBO = clientBO;
	}

	public RoutineBO getRoutineBO() {
		return routineBO;
	}

	public void setRoutineBO(RoutineBO routineBO) {
		this.routineBO = routineBO;
	}

	public ActivityBO getActivityBO() {
		return activityBO;
	}

	public void setActivityBO(ActivityBO activityBO) {
		this.activityBO = activityBO;
	}

	public ExerciseConfigurationBO getExerciseConfigurationBO() {
		return exerciseConfigurationBO;
	}

	public void setExerciseConfigurationBO(ExerciseConfigurationBO exerciseConfigurationBO) {
		this.exerciseConfigurationBO = exerciseConfigurationBO;
	}

	public ActivityDAOImpl getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(ActivityDAOImpl activityDAO) {
		this.activityDAO = activityDAO;
	}

	public RoutineDAOImpl getRoutineDAO() {
		return routineDAO;
	}

	public void setRoutineDAO(RoutineDAOImpl routineDAO) {
		this.routineDAO = routineDAO;
	}	
}
