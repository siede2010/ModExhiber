package exhiber.world.statusEffect;

import arc.Events;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.geom.Position;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import exhiber.content.EXEventType;
import mindustry.Vars;
import mindustry.entities.abilities.Ability;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Drawc;
import mindustry.gen.Entityc;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValue;
import mindustry.world.meta.StatValues;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ComponentEffect extends StatusEffect {

    HashMap<Unit,Seq<Ability>> units = new HashMap<>();
    public Seq<Ability> abilities = new Seq<>();
    Seq<Unit> toRemove = new Seq<>();
    public ComponentEffect(String name)
    {
        super(name);
        permanent = true;
        Events.run(EventType.Trigger.draw,() -> {
            if (units != null && units.size() > 0) {
                toRemove.clear();
                units.keySet().forEach(u -> {
                    if (u.isValid() && u != null)
                    units.get(u).forEach(a -> {
                        a.draw(u);
                    });
                    else toRemove.add(u);
                });
                toRemove.forEach(u -> {
                    units.remove(u);
                });
            }
            Draw.reset();
        });
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
        Seq<Ability> unitAbilitys = new Seq<>();
        abilities.forEach(a -> unitAbilitys.add(a.copy()));
        units.put(unit,unitAbilitys);
    }

    @Override
    public void update(Unit unit, float time){
        if (!units.containsKey(unit))
            start(unit);
        else
            units.get(unit).forEach(ability -> {
                ability.update(unit);
            });
        super.update(unit,time);
    }
}
