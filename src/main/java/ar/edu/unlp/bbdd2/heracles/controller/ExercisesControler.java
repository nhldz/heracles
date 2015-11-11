package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.bo.impl.ExerciseBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.TrainerBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.helper.DataTableObject;

@Controller
@RequestMapping("/exercises")
public class ExercisesControler {

	@Autowired
	private ExerciseBOImpl exerciseBO;
	@Autowired
	private TrainerBOImpl trainerBO;

	/**
	 * Pagina principal de ejercicios
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView excercisesIndex() {
		ModelAndView mv = new ModelAndView("exercises/list");
		mv.addObject("excercisesTypes", ExerciseType.values());
		mv.addObject("equipments", Equipment.values());
		mv.addObject("bodyParts", BodyPart.values());
		return mv;
	}

	@RequestMapping(value = "/exercises/{name}", method = RequestMethod.GET)
	public ModelAndView showMessage(@PathVariable("name") String name) {
		System.out.println("in controller exercise " + name);
		ModelAndView mv = new ModelAndView("exercise");
		mv.addObject("exercise", this.getExerciseBO().getExerciseDAO().findByName(name));
		return mv;
	}

	/**
	 * Elimina un ejercicio
	 * 
	 * @param id del ejercicio a eliminar
	 * 
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") String id) {
		Long idL = new Long(id);
		this.getExerciseBO().deleteExercise(idL);
	}
	
	/**
	 * Retorna un json del ejercicio
	 * @param response
	 * @param id del ejercicio 
	 * @throws IOException
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody void getExerciseById(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
		response.setContentType("application/json");
		Long idL = new Long(id);
		PrintWriter out = response.getWriter();
		Exercise exc = this.getExerciseBO().getExerciseDAO().loadById(idL);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(exc);
		out.print(json);
	}
	
	/**
	 * Guarda los cambios del formulario de ejercicios
	 * @param exercise
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody void save(@ModelAttribute Exercise exercise, Model model) {
	//	UserService userService = UserServiceFactory.getUserService();
//		String email = userService.getCurrentUser().getEmail();
		Trainer owner = this.getTrainerBO().findByEmail("matias.trainer@email.com");
		String result = "exercises";
		try {
			if(this.validSave(exercise)){
				this.getExerciseBO().save(exercise);
			}
		} catch (BusinessException e) {
			result = "error/" + e.getMessage();
		}
	}

	/**
	 * Retorna un json con todos los ejercicios existentes
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listExercises(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(this.exercisesJson(this.exerciseBO.getAllExercises()));
	}

	/**
	 * Transforma la lista de ejercicios en el json correspondiente
	 * 
	 * @param exercises lista de ejercicios
	 * @return
	 */
	private String exercisesJson(List<Exercise> exercises) {
		DataTableObject<Exercise> dataTableObject = new DataTableObject<Exercise>();
		dataTableObject.setAaData(exercises);
		dataTableObject.setiTotalDisplayRecords(11);
		dataTableObject.setiTotalRecords(exercises.size());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		return json;
	}
	
	private boolean validSave(Exercise exercise){
		return this.getExerciseBO().validSave(exercise);
	}

	public ExerciseBOImpl getExerciseBO() {
		return exerciseBO;
	}

	public void setExerciseBO(ExerciseBOImpl exerciseBO) {
		this.exerciseBO = exerciseBO;
	}

	public TrainerBOImpl getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBOImpl trainerBO) {
		this.trainerBO = trainerBO;
	}

}
