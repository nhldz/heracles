package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;

import ar.edu.unlp.bbdd2.heracles.bo.impl.ClientBOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.helper.DataTableObject;

@Controller
public class ClientController {
	
	@Autowired
	private ClientBOImpl clientBo;
	
	private List<Client> clients = new ArrayList<Client>();
	
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public List<Client> getClients() {
		return clients;
	}

	@RequestMapping("/clients")
	public ModelAndView getClient() {
		ModelAndView mv = new ModelAndView("trainer/clients");
		mv.addObject("genders", Gender.values());
		return mv;
	}

	public ClientBOImpl getClientBo() {
		return clientBo;
	}

	public void setClientBo(ClientBOImpl clientBo) {
		this.clientBo = clientBo;
	}
	
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST)
	@ResponseBody
	public void save(@ModelAttribute("client") Client client) {
//		if(client.getId() == null){
		client.setId(Long.valueOf(clients.size() + 1));
		clients.add(client);
//		}else{
//			Client cli = returnExerciseById(excercise.getId());
//			exc.setName(excercise.getName());
//			exc.setDescription(excercise.getDescription());
//			exc.setType(excercise.getType());
//			exc.setEquipment(excercise.getEquipment());
//			exc.setBodyParts(excercise.getBodyParts());
//		}
	}
	
	@RequestMapping(value = "/getclients", method = RequestMethod.GET)
	public void getClients(HttpServletResponse response) throws IOException {
		if(getClients().isEmpty()){
			setClients(cargarClientes());
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();  
		DataTableObject<Client> dataTableObject = new DataTableObject<Client>();
		dataTableObject.setAaData(getClients());
		dataTableObject.setiTotalDisplayRecords(2);
		dataTableObject.setiTotalRecords(2);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		out.print(json);
	}
	
	private List<Client> cargarClientes(){

		List<Client> privateClients = new ArrayList<Client>();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
		Client client = new Client();
		client.setId(Long.valueOf("1"));
		client.setName("Matias");
		client.setSurname("Garcia");
		client.setCellPhone("0221-155637610");
		client.setEmail("matiasagt@gmail.com");
		client.setUserName("matiasagt");
		client.setPassword("123456");
		client.setEnabledUser(true);
		client.setRegistrationDate(Calendar.getInstance().getTime());
		client.setBirthday(dateFormat.parse("03/02/1986"));
		client.setGender(Gender.MALE);
		privateClients.add(client);
		
		Client client2 = new Client();
		client2.setId(Long.valueOf("2"));
		client2.setName("Nahuel");
		client2.setSurname("Diaz");
		client2.setCellPhone("0221-156633221");
		client2.setEmail("nahd85@gmail.com");
		client2.setUserName("nahd85");
		client2.setPassword("123456");
		client2.setEnabledUser(true);
		client2.setRegistrationDate(Calendar.getInstance().getTime());
		client2.setBirthday(dateFormat.parse("03/02/1986"));
		client2.setGender(Gender.MALE);
		privateClients.add(client2);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return privateClients;
	}

}
