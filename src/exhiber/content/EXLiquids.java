package exhiber.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class EXLiquids {
    public static Liquid
            liquidTenorite,liquidZinc,liquidBrass,liquidVanadium,ammonia
            ;

        public static void EXload(){
            liquidTenorite = new Liquid("liquid-tenorite"){{
                localizedName = "Liquid Tenorite";
                color = Color.valueOf("b28768");
            }};
            liquidZinc = new Liquid("liquid-zinc"){{
                localizedName = "Liquid Zinc";
                color = Color.valueOf("719488");
            }};
            liquidBrass = new Liquid("liquid-brass"){{
                localizedName = "Liquid Brass";
                color = Color.valueOf("d1bd64");
            }};
            liquidVanadium = new Liquid("liquid-vanadium"){{
                localizedName = "Liquid Vanadium";
                color = Color.valueOf("d4553b");
            }};
            ammonia = new Liquid("ammonia"){{
                localizedName = "Ammonia";
                heatCapacity = 1;
                color = Color.valueOf("e6c5ac");
            }};
        };
}
