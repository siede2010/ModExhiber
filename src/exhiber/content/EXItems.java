package exhiber.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.type.Item;

public class EXItems{
    public static Item
        zinc,chalk,rawClay,brass,diamond,quartz, tenorite,vanadium,fiberGlass,hyperfluxMatter,radium,
        dryIce
            ;
    public static final Seq<Item> exhiberItems = new Seq<>();

        public static void EXload(){
            tenorite = new Item("tenorite"){{
               localizedName = "Tenorite";
               hardness = 3;
                color = Color.valueOf("c9a58f");
                charge = 0.2f;
            }};
            vanadium = new Item("vanadium"){{
                localizedName = "Vanadium";
                hardness = 4;
                color = Color.valueOf("ef525b");
                radioactivity = 0.5f;
                charge = 0.3f;
            }};
            zinc = new Item("zinc"){{
                localizedName = "Zinc";
                hardness = 2;
                color = Color.valueOf("6d6f7c");
                charge = 0.1f;
            }};
            chalk = new Item("chalk"){{
                localizedName = "Chalk";
                flammability = 0.3f;
                explosiveness = 0.05f;
                hardness = 1;
                color = Color.valueOf("dfdfdf");
            }};
            rawClay = new Item("raw-clay"){{
               localizedName = "Raw Clay";
               hardness = 4;
                color = Color.valueOf("b8705c");
            }};
            brass = new Item("brass"){{
                localizedName = "Brass";
                charge = 0.3f;
                hardness = 5;
                color = Color.valueOf("d1bd64");
            }};
            diamond = new Item("diamond"){{
                localizedName = "Diamond";
                explosiveness = 0.1f;
                hardness = 3;
                color = Color.valueOf("8fcaf2");
            }};
            quartz = new Item("quartz"){{
                localizedName = "Quartz";
                explosiveness = 0.05f;
                hardness = 5;
                color = Color.valueOf("cdcdcd");
            }};
            fiberGlass = new Item("fiber-glass"){{
                localizedName = "Fiber Glass";
                hardness = 5;
                flammability = 0.2f;
                charge = 0.3f;
                color = Color.valueOf("ebe49b");
            }};
            hyperfluxMatter = new Item("hyperflux-matter"){{
                localizedName = "Hyperflux Matter";
                hardness = 6;
                charge = 1f;
                flammability = 0.4f;
                radioactivity = 0.3f;
                explosiveness = 0.4f;
                color = Color.valueOf("e07b67");
            }};
            radium = new Item("radium"){{
                localizedName = "Radium";
                hardness = 7;
                radioactivity = 0.8f;
                explosiveness = 0.1f;
                color = Color.valueOf("569c3d");
            }};
            dryIce = new Item("dry-ice"){{
                localizedName = "Dry Ice";
                hardness = 8;
                flammability = -1f;
                color = Color.valueOf("ede8ed");
            }};

            exhiberItems.addAll(
                    zinc,chalk,rawClay,brass,diamond,quartz, tenorite,vanadium,fiberGlass,hyperfluxMatter,radium,dryIce
            );
        }
}
