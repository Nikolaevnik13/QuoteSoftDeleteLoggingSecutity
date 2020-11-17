package com.quote.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "login")
@Entity
@Table(name = "users")
public class UserAccount {
	@Id
	String login;
	String password;
	LocalDateTime expDate;
	
	@Singular
	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> roles;
	String firstName;
	String lastName;
	
	
	
	public boolean addRole(String role) {
		return roles.add(role);
	}

	public boolean removeRole(String role) {
		return roles.remove(role);
	}
}

