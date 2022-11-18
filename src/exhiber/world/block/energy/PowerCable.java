package exhiber.world.block.energy;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureAtlas;
import arc.graphics.g2d.TextureRegion;
import arc.util.Eachable;
import mindustry.Vars;
import mindustry.entities.units.BuildPlan;
import mindustry.graphics.Layer;
import mindustry.world.Block;
import mindustry.world.blocks.*;
import mindustry.world.Tile;
import mindustry.world.blocks.Autotiler;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.consumers.ConsumePower;
import mindustry.world.meta.Stat;

import java.io.Console;

public class PowerCable extends Conveyor { //TODO


    public PowerCable(String name){
        super(name);
        rotate = true;
        quickRotate = true;
        update = true;
        itemCapacity = 0;
        junctionReplacement = this;
        conductivePower = true;
        consumePowerBuffered(1);
    }
}
