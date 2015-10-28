package ar.edu.unlp.bbdd2.heracles.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.TrainerDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.util.RootUtil;


@Controller	
public class HelloWorldController {
	
	String message; //= "Welcome to Spring MVC!";
	
//	private ExerciseDAOImpl dao = new ExerciseDAOImpl();
//	AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/backend/spring-dao.xml");

//	private ExerciseDAOImpl dao = (ExerciseDAOImpl) context.getBean("exerciseDao");
	
	private ExerciseDAOImpl daoEx;
	private TrainerDAOImpl daoTx;
	private RootUtil rootUtil;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExerciseDAOImpl getDaoEx() {
		return daoEx;
	}

	public void setDaoEx(ExerciseDAOImpl daoEx) {
		this.daoEx = daoEx;
	}

	public TrainerDAOImpl getDaoTx() {
		return daoTx;
	}

	public void setDaoTx(TrainerDAOImpl daoTx) {
		this.daoTx = daoTx;
	}
	
	public RootUtil getRootUtil() {
		return rootUtil;
	}

	public void setRootUtil(RootUtil rootUtil) {
		this.rootUtil = rootUtil;
	}

	private Exercise createExercise (){
		
		Trainer owner = new Trainer();
//		owner.setId(2L);
		owner.setName("Duenio");
		owner.setExercises(new ArrayList<Exercise>());
		daoTx.save(owner);
		Exercise exercise = new Exercise();
//		exercise.setId(1L);
		exercise.setName("exercise");
		exercise.setDescription("description");
		exercise.setBodyParts(new ArrayList<BodyPart>());
		exercise.setOwner(owner);
		owner.getExercises().add(exercise);
		
		daoEx.save(exercise);
		return exercise;
	}
	
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller "+name);
		
		Exercise ex = createExercise();
		System.out.println("Ejercicio creado: "+ ex.toString());
		ex = daoEx.load(ex);
		System.out.println("TRAINER: "+ex.getOwner().getName());
		System.out.println("Ejercicio cargado: "+ ex.toString());
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		mv.addObject("exercise", ex);
		rootUtil.rootLoad();
		return mv;
	}

}
