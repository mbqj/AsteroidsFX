package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.OutOfBoundsPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity enemy : world.getEntities(Enemy.class)) {
            OutOfBoundsPart outOfBoundsPart = enemy.getPart(OutOfBoundsPart.class);
            int dir = (int) Math.round(Math.random()*3);
            int shoot = (int) Math.round(Math.random()*30);
            if (dir == 1) {
                enemy.setRotation(enemy.getRotation() - 5);
            }else if (dir == 2) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            if (shoot == 1){
                for (BulletSPI e : getBulletSPIs()) {
                    world.addEntity(e.createBullet(enemy, gameData));
                }
            }
            outOfBoundsPart.process(gameData, enemy);

            if (outOfBoundsPart.isOutOfBounds()){
                if (enemy.getX() < 0) {
                    enemy.setX(gameData.getDisplayWidth());
                }

                if (enemy.getX() > gameData.getDisplayWidth()) {
                    enemy.setX(0);
                }

                if (enemy.getY() < 0) {
                    enemy.setY(gameData.getDisplayHeight());
                }

                if (enemy.getY() > gameData.getDisplayHeight()) {
                    enemy.setY(0);
                }
            }
            if (enemy.isHit()){
                enemy.setDestroyed(true);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
