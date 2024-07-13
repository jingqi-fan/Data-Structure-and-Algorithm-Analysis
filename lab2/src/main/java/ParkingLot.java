import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class ParkingLot {
    private static final int MAX_CAPACITY = 5;
    private Stack<String> stack = new Stack<>();
    private HashMap<String, Integer> movements = new HashMap<>();

    public void simulateParkingLot() {
        String inputFile = "data.txt";
        String outputFile = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split(" ");
                String carNumber = tokens[0];
                String operation = tokens[1];

                if (operation.equals("arrives")) {
                    if (stack.size() >= MAX_CAPACITY) {
                        writer.write("Sorry " + carNumber + ", the lot is full\n");
                    } else {
                        stack.push(carNumber);
                        movements.put(carNumber, 0);
                    }
                } else if (operation.equals("departs")) {
                    int numMovements = 0;
                    Stack<String> tempStack = new Stack<>();
                    boolean carFound = false;

                    while (!carFound && !stack.isEmpty()) {
                        String car = stack.pop();
                        if (car.equals(carNumber)) {
                            carFound = true;
                            int NEWnumMovements = movements.get(car);
                            writer.write(car + " was moved " + NEWnumMovements + " times while it was here\n");
                        } else {
                            tempStack.push(car);
                            movements.put(car, movements.get(car) + 1);
                            numMovements++;
                        }
                    }

                    while (!tempStack.isEmpty()) {
                        stack.push(tempStack.pop());
                    }

                    if (!carFound) {
                        System.out.println("Car " + carNumber + " not found in the parking lot");
                    } else {
                        while (numMovements > 0) {
                            stack.push(stack.pop());
                            numMovements--;
                        }
                    }
                }
            }

            // Move all remaining cars out of the parking lot
            while (!stack.isEmpty()) {
                String car = stack.pop();
                int numMovements = movements.get(car);
                writer.write(car + " was moved " + numMovements + " times while it was here\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
