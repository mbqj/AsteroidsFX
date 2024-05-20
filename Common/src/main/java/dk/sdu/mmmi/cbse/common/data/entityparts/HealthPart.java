package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class HealthPart implements EntityPart{
    private int health;
    private int maxHealth;

    public HealthPart(int health){
        this.health = health;
        maxHealth = health;
    }
    public HealthPart(HealthPart healthPart){
        health = healthPart.getHealth();
    }
    @Override
    public void process(GameData gameData, Entity entity) {
        if (entity.isHit()){
            if (health <= 0){
                entity.setDestroyed(true);
            }else
                health-=1;
        }
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
