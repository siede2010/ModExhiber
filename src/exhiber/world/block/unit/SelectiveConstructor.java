package exhiber.world.block.unit;

import mindustry.ctype.UnlockableContent;
import mindustry.world.Block;
import mindustry.world.blocks.payloads.Constructor;

public class SelectiveConstructor extends Constructor {
    public UnlockableContent[] bblocks;
    public SelectiveConstructor(String name,Block[] blocks){
        super(name);
        bblocks = blocks;
    }

    @Override
    public boolean canProduce(Block b){
        for(int i = 0;i<bblocks.length;i++){
            if (bblocks[i].equals(b))
                return true;
        }
        return false;
    }
}
