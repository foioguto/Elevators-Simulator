package gui;
import run.Building;
import run.Elevator;
import run.ElevatorListener;
import run.Floor;
import run.dataStructure.UserQueue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BuildingVisualization implements ElevatorListener {
    private JFrame frame;
    private Map<Integer, JPanel> floorPanels = new HashMap<>();
    private Map<Integer, Integer> elevatorPositions = new HashMap<>(); // Rastreia a posição de cada elevador
    private Map<Integer, Elevator.ElevatorState> elevatorStates = new HashMap<>(); // Rastreia o estado de cada elevador

    public void visualizeBuilding(Building building) {
        frame = new JFrame("Prédio com Elevadores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Adiciona andares
        for (int i = building.getTotalFloors()-1; i >= 0; i--) {
            addFloorPanel(building.getFloor(i));
        }

        // Registra listeners dos elevadores
        for (int i = 0; i < building.getNumElevators(); i++) {
            building.getElevator(i).addListener(this);
        }

        frame.pack();
        frame.setVisible(true);
    }

    private void addFloorPanel(Floor floor) {
        JPanel panel = createFloorPanel(floor);
        floorPanels.put(floor.getFloor(), panel);
        frame.add(panel);
    }

    @Override
    public void onElevatorMoved(int elevatorNumber, int currentFloor, Elevator.ElevatorState state) {
        // Atualiza a posição e estado do elevador
        elevatorPositions.put(elevatorNumber, currentFloor);
        elevatorStates.put(elevatorNumber, state);

        SwingUtilities.invokeLater(() -> {
            // Atualiza todos os andares
            for (Map.Entry<Integer, JPanel> entry : floorPanels.entrySet()) {
                int floorNumber = entry.getKey();
                JPanel panel = entry.getValue();

                // Obtém o painel de elevadores
                JPanel elevatorPanel = (JPanel) panel.getComponent(1);
                elevatorPanel.removeAll();

                // Verifica se há elevador neste andar
                for (Map.Entry<Integer, Integer> pos : elevatorPositions.entrySet()) {
                    if (pos.getValue() == floorNumber) {
                        JLabel elevatorLabel = new JLabel("Elevador " + pos.getKey());
                        String icon = elevatorStates.get(pos.getKey()) == Elevator.ElevatorState.UP ? "↑" : "↓";
                        elevatorLabel.setIcon(new TextIcon(icon, Color.RED, 15));
                        elevatorPanel.add(elevatorLabel);
                    }
                }
                panel.revalidate();
                panel.repaint();
            }
        });
    }

    @Override
    public void onUsersChanged(int elevatorNumber, UserQueue currentUsers, int currentFloor) {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = floorPanels.get(currentFloor);
            if (panel != null) {
                // Atualiza a contagem de usuários
                JLabel floorLabel = (JLabel) panel.getComponent(0);
                floorLabel.setText("Andar " + currentFloor + " - " + currentUsers.getSize() + " usuários");
                panel.repaint();
            }
        });
    }

    private static JPanel createFloorPanel(Floor floor) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha usuários
                int userCount = floor.getUserCount();
                for (int i = 0; i < userCount; i++) {
                    g.setColor(new Color(0, 31, 98));
                    g.fillOval(10 + (i * 20), 40, 15, 15);
                }
            }
        };

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(new Dimension(400, 80));

        // Label do andar
        JLabel floorLabel = new JLabel("Andar " + floor.getFloor() + " - " + floor.getUserCount() + " usuários");
        panel.add(floorLabel, BorderLayout.NORTH);

        // Painel para elevadores (será atualizado dinamicamente)
        JPanel elevatorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        elevatorPanel.setOpaque(false);
        panel.add(elevatorPanel, BorderLayout.CENTER);

        return panel;
    }
}
