package run;
import run.simulator.Simulator;
import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Console GUI");
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });

        System.setOut(printStream);
        System.setErr(printStream);

        Scanner scanner = new Scanner(System.in);
        Simulator simulator = new Simulator();

        int numFloors = Integer.parseInt(JOptionPane.showInputDialog("Number of floors in the building: "));

        int numElevators = Integer.parseInt(JOptionPane.showInputDialog("Maximum capacity of the elevator: "));

        int hours = Integer.parseInt(JOptionPane.showInputDialog("Simulation Time: "));

        int numRuns = Integer.parseInt(JOptionPane.showInputDialog("Number of simulation runs: "));

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