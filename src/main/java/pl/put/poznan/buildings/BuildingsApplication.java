package pl.put.poznan.buildings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.buildings.ui.mainwindow.MainWindow;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildings.rest"})
public class BuildingsApplication {


    private static final String GUI_MODE = "gui";
    private static final String REST_MODE = "rest";
    private static final String DEFAULT_MODE = "default";

    public static void main(String[] args) {
        String mode = args.length == 0 ? DEFAULT_MODE : args[0];
        switch (mode) {
            case (DEFAULT_MODE):
                new MainWindow();
                break;
            case (GUI_MODE):
                new MainWindow();
                break;
            case (REST_MODE):
                SpringApplication.run(BuildingsApplication.class, args);
                break;
        }
    }
}
