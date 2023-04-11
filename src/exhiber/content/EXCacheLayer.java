package exhiber.content;

import mindustry.graphics.CacheLayer;

public class EXCacheLayer {
    public static CacheLayer
            nitroPool;

    public static void init()
    {
        CacheLayer.add(
            nitroPool = new CacheLayer.ShaderLayer(EXShaders.nitroPool)
        );
    }
}
