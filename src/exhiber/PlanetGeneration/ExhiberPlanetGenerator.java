package exhiber.PlanetGeneration;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import exhiber.content.EXBlocks;
import mindustry.maps.generators.BaseGenerator;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;
import mindustry.world.Block;

import java.util.Random;

public class ExhiberPlanetGenerator extends PlanetGenerator {
    public static int id = 1;
    public ExhiberPlanetGenerator()
    {
        super();

        Block[][] terrain = {
                {EXBlocks.marmatiteFloor,EXBlocks.marmatiteFloor, EXBlocks.clayFloor,EXBlocks.clayFloor, EXBlocks.clayFloor,EXBlocks.vanadiumFloor,EXBlocks.vanadiumFloor, EXBlocks.zincNitrateFloor, EXBlocks.zincNitrateFloor, EXBlocks.zincNitrateFloor},
                {EXBlocks.clayFloor,EXBlocks.clayFloor},
        };
    }

    @Override
    public float getHeight(Vec3 position) {
        return 2f;
    }

    @Override
    public Color getColor(Vec3 position) {
        return Color.valueOf(++id%10 + "" + id%10 + "" + id%10 + "" + id%10 + "" + id%10 + "" + id%10);

    }

    @Override
    public void generateSector(Sector sector){
        sector.generateEnemyBase = true;
    }
}
