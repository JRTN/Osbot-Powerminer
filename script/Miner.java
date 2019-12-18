package script;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import paint.Paint;
import util.Rock;
import util.Rocks;
import util.Sleep;

import java.util.LinkedList;
import java.util.List;

import static java.awt.event.KeyEvent.VK_SHIFT;

@ScriptManifest(author = "buttman4", name = "Simple PowerMiner", info ="Mines rocks", version = 1.0, logo = "")
public class Miner extends Script {

    private Area MINING_AREA;
    private Rock ROCK_TYPE;
    private List<String> DROP_LIST;

    @Override
    public void onStart() {
        Rocks.setMethodProvider(this);
        if(myPlayer() == null) stop();
        MINING_AREA = new Area(myPosition().getX() - 1, myPosition().getY() - 1,
                                myPosition().getX() + 1, myPosition().getY() + 1);
        ROCK_TYPE = Rock.IRON;
        DROP_LIST = new LinkedList<String>() {{
            add(ROCK_TYPE.ORE);
            add("Uncut sapphire");
            add("Uncut emerald");
            add("Uncut ruby");
            add("Uncut diamond");
            add("Unidentified minerals");
        }};

        getExperienceTracker().start(Skill.MINING);
        getBot().addPainter(new Paint(this));
    }

    @Override
    public int onLoop() throws InterruptedException {
        if(myPlayer() == null || !MINING_AREA.contains(myPosition())) {
            stop();
        }

        if(getInventory().isFull()) {
            powerDropAll(item -> DROP_LIST.contains(item.getName()));
        } else if(!myPlayer().isAnimating()) {
            Entity targetRock = Rocks.getClosestRock(ROCK_TYPE, MINING_AREA);
            if(targetRock != null) {
                targetRock.interact("Mine");
                Sleep.sleepUntil(() -> !Rocks.isType(ROCK_TYPE, targetRock) || myPlayer().isAnimating(), 1700);
            }
        }

        return 5;
    }

    private void powerDropAll(final Filter<Item> names) throws InterruptedException {
        getKeyboard().pressKey(VK_SHIFT);
        for (int i : new int[]{0, 1, 4, 5, 8, 9, 12, 13, 16, 17, 20, 21, 24, 25,
                                2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 26, 27}) {
            Item item = getInventory().getItemInSlot(i);
            if (item != null && names.match(item)) {
                getInventory().interact(i);
                sleep(random(35, 70));
            }
        }
        getKeyboard().releaseKey(VK_SHIFT);
    }
}
