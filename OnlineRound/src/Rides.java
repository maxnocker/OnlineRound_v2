/**
 * Created by MaxNockerHP on 01.03.2018.
 */
public class Rides {

    int startRow;
    int startColumn;
    int finishRow;
    int finishColumn;
    int earliestStart;
    int lastestFinish;
    int id;
    boolean assigned;
    Car calledCar;

    public Rides(int startRow, int startColumn, int finishRow, int finishColumn, int earliestStart, int lastestFinish, int id){
        this.id = id;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.finishRow = finishRow;
        this.finishColumn = finishColumn;
        this.earliestStart = earliestStart;
        this.lastestFinish = lastestFinish;
        assigned = false;
        calledCar = null;
    }

    public int getId() {
        return id;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getFinishRow() {
        return finishRow;
    }

    public void setFinishRow(int finishRow) {
        this.finishRow = finishRow;
    }

    public int getFinishColumn() {
        return finishColumn;
    }

    public void setFinishColumn(int finishColumn) {
        this.finishColumn = finishColumn;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public int getLastestFinish() {
        return lastestFinish;
    }

    public void setLastestFinish(int lastestFinish) {
        this.lastestFinish = lastestFinish;
    }

    public String toString(){
        return startRow + " " + startColumn + " " + finishRow + " " + finishColumn + " " + earliestStart + " " + lastestFinish;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCalledCar() {
        return calledCar;
    }

    public void setCalledCar(Car calledCar) {
        this.calledCar = calledCar;
    }
}
