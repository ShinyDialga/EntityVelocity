package dialga.shiny.entityvelocity.util;

import org.bukkit.entity.EntityType;

public class EntityUtil {

    public static EntityType getEntityType(String entityType) {

        for (EntityType type : EntityType.values()) {
            if (type.getEntityClass().getSimpleName().equalsIgnoreCase(entityType
                    .replace("_", ""))) {
                return type;
            }
        }
        return null;
    }
}
