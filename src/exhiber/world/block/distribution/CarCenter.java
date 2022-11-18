package exhiber.world.block.distribution;

import arc.Events;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import exhiber.content.EXUnits;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.ui.Bar;
import mindustry.world.Block;

import static mindustry.Vars.*;

public class CarCenter extends CarPoint {
    public UnitType unitType = EXUnits.smallTrajectoryCar;
    public float buildTime = 60f*25f;

    public CarCenter(String name)
    {
        super(name);
        update = true;
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("progress", (CarCenterBuild e) -> new Bar("bar.progress", Pal.ammo, e::fraction));
        addBar("Unit",(CarCenterBuild e ) -> new Bar("Unit Build",Pal.lighterOrange,e::fractionU));
    }

    public class CarCenterBuild extends CarPointBuild{
        public Unit cunit;
        public float progress;
        @Override
        public void updateTile()
        {
            if(cunit != null && (cunit.dead || !cunit.isAdded())){
                cunit = null;
            }
            if(cunit == null){
                if(unitType.isBanned()){
                    return;
                }
                if(items.has(consumeItems().items))
                    progress += edelta() * state.rules.unitBuildSpeed(team);
                    if (progress >= buildTime) {
                        if (unit == null) {
                            unit = cunit = unitType.create(team);
                            consume();
                            cunit.set(tile.x * 8, tile.y * 8);
                            cunit.add();
                        }
                    }
            }
            else{
                progress = 0;
            }
            if (unit != null && targetPos != null && link != -1)
            {
                if (unit.isCommandable())
                    unit.command().commandPosition(new Vec2(targetPos.getX(), targetPos.getY()));
                else
                    unit.health--;
                if (Mathf.sqrt(Mathf.pow(unit.getX() - targetPos.getX(),2) + Mathf.pow(unit.getY() - targetPos.getY(),2)) < 2) {
                    if (((CarPointBuild)targetPos).unit == null) {
                        ((CarPointBuild) targetPos).unit = unit;
                        unit = null;
                    }
                }
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            if (items.get(item) < itemCapacity)
                return true;
            return false;
        }
        public float fraction(){
            return progress / buildTime;
        }
        public float fractionU(){
            return cunit == null ? 0 : 1;
        }

        @Override
        public void remove()
        {
            if (cunit != null)
                cunit.kill();
            super.remove();
        }
        @Override
        public void killed()
        {
            if (cunit != null)
                cunit.kill();
            super.killed();
        }
    }
}
