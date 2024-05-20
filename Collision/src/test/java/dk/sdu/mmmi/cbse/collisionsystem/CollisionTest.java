package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.collisionsystem.CollisionSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionTest {
    Entity entity = new Entity();
    Entity entity1 = new Entity();

    @BeforeEach
    void setUp() {
        entity.setPolygonCoordinates(-5,-5,10,0,-5,5);
        entity.setSize(10);
        entity.setX(10);
        entity.setY(10);

        entity1.setPolygonCoordinates(-5,-5,10,0,-5,5);
        entity1.setSize(10);
        entity1.setX(10);
        entity1.setY(10);
    }

    @Test
    void colliding() {
        CollisionSystem cs = new CollisionSystem();
        assertTrue(cs.collision(entity, entity1));
    }
    @Test
    void notColliding(){
        CollisionSystem cs = new CollisionSystem();
        entity.setX(700);
        assertTrue(!cs.collision(entity, entity1));
    }
}
