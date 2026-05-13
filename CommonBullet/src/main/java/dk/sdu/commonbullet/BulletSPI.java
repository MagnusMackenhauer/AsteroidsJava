package dk.sdu.commonbullet;

import dk.sdu.cbse.common.Entity;

public interface BulletSPI {
    Entity createBullet(Entity shooter, int collisionGroup);
}