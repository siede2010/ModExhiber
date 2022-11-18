package exhiber.world.draw;

import arc.graphics.g2d.Draw;
import mindustry.gen.Building;
import mindustry.type.Liquid;
import mindustry.world.blocks.liquid.LiquidBlock;
import mindustry.world.draw.DrawLiquidTile;

public class DrawLastLiquidTile extends DrawLiquidTile {
    String region;
    public DrawLastLiquidTile(String region,Liquid drawLiquid,int padding)
    {
        super(drawLiquid,padding);
        this.region = region;
    }
    public DrawLastLiquidTile(String region,Liquid drawLiquid)
    {
        super(drawLiquid);
        this.region = region;
    }

    @Override
    public void draw(Building build){
        Liquid drawn = drawLiquid != null ? drawLiquid : build.liquids.current();
        LiquidBlock.drawTiledFrames(build.block.size, build.x, build.y, padding, drawn, build.liquids.get(drawn) / build.block.liquidCapacity * alpha);
    }
}
