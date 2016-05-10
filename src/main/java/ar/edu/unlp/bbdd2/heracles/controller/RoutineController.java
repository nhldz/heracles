package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.bo.ExerciseBO;
import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.dto.RoutineDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;
import ar.edu.unlp.bbdd2.heracles.security.UserPrincipal;

/**
 * 
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
@Controller
@RequestMapping("/routines")
public class RoutineController {

	@Autowired
	private RoutineBO routineBO;
	
	@Autowired
	private TrainerBO trainerBO;
	
	@Autowired
	private ClientBO clientBO;
	
	@Autowired
	private ExerciseBO exerciseBO;
	
	private Routine routine;
	
	private RoutineDTO routineDTO;
	
	private Activity activity;
	
	private List<Activity> activityList;
	
	private ExerciseConfiguration exerciseConf;
	
	private List<ExerciseConfiguration> exerciseConfList;

	/**
	 * Pagina principal de rutinas
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView routinesIndex() {
		ModelAndView mv = new ModelAndView("routines/list");
		return mv;
	}

	/**
	 * Retorna un json de la rutina
	 * 
	 * @param response
	 * @param id
	 *            de la rutina
	 * @throws IOException
	 */
	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
	public @ResponseBody void getRoutineById(HttpServletResponse response, @PathVariable("id") String id)
			throws IOException {
		response.setContentType("application/json");
		Long idL = Long.valueOf(id);
		PrintWriter out = response.getWriter();
		Routine routine = this.getRoutineBO().getRoutineById(idL);
		String json = JsonTransform.objectToJson(routine);
		out.print(json);
	}
	
	/**
	 * Direcciona a la jsp de creacion de rutina
	 * 
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() throws BusinessException {
		ModelAndView mv = new ModelAndView("routines/create");
		setRoutineDTO(new RoutineDTO());
		setRoutine(new Routine());
		mv.addObject("clients", clientBO.getAllEnabledClients());
		mv.addObject("routine", getRoutineDTO());
		return mv;
	}
			
	/**
	 * Agrega una nueva rutina
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void createRoutine(@ModelAttribute RoutineDTO routineDTO, Model model) throws BusinessException {
		UserPrincipal up = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		routineDTO.setTrainerid(up.getId());
		getRoutine().setName(routineDTO.getName());
		getRoutine().setClient(getClientBO().getClientById(routineDTO.getClientid()));
		getRoutine().setTrainer(getTrainerBO().getTrainerById(routineDTO.getTrainerid()));
		getRoutine().setCreateDate(new Date());
	//	getRoutineBO().save(getRoutine());
	}
	
	/**
	 * Direcciona a la jsp de creacion de actividad
	 * 
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/createActivity", method=RequestMethod.GET)
	public ModelAndView createActivity() throws BusinessException {
		ModelAndView mv = new ModelAndView("routines/createActivity");
		setActivity(new Activity());
		mv.addObject("activity", getActivity());
		setActivityList(new ArrayList<Activity>());
		setExerciseConf(new ExerciseConfiguration());
		setExerciseConfList(new ArrayList<ExerciseConfiguration>());
	    mv.addObject("exerciseConf", getExerciseConf());
		mv.addObject("exercises", getExerciseBO().getAllExercises());
		return mv;
	}
	
	/**
	 * Direcciona a la jsp de creacion de actividad
	 * 
	 * @throws BusinessException 
	 */
	@RequestMapping(value="/addActivity", method=RequestMethod.POST)
	public ModelAndView addActivity(@ModelAttribute Activity activity, Model model) throws BusinessException {
		ModelAndView mv = new ModelAndView("routines/create");
		this.getActivity().getExercises().addAll(getExerciseConfList());
		activity.getExercises().addAll(getExerciseConfList());
		getActivityList().add(activity);
		mv.addObject("routine", getRoutine());
		return mv;
	}
	
	/**
	 * Agrega una ejercicio a la lista de actividades
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="addExcercise", method=RequestMethod.POST)
	@ResponseBody
	public void addExcercise(@ModelAttribute ExerciseConfiguration exerciseConfig, Model model) throws BusinessException {
		exerciseConfig.setExercise(getExerciseBO().getAllExercises().get(1));
		exerciseConfig.setId(1L);
		this.getExerciseConfList().add(exerciseConfig);
		
		activity.setExercises(this.getExerciseConfList());
		activity.setId(1L);
		this.getActivityList().add(activity);

		routine.setActivities(this.getActivityList());
		routine.setClient(this.getClientBO().getAllEnabledClients().get(0));
		routine.setTrainer(this.getTrainerBO().getAllTrainers().get(0));
		this.getRoutineBO().save(routine);
		this.getRoutineBO().getAllRoutines();
	}

	
	/**
	 * 
	 * @param routine
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody void update(@ModelAttribute Routine routine, Model model) {
//		Trainer owner = this.getTrainerBO().findByEmail("matias.trainer@email.com");
//		String result = "exercises";
//		try {
//			if (this.validUpdate(exercise)) {
//				this.getExerciseBO().save(exercise);
//			}
//		} catch (BusinessException e) {
//			result = "error/" + e.getMessage();
//		}
	}
	
	/**
	 * Elimina una Rutina
	 * 
	 * @param id
	 *            de la rutina a eliminar
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void remove(@PathVariable("id") String id) throws BusinessException {
		Long idL = Long.valueOf(id);
		Routine routine = this.getRoutineBO().getRoutineById(idL);
	//	routine.setEnabled(false);
		this.getRoutineBO().save(routine);
	}
	
	/**
	 * Retorna un json con todos las rutinas existentes del trainer
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void getClients(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		UserPrincipal up = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Trainer trainer = this.getTrainerBO().getTrainerById(up.getId());
		String json = JsonTransform.listToJson(this.getRoutineBO().getTrainerRoutines(trainer));
		out.print(json);
	}

	public RoutineBO getRoutineBO() {
		return routineBO;
	}

	public void setRoutineBO(RoutineBO routineBO) {
		this.routineBO = routineBO;
	}

	public TrainerBO getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBO trainerBO) {
		this.trainerBO = trainerBO;
	}

	public ExerciseBO getExerciseBO() {
		return exerciseBO;
	}

	public void setExerciseBO(ExerciseBO exerciseBO) {
		this.exerciseBO = exerciseBO;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	public RoutineDTO getRoutineDTO() {
		return routineDTO;
	}

	public void setRoutineDTO(RoutineDTO routineDTO) {
		this.routineDTO = routineDTO;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ExerciseConfiguration getExerciseConf() {
		return exerciseConf;
	}

	public void setExerciseConf(ExerciseConfiguration exerciseConf) {
		this.exerciseConf = exerciseConf;
	}

	public List<ExerciseConfiguration> getExerciseConfList() {
		return exerciseConfList;
	}

	public void setExerciseConfList(List<ExerciseConfiguration> exerciseConfList) {
		this.exerciseConfList = exerciseConfList;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public ClientBO getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBO clientBO) {
		this.clientBO = clientBO;
	}
}
