package exhiber.content;

import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Reflect;
import mindustry.Vars;
import mindustry.game.Schematic;
import mindustry.game.Schematics;
import mindustry.world.blocks.storage.CoreBlock;

public class ExhiberLoadouts {
    public static Schematic basicAbate;

    public static void load() {
        basicAbate = Schematics.readBase64("bXNjaAF4nGNgYmBiZmDJS8xNZWAOSExh4E5JLU4uyiwoyczPY2BgYMtJTErNKWZgio5lZBBIrcjITEot0k3OL0rVLQCqZmBgZIAAAIUUENg=");

        //copy, pasted from Dusted lands
        Reflect.<ObjectMap<CoreBlock, Seq<Schematic>>>get(Vars.schematics, "loadouts").get((CoreBlock) EXBlocks.corePad, Seq::new).add(basicAbate);
        Reflect.<ObjectMap<CoreBlock, Schematic>>get(Vars.schematics, "defaultLoadouts").put((CoreBlock) EXBlocks.corePad, basicAbate);
    }
}
