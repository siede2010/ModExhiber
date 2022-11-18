package exhiber.world.block;

import arc.Events;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.blocks.units.Reconstructor;

public class RepairFactory extends Reconstructor {
    public boolean entry = true;
    public RepairFactory(String name)
    {
        super(name);
    }


    public void addUpgrade(UnitType unit){
        upgrades.add(new UnitType[]{unit, unit});
    }

    public class RepairFactoryBuild extends ReconstructorBuild {
        @Override
        public void updateTile() {
            boolean valid = false;

            if(payload != null){
                //check if offloading
                if(!hasUpgrade(payload.unit.type) || !entry){
                    moveOutPayload();
                }else{ //update progress
                    if(entry)
                        if(moveInPayload()){
                            if(efficiency > 0){
                                valid = true;
                                progress += edelta();
                        }

                        //upgrade the unit
                        if(progress >= constructTime && entry){
                            entry = false;
                            progress = 0f;
                            payload.unit = upgrade(payload.unit.type).create(payload.unit.team());
                            if(commandPos != null && payload.unit.isCommandable()){
                                payload.unit.command().commandPosition(commandPos);
                            }
                            progress %= 1f;
                            Effect.shake(2f, 3f, this);
                            Fx.producesmoke.at(this);
                            consume();
                            Events.fire(new EventType.UnitCreateEvent(payload.unit, this));
                        }
                    }
                }
            } else {
                progress = 0;
                entry = true;
            }

            speedScl = Mathf.lerpDelta(speedScl, Mathf.num(valid), 0.05f);
            time += edelta() * speedScl;
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload){
            return super.acceptPayload(source,payload) && entry;
        }

    }
}
