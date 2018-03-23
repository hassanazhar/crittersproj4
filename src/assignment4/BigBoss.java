//Hassan Chughtai hac865
//Ali Ahmed ama4943
package assignment4;

public class BigBoss extends Critter {
    private int dir;
    private int strength =1000;
    public BigBoss(){
        dir = getRandomInt(8);
    }
    @Override
    public void doTimeStep() {
        if(getEnergy()>Params.run_energy_cost){
            run(dir);

        }
        if(getEnergy()<100){
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
        if(getEnergy()>Params.start_energy){
            return true;
        }
        if(opponent.equals("assignment4.BigBoss")){
            //run(0);
            return false;
        }
        return false;
    }
    public static void runStats(java.util.List<Critter> BigBoss){


        System.out.print("" + BigBoss.size() + " total BigBosses  ");

    }

    public String toString() {
        return "W";
    }

}
