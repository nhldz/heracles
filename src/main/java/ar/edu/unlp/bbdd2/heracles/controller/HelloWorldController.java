package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;

import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;

@Controller
//@RequestMapping(value="/hello")
public class HelloWorldController {
	private List<Exercise> excercises = new ArrayList<Exercise>();
	private List<Client> clients = new ArrayList<Client>();

	@RequestMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping("/trainers")
	public ModelAndView trainers() {
		ModelAndView mv = new ModelAndView("trainer/trainers");
		excercises = cargarEjercicios();
		mv.addObject("objects", excercises);
		return mv;
	}

	@RequestMapping("/clients")
	public ModelAndView clients() {
		ModelAndView mv = new ModelAndView("trainer/clients");
		mv.addObject("genders", Gender.values());
		return mv;
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
	
	/*************EXCERCISES****************/
	@RequestMapping("/excercises")
	public ModelAndView excercises() {		
		ModelAndView mv = new ModelAndView("trainer/excercises");
		mv.addObject("excercisesTypes", ExerciseType.values());
		mv.addObject("equipments", Equipment.values());
		mv.addObject("bodyParts", BodyPart.values());
		return mv;
	}

	@RequestMapping(value = "/getexcercises", method = RequestMethod.GET)
	public void printHello(HttpServletResponse response) throws IOException {
		if(getExcercises().isEmpty()){
			setExcercises(cargarEjercicios());
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();  
		DataTableObject<Exercise> dataTableObject = new DataTableObject<Exercise>();
		dataTableObject.setAaData(getExcercises());
		dataTableObject.setiTotalDisplayRecords(11);
		dataTableObject.setiTotalRecords(11);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		out.print(json);
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	@ResponseBody
	public void save(@ModelAttribute("excercise") Exercise excercise) {
		if(excercise.getId() == null){
			excercise.setId(Long.valueOf(excercises.size() + 1));
			excercises.add(excercise);
		}else{
			Exercise exc = returnExerciseById(excercise.getId());
			exc.setName(excercise.getName());
			exc.setDescription(excercise.getDescription());
			exc.setType(excercise.getType());
			exc.setEquipment(excercise.getEquipment());
			exc.setBodyParts(excercise.getBodyParts());
		}
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void editar(HttpServletResponse response, @RequestParam("id") Long id) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();  
		Exercise exc = returnExerciseById(id);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(exc);
		out.print(json);
	}
	
	private Exercise returnExerciseById(Long id){
		Iterator<Exercise> iterador = getExcercises().iterator();
		while (iterador.hasNext()) {
			Exercise exercise = (Exercise) iterador.next();
			if(exercise.getId().equals(id)){
				return exercise;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/borrar", method = RequestMethod.POST)
	@ResponseBody
	public void borrar(@RequestParam("id") Long id) {
		Iterator<Exercise> iterador = getExcercises().iterator();
		while (iterador.hasNext()) {
			Exercise exercise = (Exercise) iterador.next();
			if(exercise.getId().equals(id)){
				iterador.remove();
			}
		}
	}

	@RequestMapping("/routines")
	public ModelAndView routines() {
		ModelAndView mv = new ModelAndView("trainer/routines");
		excercises = cargarEjercicios();
		mv.addObject("objects", excercises);
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("menu", "trainer");
		return mv;
	}
	
	private List<Exercise> cargarEjercicios() {

		List<Exercise> privateExercises = new ArrayList<Exercise>();

		Exercise exercise1 = new Exercise();
		exercise1.setId(Long.valueOf("1"));
		exercise1.setDescription("Ejercicio 1");
		exercise1.setName("Piernas");
		exercise1.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise1);
		
		Exercise exercise2 = new Exercise();
		exercise2.setId(Long.valueOf("2"));
		exercise2.setDescription("Ejercicio 2");
		exercise2.setName("Piernas");
		exercise2.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise2);
		
		Exercise exercise3 = new Exercise();
		exercise3.setId(Long.valueOf("3"));
		exercise3.setDescription("Ejercicio 3");
		exercise3.setName("Piernas");
		exercise3.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise3);
		
		Exercise exercise4 = new Exercise();
		exercise4.setId(Long.valueOf("4"));
		exercise4.setDescription("Ejercicio 4");
		exercise4.setName("Piernas");
		exercise4.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise4);
		
		Exercise exercise5 = new Exercise();
		exercise5.setId(Long.valueOf("5"));
		exercise5.setDescription("Ejercicio 5");
		exercise5.setName("Piernas");
		exercise5.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise5);
		
		Exercise exercise6 = new Exercise();
		exercise6.setId(Long.valueOf("6"));
		exercise6.setDescription("Ejercicio 6");
		exercise6.setName("Piernas");
		exercise6.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise6);
		
		Exercise exercise7 = new Exercise();
		exercise7.setId(Long.valueOf("7"));
		exercise7.setDescription("Ejercicio 7");
		exercise7.setName("Piernas");
		exercise7.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise7);
		
		Exercise exercise8 = new Exercise();
		exercise8.setId(Long.valueOf("8"));
		exercise8.setDescription("Ejercicio 8");
		exercise8.setName("Piernas");
		exercise8.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise8);
		
		Exercise exercise9 = new Exercise();
		exercise9.setId(Long.valueOf("9"));
		exercise9.setDescription("Ejercicio 9");
		exercise9.setName("Piernas");
		exercise9.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise9);
		
		Exercise exercise10 = new Exercise();
		exercise10.setId(Long.valueOf("10"));
		exercise10.setDescription("Ejercicio 10");
		exercise10.setName("APiernas");
		exercise10.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise10);
		
		Exercise exercise11 = new Exercise();
		exercise11.setId(Long.valueOf("11"));
		exercise11.setDescription("Ejercicio 11");
		exercise11.setName("Piernas");
		exercise11.setType(ExerciseType.AEROBIC);
		privateExercises.add(exercise11);

		return privateExercises;
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

	public List<Exercise> getExcercises() {
		return excercises;
	}

	public void setExcercises(List<Exercise> excercises) {
		this.excercises = excercises;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
