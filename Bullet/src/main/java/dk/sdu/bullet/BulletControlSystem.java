package dk.sdu.bullet;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.interfaces.IBulletSPI;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

/*
 System der både:
 - opretter bullets
 - flytter bullets
 - fjerner bullets
*/
public class BulletControlSystem implements IEntityProcessingService, IBulletSPI {

    /*
     Opretter et bullet når spilleren skyder
    */
    @Override
    public Entity createBullet(Entity shooter) {

        Bullet bullet = new Bullet();

        double radians = Math.toRadians(shooter.getRotation() - 90);
        double bulletSpeed = 6.0;

        // startposition = spillerens position
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());

        bullet.setRotation(shooter.getRotation());

        // lille firkant som bullet
        bullet.setShape(new double[]{
                -2.0, -2.0,
                2.0, -2.0,
                2.0,  2.0,
                -2.0,  2.0
        });

        // bullet arver lidt af spillerens fart + sin egen
        bullet.setDx(shooter.getDx() + Math.cos(radians) * bulletSpeed);
        bullet.setDy(shooter.getDy() + Math.sin(radians) * bulletSpeed);

        return bullet;
    }

    /*
     Kaldes hver frame af game loop
     Flytter bullets og fjerner dem når life er 0
    */
    @Override
    public void process(GameData gameData, World world) {

        List<Entity> removeList = new ArrayList<>();

        for (Entity entity : world.getEntities()) {

            if (entity instanceof Bullet bullet) {

                // flyt bullet
                bullet.setX(bullet.getX() + bullet.getDx());
                bullet.setY(bullet.getY() + bullet.getDy());

                // reducer levetid
                bullet.decreaseLife();

                // fjern bullet når life er slut
                if (bullet.getLife() <= 0) {
                    removeList.add(bullet);
                }
            }
        }

        // fjern bullets fra world
        for (Entity entity : removeList) {
            world.removeEntity(entity);
        }
    }
}
