package exhiber.world.statusEffect;

import arc.struct.Seq;
import mindustry.entities.abilities.Ability;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValue;
import mindustry.world.meta.StatValues;

import java.util.HashMap;

public class ComponentEffect extends StatusEffect {

    public Seq<Unit> unitTeam = new Seq<>();
    public Seq<Ability> abilities = new Seq<>();
    public ComponentEffect(String name)
    {
        super(name);
        permanent = true;
    }

    @Override
    public void setStats(){
        if (abilities.size != 0)
            for (Ability a : abilities)
            stats.add(Stat.abilities, StatValues.string(a.localized(),abilities));
        super.setStats();
    }

    @Override
    public void init()
    {
        permanent = true;
    }
    public void start(Unit unit)
    {
        unitTeam.add(unit);
        Ability[] abilite = new Ability[unit.abilities.length+abilities.size];
        int i = 0;
        for (Ability a : unit.abilities)
            abilite[i++] = a;
        for (Ability a : abilities)
            abilite[i++] = a;
        unit.abilities = abilite;
    }

    @Override
    public void update(Unit unit, float time){
        if (!unitTeam.contains(unit))
            start(unit);
        super.update(unit,time);
    }
}
