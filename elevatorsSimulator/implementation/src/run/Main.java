package run;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulator simulator = new Simulator();

        System.out.println("=== CONFIGURAÇÃO DO PRÉDIO E ELEVADOR ===");

        System.out.print("Digite o número de andares do prédio: ");
        int numFloors = scanner.nextInt();

        System.out.print("Digite a capacidade máxima do elevador: ");
        int numElevators = scanner.nextInt();

        System.out.print("Digite o número de execuções da simulação: ");
        int numRuns = scanner.nextInt();

        System.out.println("Digite o horário da simulação: ");
        int hours = scanner.nextInt();

        simulator.startBuildingManual(numFloors);
        simulator.setElevators(numElevators);

        if ( (hours < 14 && hours > 10) || (hours < 20 && hours > 16)){
            simulator.simulateElevatorRunsHot(numRuns);
        }else{
            simulator.simulateElevatorRuns(numRuns);
        }

        scanner.close();
    }
}