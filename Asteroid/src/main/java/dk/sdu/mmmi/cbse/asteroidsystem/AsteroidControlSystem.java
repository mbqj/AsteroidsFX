package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.HealthPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.OutOfBoundsPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Objects;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class AsteroidControlSystem implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {

        int spawnRate = (int) (Math.random() * 100);
        if (spawnRate == 1){
            System.out.println("new astroid");
            Entity astroid = createAsteroid(gameData);
            world.addEntity(astroid);
        }
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            OutOfBoundsPart outOfBoundsPart = asteroid.getPart(OutOfBoundsPart.class);
            HealthPart healthPart = asteroid.getPart(HealthPart.class);
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            outOfBoundsPart.process(gameData, asteroid);


            if (outOfBoundsPart.isOutOfBounds()){
                asteroid.setDestroyed(true);
            }

            if (asteroid.isHit()){
                int amount = 0;
                System.out.println(++amount);
                healthPart.process(gameData, asteroid);
                split(asteroid, gameData, asteroid.getPart(HealthPart.class), world);
                System.out.println("astroid has been hit");
                asteroid.setHit(false);
            }

        }



    }
    private Entity createAsteroid(GameData gameData) {
        System.out.println("pls");
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-20,2, -16,10, -4,16, 4,14, 8,6 ,8,-6 ,-6,-10 ,-16,-8);
        int[] cords = startCords(gameData, asteroid);
        asteroid.setSize(20);
        asteroid.setSpeedMult(10);
        asteroid.setX(cords[0]);
        asteroid.setY(cords[1]);
        asteroid.add(new OutOfBoundsPart());
        asteroid.add(new HealthPart(3));
        return asteroid;
    }
    public int[] startCords(GameData gameData, Entity entity){
        int x;
        int y;
        int[] cords = new int[2];
        int side = (int) (Math.random()*4);
        if (side==1){
            x = 0;
            y = (int) (Math.random()*gameData.getDisplayHeight());
            entity.setRotation(-90+Math.random()*180);
        } else if (side == 2) {
            x = gameData.getDisplayWidth();
            y = (int) (Math.random()*gameData.getDisplayHeight());
            entity.setRotation(90+Math.random()*180);
        } else if (side == 3) {
            y = 0;
            x = (int) (Math.random()*gameData.getDisplayWidth());
            entity.setRotation(90-Math.random()*180);
        }else{
            y = gameData.getDisplayHeight();
            x = (int) (Math.random()*gameData.getDisplayWidth());
            entity.setRotation(Math.random()*180);
        }
        cords[0] = x;
        cords[1] = y;
        return cords;
    }

    public void split(Entity asteroid, GameData gameData, HealthPart healthPart, World world){
        Entity asteroidChild = createAsteroid(gameData);
        Entity asteroidChild2 = createAsteroid(gameData);
        asteroidChild.setRotation(asteroid.getRotation()-30);
        asteroidChild2.setRotation(asteroid.getRotation()+30);
        asteroidChild.setX(asteroid.getX()+asteroidChild.getSize()+10);
        asteroidChild.setY(asteroid.getY()+asteroidChild.getSize()+10);
        asteroidChild.add(new HealthPart(healthPart));
        asteroidChild.add(new OutOfBoundsPart());
        asteroidChild2.setX(asteroid.getX()-asteroidChild.getSize());
        asteroidChild2.setY(asteroid.getY()-asteroidChild.getSize());
        asteroidChild2.add(new HealthPart(healthPart));
        asteroidChild2.add(new OutOfBoundsPart());
        double[] newCords = asteroidChild.getPolygonCoordinates();;
        for (int i = 0; i < asteroidChild.getPolygonCoordinates().length; i++){
            newCords[i] = newCords[i]/2d;
        }
        asteroidChild.setPolygonCoordinates(newCords);
        asteroidChild2.setPolygonCoordinates(newCords);
        asteroidChild2.setSize(asteroid.getSize()/2);
        asteroidChild.setSize(asteroid.getSize()/2);
        asteroid.setDestroyed(true);
        world.addEntity(asteroidChild);
        world.addEntity(asteroidChild2);
    }
}
