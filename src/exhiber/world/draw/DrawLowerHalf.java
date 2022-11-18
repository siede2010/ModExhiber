package exhiber.world.draw;

import mindustry.gen.Building;
import mindustry.type.Liquid;
import mindustry.world.blocks.liquid.LiquidBlock;
import mindustry.world.draw.DrawLiquidTile;

public class DrawLowerHalf extends DrawLiquidTile {
    String region;
    public DrawLowerHalf(String region, Liquid drawLiquid, int padding)
    {
        super(drawLiquid,padding);
        this.region = region;
    }
    public DrawLowerHalf(String region, Liquid drawLiquid)
    {
        super(drawLiquid);
        this.region = region;
    }

    @Override
    public void draw(Building build){
        Liquid drawn = drawLiquid != null ? drawLiquid : build.liquids.current();
        LiquidBlock.drawTiledFrames(build.block.size, build.x, build.y/2, padding, drawn, build.liquids.get(drawn) / build.block.liquidCapacity * alpha);
    }
}
