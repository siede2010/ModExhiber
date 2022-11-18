package exhiber.world.block.logic;

import arc.func.Cons;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.logic.LAssembler;
import mindustry.logic.LExecutor;
import mindustry.world.blocks.logic.LogicBlock;

public class ExhiberLogicBlock extends LogicBlock {
    public Effect updateEffect;
    public int updateEffectInterval;
    public ExhiberLogicBlock(String name)
    {

        super(name);

    }

    public class ExhiberLogicBuild extends LogicBuild
    {
        public int uei;
        @Override
        public void updateTile(){
            super.updateTile();
            if (efficiency > 0f)
                if (--uei <= 0) {
                    if (updateEffect != null) {
                        updateEffect.at(this.tile);
                        uei = updateEffectInterval;
                    }
                }
        }
    }
}
