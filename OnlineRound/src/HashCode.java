import java.io.*;
import java.util.ArrayList;

/**
 * Created by MaxNockerHP on 01.03.2018.
 */
public class HashCode {

    public static int rows;
    public static int columns;
    public static int vehicles;
    public static int rides;
    public static int bonus;
    public static int steps;
    public static ArrayList<Rides> ridesList;
    public static ArrayList<Car> carList;

    public static String[] fileNames = new String[] {"a_example", "b_should_be_easy", "c_no_hurry", "d_metropolis", "e_high_bonus"};

    public static void main(String[] args){
        ridesList = new ArrayList<>();
        carList = new ArrayList<Car>();
        readFile();
        System.out.println("Rows: " + rows);
        System.out.println("Columns: " +columns);
        System.out.println("Vehicles: " + vehicles);
        System.out.println("Rides: " + rides);
        System.out.println("Bonus: " + bonus);
        System.out.println("Steps: " + steps);

        for(int tempCarCount = 0; tempCarCount < vehicles; tempCarCount++) {
            carList.add(new Car(tempCarCount));
        }

        for (Rides ride: ridesList) {
            System.out.println(ride.toString());
        }

        ArrayList<Car> freeCars = carList;
        ArrayList<Rides> availableRides = ridesList;
        for(int time = 0; time<steps; time++) {

            freeCars = getFreeCars();

            for (Car car : freeCars) {
                Rides bestRide = null;

                setRide(car, availableRides, time);
            }


            for(Car car: carList) {
                car.tick(time);
            }



        }

        String finalResult = "";
        for(Car car : carList) {
            finalResult += car.getFinishedRides().size()+" ";
            for(Rides ride:car.getFinishedRides()) {
                finalResult += ride.getId()+" ";
            }
            finalResult += "\n";

        }

        writeString(finalResult);


    }

    private static void setRide(Car car, ArrayList<Rides> availableRides, int time) {
        ArrayList<Rides> possibleRides = calculateBestPossibleRide(availableRides, car, time);


        boolean called = false;
        int i=0;

        while(i<possibleRides.size() && !called){
            Rides ride = possibleRides.get(i);
            int distanceToStart = distanceBetweenTwoPostions(car.getPosition(), new int[] {ride.getStartRow(), ride.getStartColumn()});
            int distanceCalledCarToStart = -1;
            boolean existCalledCar = false;
            if(ride.getCalledCar() != null) {
                distanceCalledCarToStart = distanceBetweenTwoPostions(ride.getCalledCar().getPosition(), new int[]{ride.getStartRow(), ride.getStartColumn()});
                existCalledCar = true;
            }
            if(!existCalledCar || distanceToStart < distanceCalledCarToStart){
                if(existCalledCar){
                    ride.getCalledCar().setCalled(false);
                }
                ride.setCalledCar(car);
                car.setCalled(true);
                car.assignRide(ride);
                called = true;
                if(existCalledCar){
                    setRide(ride.getCalledCar(), availableRides, time);
                }
            }
            i++;
        }
    }

    private static ArrayList<Rides> calculateBestPossibleRide(ArrayList<Rides> availableRides, Car car, int time) {
        ArrayList<Rides> possibleRides = new ArrayList<>();
        for (Rides ride: availableRides) {
            if(!ride.isAssigned()) {
                boolean added = false;
                int dist = distanceBetweenTwoPostions(car.getPosition(), new int[]{ride.getStartRow(), ride.getStartColumn()});
                int distRide = distanceBetweenTwoPostions(new int[]{ride.getStartRow(), ride.getStartColumn()}, new int[]{ride.getFinishRow(), ride.getFinishColumn()});
                int bestDist = -1;
                int bestRideDist = 0;

                int totalTime = time + dist + distRide;
                boolean possible = false;
                int arrivalTime = time + dist;

                if(arrivalTime< ride.getEarliestStart()){
                    totalTime = ride.getEarliestStart()+distRide;
                } else {
                    totalTime = arrivalTime + distRide;
                }

                if (totalTime < ride.getLastestFinish()) {
                    possible = true;
                }

                if (possible) {
                    for (int i = 0; i < possibleRides.size(); i++) {
                        Rides best = possibleRides.get(i);
                        bestDist = distanceBetweenTwoPostions(car.getPosition(), new int[]{best.getStartRow(), best.getStartColumn()});
                        bestRideDist = distanceBetweenTwoPostions(new int[]{best.getStartRow(), best.getStartColumn()}, new int[]{best.getFinishRow(), best.getFinishColumn()});
                        int bestEarliestStart = best.getEarliestStart();
                        int bestArrivalTime = time + bestDist;
                        int totalBestTime;
                        if(bestArrivalTime< bestEarliestStart){
                            totalBestTime = bestEarliestStart + bestRideDist;
                        } else {
                            totalBestTime = bestArrivalTime + bestRideDist;
                        }
                        if (totalTime < totalBestTime && !added) {
                            possibleRides.add(0, ride);
                            added = true;
                        }
                    }
                    if (!added) {
                        possibleRides.add(possibleRides.size(), ride);
                    }
                }
            }
        }
        return  possibleRides;
    }

    public static int distanceBetweenTwoPostions(int[] a, int[] b) {
        int rowDif = Math.abs(a[0] - b[0]);
        int colDif = Math.abs(a[1] - b[1]);

        return rowDif + colDif;
    }

    public static void writeString(String line) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out/v5/"+fileNames[4]+".out"))) {



            bw.write(line);

            // no need to close it.
            //bw.close();

            System.out.println("Saved");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void readFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("in/"+fileNames[4]+".in"))) {
            String line = br.readLine();

            String info[] = line.split(" ");
            rows = Integer.parseInt(info[0]);
            columns = Integer.parseInt(info[1]);
            vehicles = Integer.parseInt(info[2]);
            rides = Integer.parseInt(info[3]);
            bonus = Integer.parseInt(info[4]);
            steps = Integer.parseInt(info[5]);

            line = br.readLine();

            int lineCounter = 0;
            while (line != null) {
                String elements[] = line.split(" ");
                Rides ride = new Rides(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]),Integer.parseInt(elements[3]), Integer.parseInt(elements[4]), Integer.parseInt(elements[5]), lineCounter);
                ridesList.add(ride);
                line = br.readLine();
                lineCounter++;
            }

            br.close();

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static ArrayList<Car> getFreeCars() {
        ArrayList<Car> temp  = new ArrayList<>();
        for(Car car : carList){
            if(car.getState() == 0){
                temp.add(car);
            }
        }
        return temp;
    }
}
