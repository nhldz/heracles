package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import ar.edu.unlp.bbdd2.heracles.entities.Exercise;

@Controller
//@RequestMapping(value="/hello")
public class HelloWorldController {
	private List<Exercise> excercises = new ArrayList<Exercise>();

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
		excercises = cargarEjercicios();
		mv.addObject("objects", excercises);
		return mv;
	}

	@RequestMapping("/excercises")
	public ModelAndView excercises() {
		ModelAndView mv = new ModelAndView("trainer/excercises");
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
		privateExercises.add(exercise1);
		
		Exercise exercise2 = new Exercise();
		exercise2.setId(Long.valueOf("2"));
		exercise2.setDescription("Ejercicio 2");
		exercise2.setName("Piernas");
		privateExercises.add(exercise2);
		
		Exercise exercise3 = new Exercise();
		exercise3.setId(Long.valueOf("3"));
		exercise3.setDescription("Ejercicio 3");
		exercise3.setName("Piernas");
		privateExercises.add(exercise3);
		
		Exercise exercise4 = new Exercise();
		exercise4.setId(Long.valueOf("4"));
		exercise4.setDescription("Ejercicio 4");
		exercise4.setName("Piernas");
		privateExercises.add(exercise4);
		
		Exercise exercise5 = new Exercise();
		exercise5.setId(Long.valueOf("5"));
		exercise5.setDescription("Ejercicio 5");
		exercise5.setName("Piernas");
		privateExercises.add(exercise5);
		
		Exercise exercise6 = new Exercise();
		exercise6.setId(Long.valueOf("6"));
		exercise6.setDescription("Ejercicio 6");
		exercise6.setName("Piernas");
		privateExercises.add(exercise6);
		
		Exercise exercise7 = new Exercise();
		exercise7.setId(Long.valueOf("7"));
		exercise7.setDescription("Ejercicio 7");
		exercise7.setName("Piernas");
		privateExercises.add(exercise7);
		
		Exercise exercise8 = new Exercise();
		exercise8.setId(Long.valueOf("8"));
		exercise8.setDescription("Ejercicio 8");
		exercise8.setName("Piernas");
		privateExercises.add(exercise8);
		
		Exercise exercise9 = new Exercise();
		exercise9.setId(Long.valueOf("9"));
		exercise9.setDescription("Ejercicio 9");
		exercise9.setName("Piernas");
		privateExercises.add(exercise9);
		
		Exercise exercise10 = new Exercise();
		exercise10.setId(Long.valueOf("10"));
		exercise10.setDescription("Ejercicio 10");
		exercise10.setName("APiernas");
		privateExercises.add(exercise10);
		
		Exercise exercise11 = new Exercise();
		exercise11.setId(Long.valueOf("11"));
		exercise11.setDescription("Ejercicio 11");
		exercise11.setName("Piernas");
		privateExercises.add(exercise11);

		return privateExercises;
	}

	public List<Exercise> getExcercises() {
		return excercises;
	}

	public void setExcercises(List<Exercise> excercises) {
		this.excercises = excercises;
	}

}
