package exhiber.world.abilitys;

import arc.util.Time;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;

public class DamageAbility extends Ability {
    public float damage = 1;
    float timer = 0;
    public DamageAbility(float damage)
    {
        this.damage = damage;
    }

    @Override
    public void update(Unit unit){
        timer += Time.delta;

        if(timer >= 60) {
            timer %= 60;
            unit.health -= damage;
        }
    }
}
