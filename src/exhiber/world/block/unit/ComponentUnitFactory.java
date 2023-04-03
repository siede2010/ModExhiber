package exhiber.world.block.unit;

import arc.Events;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.io.Reads;
import arc.util.io.Writes;
import exhiber.world.block.crafter.MultiLiquifier;
import exhiber.world.statusEffect.ComponentEffect;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.consumers.ConsumeItemDynamic;

import java.util.concurrent.atomic.AtomicInteger;

public class ComponentUnitFactory extends RepairFactory{
    public int maxSize = 3;
    public Seq<StatusEffect> statusEffects = new Seq<StatusEffect>();
    public Seq<ItemStack[]> items = new Seq<ItemStack[]>();
    public ComponentUnitFactory(String name)
    {
        super(name);
        configurable = true;
        config(Integer.class,(ComponentUnitFactoryBuild build,Integer i) -> {
            build.selectedComponent = i;
        });
        config(StatusEffect.class,(ComponentUnitFactoryBuild build,StatusEffect s) -> {
            build.selectedComponent = statusEffects.indexOf(s);
        });
        configClear((ComponentUnitFactoryBuild build) -> {
            build.selectedComponent = -1;
        });
        itemCapacity = 10;
        consume(new ConsumeItemDynamic(
                // items seq is already shrunk, it's safe to access
                (ComponentUnitFactoryBuild b) -> {
                    if (b.selectedComponent == -1) return ItemStack.empty;
                    return items.get(b.selectedComponent);
                })
        );
    }

    public void addComponent(StatusEffect statusEffect, ItemStack[] items)
    {
        statusEffects.add(statusEffect);
        this.items.add(items);
    }

    public class ComponentUnitFactoryBuild extends RepairFactoryBuild {
        public Integer selectedComponent = -1;

        @Override
        public int getMaximumAccepted(Item item){
            ItemStack[] itemstacks = selectedComponent > -1 ? ComponentUnitFactory.this.items.get(selectedComponent) : null;
            for(ItemStack is : itemstacks)
                if (is.item == item)
                    return is.amount*2;
            return 0;
        }
        @Override
        public boolean acceptItem(Building source, Item item) {
            return items.get(item) < getMaximumAccepted(item);
        }
        public boolean eff()
        {
            ItemStack[] itemstacks = selectedComponent > -1 ? ComponentUnitFactory.this.items.get(selectedComponent) : null;
            for(ItemStack is : itemstacks)
                if(is.amount > items.get(is.item))
                    return false;
            return true;
        }
        @Override
        public boolean acceptPayload(Building source, Payload payload){
            if (payload.content() instanceof UnitType u && this.payload == null && u.hitSize / 8f <= maxSize) {
               entry = true;
               return true;
            }
            return false;
        }
        @Override
        public Object config()
        {
            return selectedComponent;
        }
        @Override
        public void updateTile() {
            if(payload != null){
                //check if offloading
                if(!entry){
                    moveOutPayload();
                }else{ //update progress
                    if(moveInPayload()) {
                        if(eff() && selectedComponent > -1)
                            progress += delta();
                    }
                            //upgrade the unit
                    if(progress >= constructTime && entry){
                        entry = false;
                        progress = 0f;
                        payload.unit.apply(statusEffects.get(selectedComponent),60f);
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

            speedScl = Mathf.lerpDelta(speedScl, 1, 0.05f);
            time += edelta() * speedScl;
            /*
            if(payload != null && selectedComponent > -1) {
                payload.unit.apply(statusEffects.get(selectedComponent));
            }
             */
        }
        @Override
        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(ComponentUnitFactory.this, table, statusEffects,() -> selectedComponent > -1 ? statusEffects.get(selectedComponent) : null,icon -> configure(statusEffects.indexOf(icon)),selectionRows,selectionColumns);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.i(selectedComponent);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            selectedComponent = read.i();
        }
    }
}
