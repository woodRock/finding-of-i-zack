package TheFindingOfIZack.Behaviour;

/**
 * Enum representation of different Mob Behaviours
 * Allows for easy and standard behaviour creation
 * @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public enum MobType {
    Fast {
        @Override
        public Mob constructMob() {
            return new MobFast();
        }
    },
    Shooter {
        @Override
        public Mob constructMob() {
            return new MobShooter();
        }
    },
    Slow {
        @Override
        public Mob constructMob() {
            return new MobSlow();
        }
    },
    Standard {
        @Override
        public Mob constructMob() {
            return new MobStandard();
        }
    },
    Boss {
        @Override
        public Mob constructMob() {
            return new MobBoss();
        }
    };


    /**
     * Create a mob of the type the enum represents
     * @return  the created mob
     */
    public abstract Mob constructMob();

    /**
     * Generate and return a MobType randomly
     * Excludes Boss type
     * @return
     */
    public static MobType generateRandomMob(){
            int type = (int) (Math.random()*5);
            if (type>2)         return Standard;
            else if (type==2)   return Fast;
            else if (type==1)   return Shooter;
            else                return Slow;
    }
}

