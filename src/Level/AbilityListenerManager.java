package Level;

import java.util.ArrayList;
import java.util.List;

import Players.PlayerFireball;


public class AbilityListenerManager {
    // List of (probably) enemies that need to have a reaction to getting hit by an elemental ability.
    // Simple terms - if they should be reacting (taking damage, etc) to elemental abilities, you need to add them to this list. 
    // If you're confused, check BugEnemy's initialize block. It's the second line (first after the comment)
    private static List<AbilityListener> enemyListeners = new ArrayList<>();

    // List of elemental abilities that need to react to something from an enemy; currently water and fire ability
    private static List<AbilityListener> abilityListeners = new ArrayList<>();

    // Add a listener to the arraylist of enemies 
    public static void addEnemyListener(AbilityListener listener) {
        if (!enemyListeners.contains(listener)) {
            enemyListeners.add(listener);
        }
    }

    // Add a listener to the arraylist of elemental abilities
    public static void addAbilityListener(AbilityListener listener){
        if (!abilityListeners.contains(listener)){
            abilityListeners.add(listener);
        }
    }

    // Remove a listener from the arraylist of enemies.
    // not relevant right now i believe
    public static void removeListener(AbilityListener listener) {
        enemyListeners.remove(listener);
    }

    // broadcasts the fireball spawning to all listeners
    public static void fireballSpawned(PlayerFireball playerFireball) {
    for (AbilityListener listener : enemyListeners) {
            listener.fireballSpawned(playerFireball);
        }
    }

    // broadcasts the fireball despawning to all listeners
    public static void fireballDespawned() {
    for (AbilityListener listener : enemyListeners) {
            listener.fireballDespawned();
        }
    }

    // broadcasts the fact that the fireball killed an enemy to the fireball so that it disappears 
    public static void fireballKilledEnemy(){
    for (AbilityListener listener : abilityListeners){
            listener.fireballKilledEnemy();
        }
    }

   
}
    