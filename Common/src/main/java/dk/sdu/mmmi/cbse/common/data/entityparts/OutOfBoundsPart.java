package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class OutOfBoundsPart implements EntityPart{
    private boolean outOfBounds = false;
    @Override
    public void process(GameData gameData, Entity entity) {
        if(gameData.getDisplayWidth()<=entity.getX()) setOutOfBounds(true);
        else if (gameData.getDisplayHeight()<=entity.getY()) setOutOfBounds(true);
        else if (entity.getY()<=0) setOutOfBounds(true);
        else if (entity.getX()<=0) setOutOfBounds(true);
        else setOutOfBounds(false);
    }
    public boolean isOutOfBounds(){
        return outOfBounds;
    }

    public void setOutOfBounds(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
    }
}
