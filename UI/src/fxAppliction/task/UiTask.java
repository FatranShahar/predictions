package fxAppliction.task;

import dto.implement.AllSimulationDetailsDTO;
import engine.EngineDefinition;

import engine.simulator.api.SimulationStatus;
import fxAppliction.app.AppController;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class UiTask extends Task<Boolean> {

    private Integer simulationID;
    private EngineDefinition engine;
    private AppController mainController;

    private AllSimulationDetailsDTO allSimulationData;

    public UiTask(Integer simulationID, EngineDefinition engine, AppController mainController) {
        this.simulationID = simulationID;
        this.engine = engine;
        this.mainController = mainController;
    }

    @Override
    protected Boolean call() throws Exception {

        while (this.engine.getSimulatorAllDataDTO(this.simulationID).getStatus() != SimulationStatus.ENDED) {

            this.engine.getSimulatorAllDataDTO(this.simulationID);
            Platform.runLater(()->this.mainController.getResultsComponentController().updateProgressSimulationDetails());

            Thread.sleep(300);
        }
        return true;
    }

}
