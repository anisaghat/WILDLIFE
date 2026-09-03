package app;

import controller.controllerLOGIN;
import controller.controllerMAINVIEW;
import model.authentication.MapAuthenticator;
import model.authentication.PropertiesAuthenticator;
import model.dao.*;
import view.LOGIN;
import view.MAINVIEW;

public class launchApplication {

    private final PropertiesAuthenticator authenticator;

    private final BiomeDAO biomeDAO;
    private final HabitatDAO habitatDAO;

    private final MammalDAO mammalDAO;
    private final BirdDAO birdDAO;
    private final FishDAO fishDAO;
    private final ReptileDAO reptileDAO;
    private final AmphibianDAO amphibianDAO;

    private final AnimalRepository animalRepository;

    public launchApplication() {

        authenticator = new PropertiesAuthenticator( "user.properties");

        biomeDAO =
                new BiomeDAO("data/biomes.csv");

        habitatDAO =
                new HabitatDAO(
                        "data/habitats.csv",
                        biomeDAO
                );

        mammalDAO =
                new MammalDAO(
                        "data/mammals.csv",
                        habitatDAO
                );

        birdDAO =
                new BirdDAO(
                        "data/birds.csv",
                        habitatDAO
                );

        fishDAO =
                new FishDAO(
                        "data/fishes.csv",
                        habitatDAO
                );

        reptileDAO =
                new ReptileDAO(
                        "data/reptiles.csv",
                        habitatDAO
                );

        amphibianDAO =
                new AmphibianDAO(
                        "data/amphibians.csv",
                        habitatDAO
                );

        animalRepository =
                new AnimalRepository(
                        mammalDAO,
                        birdDAO,
                        fishDAO,
                        reptileDAO,
                        amphibianDAO
                );
    }

    public void start() {

        LOGIN loginView =
                new LOGIN();

        new controllerLOGIN(
                loginView,
                authenticator,
                this
        );

        loginView.setVisible(true);
    }

    public void showMainWindow() {

        MAINVIEW mainView =
                new MAINVIEW();

        new controllerMAINVIEW(
                mainView,
                authenticator,
                this,
                animalRepository,
                biomeDAO,
                habitatDAO,
                mammalDAO,
                birdDAO,
                fishDAO,
                reptileDAO,
                amphibianDAO
        );

        mainView.setVisible(true);
    }
}