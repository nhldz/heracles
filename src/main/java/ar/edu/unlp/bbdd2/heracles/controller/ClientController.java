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

import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.helper.JsonTransform;

/**
 *
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientBO clientBO;

	/**
	 * Pagina principal de clientes
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView clientIndex() {
		ModelAndView mv = new ModelAndView("client/list");
		mv.addObject("genders", Gender.values());
		return mv;
	}

	/**
	 * Retorna un json del cliente
	 * 
	 * @param response
	 * @param id
	 *            del cliente
	 * @throws IOException
	 */
//	@RequestMapping(value = "{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public void getClientById(HttpServletResponse response, @PathVariable("id") String id)
//			throws IOException {
//		response.setContentType("application/json");
//		Long idL = Long.valueOf(id);
//		PrintWriter out = response.getWriter();
//		Client client = this.getClientBO().getClientById(idL);
//		String json = JsonTransform.objectToJson(client);
//		out.print(json);
//	}
	
	/**
	 * Retorna la pagina de inicio del cliente logueado
	 * 
	 * @param response
	 * @param id
	 *            del cliente
	 * @throws IOException
	 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public ModelAndView getClientByName(HttpServletResponse response, @PathVariable("name") String name)
			throws IOException {
		ModelAndView mv  = new ModelAndView("client/profile");
		Client client = this.getClientBO().getClientByName(name);
		mv.addObject("client", client);
		return mv;
	}
	
	/**
	 * Retorna la pagina del perfil del cliente logueado.
	 * 
	 * @param response
	 * @param id
	 *            del cliente
	 * @throws IOException
	 */
	@RequestMapping(value = "{name}/profile", method = RequestMethod.GET)
	public ModelAndView clientProfile(HttpServletResponse response, @PathVariable("name") String name)
			throws IOException {
		ModelAndView mv  = new ModelAndView("client/profile");
		Client client = this.getClientBO().getClientByName(name);
		mv.addObject("client", client);
		return mv;
	}
	
	/**
	 * Guarda los cambios del formulario de cliente
	 * 
	 * @param client
	 * @param model
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody void save(@ModelAttribute Client client, Model model) throws BusinessException {
		System.out.println(client);
	}

	/**
	 * Deshabilita un cliente
	 * 
	 * @param id
	 *            del cliente a deshabilitar
	 * 
	 * @return
	 */
	@RequestMapping(value = "disable/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void disable(@PathVariable("id") String id) {
		Long idL = new Long(id);
		this.getClientBO().clientDisable(idL);
	}

	/**
	 * Retorna un json con todos los clientes existentes
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void getClients(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = JsonTransform.listToJson(this.getClientBO().getAllEnabledClients());
		out.print(json);
	}

	public ClientBO getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBO clientBO) {
		this.clientBO = clientBO;
	}

}
