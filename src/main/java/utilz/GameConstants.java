package utilz;

public enum GameConstants {
    ;
    public enum Sizes {
        LENGTH(30),
        HEIGHT(18);
        int size;

        Sizes(int size){
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
