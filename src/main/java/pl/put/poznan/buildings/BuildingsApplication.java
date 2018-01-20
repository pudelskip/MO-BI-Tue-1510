package pl.put.poznan.buildings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.buildings.ui.mainwindow.MainWindow;

/**
 * Main class (application) for program
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildings.rest"})
public class BuildingsApplication {

    /**
     * Constant used in launch params for gui interface
     */
    private static final String GUI_MODE = "gui";
    /**
     * Constant used in launch params for rest functions
     */
    private static final String REST_MODE = "rest";
    /**
     * Constant used in launch params for default behaviour
     */
    private static final String DEFAULT_MODE = "default";


    /**
     * Main function that only starts gui or rest api
     *
     * @param args launch parameters
     */
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
