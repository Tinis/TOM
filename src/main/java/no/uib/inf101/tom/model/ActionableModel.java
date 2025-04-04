package no.uib.inf101.tom.model;

import no.uib.inf101.tom.model.box.HitBox;
import no.uib.inf101.tom.model.box.Projectile;

/**
 * A character needs some access to the model so that they 
 * can hit the other characters and stuff using their actions. 
 * That access is granted in an encapsulated way with this interface. 
 */
public interface ActionableModel {
    
    /**
     * hits the characters in the box with a differet good-value than the actorIsGood. 
     * @param hit the hitbox. 
     * @param actorIsGood the good-value of the actor (the one who performs/spawned the hit). 
     */
    public void hitCharactersInBox(HitBox hit, boolean actorIsGood, int strength);

    /**
     * adds a projectile to the model. (this projectile will be updated in the model's update method).
     * @param projectile the projectile to add to the model. 
     */
    public void addProjectile(Projectile projectile);
}
