package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.bo.impl.ExerciseBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.TrainerBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;
import ar.edu.unlp.bbdd2.heracles.security.UserPrincipal;


@Controller
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
	@RequestMapping(value = "/exercises", method = RequestMethod.GET)
	public ModelAndView getExercises (){
		ModelAndView mv = new ModelAndView("exercises/list");
		mv.addObject("excercisesTypes", ExerciseType.values());
		mv.addObject("equipments", Equipment.values());
		mv.addObject("bodyParts", BodyPart.values());
		return mv;
	}

	/**
	 * Retorna un json del ejercicio
	 * 
	 * @param response
	 * @param id
	 *            del ejercicio
	 * @throws IOException
	 */
	@RequestMapping(value = "/exercises/{id}", method = RequestMethod.GET)
	public @ResponseBody void getExerciseById(HttpServletResponse response, @PathVariable("id") String id)
			throws IOException {
		response.setContentType("application/json");
		Long idL = Long.valueOf(id);
		PrintWriter out = response.getWriter();
		Exercise exc = this.getExerciseBO().getExerciseDAO().loadById(idL);
		String json = JsonTransform.objectToJson(exc);
		out.print(json);
	}
	
	/**
	 * Action para crear/agregar un nuevo ejercicio.
	 * 
	 * @param exercise
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exercises", method = RequestMethod.POST)
	public String add (@ModelAttribute Exercise exercise, Model model){
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = up.getEmail();
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
	
	/**
	 * Action para actualizar un ejecicio
	 * 
	 * @param exercise
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exercises", method = RequestMethod.PUT)
	public @ResponseBody void update(@ModelAttribute Exercise exercise, Model model) {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = up.getEmail();
		Trainer owner = this.getTrainerBO().findByEmail(email);
		String result = "exercises";
		try {
			if (this.validUpdate(exercise)) {
				this.getExerciseBO().save(exercise);
			}
		} catch (BusinessException e) {
			result = "error/" + e.getMessage();
		}
	}
	
	/**
	 * Elimina un ejercicio
	 * @param id
	 * 		id del ejercicio a eliminar.
	 * @return
	 */
	@RequestMapping(value = "/exercises/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ModelAndView delete (@PathVariable("id") String id){
		Long idL = Long.valueOf(id);
		Exercise exc = this.getExerciseBO().loadById(idL);
		exc.setEnabled(false);
		this.getExerciseBO().save(exc);
	}
	
	/**
	 * Retorna un json con todos los ejercicios existentes
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/exercises/list", method = RequestMethod.GET)
	public void listExercises(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = JsonTransform.listToJson(this.getExerciseBO().getAllExercises());
		out.print(json);
	}
	
	private boolean validSave(Exercise exercise) {
		return this.getExerciseBO().validSave(exercise);
	}
	
	private boolean validUpdate(Exercise exercise) {
		return this.getExerciseBO().validUpdate(exercise);
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
