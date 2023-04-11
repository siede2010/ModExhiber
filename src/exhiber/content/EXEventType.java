package exhiber.content;

import arc.Events;
import arc.struct.Seq;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;

import java.util.List;
import java.util.Stack;

public class EXEventType {

    public static void load()
    {
        Events.on(AddAbilityEvent.class, e -> {
            e.unit.abilities();
            List<Ability> abilitys = new Stack<>();
            for(Ability a : e.unit.abilities)
                abilitys.add(a);
            e.abilitys.forEach(a -> {
                abilitys.add(a.copy());
            });
            e.unit.abilities((Ability[]) abilitys.toArray());
        });
    }
    public static class AddAbilityEvent{
        public Unit unit;
        public Seq<Ability> abilitys;

        public AddAbilityEvent(Unit unit, Seq<Ability> abilitys){
            this.unit = unit;
            this.abilitys = abilitys;
        }
    }
}
