package com.georgitsipov.passwordgenerator;

import com.vaadin.flow.component.dependency.NpmPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.artur.helpers.LaunchUtil;

@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class PasswordGeneratorApplication {

	public static void main(String[] args) {
		LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(PasswordGeneratorApplication.class, args));
	}

}
