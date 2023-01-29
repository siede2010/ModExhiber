package exhiber.world;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;
import mindustry.world.Tile;

public class Icon extends UnlockableContent {
    public float value = 0;
    public Icon(String name)
    {
        super(name);
    }

    @Override
    public void load()
    {
        super.load();
        fullIcon = Core.atlas.find("exhiber-"+name);
        uiIcon = Core.atlas.find("exhiber-"+name);
        if (fullIcon == null)
        {
            fullIcon = Core.atlas.find("error");
            uiIcon = Core.atlas.find("error");
        }
    }

    @Override
    public ContentType getContentType() {
        return ContentType.typeid_UNUSED;
    }
}
