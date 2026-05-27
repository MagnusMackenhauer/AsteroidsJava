package dk.sdu.bullet;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.entityparts.LifePart;
import dk.sdu.cbse.common.entityparts.MovingPart;
import dk.sdu.cbse.common.entityparts.PositionPart;
import dk.sdu.commonbullet.Bullet;
import dk.sdu.commonbullet.BulletSPI;
import dk.sdu.cbse.common.interfaces.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public Entity createBullet(Entity shooter, int collisionGroup) {
        Bullet bullet = new Bullet();

        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        MovingPart shooterMov = shooter.getPart(MovingPart.class);

        double radians = Math.toRadians(shooterPos.getRotation() - 90);
        double bulletSpeed = 6.0;
        double spawnOffset = shooter.getRadius() + 5;

        bullet.setRadius(2);
        bullet.setShape(new double[]{
                -2.0, -2.0,
                2.0, -2.0,
                2.0, 2.0,
                -2.0, 2.0
        });

        bullet.add(new PositionPart(
                shooterPos.getX() + Math.cos(radians) * spawnOffset,
                shooterPos.getY() + Math.sin(radians) * spawnOffset,
                shooterPos.getRotation()
        ));

        MovingPart movingPart = new MovingPart(1.0, 0, 10, 0);
        movingPart.setDx(shooterMov.getDx() + Math.cos(radians) * bulletSpeed);
        movingPart.setDy(shooterMov.getDy() + Math.sin(radians) * bulletSpeed);
        bullet.add(movingPart);

        bullet.add(new LifePart(120));

        bullet.setCollisionGroup(collisionGroup);

        return bullet;
    }

    @Override
    public void process(GameData gameData, World world) {

        List<Entity> removeList = new ArrayList<>();

        for (Entity entity : world.getEntities()) {

            if (entity instanceof Bullet) {

                entity.processAllParts(gameData);

                LifePart lifePart = entity.getPart(LifePart.class);
                if (lifePart.isExpired()) {
                    removeList.add(entity);
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
