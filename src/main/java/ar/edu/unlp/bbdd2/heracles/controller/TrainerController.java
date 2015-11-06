package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.unlp.bbdd2.heracles.bo.impl.TrainerBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.helper.DataTableObject;

@Controller
public class TrainerController {
	
	@Autowired
	private TrainerBOImpl trainerBO;

	@RequestMapping("/trainers")
	public ModelAndView getTrainers() {
		ModelAndView mv = new ModelAndView("trainer/trainers");
		mv.addObject("genders", Gender.values());
		mv.addObject("exercisesJson", this.exercisesJson(this.getTrainerBO().getAllTrainers()));
		return mv;
	}

	@RequestMapping(value = "/getTrainers", method = RequestMethod.GET)
	public void getClients(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(this.exercisesJson(this.getTrainerBO().getAllTrainers()));
	}

	private String exercisesJson(List<Trainer> trainers) {
		DataTableObject<Trainer> dataTableObject = new DataTableObject<Trainer>();
		dataTableObject.setAaData(trainers);
		dataTableObject.setiTotalDisplayRecords(11);
		dataTableObject.setiTotalRecords(trainers.size());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		return json;
	}

	public TrainerBOImpl getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBOImpl trainerBO) {
		this.trainerBO = trainerBO;
	}
}
