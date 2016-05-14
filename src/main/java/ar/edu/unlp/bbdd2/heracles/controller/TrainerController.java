package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.dto.TrainerDTO;
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
	 * Retorna un json del trainer
	 * 
	 * @param response
	 * @param id
	 *            del cliente
	 * @throws IOException
	 */
	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getClientById(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
		response.setContentType("application/json");
		Long idL = Long.valueOf(id);
		PrintWriter out = response.getWriter();
		Trainer trainer = this.getTrainerBO().getTrainerById(idL);
		String json = JsonTransform.objectToJson(new TrainerDTO(trainer));
		out.print(json);
	}

	/**
	 * Guarda un trainer
	 * 
	 * @param trainer
	 * @param model
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void save(@ModelAttribute TrainerDTO trainer, Model model) throws BusinessException {
		getTrainerBO().createTrainer(trainer);
	}

	/**
	 * Actualiza los datos de un trainer
	 * 
	 * @param trainer
	 * @param model
	 * @throws BusinessException 
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public void update(@ModelAttribute TrainerDTO trainerDTO, Model model) throws BusinessException {
		getTrainerBO().updateTrainer(trainerDTO);
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
		List<TrainerDTO> listDTO = transformListDTO(this.getTrainerBO().getAllTrainers());
		String json = JsonTransform.listToJson(listDTO);
		out.print(json);
	}

	private List<TrainerDTO> transformListDTO(List<Trainer> trainers) {
		List<TrainerDTO> listDTO = new ArrayList<TrainerDTO>();
		for(Trainer trainer : trainers){
			TrainerDTO trainerDTO = new TrainerDTO();
			trainerDTO.loadDataForTheList(trainer);
			listDTO.add(trainerDTO);
		}
		return listDTO;
	}

	public TrainerBO getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBO trainerBO) {
		this.trainerBO = trainerBO;
	}
}
