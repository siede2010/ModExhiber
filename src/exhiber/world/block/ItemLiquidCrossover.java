package exhiber.world.block;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Eachable;
import arc.util.Tmp;
import exhiber.world.block.distribution.StaticConveyor;
import mindustry.entities.TargetPriority;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.meta.BlockGroup;

import static mindustry.Vars.*;
import static mindustry.Vars.itemSize;

public class ItemLiquidCrossover extends StaticConveyor{
    public ItemLiquidCrossover(String name)
    {
        super(name);
        solid = true;
        noUpdateDisabled = true;
        canOverdrive = false;
        floating = true;
        hasLiquids = true;
        liquidCapacity = 10;
        rotate = true;
        speed = 0.05f;
        group = BlockGroup.transportation;
        hasItems = true;
        itemCapacity = 3;
        priority = TargetPriority.transport;
        conveyorPlacement = true;
        underBullets = true;
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(name,plan.drawx(),plan.drawy());
        Draw.rect(name+"-top",plan.drawx(),plan.drawy(),plan.rotation*90);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{Core.atlas.find(name),Core.atlas.find(name+"-top")};
    }

    public class ItemLiquidCrossoverBuild extends StaticConveyorBuild {

        @Override
        public void updateTile()
        {
            super.updateTile();
            if(liquids.currentAmount() > 0.01f){
                dumpLiquid(liquids.current());
            }
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            return (liquids.current() == liquid || liquids.currentAmount() < 0.2f);
        }

        @Override
        public void draw(){
            Draw.rect(name,x,y);
            Draw.z(Layer.blockOver + 0.1f);
            Draw.rect(name+"-top",x,y,rotation*90);
        }
    }
}
