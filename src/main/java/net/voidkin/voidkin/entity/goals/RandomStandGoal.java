package net.voidkin.voidkin.entity.goals;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.goal.Goal;
import net.voidkin.voidkin.entity.custom.cheesehorse.AbstractCheeseHorse;

public class RandomStandGoal extends Goal {
    private final AbstractCheeseHorse horse;
    private int nextStand;

    public RandomStandGoal(AbstractCheeseHorse horse) {
        this.horse = horse;
        this.resetStandInterval(horse);
    }

    public void start() {
        this.horse.standIfPossible();
        this.playStandSound();
    }

    private void playStandSound() {
        SoundEvent soundevent = this.horse.getAmbientStandSound();
        if (soundevent != null) {
            this.horse.playSound(soundevent);
        }

    }

    public boolean canContinueToUse() {
        return false;
    }

    public boolean canUse() {
        ++this.nextStand;
        if (this.nextStand > 0 && this.horse.getRandom().nextInt(1000) < this.nextStand) {
            this.resetStandInterval(this.horse);
            return !this.horse.isImmobile() && this.horse.getRandom().nextInt(10) == 0;
        } else {
            return false;
        }
    }

    private void resetStandInterval(AbstractCheeseHorse horse) {
        this.nextStand = -horse.getAmbientStandInterval();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
