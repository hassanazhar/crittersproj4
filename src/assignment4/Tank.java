//Hassan Chughtai hac865
//Ali Ahmed ama4943

package assignment4;

public class Tank extends Critter {


        @Override
        public String toString() { return "T";}
        private int dir;

        public Tank(){
            dir = 1;
        }

        @Override



        public boolean fight(String not_used){

            return true;
        }

        @Override
        public void doTimeStep(){
            run(dir);
        }


        /*private void powerUp(){
            setEnergyCritPowerUp(getEnergy());
        }*/

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
