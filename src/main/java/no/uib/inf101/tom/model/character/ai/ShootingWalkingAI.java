package no.uib.inf101.tom.model.character.ai;

import java.util.ArrayList;

import no.uib.inf101.tom.model.Coordinate;
import no.uib.inf101.tom.model.action.ActionCommand;
import no.uib.inf101.tom.model.action.Shoot;
import no.uib.inf101.tom.model.action.Walk;
import no.uib.inf101.tom.model.character.CharacterViewableModel;
import no.uib.inf101.tom.model.character.NPC;

public class ShootingWalkingAI extends AICommander{

    private ArrayList<Coordinate> route;
    private int current_route_index;
    private NPC me;

    private boolean firstCommand;

    public ShootingWalkingAI(ArrayList<Coordinate> route) {
        this.state = 0;
        this.stateAmount = 121; //two seconds worth of states.
        this.current_route_index = 0;
        this.firstCommand = true;

        this.route = route;
    }

    /**
     * gives the ai access to the npc its controlling. 
     * @param me the npc the ai is controlling. 
     */
    public void giveNpcAccess(NPC me) {
        this.me = me;
    }

    
    @Override
    protected ActionCommand getActionCommandForState(CharacterViewableModel model, int state) {
        //To make him walk when he spawns. 
        if (this.firstCommand) {
            this.firstCommand = false;
            return new ActionCommand(new Walk(), this.route.get(current_route_index));
        }
        //Normal actions:
        if (state % 30 == 0) {
            return new ActionCommand(new Shoot(), model.getPlayerCenter());
        } else if (standsStill(this.me)) {
            this.current_route_index ++;
            if (this.current_route_index >= this.route.size()) {
                this.current_route_index -= this.route.size();
            }
            return new ActionCommand(new Walk(), this.route.get(this.current_route_index));
        } else {
            return null;
        }
    }

    private boolean standsStill(NPC npc) {
        return npc.getViewableMovementVector().equals("[0,000, 0,000]");
    }

    @Override
    public String getCommanderName() {
        return "ShootingWalkingAI";
    }
    
}
