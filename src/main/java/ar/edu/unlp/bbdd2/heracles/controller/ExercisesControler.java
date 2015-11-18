package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.bo.impl.ExerciseBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.TrainerBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;

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

	/**
	 * Retorna un json del ejercicio
	 * 
	 * @param response
	 * @param id
	 *            del ejercicio
	 * @throws IOException
	 */
	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
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
	 * Guarda los cambios del formulario de ejercicios
	 * 
	 * @param exercise
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody void save(@ModelAttribute Exercise exercise, Model model) {
		// UserService userService = UserServiceFactory.getUserService();
		// String email = userService.getCurrentUser().getEmail();
		Trainer owner = this.getTrainerBO().findByEmail("matias.trainer@email.com");
		exercise.setOwner(owner);
		String result = "exercises";
		try {
			if (this.validSave(exercise)) {
				this.getExerciseBO().save(exercise);
			}
		} catch (BusinessException e) {
			result = "error/" + e.getMessage();
		}
	}
	
	/**
	 * Guarda los cambios del formulario de ejercicios
	 * 
	 * @param exercise
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody void update(@ModelAttribute Exercise exercise, Model model) {
		Trainer owner = this.getTrainerBO().findByEmail("matias.trainer@email.com");
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
	 * 
	 * @param id
	 *            del ejercicio a eliminar
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void remove(@PathVariable("id") String id) throws BusinessException {
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
	@RequestMapping(value = "list", method = RequestMethod.GET)
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
