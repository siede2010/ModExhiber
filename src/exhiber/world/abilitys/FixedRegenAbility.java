package exhiber.world.abilitys;

import mindustry.entities.abilities.RegenAbility;

public class FixedRegenAbility extends RegenAbility {
    public FixedRegenAbility(float amount,float percentageAmount)
    {
        this.amount = amount;
        this.percentAmount = percentageAmount;
    }
}
