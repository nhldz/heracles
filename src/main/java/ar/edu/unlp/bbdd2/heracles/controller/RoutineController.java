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

import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
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
	 * Guarda los cambios del formulario de rutinas
	 * 
	 * @param routine
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody void save(@ModelAttribute Routine routine, Model model) {
		// UserService userService = UserServiceFactory.getUserService();
		// String email = userService.getCurrentUser().getEmail();
//		Trainer owner = this.getTrainerBO().findByEmail("matias.trainer@email.com");
//		exercise.setOwner(owner);
//		String result = "exercises";
//		try {
//			if (this.validSave(exercise)) {
//				this.getExerciseBO().save(exercise);
//			}
//		} catch (BusinessException e) {
//			result = "error/" + e.getMessage();
//		}
	}
	
	/**
	 * Guarda los cambios del formulario de rutinas
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
}
