package ar.edu.unlp.bbdd2.heracles.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ar.edu.unlp.bbdd2.heracles.dao.TrainerDAO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoleDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.UserDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.User;

public class HeraclesAuthenticationProvider implements AuthenticationProvider {
	
	private UserDAOImpl userDAO;
	private RoleDAOImpl roleDAO;
	private TrainerDAO trainerDAO;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = this.getUserDAO().loadByUserName(name);
		Authentication auth = null;
		if (user != null){
			UserPrincipal up = new UserPrincipal(user);
			if (user.getUsername().equals(name) && user.getPassword().equals(password)){
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
	            grantedAuths.add(new SimpleGrantedAuthority(user.getRoles().get(0).getName()));
	            auth = new UsernamePasswordAuthenticationToken(up, password, grantedAuths);
			}
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public UserDAOImpl getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	public RoleDAOImpl getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAOImpl roleDAO) {
		this.roleDAO = roleDAO;
	}

	public TrainerDAO getTrainerDAO() {
		return trainerDAO;
	}

	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}
	
}
