//Hassan Chughtai hac865
//Ali Ahmed ama4943
package assignment4;

public class Ali extends Critter {

    static int count = 0;
    @Override
    public String toString() { return "M";}
    private int dir;

    public Ali(){
        dir = Critter.getRandomInt(8);
    }

    public boolean fight(String not_used){
        if(dir%2 == 1 && this.getEnergy()>80) {
            return true;
        }else{
            return false;
        }
    }

    public void doTimeStep(){
        count = count + 1;
        run(dir);
        int tmp = Critter.getRandomInt(50);
        if(getEnergy()>Params.min_reproduce_energy){
            //System.out.println(("Come AT ME"));
            attractMate(tmp);
        }
    }

    public void attractMate(int success){
        if(success>35){
            Ali child = new Ali();
            reproduce(child, Critter.getRandomInt(8));
        }
    }

    public static void runStats(java.util.List<Critter> alis){
        System.out.print("" + alis.size() + " total Alis  ");
    }
}
