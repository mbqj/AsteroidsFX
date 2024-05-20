package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.OutOfBoundsPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            OutOfBoundsPart outOfBoundsPart = bullet.getPart(OutOfBoundsPart.class);

            outOfBoundsPart.process(gameData, bullet);
            if (outOfBoundsPart.isOutOfBounds()){
                System.out.println("is out of bounds");
                bullet.setDestroyed(true);
            }
            double changeX = Math.cos(Math.toRadians(bullet.getRotation())) * bullet.getSpeedMult();
            double changeY = Math.sin(Math.toRadians(bullet.getRotation())) * bullet.getSpeedMult();
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);
            if (bullet.isHit()){
                bullet.setDestroyed(true);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity Bullet = new Bullet(shooter);
        Bullet.setX(shooter.getX());
        Bullet.setY(shooter.getY());
        Bullet.setRotation(shooter.getRotation());
        Bullet.setPolygonCoordinates(-3,-1, 3,-1, 3,1, -3,1);
        Bullet.setSpeedMult(5);
        Bullet.add(new OutOfBoundsPart());
        return Bullet;
    }

    private void setShape(Entity entity) {
    }

}
