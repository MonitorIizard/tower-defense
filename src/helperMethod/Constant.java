package helperMethod;

public class Constant {
    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Tiles {
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;

        public static final int ROAD_TILE = 2;
        public static final int LOCATION = 3;
    }

    public static class Enemies {
        public static final int BANDIT = 0;
        public static final int EYE = 1;
        public static final int SPIDER = 2;
        public static final int KNIGHT = 3;
        public static final int BUSH = 4;

        public static float  GetSpeed(int enemyType) {
            switch (enemyType) {
                case BANDIT :
                    return 0.5f;
                case EYE :
                    return 1.0f;
                case SPIDER:
                    return 0.75f;
                case KNIGHT:
                    return 0.3f;
                case BUSH :
                    return 0.80f;

            }

            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case BANDIT:
                    return 100;
                case EYE:
                    return 50;
                case SPIDER:
                    return 79;
                case KNIGHT:
                    return 300;
                case BUSH:
                    return 150;
            }
            return 0;
        }
    }

    public static class Towers {
        public static final int ARCHER = 0;
        public static final int WIZARD = 1;

        public static String GetName( int towerType ) {
            switch (towerType) {
                case ARCHER :
                    return "ARCHER";
                case WIZARD :
                    return "WIZARD";
            }
            return null;
        }

        public static int GetDefaultDMG( int towerType ) {
            switch (towerType) {
                case ARCHER :
                    return 30;
                case WIZARD :
                    return 50;
            }
            return 0;
        }

        public static int GetDefaultRange( int towerType ) {
            switch (towerType) {
                case ARCHER :
                    return 200;
                case WIZARD :
                    return 150;
            }
            return 0;
        }

        public static int GetDefaultCooldown( int towerType ) {
            switch (towerType) {
                case ARCHER :
                    return 1;
                case WIZARD :
                    return 2;
            }
            return 0;
        }

    }
}
