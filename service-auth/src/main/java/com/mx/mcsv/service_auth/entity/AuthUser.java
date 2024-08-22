package com.mx.mcsv.service_auth.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String userName;
    
    private String password;
    
    private String role;
    
	public AuthUser() {
	}

	public AuthUser(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	

	public AuthUser(int id, String userName, String password, String role) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public static AuthUserBuilder builder() {
        return new AuthUserBuilder();
	}

	// Clase Builder como método estático
	public static class AuthUserBuilder {
        private int id;
        private String userName;
        private String password;
        private String role;

        // Constructor vacío
        public AuthUserBuilder() {
        }

        // Métodos para establecer los valores de los atributos
        public AuthUserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public AuthUserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public AuthUserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthUserBuilder role(String role) {
            this.role = role;
            return this;
        }

        // Método para construir la instancia de AuthUser
        public AuthUser build() {
            return new AuthUser(id, userName, password, role);
        }
    }
    
    
}
