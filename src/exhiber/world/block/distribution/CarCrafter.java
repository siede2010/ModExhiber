package exhiber.world.block.distribution;

import arc.math.Mathf;
import arc.struct.EnumSet;
import exhiber.type.ForcesStack;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.type.Item;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;
import mindustry.world.meta.*;

public class CarCrafter extends GenericCrafter {
    public ForcesStack[] outputForces;

    public CarCrafter(String name)
    {
        super(name);
        update = true;
        solid = true;
        hasItems = true;
        ambientSound = Sounds.machine;
        sync = true;
        ambientSoundVolume = 0.03f;
        flags = EnumSet.of(BlockFlag.factory);
        drawArrow = false;
    }
    @Override
    public void setStats(){
        stats.timePeriod = craftTime;
        super.setStats();
        stats.add(Stat.productionTime, craftTime / 60f, StatUnit.seconds);
    }

    @Override
    public void load(){
        super.load();

        drawer.load(this);
    }

    @Override
    public void init(){
        if(outputForces == null) outputForces = new ForcesStack[0];
    }

    public class CarCrafterBuild extends GenericCrafterBuild
    {

    }
}
