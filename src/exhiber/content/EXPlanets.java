package exhiber.content;

import arc.graphics.Color;
import arc.math.Mathf;
import exhiber.PlanetGeneration.ExhiberPlanetGenerator;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.game.Rules;
import mindustry.graphics.g3d.HexMesh;
import mindustry.maps.Map;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;
import exhiber.ExhiberMain;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import exhiber.ExhiberMain;

public class EXPlanets extends Planets {
    public static Planet
        exhiber
            ;
        public static void EXload(){
            exhiber = new Planet("exhiber",sun,0.7f,2){{
                localizedName = "Exhiber";
               generator = new ExhiberPlanetGenerator();
               lightColor = Color.valueOf("ffffff");
               alwaysUnlocked = true;
               tidalLock = false;
               accessible = true;
               meshLoader = () -> new HexMesh(this, 5);
                System.out.println(sectors.size);
               atmosphereColor = Color.valueOf("ffffff");
               startSector = 15;
               totalRadius = 50f;
               clipRadius = 4;
               lightColor = Color.valueOf("aaaaaa");
                atmosphereRadIn = -0.01f;
                atmosphereRadOut = 0.5f;
                landCloudColor = Color.valueOf("ffffff");
                bloom = true;
                hiddenItems.addAll(Item.getAllOres()).removeAll(EXItems.exhiberItems);

            }};

            Planets.serpulo.hiddenItems.addAll(EXItems.exhiberItems);
            Planets.erekir.hiddenItems.addAll(EXItems.exhiberItems);
            Planets.tantros.hiddenItems.addAll(EXItems.exhiberItems);
        }
}
