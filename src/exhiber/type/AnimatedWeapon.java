package exhiber.type;

import arc.math.Angles;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.type.Weapon;

public class AnimatedWeapon extends Weapon {
    public float warmupXpos;
    public float warmupYpos;
    public AnimatedWeapon(String name)
    {
        super(name);
    }

    @Override
    public void update(Unit unit, WeaponMount mount)
    {
        super.update(unit,mount);
    }
}
