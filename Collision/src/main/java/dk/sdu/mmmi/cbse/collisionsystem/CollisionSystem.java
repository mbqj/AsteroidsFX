package dk.sdu.mmmi.cbse.collisionsystem;


import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.OutOfBoundsPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionSystem implements IPostEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()){
            for (Entity entity1 : world.getEntities()){
                if (entity instanceof Bullet && ((Bullet) entity).shooter == entity1)
                    continue;
                if (entity1 instanceof Bullet && ((Bullet) entity1).shooter == entity)
                    continue;
                if (collision(entity, entity1)) {
                    entity.setHit(true);
                    entity1.setHit(true);
                    break;
                }
            }
        }
    }

    public boolean collision (Entity entity, Entity entity1){
        int distance = (int) (Math.sqrt(Math.pow(entity.getX()-entity1.getX(), 2) + Math.pow(entity.getY()-entity1.getY(), 2)));
        if (distance<=entity.getSize()+entity1.getSize() && entity != entity1){
            return true;
        }
        else
            return false;
    }


}
