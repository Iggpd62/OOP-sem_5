
abstract class BaseHero implements CharacterInterface {
    protected String name;
    protected int health;
    protected int speed;
    protected Coordinates coordinates;

    protected BaseHero(String name, int health, int speed, int x, int y) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.coordinates = new Coordinates(x, y);
    }


    public void attack() {
        System.out.println(name + " наносит удар!");
    }

    public void defend() {
        System.out.println(name + " защищается!");
    }

    public void move() {
        System.out.println(name + " двигается!");
    }
    public abstract void step();

    public String getInfo() {
        return getClass().getSimpleName();
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
}

