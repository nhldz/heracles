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

import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
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
@RequestMapping("/client/{name}")
public class ClientRoutineControler {
	
	@Autowired
	private ClientBO clientBO;
	@Autowired
	private RoutineBO routineBO;
	
	/**
	 * Vista con el listado de rutinas de un cliente
	 * @return
	 */
	@RequestMapping(value = "/routines", method = RequestMethod.GET)
	public ModelAndView getClientRoutines(HttpServletResponse response, @PathVariable("name") String name) {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = null;
		if (up.getName().equals(name)){
			mv = new ModelAndView("/client/routines");
		}
		return mv;
	}
	
	/**
	 * Retorna un json con todos las rutinas de un cliente
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/routines/list", method = RequestMethod.GET)
	public void listRoutines(HttpServletResponse response) throws IOException {
		UserPrincipal up = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = this.getClientBO().getClientById(up.getId());
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Routine> routines = new ArrayList<Routine>();
		routines.addAll(this.getRoutineBO().getClientRoutines(client));
		routines.add(client.getActualRoutine());
		String json = JsonTransform.listToJson(routines);
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
	
}
