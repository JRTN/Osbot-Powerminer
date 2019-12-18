package paint;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.ExperienceTracker;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class Paint implements Painter {

    MethodProvider methodProvider;

    public Paint(MethodProvider provider) {
        methodProvider = provider;
    }

    @Override
    public void onPaint(Graphics2D g2) {
        //Render cursor
        Point mP = methodProvider.getMouse().getPosition();
        g2.setColor(Color.GREEN);
        g2.drawLine(mP.x, 0, mP.x, 500);
        g2.drawLine(0, mP.y, 800, mP.y);

        int y = 45;
        int x = 10;

        ExperienceTracker tracker = methodProvider.getExperienceTracker();

        g2.setColor(new Color(0, 0, 0, 0.40f));
        g2.fillRect(x - 5, y - 20, 110, 75);
        g2.setColor(Color.GREEN);
        g2.drawRect(x - 6, y - 21, 111, 76);
        g2.drawString("buttMiner", x, y);
        g2.drawString("Exp: " + tracker.getGainedXP(Skill.MINING), x, y += 20);
        g2.drawString("Exp/hr: " + tracker.getGainedXPPerHour(Skill.MINING), x, y += 20);

    }
}
