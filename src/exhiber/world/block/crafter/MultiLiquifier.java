package exhiber.world.block.crafter;

import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.io.TypeIO;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.*;
import mindustry.world.blocks.production.GenericCrafter;

import static mindustry.Vars.content;

public class MultiLiquifier extends GenericCrafter {
    Seq<Item> inputitems;
    Seq<Liquid> outputliquids;
    public float liquidAmount;
    public MultiLiquifier(String name, Seq<Item> inputitems, Seq<Liquid> outputliquids){
        super(name);
        hasItems = true;
        hasLiquids = true;
        outputsLiquid = true;
        variants = 0;
        sync = true;
        configurable = true;
        clearOnDoubleTap = true;
        saveConfig = true;
        copyConfig = true;
        this.inputitems = inputitems;
        this.outputliquids = outputliquids;
        configClear((MultiLiquifierBuild tile) -> tile.inputItem = null);
        config(Item.class, (MultiLiquifierBuild entity, Item i) -> {
            entity.inputItem = i;
        });
    }

    @Override
    public void init() {
        super.init();
        outputLiquids = new LiquidStack[outputliquids.size];
        for (int i = 0;i<outputliquids.size;i++)
        {
            consumeItem(inputitems.get(i),1);
            outputLiquids[i] = new LiquidStack(outputliquids.get(i),liquidAmount);

        }
    }

    public class MultiLiquifierBuild extends GenericCrafterBuild {
        public @Nullable Vec2 commandPos;
        public Item inputItem;
        @Override
        public void updateTile() {
            if (inputItem != null) {
                if (items.has(inputItem)) {
                    progress += getProgressIncrease(craftTime);
                    warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);
                    if (wasVisible && Mathf.chanceDelta(updateEffectChance))
                        updateEffect.at(x + Mathf.range(size * 4f), y + Mathf.range(size * 4));
                    liquids.add(outputliquids.get(inputitems.indexOf(items.first())), liquidAmount);
                }
                totalProgress += warmup * Time.delta;
                if (progress >= 1f) {
                    craft();
                    if (items.length() != 0)
                        items.remove(items.first(),1);
                }
            }
            dumpOutputs();
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            if (item == inputItem)
                if (items.total() < itemCapacity)
                    return true;
            return false;
        }

        @Override
        public Object config()
        {
            return inputItem;
        }

        @Override
        public void buildConfiguration(Table table){
            ItemSelection.buildTable(MultiLiquifier.this, table, inputitems,() -> inputItem,this::configure);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.s(inputItem == null ? -1 : inputItem.id);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            inputItem = content.item(read.s());
        }
    }
}
