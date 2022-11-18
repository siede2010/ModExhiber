package exhiber.logic;

import arc.struct.Seq;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.logic.LCategory;

public class EXLCategory {
    public static final Seq<LCategory> EXall = new Seq<>();
    public static LCategory
    unit = new LCategory("unit", Pal.logicUnits, Icon.unitsSmall),
    world = new LCategory("world", Pal.logicWorld, Icon.terrainSmall);

    
}
