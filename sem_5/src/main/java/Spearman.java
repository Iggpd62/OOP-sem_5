import java.util.List;

class Spearman extends BaseHero implements CharacterInterface {
    private int arrows;
    private final List<CharacterInterface> heroes1;
    private final List<CharacterInterface> heroes2;
    private CharacterInterface nearestEnemy;

    public double calculateDistance(Coordinates c1, Coordinates c2) {
        // реализация метода calculateDistance()
        // например, вычисление расстояния между координатами c1 и c2
        return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) + Math.pow(c2.getY() - c1.getY(), 2));
    }

    public Spearman(String name, int health, int speed, int x, int y, int arrows, List<CharacterInterface> heroes1, List<CharacterInterface> heroes2) {
        super(name, health, speed, x, y);
        this.arrows = arrows;
        this.heroes1 = heroes1;
        this.heroes2 = heroes2;
    }

    public void step() {
        if (health <= 0) {
            System.out.println(name + " погиб, не может совершать ход.");
            return;
        }

        if (arrows <= 0) {
            System.out.println(name + " не осталось стрел, не может совершать ход.");
            return;
        }

        CharacterInterface nearestEnemy = findNearestEnemy();
        if (nearestEnemy == null) {
            System.out.println("Нет врагов вокруг " + name + ", не может совершать ход.");
            return;
        }

        moveTowardsEnemy(nearestEnemy);

        if (isCharacterOnTile(nearestEnemy.getCoordinates().getX(), nearestEnemy.getCoordinates().getY())) {
            System.out.println(name + " не может двигаться на клетку с живым персонажем.");
            return;
        }

        if (calculateDistance(nearestEnemy.getCoordinates().getX(), nearestEnemy.getCoordinates().getY()) == 1) {
            attackEnemy(nearestEnemy);
        }

        arrows--;
    }

    public void attack(CharacterInterface enemy) {
    }

    private CharacterInterface findNearestEnemy() {
        CharacterInterface nearestEnemy = null;
        double closestDistance = Double.MAX_VALUE;

        for (CharacterInterface enemy : heroes2) {
            assert false;
            double distance = calculateDistance(enemy.getCoordinates().getX(), nearestEnemy.getCoordinates().getY());
            if (distance < closestDistance) {
                closestDistance = distance;
                nearestEnemy = enemy;
            }
        }

        return nearestEnemy;
    }

    private void moveTowardsEnemy(CharacterInterface enemy) {
        int enemyX = enemy.getCoordinates().getX();
        int enemyY = enemy.getCoordinates().getY();

        int deltaX = enemyX - coordinates.getX();
        int deltaY = enemyY - coordinates.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            coordinates.setX(coordinates.getX() + Integer.compare(deltaX, 0));
        } else {
            coordinates.setY(coordinates.getY() + Integer.compare(deltaY, 0));
        }
        System.out.println(name + " двигается в сторону врага.");
    }


    private boolean isCharacterOnTile(int posX, int posY) {
        for (CharacterInterface hero : heroes1) {
            if (hero.getCoordinates().getX() == posX && hero.getCoordinates().getY() == posY) {
                return true;
            }
        }

        return false;
    }

    private double calculateDistance(int posX, int posY) {
        int deltaX = Math.abs(posX - getCoordinates().getX());
        int deltaY = Math.abs(posY - getCoordinates().getY());

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private void attackEnemy(CharacterInterface enemy) {
        int damage = calculateAverageDamage();
        enemy.takeDamage(damage);
        System.out.println(name + " атакует врага " + enemy.getInfo() + " и наносит " + damage + " урона.");
    }

    private int calculateAverageDamage() {
        // Расчет среднего повреждения
        return 15;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(name + " погиб!");
        }
    }
}