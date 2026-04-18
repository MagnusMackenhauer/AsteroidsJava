package dk.sdu.bullet;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.commonbullet.Bullet;
import dk.sdu.commonbullet.BulletSPI;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public Entity createBullet(Entity shooter) {

        Bullet bullet = new Bullet();

        bullet.setRadius(2);

        double radians = Math.toRadians(shooter.getRotation() - 90);
        double bulletSpeed = 6.0;

        /*
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        */
        double spawnOffset = shooter.getRadius() + 5;
        bullet.setX(shooter.getX() + Math.cos(radians) * spawnOffset);
        bullet.setY(shooter.getY() + Math.sin(radians) * spawnOffset);
        bullet.setRotation(shooter.getRotation());

        bullet.setShape(new double[]{
                -2.0, -2.0,
                2.0, -2.0,
                2.0,  2.0,
                -2.0,  2.0
        });

        bullet.setDx(shooter.getDx() + Math.cos(radians) * bulletSpeed);
        bullet.setDy(shooter.getDy() + Math.sin(radians) * bulletSpeed);

        return bullet;
    }

    @Override
    public void process(GameData gameData, World world) {

        List<Entity> removeList = new ArrayList<>();

        for (Entity entity : world.getEntities()) {

            if (entity instanceof Bullet bullet) {

                bullet.setX(bullet.getX() + bullet.getDx());
                bullet.setY(bullet.getY() + bullet.getDy());

                bullet.decreaseLife();

                if (bullet.getLife() <= 0) {
                    removeList.add(bullet);
                }
                if (entity.isHit()) {
                    removeList.add(entity);
                }
                entity.setIsHit(false);
            }
        }

        for (Entity entity : removeList) {
            world.removeEntity(entity);
        }
    }
}