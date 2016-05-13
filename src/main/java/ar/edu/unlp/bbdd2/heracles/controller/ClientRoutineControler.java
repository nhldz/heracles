package ar.edu.unlp.bbdd2.heracles.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlp.bbdd2.heracles.bo.ActivityBO;
import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
import ar.edu.unlp.bbdd2.heracles.dto.ActivityDTO;
import ar.edu.unlp.bbdd2.heracles.dto.RoutineDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;
import ar.edu.unlp.bbdd2.heracles.security.UserPrincipal;

/**
 * Controlador que permite administrar la acciones en la rutinas para un cliente
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Controller
@RequestMapping("/client/{name}/routine")
public class ClientRoutineControler {
	
	@Autowired
	private ClientBO clientBO;
	@Autowired
	private RoutineBO routineBO;
	@Autowired
	private ActivityBO activityBO;
	
	/**
	 * Vista con el listado de rutinas de un cliente
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getClientRoutines(HttpServletResponse response, @PathVariable("name") String name) {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = null;
		if (up.getName().equals(name)){
			mv = new ModelAndView("/client/routines");
			Client client = this.getClientBO().getClientById(up.getId());
			Routine actualRoutine = client.getActualRoutine();
			mv.addObject("actualRoutine",actualRoutine);
			mv.addObject("actualRoutineProgress",this.getRoutineBO().progress(actualRoutine));
		}
		return mv;
	}
	
	/**
	 * Retorna un json con todos las rutinas de un cliente
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listRoutines(HttpServletResponse response) throws IOException {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = this.getClientBO().getClientById(up.getId());
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Routine> routines = new ArrayList<Routine>();
		routines.addAll(this.getRoutineBO().getClientRoutines(client));
		Routine actualRoutine = client.getActualRoutine();
		if (routines.contains(actualRoutine)){
			routines.remove(actualRoutine);
		}
		List<RoutineDTO> routinesDTO = new ArrayList<RoutineDTO>();
		for (Routine routine : routines) {
			routinesDTO.add(new RoutineDTO(routine));
		}
		String json = JsonTransform.listToJson(routinesDTO);
		out.print(json);
	}
	
	@RequestMapping(value="/{routine}")
	public ModelAndView getClientRoutine (HttpServletResponse response, @PathVariable("name") String name, @PathVariable("routine") String routine){
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = null;
		if (up.getName().equals(name)){
			mv = new ModelAndView("/client/routine");
			Routine rt = this.getRoutineBO().getRoutineById(Long.valueOf(routine));
			mv.addObject("actualRoutine", rt);
			mv.addObject("actualRoutineProgress",this.getRoutineBO().progress(rt));
			mv.addObject("runActivity", rt.getRunActivity());
			mv.addObject("runActivityProgress",this.getActivityBO().activityProggress(rt.getRunActivity()));
		}
		return mv;
	}
	
	@RequestMapping(value = "/{routine}/activities", method = RequestMethod.GET)
	public void listRoutineActivities(HttpServletResponse response, @PathVariable("routine") String routine) throws IOException {
		
		Routine rt = this.getRoutineBO().getRoutineById(Long.valueOf(routine));
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Activity> activities = new ArrayList<Activity>();
		activities.addAll(rt.getActivities());
		Activity runActivity = rt.getRunActivity();
		if (activities.contains(runActivity)){
			activities.remove(runActivity);
		}
		List<ActivityDTO> activitiesDTO = new ArrayList<ActivityDTO>();
		for (Activity activity : activities) {
			activitiesDTO.add(new ActivityDTO(activity));
		}
		String json = JsonTransform.listToJson(activitiesDTO);
		out.print(json);
	}

	public ClientBO getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBO clientBO) {
		this.clientBO = clientBO;
	}

	public RoutineBO getRoutineBO() {
		return routineBO;
	}

	public void setRoutineBO(RoutineBO routineBO) {
		this.routineBO = routineBO;
	}

	public ActivityBO getActivityBO() {
		return activityBO;
	}

	public void setActivityBO(ActivityBO activityBO) {
		this.activityBO = activityBO;
	}
	
}
