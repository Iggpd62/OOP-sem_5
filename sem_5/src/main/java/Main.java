
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

interface CharacterInterface {
    void takeDamage(int damage);
    void step();
    String getInfo();
    Coordinates getCoordinates();
    void attack(CharacterInterface enemy);
    double calculateDistance(Coordinates c1, Coordinates c2);
}

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static double calculateDistance(Coordinates c1, Coordinates c2) {
        // ... реализация расчета расстояния между координатами
        int dx = c2.getX() - c1.getX();
        int dy = c2.getY() - c1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        List<CharacterInterface> heroes1 = new ArrayList<>();
        List<CharacterInterface> heroes2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            heroes1.add(new Peasant("Крестьянин" + (i + 1), 100, 5, 10, 20, 30, heroes1,heroes2));
            heroes2.add(new Rogue("Разбойник" + (i + 1), 150, 10, 10, 20, 30, heroes1,heroes2));
            heroes2.add(new Sniper("Снайпер" + (i + 1), 130, 8,10, 20, 30, heroes1,heroes2));
            heroes2.add(new Warlock("Колдун" + (i + 1), 80, 6, 10, 20, 30, heroes1,heroes2));
            heroes1.add(new Spearman("Клейщик" + (i + 1), 150, 7, 10, 20, 30, heroes1,heroes2));
            heroes1.add(new Crossbowman("Арбалетчик" + (i + 1), 120, 8, 10, 20, 30, heroes1,heroes2));
            heroes1.add(new Monk("Монах" + (i + 1), 90, 5, 10, 20, 30, heroes1,heroes2));
        }

        List<Circle> circles1 = new ArrayList<>();
        for (CharacterInterface hero : heroes1) {
            Circle circle = new Circle(hero.getCoordinates().getX() * 50 + 50, hero.getCoordinates().getY() * 50 + 50, 20, Color.BLUE);
            circles1.add(circle);
            root.getChildren().add(circle);
        }
        List<Circle> circles2 = new ArrayList<>();
        for (CharacterInterface hero : heroes2) {
            Circle circle = new Circle(hero.getCoordinates().getX() * 50 + 50, hero.getCoordinates().getY() * 50 + 50, 20, Color.RED);
            circles2.add(circle);
            root.getChildren().add(circle);
        }

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                for (CharacterInterface hero : heroes1) {
                    hero.step();
                }
                for (CharacterInterface hero : heroes2) {
                    hero.step();
                }

                for (CharacterInterface hero : heroes1) {
                    CharacterInterface closestEnemy = null;
                    double closestDistance = Double.MAX_VALUE;

                    for (CharacterInterface enemy : heroes2) {
                        double distance = calculateDistance(hero.getCoordinates(), enemy.getCoordinates());
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestEnemy = enemy;
                        }
                    }
                    if (closestEnemy != null) {
                        hero.attack(closestEnemy);
                    }
                }

                for (CharacterInterface hero : heroes2) {
                    CharacterInterface closestEnemy = null;
                    double closestDistance = Double.MAX_VALUE;
                    for (CharacterInterface enemy : heroes1) {
                        double distance = calculateDistance(hero.getCoordinates(), enemy.getCoordinates());
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestEnemy = enemy;
                        }
                    }
                    if (closestEnemy != null) {
                        hero.attack(closestEnemy);
                    }
                }
            }
        };

        timer.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}