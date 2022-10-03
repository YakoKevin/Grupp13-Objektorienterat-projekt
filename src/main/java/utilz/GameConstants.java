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
}
