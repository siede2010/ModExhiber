package exhiber.world;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import exhiber.content.EXIcons;
import exhiber.world.block.crafter.MultiLiquifier;
import mindustry.entities.Units;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.defense.BaseShield;

import java.util.Arrays;
import java.util.List;

import static mindustry.Vars.*;

public class SquareBaseShiled extends BaseShield {
    public Seq<Icon> sizes = new Seq<>();
    public SquareBaseShiled(String name)
    {
        super(name);
        sync = true;
        configurable = true;
        clearOnDoubleTap = true;
        saveConfig = true;
        copyConfig = true;
        configClear((SquareBaseShiled.SquareBaseShiledBuild tile) -> tile.iconSize = null);
        config(Icon.class, (SquareBaseShiled.SquareBaseShiledBuild entity, Icon i) -> {
            entity.iconSize = i;
        });
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        drawPotentialLinks(x, y);
        drawOverlay(x * tilesize + offset, y * tilesize + offset, rotation);
        Drawf.dashSquare( player.team().color,x * tilesize + offset, y * tilesize + offset, radius*2);
    }

    public class SquareBaseShiledBuild extends BaseShieldBuild
    {
        public Icon iconSize;
        @Override
        public void drawSelect(){
            this.block.drawOverlay(this.x, this.y, this.rotation);

            Drawf.dashSquare( team().color,x, y, radius*(iconSize == null?1:iconSize.value)*2);
        }
        @Override
        public void updateTile(){
            smoothRadius = Mathf.lerpDelta(smoothRadius, radius*2 * efficiency, 0.05f);

            Float rad = radius();

            if(rad > 1){
                paramBuild = this;
                //paramEffect = absorbEffect;
                Groups.bullet.forEach(bullet->{
                    if (team() != bullet.team())
                        if (Math.abs(x-bullet.x) < radius*(iconSize == null?1:iconSize.value) && Math.abs(y-bullet.y) < radius*(iconSize == null?1:iconSize.value) )
                            bullet.absorb();
                });
                Groups.unit.forEach(unit->{
                    if (team() != unit.team())
                        if (Math.abs(x-unit.x) < radius*(iconSize == null?1:iconSize.value)+10 && Math.abs(y-unit.y) < radius*(iconSize == null?1:iconSize.value)+10)
                        {
                            if (x()-unit.x() < y()-unit.y())
                                if (-(x()-unit.x()) < y()-unit.y())
                                    unit.y = y-(radius*(iconSize == null?1:iconSize.value)+10); //down
                                else
                                    unit.x = x+(radius*(iconSize == null?1:iconSize.value)+10); //right
                            else
                            if (-(x()-unit.x()) < y()-unit.y())
                                unit.x = x-(radius*(iconSize == null?1:iconSize.value)+10); //left
                            else
                                unit.y = y+(radius*(iconSize == null?1:iconSize.value)+10); //up

                        }
                });
            }
        }
        @Override
        public void buildConfiguration(Table table){
            ItemSelection.buildTable(SquareBaseShiled.this, table, sizes,() -> iconSize,this::configure);
        }

        @Override
        public Object config()
        {
            return iconSize;
        }

        public void drawShield(){
            if(!broken){
                float visualRadius = (radius()-offset)*Mathf.sin(45)*0.82f*(iconSize == null?1:iconSize.value);

                Draw.z(Layer.shields);

                Draw.color(team.color, Color.white, Mathf.clamp(hit));

                if(renderer.animateShields){
                    Fill.poly(x, y, 4, visualRadius,45);
                }else{
                    Lines.stroke(1.5f);
                    Draw.alpha(0.09f + Mathf.clamp(0.08f * hit));
                    Fill.poly(x, y, 4, visualRadius,45);
                    Draw.alpha(1f);
                    Lines.poly(x, y, 4, visualRadius,45);
                    Draw.reset();
                }
            }

            Draw.reset();
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.str(iconSize == null?EXIcons.one.name:iconSize.name);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            String se = read.str();
            iconSize = sizes.find((Icon c) -> c.name.equals(se));
        }
    }
}
