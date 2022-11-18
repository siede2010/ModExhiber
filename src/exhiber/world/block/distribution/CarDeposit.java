package exhiber.world.block.distribution;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.graphics.Drawf;
import mindustry.type.Item;

import static mindustry.Vars.tilesize;

public class CarDeposit extends CarPoint{
    public CarDeposit(String name)
    {
        super(name);
        rotate = true;
        quickRotate = true;
        hasItems = true;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x,y,rotation,valid);
        Draw.rect(Core.atlas.find(name+"-top"),x*8,y*8,rotation*90+90);
    }

    public class CarDepositBuild extends  CarPointBuild{

        @Override
        public void updateTile(){
            if(unit != null && (unit.dead || !unit.isAdded())){
                unit = null;
            }
            if (targetPos != null && unit != null && link != -1) {
                boolean movable = unit.isCommandable() && targetPos != null && linkValid(tile,targetPos.tile());
                if (Mathf.sqrt(Mathf.pow(unit.getX() - tile.getX(),2) + Mathf.pow(unit.getY() - tile.getY(),2)) < 2) {
                    if (!unit.hasItem()) {
                        if (movable)
                            move = true;

                    }
                    else {
                        if (items.get(unit.item()) < itemCapacity) {
                            this.items.add(unit.item(),unit.stack().amount);
                            unit.clearItem();
                        }
                        else if (movable)
                            move = true;

                    }
                }
                if (move && movable)
                    unit.command().commandPosition(new Vec2(targetPos.x, targetPos.y));
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
            dump();
        }

        @Override
        public void draw(){
            super.draw();
            Draw.rect(Core.atlas.find(name+"-top"),x,y,rotation*90+90);
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return false;
        }
    }
}
