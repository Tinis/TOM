package no.uib.inf101.tom.model.cutscene;

/**
 * the same as buttonConsequence but for cutscenes 
 * (they're two different files for better documentation).
 * A cutscene consequence should either change to a different cutscene or change the gamestate. 
 */
public interface CutsceneConsequence {

    /**
     * executes the consequence of the cutscene. 
     */
    public void executeConsequence();
}
