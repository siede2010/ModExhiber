package exhiber.world.block.unit;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import exhiber.world.block.crafter.MultiLiquifier;
import exhiber.world.statusEffect.ComponentEffect;
import mindustry.gen.Building;
import mindustry.type.ItemStack;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.payloads.Payload;

public class ComponentUnitFactory extends RepairFactory{
    public int maxSize = 3;
    public Seq<StatusEffect> statusEffects = new Seq<StatusEffect>();
    public Seq<ItemStack[]> items = new Seq<ItemStack[]>();
    public ComponentUnitFactory(String name)
    {
        super(name);
        configurable = true;
        config(StatusEffect.class,(ComponentUnitFactoryBuild build,StatusEffect i) -> {
            build.selectedComponent = i;
        });
        configClear((ComponentUnitFactoryBuild build) -> {
            build.selectedComponent = null;
        });
    }

    public void addComponent(StatusEffect statusEffect, ItemStack[] items)
    {
        statusEffects.add(statusEffect);
        this.items.add(items);
    }

    public class ComponentUnitFactoryBuild extends RepairFactoryBuild {
        public StatusEffect selectedComponent = null;

        @Override
        public boolean acceptPayload(Building source, Payload payload){
            if (payload.content() instanceof UnitType u) {
                if (u.hitSize / 8 <= maxSize)
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
            if (selectedComponent != null)
                super.updateTile();
            if(!entry) {
                payload.unit.apply(selectedComponent);
            }
        }
        @Override
        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(ComponentUnitFactory.this, table, statusEffects,() -> selectedComponent,this::configure);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.i(statusEffects.indexOf(selectedComponent));
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            selectedComponent = statusEffects.get(read.i());
        }
    }
}
