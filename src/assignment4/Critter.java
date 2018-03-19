package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	private int energy = 0;
	protected int getEnergy() { return energy; }
	private int x_coord;
	private int y_coord;

	//DONE UPDATED
	protected final void walk(int direction) {
		//Remember: world starts at 0,0, which is top left
		energy=energy-Params.walk_energy_cost;
		int x = x_coord;
		int y= y_coord;
		switch(direction){
			case (0)://E
				x_coord=torusx(1,x);
			case(1)://NE
				x_coord=torusx(1,x);
				y_coord=torusy(-1,y);
			case(2)://N
				y_coord=torusy(-1,y);
			case(3)://NW
				x_coord=torusx(-1,x);
				y_coord=torusy(-1,y);
			case(4)://W
				x_coord=torusx(-1,x);
			case(5)://SW
				x_coord=torusx(-1,x);
				y_coord=torusy(1,y);
			case(6)://S
				y_coord=torusy(1,y);
			case(7)://SE
				x_coord=torusx(1,x);
				y_coord=torusy(1,y);
		}

	}
	//Stage2 DONE UPDATED
	protected final void run(int direction) {
		energy = energy-Params.run_energy_cost;
		int x= x_coord;
		int y = y_coord;
		switch(direction){
			case(0):
				x_coord=torusx(2,x);

			case(1):
				x_coord=torusx(2,x);
				y_coord=torusy(-2,y);
			case(2):
				y_coord=torusy(-2,y);
			case(3):
				x_coord=torusx(-2,x);
				y_coord=torusy(-2,y);
			case(4):
				x_coord=torusx(-2,x);
			case(5):
				x_coord=torusx(-2,x);
				y_coord=torusy(2,y);
			case(6):
				y_coord=torusy(2,y);
			case(7):
				x_coord=torusx(2,x);
				y_coord=torusy(2,y);


		}
	}
	//DONE UPDATED
	protected final void reproduce(Critter offspring, int direction) {
		if (this.energy<Params.min_reproduce_energy||!this.isAlive){
			return;
		}
		else{
			offspring.energy= this.energy/2;
			double energyroundup=Math.ceil(this.energy/2);
			this.energy=(int)energyroundup;
			int x = this.x_coord;
			int y = this.y_coord;
			switch(direction){
				case(0):
					offspring.x_coord=torusx(1, x);
					offspring.y_coord=y;
				case(1)://NE
					offspring.x_coord=torusx(1,x);
					offspring.y_coord=torusy(-1,y);
				case(2)://N
					offspring.x_coord=x;
					offspring.y_coord=torusy(-1,y);
				case(3)://NW
					offspring.x_coord=torusx(-1,x);
					offspring.y_coord=torusy(-1,y);
				case(4)://W
					offspring.x_coord=torusx(-1,x);
					offspring.y_coord=y;
				case(5)://SW
					offspring.x_coord=torusx(-1,x);
					offspring.y_coord=torusy(1,y);
				case(6)://S
					offspring.x_coord=x;
					offspring.y_coord=torusy(1,y);
				case(7)://SE
					offspring.x_coord=torusx(1,x);
					offspring.y_coord=torusy(1,y);

			}
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	//MY NEW PRIVATE METHODS/ CONSTANTS
	//TORUS WORLD
	//H STAGE 1
	private static String [] realCritters = {"assignment4.Algae","assignment4.Craig","Assignment4.MyCritter1","Assignment4.MyCritter6","Assignment4.MyCritter7"};
	private final int torusx(int moves, int xcoord){
		if ((xcoord+moves)>(Params.world_width-1)){
			return(moves-1);

		}
		else if((xcoord+moves)<0){
			return(Params.world_width-moves);
		}
		else{
			xcoord+=moves;
			x_coord+=moves;//need to update the variable as well
			return(xcoord);
		}
	}
	private final int torusy(int moves, int ycoord){
		if((ycoord+moves)<0){
			return(Params.world_height-moves);
		}
		else if((Params.world_height-1)<(ycoord+moves)){
			return(moves-1);

		}
		else{
			ycoord+=moves;
			y_coord=y_coord+moves; //need to update y_coord as well
			return(ycoord);
		}
	}
	private boolean isAlive;

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	//DONE UPDATED
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		//Need to make sure critter is real
		Class<?> myCritClass = null;
		Object obj;
		Constructor<?> consttr;
		try{
            myCritClass =Class.forName(critter_class_name);
            consttr=myCritClass.getConstructor();
            obj= consttr.newInstance();
			Critter c = (Critter) obj; //IS THIS OK?
			c.energy=Params.start_energy;
			c.x_coord= getRandomInt(Params.world_width);
			c.y_coord=getRandomInt(Params.world_height);
			c.isAlive= true;
			//add more stuff as necessary
			population.add(c);

		}catch(ClassNotFoundException e) {
			System.out.println("ERROR 1");
			throw new InvalidCritterException(critter_class_name);
		}
		catch(NoSuchMethodException e){
			System.out.println("ERROR 2");
			throw new InvalidCritterException(critter_class_name);
		}
		catch (IllegalAccessException e){
		    System.out.println("ERROR 3");
		    throw new InvalidCritterException(critter_class_name);
        }
        catch(InvocationTargetException e){
			System.out.println("ERROR 4");
			throw new InvalidCritterException(critter_class_name);

		}
        catch(InstantiationException e){
		    System.out.println("ERROR 5");
		    throw new InvalidCritterException(critter_class_name);
        }

		//if valid, put it in pop. Make new class for critter_class_name
	}
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	//DONE
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		Class<?> crit = null;
		try{
			crit=Class.forName(critter_class_name);
		}
		catch (ClassNotFoundException e){
			throw new InvalidCritterException(critter_class_name);
		}
		for(Critter i: population){
			if(crit.isInstance(i)){
				result.add(i);
			}
		}

		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/* the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here.
	 *
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}


		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	//DONE
	public static void clearWorld() {
		// Complete this method.
		for(Critter i: population){
			population.remove(i);
		}
		for(Critter j: babies){
			babies.remove(j);
		}


	}

	//Add code for encounter resolution b/w critters in same loc
	//Add code for Reproduce. Add new critters to Population
	//Add code to remove dead critters
	// Complete this method.
	public static void worldTimeStep() {
	    for (Critter i : Critter.population){
	        if(i.energy<=0){
	            i.isAlive=false;
	            Critter.population.remove(i);
            }
            else if(i.energy>0){
	            i.isAlive=true;
                i.doTimeStep();
            }
        }


	}

	public static void displayWorld() {
		// Complete this method.
	}
}
