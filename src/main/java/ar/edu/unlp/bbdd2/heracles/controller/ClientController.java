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

import ar.edu.unlp.bbdd2.heracles.bo.impl.ClientBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.helper.DataTableObject;

@Controller
public class ClientController {

	@Autowired
	private ClientBOImpl clientBO;

	@RequestMapping("/clients")
	public ModelAndView getClient() {
		ModelAndView mv = new ModelAndView("trainer/clients");
		mv.addObject("genders", Gender.values());
		mv.addObject("exercisesJson", this.exercisesJson(this.getClientBO().getAllClients()));
		return mv; 
	}

	@RequestMapping(value = "/getClients", method = RequestMethod.GET)
	public void getClients(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(this.exercisesJson(this.getClientBO().getAllClients()));
	}

	private String exercisesJson(List<Client> clients) {
		DataTableObject<Client> dataTableObject = new DataTableObject<Client>();
		dataTableObject.setAaData(clients);
		dataTableObject.setiTotalDisplayRecords(11);
		dataTableObject.setiTotalRecords(clients.size());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		return json;
	}

	public ClientBOImpl getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBOImpl clientBO) {
		this.clientBO = clientBO;
	}

}
