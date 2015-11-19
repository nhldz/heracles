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

import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;

/**
 *
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
@Controller
@RequestMapping("/trainer")
public class TrainerController {

	@Autowired
	private TrainerBO trainerBO;

	/**
	 * Pagina principal de profesores
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView trainerIndex() {
		ModelAndView mv = new ModelAndView("trainer/list");
		mv.addObject("genders", Gender.values());
		return mv;
	}

	/**
	 * Retorna un json del profesor
	 * 
	 * @param response
	 * @param id
	 *            del profesor
	 * @throws IOException
	 */
	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
	public @ResponseBody void getTrainerById(HttpServletResponse response, @PathVariable("id") String id)
			throws IOException {
		response.setContentType("application/json");
		Long idL = Long.valueOf(id);
		PrintWriter out = response.getWriter();
		Trainer trainer = this.getTrainerBO().getTrainerById(idL);
		String json = JsonTransform.objectToJson(trainer);
		out.print(json);
	}
	
	/**
	 * Guarda los cambios del formulario de profesores
	 * 
	 * @param trainer
	 * @param model
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody void save(@ModelAttribute Trainer trainer, Model model) throws BusinessException {
		this.getTrainerBO().save(trainer);
	}

	/**
	 * Deshabilita un profesor
	 * 
	 * @param id
	 *            del profesor a deshabilitar
	 * 
	 * @return
	 */
	@RequestMapping(value = "disable/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void disable(@PathVariable("id") String id) {
		Long idL = Long.valueOf(id);
		this.getTrainerBO().trainerDisable(idL);
	}

	/**
	 * Retorna un json con todos los profesores existentes
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void getClients(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = JsonTransform.listToJson(this.getTrainerBO().getAllTrainers());
		out.print(json);
	}

	public TrainerBO getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBO trainerBO) {
		this.trainerBO = trainerBO;
	}
}
