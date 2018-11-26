import java.util.ArrayList;

public class Car {

    private int carNr;
    private int[] position; //r,c
    private int state; //0 free 1 travel to start 2 travel to destination
    private int[] destination; //r, c
    private Rides ride;
    private ArrayList<Rides> finishedRides;
    private boolean free;
    private boolean called;

    public ArrayList<Rides> getFinishedRides() {
        return finishedRides;
    }

    public Car(int carNr) {

        this.carNr = carNr;
        this.position = new int[] {0, 0};
        this.state = 0;
        this.destination = new int[] {0, 0};
        this.finishedRides = new ArrayList<Rides>();

    }

    public boolean isDestinationEqualPosition() {
        if(this.position[0] == this.destination[0] && this.position[1] == this.destination[1]) {
            return true;
        } else {
            return false;
        }
    }


    public void assignRide(Rides ride) {
        this.state = 1;
        this.destination = new int[] {ride.getStartRow(), ride.getStartColumn()};
        this.ride = ride;
    }

    public void gotoRide(Rides ride) {
        this.ride = ride;
        this.destination = new int[] {ride.getStartRow(), ride.getStartColumn()};
    }


    public boolean isFree() {
        if(this.state == 0) {
            return true;
        } else {
            return false;
        }
    }


    public void setFree(boolean free){
        this.free = false;
    }

    public void tick(int time) {
        if (!this.isDestinationEqualPosition()) {
            if (!(this.position[0] == this.destination[0])) {
                if (this.destination[0] < this.position[0]) {
                    this.position[0]--;
                } else {
                    this.position[0]++;
                }
            } else {
                if (this.destination[1] < this.position[1]) {
                    this.position[1]--;
                } else {
                    this.position[1]++;
                }

            }

        }
        if (this.isDestinationEqualPosition() && state == 1  && time >= this.ride.getEarliestStart() && !this.ride.isAssigned()) {
            this.destination = new int[]{ride.getFinishRow(), ride.getFinishColumn()};
            this.ride.setAssigned(true);
            this.state = 2;

        }

        if (this.isDestinationEqualPosition() && state == 2) {
            this.finishedRides.add(this.ride);
            System.out.println("Car "+this.carNr+" finished ride "+this.ride.getId());
            this.state = 0;
            called = false;
            this.ride = null;

        }
    }


    public int getCarNr() {
        return carNr;
    }

    public void setCarNr(int carNr) {
        this.carNr = carNr;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int[] getDestination() {
        return destination;
    }

    public void setDestination(int[] destination) {
        this.destination = destination;
        if(this.state == 0) {
            this.state = 1;
        } else if(this.state == 1) {
            this.state = 2;
        }
    }

    public Rides getRide() {
        return ride;
    }

    public void setRide(Rides ride) {
        this.ride = ride;
    }

    public boolean isCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
        if(!called) {
            destination = this.position;
            this.state = 0;
        }
    }
}
