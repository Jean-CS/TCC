package chapter5;

/**
 * Simple course module abstraction, which defines the Professors teaching the module and its duration.
 */
public class Module {
    private final int moduleId;
    private final String moduleCode;
    private final String module;
    private final int professorIds[];
    private final int duration;
    
    /**
     * Initialize new Module
     * 
     * @param moduleId
     * @param moduleCode
     * @param module
     * @param duration
     * @param professorIds
     */
    public Module(int moduleId, String moduleCode, String module, int duration, int professorIds[]){
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.module = module;
        this.duration = duration;
        this.professorIds = professorIds;
    }
    
    /**
     * Get moduleId
     * 
     * @return moduleId
     */
    public int getModuleId(){
        return this.moduleId;
    }
    
    /**
     * Get module code
     * 
     * @return moduleCode
     */
    public String getModuleCode(){
        return this.moduleCode;
    }
    
    /**
     * Get duration
     * 
     * @return duration
     */
    public int getDuration(){
        return this.duration;
    }
    
    /**
     * Get module name
     * 
     * @return moduleName
     */
    public String getModuleName(){
        return this.module;
    }
    
    /**
     * Get random professor Id
     * 
     * @return professorId
     */
    public int getRandomProfessorId(){
        int professorId = professorIds[(int) (professorIds.length * Math.random())];
        return professorId;
    }
}