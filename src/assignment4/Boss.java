package assignment4;

import java.util.List;

public class Boss extends Critter{
    private int dir;
    private int strength =500;
    public Boss(){
        dir = getRandomInt(8);
    }
    @Override
    public void doTimeStep() {
        if(getEnergy()>Params.run_energy_cost){
            run(dir);

        }
        if(getEnergy()<Params.start_energy/2){
            int x = getRandomInt(8);
            for(int i =0; i<x;i++){
                Boss b1 = new Boss();
                dir = getRandomInt(8);
                reproduce(b1,dir);
            }
        }

        run(0);
    }

    @Override
    public boolean fight(String opponent) {
        //always fights unless another boss
        if(opponent.equals("project4.Boss")){
            //run(2);
            Boss one = new Boss();
            reproduce(one, (dir*2)%8);
            run(2);
            return false;
        }
        return true;
    }
    public static void runStats(java.util.List<Critter> Boss){


        System.out.print("" + Boss.size() + " total Bosses  ");

    }

    public String toString() {
        return "B";
    }

}
