package ar.edu.unlp.bbdd2.heracles.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import ar.edu.unlp.bbdd2.heracles.entities.RoleName;

/**
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public class HeraclesAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Authentication authentication)
					throws IOException, javax.servlet.ServletException {
		super.onAuthenticationSuccess(request, response, authentication);
		UserPrincipal up = (UserPrincipal)authentication.getPrincipal();
		String defaultUrl = null;
		//TODO: pensar como hacer para ir a la url /client/{name} y 
		//elegir la url dependiendo de la gerarquia por ejemplo admin por encima de entrandor y este por encima de cliente
		if (up.getRoles().contains(RoleName.TRAINER.getType())){
			defaultUrl = RoleName.TRAINER.getDefaultUrl();
		}else{
			if (up.getRoles().contains(RoleName.CLIENT.getType())){
				//Cambiar por el nombre mas adelante
				defaultUrl = StringUtils.replace(RoleName.CLIENT.getDefaultUrl(), "{name}", up.getName());
			}
		}
		response.sendRedirect(defaultUrl);

	}

}
