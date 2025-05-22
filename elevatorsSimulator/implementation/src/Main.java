import javax.swing.*;
import java.awt.*;
import gui.BuildingVisualization;
import run.Elevator;
import simulator.EventsList;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Minha Janela Swing");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        EventsList eventsList = new EventsList();
        eventsList.callTimeEvents();

     }

}
