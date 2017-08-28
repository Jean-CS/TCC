package chapter5;

/**
 * Don't be daunted by the number of classes in this chapter -- most of them are
 * just simple containers for information, and only have a handful of properties
 * with setters and getters.
 * 
 * The real stuff happens in the GeneticAlgorithm class and the Timetable class.
 * 
 * The Timetable class is what the genetic algorithm is expected to create a
 * valid version of -- meaning, after all is said and done, a chromosome is read
 * into a Timetable class, and the Timetable class creates a nicer, neater
 * representation of the chromosome by turning it into a proper list of Classes
 * with rooms and professors and whatnot.
 * 
 * The Timetable class also understands the problem's Hard Constraints (ie, a
 * professor can't be in two places simultaneously, or a room can't be used by
 * two classes simultaneously), and so is used by the GeneticAlgorithm's
 * calcFitness class as well.
 * 
 * Finally, we overload the Timetable class by entrusting it with the
 * "database information" generated here in initializeTimetable. Normally, that
 * information about what professors are employed and which classrooms the
 * university has would come from a database, but this isn't a book about
 * databases so we hardcode it.
 * 
 * @author bkanber
 *
 */
public class TimetableGA {

    public static void main(String[] args) {
    	// Get a Timetable object with all the available information.
        Timetable timetable = initializeTimetable();
        
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(10, 0.1, 0.9, 2, 5);
        
        // Initialize population
        Population population = ga.initPopulation(timetable);
        
        // Evaluate population
        ga.evalPopulation(population, timetable);
        
        // Keep track of current generation
        int generation = 1;
        
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, 200) == false
            && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());

            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // Evaluate population
            ga.evalPopulation(population, timetable);

            // Increment the current generation
            generation++;
        }

        // Print fitness
        timetable.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + timetable.calcClashes());

        // Print classes
        System.out.println();
        Class classes[] = timetable.getClasses();
        int classIndex = 1;
        for (Class bestClass : classes) {
            System.out.println("Class " + classIndex + ":");
            System.out.println("Module: " + 
                    timetable.getModule(bestClass.getModuleId()).getModuleName());
            System.out.println("Group: " + 
                    timetable.getGroup(bestClass.getGroupId()).getGroupId());
            System.out.println("Professor: " + 
                    timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
            for (int timeslot : bestClass.getTimeslotId()) {
            	System.out.println("Time: " + 
                        timetable.getTimeslot(timeslot).getTimeslot());
            }
            System.out.println("-----");
            classIndex++;
        }
    }

    /**
     * Creates a Timetable with all the necessary course information.
     * 
     * Normally you'd get this info from a database.
     * 
     * @return
     */
	private static Timetable initializeTimetable() {
		// Create timetable
		Timetable timetable = new Timetable();

		// Set up rooms
		timetable.addRoom(1, "A1", 15);
//		timetable.addRoom(2, "B1", 30);
//		timetable.addRoom(4, "D1", 20);
//		timetable.addRoom(5, "F1", 25);

		// Set up timeslots		
		timetable.addTimeslot(1,  "Seg 07:35 - 08:15");
		timetable.addTimeslot(2,  "Seg 08:15 - 09:00");
		timetable.addTimeslot(3,  "Seg 09:00 - 09:45");
		timetable.addTimeslot(4,  "Seg 10:05 - 10:55");
		timetable.addTimeslot(5,  "Seg 10:55 - 11:35");
		
		timetable.addTimeslot(6,  "Ter 07:35 - 08:15");
		timetable.addTimeslot(7,  "Ter 08:15 - 09:00");
		timetable.addTimeslot(8,  "Ter 09:00 - 09:45");
		timetable.addTimeslot(9,  "Ter 10:05 - 10:55");
		timetable.addTimeslot(10,  "Ter 10:55 - 11:35");
		
		timetable.addTimeslot(11,  "Qua 07:35 - 08:15");
		timetable.addTimeslot(12,  "Qua 08:15 - 09:00");
		timetable.addTimeslot(13,  "Qua 09:00 - 09:45");
		timetable.addTimeslot(14,  "Qua 10:05 - 10:55");
		timetable.addTimeslot(15,  "Qua 10:55 - 11:35");
		
		timetable.addTimeslot(16,  "Qui 07:35 - 08:15");
		timetable.addTimeslot(17,  "Qui 08:15 - 09:00");
		timetable.addTimeslot(18,  "Qui 09:00 - 09:45");
		timetable.addTimeslot(19,  "Qui 10:05 - 10:55");
		timetable.addTimeslot(20,  "Qui 10:55 - 11:35");
		
		timetable.addTimeslot(21,  "Sex 07:35 - 08:15");
		timetable.addTimeslot(22,  "Sex 08:15 - 09:00");
		timetable.addTimeslot(23,  "Sex 09:00 - 09:45");
		timetable.addTimeslot(24,  "Sex 10:05 - 10:55");
		timetable.addTimeslot(25,  "Sex 10:55 - 11:35");

		// Set up professors
		timetable.addProfessor(1, "Flavio");
		timetable.addProfessor(2, "Augusto");
		timetable.addProfessor(3, "Luciana");
		timetable.addProfessor(4, "Gabriel");
		timetable.addProfessor(5, "Romualdo");
		timetable.addProfessor(6, "Adriana");

		// Set up modules and define the professors that teach them
		timetable.addModule(1, "mob", "Mobile", 3, new int[] { 1, 5 });
		timetable.addModule(2, "web", "Web", 4, new int[] { 2, 4, 6, 1 });
		timetable.addModule(3, "seg", "Segurança", 2, new int[] { 3 });
		timetable.addModule(4, "red", "Redes", 4, new int[] { 4 });
		timetable.addModule(5, "dis", "Distribudos", 4, new int[] { 2, 4 });
		timetable.addModule(6, "pad", "Padroes", 3, new int[] { 5, 1 });
		timetable.addModule(7, "sof", "Software", 3, new int[] { 6, 1, 5 });

		// Set up student groups and the modules they take.
		
		// 3 Ano
		timetable.addGroup(1, 10, new int[] { 1 , 2, 3, 4, 5, 6, 7});
		// 2 Ano
		timetable.addGroup(2, 10, new int[] { 1 , 2, 3, 4, 5, 6, 7});
		// 1 Ano
		timetable.addGroup(3, 10, new int[] { 1 , 2, 3, 4, 5, 6, 7});
		
		return timetable;
	}
}