package Level;


import Enemies.Beaker;
import Players.PlayerFireball;


// This listener will be used to broadcast and listen to events relating to the elemental abilities
public interface AbilityListener {
    void fireballSpawned(PlayerFireball fireball);
    void fireballDespawned();
    // wasn't sure what to name this - this should be broadcasted by an enemy if they get killed by the fireball
    // this will trigger the fireball to delete itself
    void fireballKilledEnemy();
    
    void beakerSpawned(Beaker beaker);
    void beakerDespawned();
    
    void beakerKilledEnemy();

}

