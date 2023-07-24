// Класс, описывающий крестьянина
import java.util.List;
class Peasant extends BaseHero implements CharacterInterface {
    private int arrows;
    private List<CharacterInterface> heroes1;
    private List<CharacterInterface> heroes2;
    public Peasant (String name, int health, int speed, int x, int y, int arrows, List<CharacterInterface> heroes1, List<CharacterInterface> heroes2) {
        super(name, health, speed, x, y);
        this.arrows = arrows;
        this.heroes1 = heroes1;
        this.heroes2 = heroes2;
    }
    public void step() {
        if (health <= 0) {
            return;
        }

        if (arrows <= 0) {
            return;
        }

        CharacterInterface closestEnemy = findClosestEnemy();
        if (closestEnemy != null) {
            int averageDamage = calculateAverageDamage();
            closestEnemy.takeDamage(averageDamage);
        }

        arrows--;
    }

    private CharacterInterface findClosestEnemy() {
        CharacterInterface closestEnemy = null;
        double closestDistance = Double.MAX_VALUE;

        for (CharacterInterface enemy : heroes2) {
            double distance = Main.calculateDistance(this, enemy);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestEnemy = enemy;
            }
        }

        return closestEnemy;
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
    public void farm() {

        System.out.println(name + " занимается фермерством");
        System.out.println(name + " делает шаг!");
    }
}