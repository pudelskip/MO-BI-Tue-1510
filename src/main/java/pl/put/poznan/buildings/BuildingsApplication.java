package pl.put.poznan.buildings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.buildings.ui.mainwindow.MainWindow;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildings.rest"})
public class BuildingsApplication {

	public static void main(String[] args) {
		new MainWindow();
		SpringApplication.run(BuildingsApplication.class, args);
	}
}
