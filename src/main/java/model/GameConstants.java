package model;

/**
 * Constants used throughout the game.
 */
public enum GameConstants {
    ;
    public enum RoomMapSizes {
        WIDTH(20),
        HEIGHT(12);
        int size;

        RoomMapSizes(int size){
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    public enum LevelSizes {
        SMALL(4),
        MEDIUM(5),
        LARGE(6);
        int size;

        LevelSizes(int size){
            this.size = size;
        }

        public int getSize(){return size;}
    }

    public enum GameSizes {
        HEIGHT(RoomMapSizes.HEIGHT.getSize()*GameScalingFactors.TILE_SCALE_FACTOR.getSize()),
        WIDTH(RoomMapSizes.WIDTH.getSize()*GameScalingFactors.TILE_SCALE_FACTOR.getSize());

        int size;

        GameSizes(int size){
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    public enum GameScalingFactors {
        TILE_SCALE_FACTOR(42); //1.3*32
        int size;

        GameScalingFactors(int size){
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
