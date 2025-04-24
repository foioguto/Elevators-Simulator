import dataStructure.Floor;
import run.Building;
import run.Elevator;

import java.util.Random;

public class Simulator {
    Floor floors[];
    Random random = new Random();
    Building building;
    Elevator elevator;

    public void startBuildingRandom(){
        int min = 2;
        int floorsNumber = 2 + random.nextInt(3);
        this.building = new Building(floorsNumber);
    }

    public void startBuildingManual(int floorsNumber){
        this.building = new Building(floorsNumber);
    }

    public void setUsersBuilding(){
        for(int i=0; i<building.getFloors().length; i++){
            building.getFloors()[i] = new Floor(i);
            building.getFloors()[i].setUsers(random.nextInt(4), building.getTotalFloors(), building.getFloors()[i].getFloor());
        }
    }

    public void setElevators(){
        elevator = new Elevator(8);
        building.setElevator(elevator);
    }

    public void startElevator(){
        elevator.moveUp(building);
    }

    public void generateNewUserRequests() {
        for (int i = 0; i < building.getFloors().length; i++) {
            Floor floor = building.getFloors()[i];

            // Adiciona novos usuários sem apagar os anteriores
            int newUsers = random.nextInt(3); // até 2 novos usuários
            floor.setAdditionalUsers(newUsers, building.getTotalFloors(), i);
        }
    }

    public void simulateElevatorRuns(int times) {
        System.out.println("🔁 Iniciando simulação com " + times + " ciclos de elevador...\n");

        for (int i = 1; i <= times; i++) {
            System.out.println("========== CICLO #" + i + " ==========");

            // Gera novas solicitações de usuários sem apagar os existentes
            generateNewUserRequests();

            // Elevador começa no andar atual (sem resetar)
            elevator.moveUp(building);
            elevator.resetSleepMode();

            System.out.println("========== FIM DO CICLO #" + i + " ==========\n");
        }

        System.out.println("✅ Simulação finalizada.");
    }

}
