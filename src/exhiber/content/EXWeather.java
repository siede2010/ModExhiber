package exhiber.content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.type.Weather;
import mindustry.type.weather.*;

public class EXWeather {
    public static Weather
        diamondRain
            ;
        public static void EXload(){
            diamondRain = new RainWeather("diamond-rain"){{
                localizedName = "Diamond Rain";
                status = EXStatusEffects.scabbed;
                stroke = 1.5f;
                liquid = Liquids.cryofluid;
                color = Color.valueOf("77B5FEFF");
                yspeed = 10;
                xspeed = 3;
                padding = 6;
                sizeMin = 10;
                sizeMax = 15;
            }};
        }
}
