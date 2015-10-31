package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.bo.impl.ExerciseBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.TrainerBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.helper.DataTableObject;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;


@Controller
public class ExercisesControler {
	
	
	private ExerciseBOImpl exerciseBO;
	@Autowired
	private TrainerBOImpl trainerBO;
	
	@RequestMapping(value = "/exercises/{name}", method = RequestMethod.GET)
	public ModelAndView showMessage(
			@RequestParam(value = "name") String name) {
		System.out.println("in controller exercise "+name);
 
		ModelAndView mv = new ModelAndView("exercise");
		mv.addObject("exercise", this.getExerciseBO().getExerciseDAO().findByName(name));
		return mv;
	}
	
	@RequestMapping(value = "/exercises", method = RequestMethod.GET)
	public ModelAndView getExercises (){
		ModelAndView mv = new ModelAndView("trainer/exercises");
//		mv.addObject("exercises",this.exerciseBO.getAllExercises());
		mv.addObject("exercisesJson",this.exercisesJson(this.exerciseBO.getAllExercises()));
		return mv;
	}
	
	@RequestMapping(value = "/exercises/{id}", method = RequestMethod.GET)
	public ModelAndView getExerciseById (
			@RequestParam(value = "id") String id){
		ModelAndView mv = new ModelAndView("exercise");
		mv.addObject("exercise", this.getExerciseBO().getExerciseDAO().loadById(new Long(id)));
		return mv;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void editar(HttpServletResponse response, @RequestParam("id") Long id) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();  
		Exercise exc = this.getExerciseBO().getExerciseDAO().loadById(id);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(exc);
		out.print(json);
	}
	
	@RequestMapping(value = "exercise", method = RequestMethod.POST)
	public String add (@ModelAttribute Exercise exercise, Model model){
		UserService userService = UserServiceFactory.getUserService();
		String email = userService.getCurrentUser().getEmail();
		Trainer owner = this.getTrainerBO().findByEmail(email);
		Exercise ex = null;
		String result = "exercises/";
		try {
			ex = this.getExerciseBO().createExercise(owner, exercise.getName(), exercise.getType(),
					exercise.getEquipment(),
					exercise.getBodyParts(),
					exercise.getDescription());
			result = result +"/id/"+ex.getId();
		} catch (BusinessException e) {
			result = "error/"+e.getMessage();
		}
		model.addAttribute("exercise", ex);
		return result; 
	}
	
	@RequestMapping(value = "/getExercises", method = RequestMethod.GET)
	public void printHello(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(this.exercisesJson(this.exerciseBO.getAllExercises()));
	}
	
	private String exercisesJson (List<Exercise> exercises){
		DataTableObject<Exercise> dataTableObject = new DataTableObject<Exercise>();
		dataTableObject.setAaData(exercises);
		dataTableObject.setiTotalDisplayRecords(11);
		dataTableObject.setiTotalRecords(exercises.size());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		return json;
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
