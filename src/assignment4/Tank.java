package assignment4;

public class Tank extends Critter {


        @Override
        public String toString() { return "T";}
        private int dir;

        public Tank(){
            dir = 1;
        }
<<<<<<< HEAD
        @Override
=======

>>>>>>> 8d898fa9261881329b370ca3a04d8199be9874f6
        public boolean fight(String not_used){

            return true;
        }
<<<<<<< HEAD
        @Override
        public void doTimeStep(){
            run(dir);
        }


        /*private void powerUp(){
            setEnergyCritPowerUp(getEnergy());
        }*/
=======

        public void doTimeStep(){
            run(dir);
            powerUp();
        }

        public void powerUp(){
            setEnergyCritPowerUp(getEnergy());
        }
>>>>>>> 8d898fa9261881329b370ca3a04d8199be9874f6

        public static void runStats(java.util.List<Critter> tanks){
            int max = 0;
            int tmp = 0;
            for(Object obj : tanks){
                Tank maxCrit = (Tank) obj;
                tmp = maxCrit.getEnergy();
                if(tmp > max){
                    max = tmp;
                }
            }

            System.out.print("" + tanks.size() + " total Tanks  ");
            System.out.print("Max Energy = " + max);//hi

        }
}
