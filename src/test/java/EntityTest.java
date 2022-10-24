import model.CardinalDirection;
import model.entity.LivingStates;
import model.entity.Player;
import model.entity.PlayerFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityTest {

    @Test
    public void movePlayer(){
        Player player = PlayerFactory.createPlayer();
        player.setDirection(CardinalDirection.NORTH);
        player.setState(LivingStates.RUNNING);
        var previousCoordinates = player.getPosition().getX();
        player.setVelX((float)player.getMovementSpeed() * 100);

        var currentCoordinates = player.getPosition().getX();
        Assertions.assertTrue(currentCoordinates != previousCoordinates);


    }

}
