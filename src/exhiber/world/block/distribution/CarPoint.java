package exhiber.world.block.distribution;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.ai.UnitCommand;
import mindustry.core.Renderer;
import mindustry.entities.TargetPriority;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ItemBridge;

import java.util.List;
import java.util.Stack;


import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class CarPoint extends Block {
    public float range = 10;
    public short railId = 0;
    public float railSize = 1;
    public CarPoint(String name)
    {
        super(name);

        solid = true;
        update = true;
        hasItems = true;
        itemCapacity = 500;
        configurable = true;
        allowConfigInventory = false;
        priority = TargetPriority.transport;
        sync = true;
        drawDisabled = false;

        config(Point2.class, (CarPoint.CarPointBuild tile, Point2 i) -> tile.link = Point2.pack(i.x + tile.tileX(), i.y + tile.tileY()));
        config(Integer.class, (CarPoint.CarPointBuild tile, Integer i) -> tile.link = i);
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x,y,rotation,valid);
        Drawf.circles(x*8, y*8, range * tilesize);
    }
    public class CarPointBuild extends Building
    {
        float r = 0;
        public List<Block> Connections;
        public boolean move = false;
        public int link = -1;
        public Unit unit;
        public Building targetPos;

        @Override
        public void created()
        {
            Connections = new Stack<>();
        }

        @Override
        public void updateTile(){
            if(unit != null && (unit.dead || !unit.isAdded())){
                unit = null;
            }
            if (targetPos != null && unit != null && link != -1) {

                if (Mathf.sqrt(Mathf.pow(unit.getX() - tile.getX(),2) + Mathf.pow(unit.getY() - tile.getY(),2)) < 2) {
                    if (!unit.hasItem()) {
                        if (this.items != null &&this.items.total() != 0) {
                            unit.addItem(this.items.first(), 1);
                            this.removeStack(this.items.first(), 1);
                        }
                        else
                            move = true;
                    }
                    else {
                        if (this.items.get(unit.item()) != 0 && unit.acceptsItem(unit.item())) {
                            unit.addItem(unit.item(), 1);
                            this.removeStack(unit.item(), 1);
                        }
                        else
                            move = true;
                    }
                }
                if (move)
                    if (unit.isCommandable())
                        if (((CarPointBuild)targetPos).unit == null)
                            unit.command().commandPosition(new Vec2(targetPos.x, targetPos.y));
                        else
                            unit.command().commandPosition(new Vec2(this.x,this.y));
                    else
                        unit.health--;
                else
                    if (unit.isCommandable())
                        unit.command().commandPosition(new Vec2(this.x,this.y));
                    else
                        unit.health--;
                if (Mathf.sqrt(Mathf.pow(unit.getX() - targetPos.getX(),2) + Mathf.pow(unit.getY() - targetPos.getY(),2)) < 2) {
                    if (((CarPointBuild)targetPos).unit == null) {
                        ((CarPointBuild) targetPos).unit = unit;
                        unit = null;
                        move = false;
                    }
                }
            }
            else if (link != -1 && unit != null)
                unit.command().commandPosition(new Vec2(tile.x, tile.y));
        }

        public boolean linkValid(Tile tile, Tile other) {
            if(other == null || other.build == null) {configure(-1); return false;}
            if(tile == other || tile == null ) return false;

            if(tile.build.team != other.build.team) return false;
            float distance = Mathf.sqrt(Mathf.pow((other.x - tile.x),2f) + Mathf.pow((other.y - tile.y),2));
            if((other.block() instanceof CarPoint c && ((CarPoint)other.block()).railId == ((CarPoint)tile.block()).railId && distance < range)){
                return true;
            }
            return false;
        }

        @Override
        public void drawConfigure(){
            Drawf.circles(x, y, tile.block().size * tilesize / 2f + 1f + Mathf.absin(Time.time, 4f, 1f));
            Drawf.circles(x, y, range * tilesize);
        }

        @Override
        public boolean onConfigureBuildTapped(Building other){
            if(other instanceof CarPointBuild b && b.link == pos()){
                configure(other.pos());
                targetPos = other;
                other.configure(-1);
                return true;
            }

            if(linkValid(tile, other.tile)){
                if(link == other.pos()){
                    configure(-1);
                }else{
                    targetPos = other;
                    configure(other.pos());
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            if(hasItems && items.total() < itemCapacity)
                return true;
            return false;
        }

        @Override
        public void draw(){
            r += delta();
            r %= 360;

            Draw.rect(Core.atlas.find(name),x,y,0);

            Draw.z(Layer.power);

            Tile other = (world.tile(link));
            if(!linkValid(tile, other) && !(link == -1)) return;

            if(Mathf.zero(Renderer.bridgeOpacity)) return;

            if (linkValid(tile, other) && other != null && other.block() != null) {
                int i = relativeTo(other.x, other.y);
                Draw.color(Color.white, Color.black, Mathf.absin(Time.time, 6f, 0.07f));

                Draw.alpha((1f) * Renderer.bridgeOpacity);

                Draw.rect(Core.atlas.find(name+"-spin"), x, y, r);
                Draw.rect(Core.atlas.find(name+"-spin"), other.drawx(), other.drawy(), r);

                Lines.stroke(8f*railSize);

                Tmp.v1.set(x, y).sub(other.worldx(), other.worldy()).setLength(tilesize / 2f).scl(-1f);

                Lines.line(Core.atlas.find(name+"-rail"),
                        x + Tmp.v1.x,
                        y + Tmp.v1.y,
                        other.worldx() - Tmp.v1.x,
                        other.worldy() - Tmp.v1.y, false);
                float mx = (x+other.worldx())/2 - Tmp.v1.x*railSize;
                float my = (y+other.worldy())/2 - Tmp.v1.y*railSize;
                Lines.line(Core.atlas.find(name+"-arrow"),
                        mx,
                        my,
                        mx + Tmp.v1.x*2*railSize,
                        my + Tmp.v1.y*2*railSize,
                        false);


                int dist = Math.max(Math.abs(other.x - tile.x), Math.abs(other.y - tile.y)) - 1;
            }

            Draw.color();

            Draw.reset();
        }

        @Override
        public void drawSelect(){
            super.drawSelect();
            Drawf.circles(x, y, range * tilesize);
        }

        @Override
        public Point2 config(){
            return Point2.unpack(link).sub(tile.x, tile.y);
        }

        @Override
        public void remove()
        {
            if (unit != null)
                unit.kill();
            super.remove();
        }
        @Override
        public void killed()
        {
            if (unit != null)
                unit.kill();
            super.killed();
        }
    }
}
