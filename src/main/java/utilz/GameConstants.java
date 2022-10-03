package utilz;

public enum GameConstants {
    ;
    public enum TileSizes {
        LENGTH(30),
        HEIGHT(18);
        int size;

        TileSizes(int size){
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
        HEIGHT(TileSizes.HEIGHT.getSize()*GameScalingFactors.TILE_SCALE_FACTOR.getSize()),
        WIDTH(TileSizes.LENGTH.getSize()*GameScalingFactors.TILE_SCALE_FACTOR.getSize());
        int size;

        GameSizes(int size){
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    private enum GameScalingFactors {
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
