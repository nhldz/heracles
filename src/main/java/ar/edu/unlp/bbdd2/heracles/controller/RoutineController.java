package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import ar.edu.unlp.bbdd2.heracles.dto.ActivityDTO;
import ar.edu.unlp.bbdd2.heracles.dto.ClientDTO;
import ar.edu.unlp.bbdd2.heracles.dto.ExerciseConfigurationDTO;
import ar.edu.unlp.bbdd2.heracles.dto.ExerciseDTO;
import ar.edu.unlp.bbdd2.heracles.dto.RoutineDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
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
	
	private ActivityDTO activityDTO;
	
	private ClientDTO clientDTO;
	
	private ExerciseDTO exerciseDTO;
	
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
	public @ResponseBody void getRoutineById(HttpServletResponse response, @PathVariable("id") Long id)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Routine routine = this.getRoutineBO().getRoutineById(id);
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
		setActivityDTO(new ActivityDTO());
		List<ExerciseDTO> listDTO = transformExerciseListDTO(getExerciseBO().getAllExercises());
 		mv.addObject("routine", getRoutineDTO());
 		mv.addObject("exercises", listDTO);
		return mv;
	}
				
	/**
	 * Agrega una nueva rutina
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView createRoutine(@ModelAttribute RoutineDTO routineDTO, Model model) throws BusinessException {
		ModelAndView mv = new ModelAndView("routines/list");
		UserPrincipal up = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		routineDTO.setTrainerid(up.getId());
		routineDTO.setActivities(getRoutineDTO().getActivities());
		getRoutineBO().save(routineDTO);
		return mv;
	}
			
	/**
	 * Agrega una actividad
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="activity", method=RequestMethod.POST)
	@ResponseBody
	public void addActivity(@ModelAttribute ActivityDTO activityDTO, Model model) throws BusinessException {
		String uniqueID = UUID.randomUUID().toString();
		getActivityDTO().setId(uniqueID);
		getActivityDTO().setDescription(activityDTO.getDescription());
		getActivityDTO().setName(activityDTO.getName());
		getActivityDTO().setExerciseCount(getActivityDTO().getExercises().size());
		getRoutineDTO().getActivities().add(getActivityDTO());
		setActivityDTO(new ActivityDTO());
	}
	
	/**
	 * Actualiza una actividad
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="activity", method=RequestMethod.PUT)
	@ResponseBody
	public void updateActivity(@ModelAttribute ActivityDTO activityDTO, Model model) throws BusinessException {
		ActivityDTO activity = findActivity(activityDTO.getId());
		activity.setDescription(activityDTO.getDescription());
		activity.setName(activityDTO.getName());
		activity.setExerciseCount(getActivityDTO().getExercises().size());
		activity.setExercises(getActivityDTO().getExercises());
		setActivityDTO(new ActivityDTO());
	}
	
	/**
	 * Retorna un json de ejercicio
	 * 
	 * @param response
	 * @param id
	 *            de la rutina
	 * @throws IOException
	 */
	@RequestMapping(value = "activity/{id}", method = RequestMethod.GET)
	public @ResponseBody void loadActivity(HttpServletResponse response, @PathVariable("id") String id)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		this.activityDTO = loadActivity(id);
		String json = JsonTransform.objectToJson(activityDTO);
		out.print(json);
	}
	
	/**
	 * Elimina una actividad
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="activity/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void removeActivity(@PathVariable("id") String id) throws BusinessException {
		ActivityDTO activity = findActivity(id);
		getRoutineDTO().getActivities().remove(activity);
	}
	
	/**
	 * Busca una actividad
	 * 
	 * @throws BusinessException 
	 */   
	private ActivityDTO findActivity(String id){
		ActivityDTO activity = null;
		for (ActivityDTO activityDTO : getRoutineDTO().getActivities()) {
			if(activityDTO.getId().equalsIgnoreCase(id)){
				activity = activityDTO;
			}
		}
		return activity;
	}
	
	/**
	 * Carga una actividad
	 * 
	 * @throws BusinessException 
	 */   
	private ActivityDTO loadActivity(String id){
		ActivityDTO activity = new ActivityDTO();
		for (ActivityDTO activityDTO : getRoutineDTO().getActivities()) {
			if(activityDTO.getId().equalsIgnoreCase(id)){
				activity.setId(activityDTO.getId());
				activity.setName(activityDTO.getName());
				activity.setDescription(activityDTO.getDescription());
				List<ExerciseConfigurationDTO> list = new ArrayList<ExerciseConfigurationDTO>();
				for (ExerciseConfigurationDTO exConfDTO : activityDTO.getExercises()) {
					ExerciseConfigurationDTO exConf = loadExercise(exConfDTO);					
					list.add(exConf);
				}
				activity.setExercises(list);
			}
		}
		return activity;
	}
	
	private ExerciseConfigurationDTO loadExercise(ExerciseConfigurationDTO exConfDTO) {
		ExerciseConfigurationDTO exConf = new ExerciseConfigurationDTO();
		exConf.setId(exConfDTO.getId());
		exConf.setExerciseName(exConfDTO.getExerciseName());
		exConf.setExercise(exConfDTO.getExercise());
		exConf.setExerciseId(exConfDTO.getExerciseId());
		exConf.setReps(exConfDTO.getReps());
		exConf.setRest(exConfDTO.getRest());
		exConf.setSets(exConfDTO.getSets());
		exConf.setWeigth(exConfDTO.getWeigth());
		return exConf;
	}

	/**
	 * Lista las actividades de una rutina que se esta creando o modificando
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="activityList", method=RequestMethod.GET)
	@ResponseBody
	public void activityList(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = JsonTransform.listToJson(getRoutineDTO().getActivities());
		out.print(json);
	}
	
	
	/**
	 * Agrega un ejercicio a la lista de actividades
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="exercise", method=RequestMethod.POST)
	@ResponseBody
	public void addExercise(@ModelAttribute ExerciseConfigurationDTO exerciseConfigDTO, Model model) throws BusinessException {
		String uniqueID = UUID.randomUUID().toString();
		Exercise exercise = exerciseBO.getExerciseById(exerciseConfigDTO.getExerciseId());
		exerciseConfigDTO.setExercise(new ExerciseDTO(exercise));
		exerciseConfigDTO.setId(uniqueID);
		getActivityDTO().getExercises().add(exerciseConfigDTO);
	}
	
	/**
	 * Agrega un ejercicio a la lista de actividades
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="exercise", method=RequestMethod.PUT)
	@ResponseBody
	public void updateExercise(@ModelAttribute ExerciseConfigurationDTO exerciseConfigDTO, Model model) throws BusinessException {
		ExerciseConfigurationDTO exConf = findExercise(exerciseConfigDTO.getId());
		Exercise exercise = exerciseBO.getExerciseById(exerciseConfigDTO.getExerciseId());
		exerciseConfigDTO.setExercise(new ExerciseDTO(exercise));
		getActivityDTO().getExercises().remove(exConf);
		getActivityDTO().getExercises().add(exerciseConfigDTO);
	}
	
	/**
	 * Retorna un json de ejercicio
	 * 
	 * @param response
	 * @param id
	 *            de la rutina
	 * @throws IOException
	 */
	@RequestMapping(value = "exercise/{id}", method = RequestMethod.GET)
	public @ResponseBody void loadExercise(HttpServletResponse response, @PathVariable("id") String id)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ExerciseConfigurationDTO exercise = findExercise(id);
		String json = JsonTransform.objectToJson(exercise);
		out.print(json);
	}
	
	/**
	 * Borra un ejercicio a la lista de actividades
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="exercise/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteExercise(@PathVariable("id") String id) throws BusinessException {
		ExerciseConfigurationDTO exercise = findExercise(id);
		getActivityDTO().getExercises().remove(exercise);
	}
	
	/**
	 * Lista los ejercicios de una acrtividad que se esta creando o modificando
	 * 
	 * @throws BusinessException 
	 */   
	@RequestMapping(value="exerciseList", method=RequestMethod.GET)
	@ResponseBody
	public void exerciseList(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = JsonTransform.listToJson(getActivityDTO().getExercises());
		out.print(json);
	}

	/**
	 * Busca un ejercicio
	 * 
	 * @throws BusinessException 
	 */   
	private ExerciseConfigurationDTO findExercise(String id){
		ExerciseConfigurationDTO exercise = null;
		for (ExerciseConfigurationDTO exerciseConfigDTO : getActivityDTO().getExercises()) {
			if(exerciseConfigDTO.getId().equalsIgnoreCase(id)){
				exercise = exerciseConfigDTO;
			}
		}
		return exercise;
	}
	
	/**
	 * Retorna un json con todos las rutinas existentes del trainer
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listRoutines(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		UserPrincipal up = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Trainer trainer = this.getTrainerBO().getTrainerById(up.getId());
		List<RoutineDTO> listDTO =  transformRoutineListDTO(this.getRoutineBO().getTrainerRoutines(trainer));
		String json = JsonTransform.listToJson(listDTO);
		out.print(json);
	}
	
	/**
	 * Recorre una lista de rutinas transformandola en lista de DTO
	 * @param trainerRoutines
	 * @return
	 */
	private List<RoutineDTO> transformRoutineListDTO(List<Routine> trainerRoutines) {
		List<RoutineDTO> listDTO = new ArrayList<RoutineDTO>();
		for(Routine routine : trainerRoutines){
			RoutineDTO routineDTO = new RoutineDTO();
			routineDTO.loadDataForTheList(routine);
			listDTO.add(routineDTO);
		}
		return listDTO;
	}
	
	/**
	 * Recorre una lista de ejercicios transformandola en lista de DTO
	 * 
	 * @param exercises
	 * @return
	 */
	private List<ExerciseDTO> transformExerciseListDTO(List<Exercise> exercises) {
		List<ExerciseDTO> listDTO = new ArrayList<ExerciseDTO>();
		for (Exercise exercise : exercises) {
			listDTO.add(new ExerciseDTO(exercise));
		}
		return listDTO;
	}
	
	@RequestMapping(value = "cleanActivity", method = RequestMethod.POST)
	public @ResponseBody void cleanActivity()
			throws IOException {
		this.activityDTO = new ActivityDTO();
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

	public ActivityDTO getActivityDTO() {
		return activityDTO;
	}

	public void setActivityDTO(ActivityDTO activityDTO) {
		this.activityDTO = activityDTO;
	}

	public ClientDTO getClientDTO() {
		return clientDTO;
	}

	public void setClientDTO(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
	}

	public ExerciseDTO getExerciseDTO() {
		return exerciseDTO;
	}

	public void setExerciseDTO(ExerciseDTO exerciseDTO) {
		this.exerciseDTO = exerciseDTO;
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
